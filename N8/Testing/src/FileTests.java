import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class FileTests {

    public static void main(String[] args) {
/*
        File file = new File("/Users/renegade/fileTest.txt");
        System.out.println("Does it exist? " + file.exists());
        System.out.println("The file has " + file.length() + " bytes");
        System.out.println("Can it be read? " + file.canRead());
        System.out.println("Can it be written? " + file.canWrite());
        System.out.println("Is it a directory? " + file.isDirectory());
        System.out.println("Is it a file? " + file.isFile());
        System.out.println("Is it absolute? " + file.isAbsolute());
        System.out.println("Is it hidden? " + file.isHidden());
        System.out.println("Absolute path is " + file.getAbsolutePath());
        System.out.println("Last modified on " + new java.util.Date(file.lastModified()));
*/

        ArrayList<String> readingLines = new ArrayList<>(); //1
        String line;
        try {
            FileReader fileReader = new FileReader(new File("/Users/renegade/fileTest.txt")); //2
            BufferedReader bufferedReader = new BufferedReader(fileReader); //3
            while ((line = bufferedReader.readLine()) != null) {
                line = cleanLine(line);
                readingLines.add(line); //4
            }
            bufferedReader.close();
            for (String readingLine : readingLines) {
                //   System.out.println(readingLine);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }


        System.out.println("Does line 1 contain the substring like?");

        System.out.println(lineContains(readingLines.get(0), "like"));

        System.out.println("Does the file contain the word elf?");
        try {
            System.out.println(containsPrefix("elf"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("DIRECTORY TESTS");
        try {
            currentDirReport();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            practiceWriting("/Users/renegade/NEWDIR","test.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * Suprime de la línea todos los caracteres de puntuación, remplazándolos por un carácter blanco
     *
     * @param linea es la línea de entrada
     * @return Línea procesada
     */
    private static String cleanLine(String linea) {
        // Elimina los signos de puntuación y los tabs

        linea = linea.replace('\t', ' ');
        linea = linea.replace('/', ' ');
        linea = linea.replace('.', ' ');
        linea = linea.replace(',', ' ');
        linea = linea.replace(':', ' ');
        linea = linea.replace(';', ' ');
        linea = linea.replace('!', ' ');
        linea = linea.replace('?', ' ');
        linea = linea.replace('\"', ' ');
        linea = linea.replace('*', ' ');
        linea = linea.replace('(', ' ');
        linea = linea.replace(')', ' ');
        linea = linea.replace('[', ' ');
        linea = linea.replace(']', ' ');
        linea = linea.replace('{', ' ');
        linea = linea.replace('}', ' ');
        linea = linea.replace('\'', ' ');
        linea = linea.replace('\\', ' ');


        // Devuelve la línea sin espacios iniciales/finales
        return linea.trim();
    }

    private static boolean lineContains(String linea, String prefijo) {
        // Suppresses all punctuation marks
        linea = cleanLine(linea);

        // Splits the line into words (removes all spaces and places each word into the array
        String[] tokens = linea.split("\\s+");

        // Busca palabra por palabra
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].toLowerCase().startsWith(prefijo.toLowerCase()))
                return true;
        }
        return false;
    }


    public static boolean containsPrefix(String prefix) throws IOException {
        // Define the file.
        File file = new File("/Users/renegade/fileTest.txt");
        boolean contains = false;


        int freq = 0;
        // Opens a file using FileReader.
        FileReader fileReader = new FileReader(file);

        // Uses a BufferedReader to read line by line.
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        // Read the file line by line
        String linea = bufferedReader.readLine();
        while (linea != null && !contains) {
            contains = lineContains(linea, prefix);
            linea = bufferedReader.readLine();
        }

        System.out.println(freq);
        // Closes the readers and returns the result
        bufferedReader.close();
        fileReader.close();
        return contains;
    }


    public static void currentDirReport() throws IOException {
        // Opens a file using FileReader.
        File file = new File("/Users/renegade/Documents");
        // Opens a file using FileReader.

        // Read the file line by line
        File[] files = file.listFiles();

        System.out.println("#Subdirectories:");
        String[] fileNames;
        if (files != null) {
            fileNames = new String[files.length];
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    fileNames[i] = files[i].getName();
                    System.out.println(fileNames[i]);
                }
            }

            System.out.println("#Files:");
            for (int i = 0; i < files.length; i++) {
                if (!files[i].isDirectory()) {
                    fileNames[i] = files[i].getName();
                    System.out.println(fileNames[i]);
                }
            }

        }

    }


    public static void practiceWriting(String pPath, String pFileName) throws IOException {

        File fileDirectory = new File(pPath);
        File currentDir = new File("/Users/renegade/");
        if (!fileDirectory.exists())
            fileDirectory.mkdirs();
        File outputFile = new File(fileDirectory, pFileName);
        PrintWriter out = new PrintWriter(outputFile);

        File[] files = currentDir.listFiles();

        if (files != null) {
            System.out.println("#Subdirectories");
            for (int i = 0; i < files.length; i++) {
                if(files[i].isDirectory())
                    out.println(files[i].getName());
            }
            out.println("a,fjhsjhgfds");
            out.close();
        }





    }
}



