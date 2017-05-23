import java.util.*;
import java.io.*;

public class Driver {

    private static double[][] toneStorer = new double[70][4];

    public static void main(String[] args)
    {
        File f0 = new File("/Users/jasonkao/Desktop/pitch-analyzer/f0");
        File[] files = f0.listFiles();

        File[] zhuFiles = {
            new File("/Users/jasonkao/Desktop/pitch-analyzer/backup/zhu-tone1.txt"),
            new File("/Users/jasonkao/Desktop/pitch-analyzer/backup/zhu-tone2.txt"),
            new File("/Users/jasonkao/Desktop/pitch-analyzer/backup/zhu-tone3.txt"),
            new File("/Users/jasonkao/Desktop/pitch-analyzer/backup/zhu-tone4.txt")
        };

        String dir = "/Users/jasonkao/Desktop/pitch-analyzer/f0/";

        for ( File f : files ) {

            try {

                String[] fileParts = f.getName().split( "-" );
                int userID = Integer.parseInt( fileParts[0] );
                int tone = fileParts[1].charAt(0) - '0';

                Scanner userScan = new Scanner( f );
                Scanner zhuScan = new Scanner( zhuFiles[ tone - 1 ]);

                User user = new User( userScan, zhuScan, false );

                toneStorer[ userID ][ tone - 1 ] = user.getRSquared();

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("!#EXCEPTION: Failed to read file " + f);
            }
        }

        printOut( toneStorer );
    }

    public static void printOut(double[][] d)
    {
        System.out.println("userID,tone1,tone2,tone3,tone4");
        for (int u = 0; u < d.length; u++) {
            if ( takeSum(d[u]) == 0.0 ) { // empty means 0.0
                continue;
            }
            System.out.print(u + ",");
            for (double tone : d[u]) {
                System.out.print(tone + ",");
            }
            System.out.println();
        }
    }

    private static double takeSum(double[] d) {
        double output = 0.0;
        for (double dub : d) {
            output += dub;
        }
        return output;
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
