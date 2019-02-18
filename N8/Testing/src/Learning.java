import java.io.*;
import java.util.ArrayList;

public class fileRead {
    public static void main(String[] args) {

        try {
            readFile("/Users/renegade/fileTest.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFile(String pPath) throws IOException {
        FileReader in = new FileReader(pPath);
        BufferedReader br = new BufferedReader(in);
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        in.close();

        String s = "Abc";
        String v = "cdf";

        s.concat(v);
    }
}
