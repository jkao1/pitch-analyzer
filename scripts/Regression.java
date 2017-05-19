import java.util.*;

public class Regression {

    private double[] x, y;
    private double r, a, b;

    private double ROUND_OFF = 5; // round to {{ ROUND_OFF }} digits

    public Regression(ArrayList<Double> times, ArrayList<Double> pitches, int l)
    {
        if (times.size() != pitches.size()) {
            throw new IllegalArgumentException("ArrayLists must be of same length.");
        }

        x = new double[times.size()];
        y = new double[pitches.size()];
        ROUND_OFF = Math.pow(10, ROUND_OFF);

        for (int i = 0; i < times.size(); i++) {
            x[i] = times.get(i);
            y[i] = pitches.get(i);
            if ( l == 0 ) {
                x[i] = times.get(i);
                y[i] = pitches.get(i);
            } else if ( l == 1 ) {
                x[i] = times.get(i);
                y[i] = Math.log( pitches.get(i) );
            } else if ( l == 2 ) {
                x[i] = Math.log( times.get(i) );
                y[i] = Math.log( pitches.get(i) );
            }
        }

        r = getCovariance() / (getStDevX() * getStDevY());
        b = getSlope();
        a = getIntercept();

        if (l > 0) {
            a = Math.pow( Math.E, a );
        }
        if (l == 1) {
            b = Math.pow( Math.E, b );
        }
    }

    public double getRSquared()
    {
        return r * r;
    }

    private double getCovariance()
    {
        double xbar = 0.0;
        for (double d : x) {
            xbar += d;
        }
        xbar /= x.length;

        double ybar = 0.0;
        for (double d : y) {
            ybar += d;
        }
        ybar /= y.length;

        double covariance = 0.0;
        for (int i = 0; i < x.length; i++) {
            covariance += (x[i] - xbar) * (y[i] - ybar);
        }
        covariance /= x.length - 1;
        return covariance;
    }

    private double getStDevY()
    {
        double xbar = 0.0;
        for (double d : y) {
            xbar += d;
        }
        xbar /= y.length;

        double stdev = 0.0;
        for (double d : y) {
            stdev += Math.pow(d - xbar, 2);
        }
        stdev /= y.length - 1;
        return Math.pow(stdev, 0.5);
    }

    private double getStDevX()
    {
        double xbar = 0.0;
        for (double d : x) {
            xbar += d;
        }
        xbar /= x.length;

        double stdev = 0.0;
        for (double d : x) {
            stdev += Math.pow(d - xbar, 2);
        }
        stdev /= x.length - 1;
        return Math.pow(stdev, 0.5);
    }

    private double getSlope()
    {
        return r * getStDevY() / getStDevX();
    }

    private double getIntercept()
    {
        double xbar = 0.0;
        for (double d : x) {
            xbar += d;
        }
        xbar /= x.length;

        double ybar = 0.0;
        for (double d : y) {
            ybar += d;
        }
        ybar /= y.length;

        return ybar - xbar * getSlope();
    }

    private void print1D(double[] d) {
        String output = "[";
        for (double x : d) {
            output += x + ", ";
        }
        if (d.length > 0) {
            output = output.substring( 0, output.length() - 2 );
        }
        System.out.println( output + "]" );
    }

    public String toString()
    {
        return "b: " +
            Math.round(b * ROUND_OFF) / ROUND_OFF +
            ", R-squared: " +
            Math.round(getRSquared() * ROUND_OFF) / ROUND_OFF;
    }

}
