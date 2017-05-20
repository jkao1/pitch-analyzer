import java.util.*;
import java.io.*;

public class Driver {

    public static void main(String[] args)
    {
        File f0 = new File("/Users/jasonkao/Desktop/pitch-analyzer/f0");
        File[] files = f0.listFiles();

        ArrayList<Double>[] scores = {
            new ArrayList<Double>(),
            new ArrayList<Double>(),
            new ArrayList<Double>(),
            new ArrayList<Double>(),
        };

        File[] zhu = {
            new File("/Users/jasonkao/Desktop/pitch-analyzer/zhu-tone1.txt"),
            new File("/Users/jasonkao/Desktop/pitch-analyzer/zhu-tone2.txt"),
            new File("/Users/jasonkao/Desktop/pitch-analyzer/zhu-tone3.txt"),
            new File("/Users/jasonkao/Desktop/pitch-analyzer/zhu-tone4.txt")
        };

        try {

            String dir = "/Users/jasonkao/Desktop/pitch-analyzer/f0/";

            for ( File f : files ) {

                Scanner name = new Scanner( f.getName() );
                int userID = name.nextInt();
                int tone = name.nextInt();

                Scanner userFile = new Scanner( f );
                Scanner zhuFile = new Scanner( zhu[tone - 1 ]);

                User user = new User( userFile, zhuFile );

                scores[tone - 1].add( user.getRSquared() );
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
