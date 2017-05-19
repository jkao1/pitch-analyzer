import java.util.*;
import java.io.*;

public class Driver {

    public static void main(String[] args)
    {
        File f0 = new File("/Users/jasonkao/Desktop/pitch-analyzer/f0");
        File[] data = f0.listFiles();
        BufferedWriter output = null;
        double[][] Rsq = new double[5][50];
        int[] sizes = {0, 0, 0, 0, 0};

        try {
            for (int t = 1; t < 5; t++) {
                String filename = "/Users/jasonkao/Desktop/pitch-analyzer/f0/tone" + t + ".csv";
                System.out.println("Writing to " + filename + ".");
                output = new BufferedWriter( new FileWriter( new File( filename )));
                for (int i = 0; i < data.length; i++) {
                    File f = data[i];
                    if (f.getName().indexOf(t + ".txt") > 0) {
                        User u = new User( new Scanner(f) );
                        System.out.println(u);
                        output.write("" + u.getRSquared());
                        output.newLine();
                    }
                }
                output.close();
                System.out.println(filename + " closed.");
            }
        } catch(IOException e) {
            System.out.println("IO Exception");
        }
    }

    public static void print2D(double[][] d)
    {
        String output = "";
        for (int i = 1; i < d.length; i++) {
            output += "[";
            for (double x : d[i]) {
                output += x + ", ";
            }
            output = output.substring(0, output.length() - 2) + "]\n";
        }
        System.out.println(output);
    }

    public static void print1D(Object[] o) {
        String output = "";
        for (Object x : o) {
            output += x + ", ";
        }
        if (o.length > 0) {
            output = output.substring(0, output.length() - 2);
        }
        System.out.println(output + "]");
    }
}
