import java.util.*;
import java.io.*;

public class User {

    private final int NORMAL_CONSTANT = 1000; // used to normailze data and remove negative values

    private int id, tone;
    private ArrayList<Double> times, pitches;
    private Regression reg;

    public User(Scanner in, Scanner zhu)
    {
        id = in.nextInt();
        tone = in.nextInt();

        times = new ArrayList<>();
        pitches = new ArrayList<>();

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
        isPitch = false;
        while (zhu.hasNext()) {
            String v = zhu.next();
            if (isDouble(v)) {
                if (isPitch) {
                    pitches.add(Double.parseDouble(v));
                } else {
                    times.add(Double.parseDouble(v));
                }
                isPitch = !isPitch;
            }
        }
        normalize(times);
        normalize(pitches);

        reg = new Regression(times, pitches, 2); // 0-linear, 1-exponential, 2-power
    }

    private boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void normalize(ArrayList<Double> x)
    {
        Double mean = 0.0;
        for (Double d : x) {
            mean += d;
        }
        mean /= x.size();
        for (int i = 0; i < x.size(); i++) {
            x.set(i, x.get(i) - mean + NORMAL_CONSTANT);
        }
    }

    public double getRSquared() {
        return reg.getRSquared();
    }

    public String toString() {
        return id + "-" + tone + "; " + reg;
    }
}
