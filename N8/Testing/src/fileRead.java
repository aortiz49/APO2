import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class fileRead {
    public static void main(String[] args) {

        try {
            readFile("Users/renegade/fileTest.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFile(String pPath) throws IOException {
        FileReader in = new FileReader(pPath);
        BufferedReader br = new BufferedReader(in);
        String line = br.readLine();
        while (line != null) {
            System.out.println(line);
            line = br.readLine();
        }
        in.close();
    }
}

