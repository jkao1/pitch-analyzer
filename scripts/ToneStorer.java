import java.util.*;

public class ToneStorer {

    ArrayList<Double> tone1;
    ArrayList<Double> tone2;
    ArrayList<Double> tone3;
    ArrayList<Double> tone4;

    public ToneStorer()
    {
        tone1 = new ArrayList<Double>();
        tone2 = new ArrayList<Double>();
        tone3 = new ArrayList<Double>();
        tone4 = new ArrayList<Double>();
    }

    public void add(int t, double d)
    {
        if (t == 1)
            tone1.add(d);
        if (t == 2)
            tone2.add(d);
        if (t == 3)
            tone3.add(d);
        if (t == 4)
            tone4.add(d);
    }
}
