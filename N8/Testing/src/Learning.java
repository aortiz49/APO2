import java.io.*;
import java.util.ArrayList;

public class Learning {
    public static void main(String[] args) {

        System.out.println("Constructing string");
        constructingString();

        System.out.println("Comparing strings");
        comparingAString();

        System.out.println("String length and combining strings");
        strLen();

        System.out.println("Substrings");
        substring();

        System.out.println("Converting");
        converting();

        System.out.println("Matching, Replacing and Splitting by Patterns");
        matching();


    }

    /**
     * Constructing a string and prints text to the console.
     */
    public static void constructingString() {

        String newString = "Hello";
        System.out.println(newString);
    }


    /**
     * String comparisons
     */
    public static void comparingAString() {

        String s1 = "Welcome to Java";
        String s2 = "Welcome to Java";
        String s3 = "Welcome to C++";

        String s4 = "abc";
        String s5 = "abg";

        // This will return true because .equals is comparing the content of both strings.
        System.out.println(s1.equals(s2)); // true
        System.out.println(s1.equals(s3)); // false

        // This will return -4 because it performs a lexicographical comparison character by
        // character. When the strings are compared, a vs a are equal so it goes to the next
        // character. Since b vs b are equal, it goes to the next character. s4 'c' is 4 less
        // than 'g' so the comparison stops here and returns -4.
        System.out.println(s4.compareTo(s5));

        // This checks if the region of two strings starting and ending at a custom location.
        System.out.println(s1.regionMatches(2, s3, 2, 5));
    }

    /**
     * String Length, Characters, and Combining Strings
     */
    public static void strLen() {

        String s1 = "Hello world!";
        String s2 = "I am Andy";

        // Length of a string
        System.out.println(s1.length());

        // Return the letter w at index 6.
        System.out.println(s1.charAt(6));

        // This joins the two strings together.
        String s3 = s1.concat(s2);
        System.out.println(s3);
    }

    /**
     * Obtaining substrings
     */
    public static void substring() {

        String s1 = "Welcome to Java";

        // This is a substring of s1. Going from index 7 to the end of the string.
        System.out.println(s1.substring(8));

        // This is a substring of s1 going from index 0 to 6 (Range printed will be 0 to endIndex
        // -1). If beginIndex > endIndex, it will be a runtime error.
        System.out.println(s1.substring(0, 7));

        // Example
        System.out.println(s1.substring(0, 11) + "C++");


    }


    /**
     * Converting, Replacing, and Splitting Strings
     */
    public static void converting() {

        String s1 = "    Welcome to Java. This is a Java course   ";
        String s2 = "My nsme is sndy snd I sm hsppy";
        String s3 = "alex,rider,1218713597,bogota,uniandes";

        System.out.println(s1);

        // This eliminates leading and terminating blank space.
        System.out.println(s1.trim());

        // Returns a new string that replaces all matching characters in this string with the new
        // character.
        System.out.println(s2.replace('s', 'a'));


        // Returns a new string that replaces the first matching substring in this string with
        // the new substring.
        System.out.println(s1.replaceFirst("Java", "Python"));

        // Returns a new string that replaces all matching substrings in this string with the new
        // substring.
        System.out.println(s1.replaceAll("Java", "Python"));

        // Returns an array of strings consisting of the substrings split by the delimiter.
        String tokens[] = s3.split(",");
        for (String token : tokens)
            System.out.println(token);
    }


    /**
     * Matching, Replacing and Splitting by Patterns
     */
    public static void matching() {


        // matches() method can match not only a fixed string, but also a set of strings that
        // follow a pattern.
        String s1 = "Java is fun";
        String s2 = "Java is a very nice language";

        System.out.println(s1.matches("Java.*"));

        // The following statement returns a new string that replaces !, @, or # in the string s2
        // with the string NNN.
        String s3 = "A!boy@is#coding";
        System.out.println(s3.replaceAll("[!@#]", "NNN"));

        // The following statement splits the string into an array of strings delimited by
        // punctuation marks.
        String[] tokens = "Java,C?C#,C++".split("[.,:;?]");
        for (String token : tokens)
            System.out.println(token);

        //
        String[] names = {"andy,123456", "john,165123", "kyle,187365", "maria,876928"};
        System.out.println("Names:\n");
        String[] tokens2 = new String[names.length];
        for (int i = 0; i < names.length; i++) {
            String[] tokens1 = new String[names.length];


            tokens1[i] = names[i].split(",")[0];
            tokens2[i] = names[i].split(",")[1];

            System.out.println(tokens1[i]);

        }

        System.out.println("Ids:\n");
        for (int i = 0; i <names.length ; i++) {
            System.out.println(tokens2[i]);
        }

    }

}

