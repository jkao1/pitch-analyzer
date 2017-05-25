import java.util.*;
import java.io.*;

public class User implements Comparable<User> {

    private static int SIZE = 100; // how much to approximate into

    private int id, tone;
    private ArrayList<Double> times, pitches;
    private ArrayList<Double> zhuTimes, zhuPitches;
    private ArrayList<Double> approxTime, approxPitch;
    private ArrayList<Double> approxZhuTime, approxZhuPitch;
    private Regression reg;
    private MeanError me;

    public User(Scanner in, Scanner zhu, boolean debug)
    {
        id = in.nextInt();
        tone = in.nextInt();

        approxTime = new ArrayList<Double>();
        approxPitch = new ArrayList<Double>();
        approxZhuTime = new ArrayList<Double>();
        approxZhuPitch = new ArrayList<Double>();

        times = new ArrayList<>();
        pitches = new ArrayList<>();
        move( times, pitches, in );
        normalizeGood( times );
        normalize( pitches );
        approximate( times, approxTime );
        approximate( pitches, approxPitch );

        zhuTimes = new ArrayList<>();
        zhuPitches = new ArrayList<>();
        move( zhuTimes, zhuPitches, zhu );
        normalizeGood( zhuTimes );
        normalize( zhuPitches );
        approximate( zhuTimes, approxZhuTime );
        approximate( zhuPitches, approxZhuPitch );

        if (debug) {
            /*
            for (int i = 0; i < SIZE; i++) {
                System.out.println(approxPitch.get(i) + "," + approxZhuPitch.get(i));
            }
            System.out.println("48-3Times,48-3Pitches");
            for (int i = 0; i < times.size(); i++) {
                System.out.println(times.get(i) + "," + pitches.get(i));
                }*/
            System.out.println("48-3ApproxTimes,48-3ApproxPitches");
            for (int i = 0; i < approxTime.size(); i++) {
                System.out.println(approxTime.get(i) + "," + approxPitch.get(i));
            }
            System.out.println("zhuAATimes,zhuAAPitches");
            for (int i = 0; i < approxZhuTime.size(); i++) {
                System.out.println(approxZhuTime.get(i) + "," + approxZhuPitch.get(i));
            }/*
            System.out.println("approxPitch,approxZhuPitch");
            for (int i = 0; i < approxTime.size(); i++) {
                System.out.println(approxPitch.get(i) + "," + approxZhuPitch.get(i));
                }*/
        }

        reg = new Regression( approxPitch, approxZhuPitch );
        me = new MeanError( approxPitch, approxZhuPitch );
    }

    public double getRMSD() {
        return me.getRMSD();
    }

    public double getMAE() {
        return me.getMAE();
    }

    private static void approximate( ArrayList<Double> x, ArrayList<Double> y )
    {
        for (int i = 0; i < SIZE; i++) {
            int j = (int) ( ( (double) x.size() ) / SIZE * i);
            y.add( x.get( j ));
        }
    }

    private static void concatenate(ArrayList<Double> x, ArrayList<Double> y) {
        for (Double d : y) {
            x.add(d);
        }
    }

    private static void move(ArrayList<Double> times, ArrayList<Double> pitches, Scanner in )
    {
        boolean isPitch = false;
        while (in.hasNext()) {
            String v = in.next();
            if (isDouble(v)) {
                if (isPitch) {
                    pitches.add(Double.parseDouble(v));
                } else {
                    times.add(Double.parseDouble(v));
                }
                isPitch = !isPitch;
            }
        }
    }

    private static boolean isDouble(String value) {
        try {
            Integer.parseInt(value);
            return false;
        } catch (NumberFormatException e) {}

        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static void normalizeGood(ArrayList<Double> x)
    {
        double min = Collections.min(x);
        double max = Collections.max(x);

        for (int i = 0; i < x.size(); i++) {
            x.set(i, (x.get(i) - min) / (max - min) );
        }
    }

    private static void normalize(ArrayList<Double> x)
    {
        Double mean = 0.0;
        for (Double d : x) {
            mean += d;
        }
        mean /= x.size();
        for (int i = 0; i < x.size(); i++) {
            x.set(i, x.get(i) - mean);
        }
    }

    public double getRSquared() {
        return reg.getRSquared();
    }

    public String toString() {
        return id + "-" + tone + "; " + reg;
    }

    public int compareTo(User o)
    {
        return (10 * id + tone) - (10 * o.id + o.tone);
    }
}
