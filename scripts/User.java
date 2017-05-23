import java.util.*;
import java.io.*;

public class User implements Comparable<User> {

    private final static int NORMAL_CONSTANT = 1000; // used to normailze data and remove negative values

    private int id, tone;
    private ArrayList<Double> times, pitches;
    private Regression reg;

    public User(Scanner in, Scanner zhu, boolean debug)
    {
        id = in.nextInt();
        tone = in.nextInt();

        times = new ArrayList<>();
        pitches = new ArrayList<>();

        move( times, pitches, in );
        move( times, pitches, zhu );

        normalize( times );
        normalize( pitches );
        if (debug) {
            for (int i = 0; i < times.size(); i++) {
                System.out.print( times.get(i) + " " );
                System.out.print( pitches.get(i) );
                System.out.println();
            }
        }

        reg = new Regression(times, pitches);
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
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
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
