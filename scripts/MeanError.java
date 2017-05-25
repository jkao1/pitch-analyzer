import java.util.*;

public class MeanError {

    private ArrayList<Double> x, y;

    public MeanError(ArrayList<Double> x, ArrayList<Double> y)
    {
        this.x = x;
        this.y = y;
    }

    public double getRMSD()
    {
        double output = 0.0;
        for (int i = 0; i < x.size(); i++) {
            output += Math.pow(x.get(i) - y.get(i), 2);
        }
        output /= x.size();
        return Math.pow(output, 0.5);
    }

    public double getMAE()
    {
        double output = 0.0;
        for (int i = 0; i < x.size(); i++) {
            output += Math.abs(x.get(i) - y.get(i));
        }
        return output / x.size();
    }
}
