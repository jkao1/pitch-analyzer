import java.io.*;
import java.util.*;

public class Error {

    public static void main(String[] args)
    {
        try {
            User u = new User( new Scanner( new File("/Users/jasonkao/pitch-data/f0/10-1.txt" )));
            System.out.println(u.getRSquared());
        } catch (IOException e) {}
    }
}
