import java.util.*;
import java.io.*;

public class Convert {

    public static void main(String[] args)
    {
        Scanner in = new Scanner( new BufferedReader( new InputStreamReader ( System.in )));
        while ( in.hasNext() ) {
            System.out.println(in.next() + "," + in.next());
            //System.out.println(in.next() + "," + in.next() + "," + in.next() + "," + in.next() + "," + in.next() + "," + in.next() + "," + in.next() + "," + in.next());
        }
    }
}
