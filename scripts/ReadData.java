import java.util.*;
import java.io.*;

public class ReadData {

    private static int USERS = 24;

    private static ArrayList<Double> toneStorage = new ArrayList<Double>();

    public static void main(String[] args)
    {
        Scanner in = new Scanner( new BufferedReader( new InputStreamReader( System.in )));
        in.useDelimiter(",");

        while ( in.hasNext() ) {
            toneStorage.add( in.next() );
        }
    }
}
