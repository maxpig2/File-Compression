import java.util.*;
import java.io.*;
import java.util.regex.Pattern;

public class KMP {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Please call this program with " +
                               "two arguments which is the input file name " +
                               "and the string to search.");
        } else {
            try {
                Scanner s = new Scanner(new File(args[0]));

                // Read the entire file into one String.
                StringBuilder fileText = new StringBuilder();
                while (s.hasNextLine()) {
                    fileText.append(s.nextLine() + "\n");
                }

                System.out.println(search(fileText.toString(), args[1]));
            } catch (FileNotFoundException e) {
                System.out.println("Unable to find file called " + args[0]);
            }
        }
    }

    /**
     * Perform KMP substring search on the given text with the given pattern.
     * 
     * This should return the starting index of the first substring match if it
     * exists, or -1 if it doesn't.
     */
    public static int search(String text, String pattern) {
        // TODO
        //Print out pattern.
        int lengthPattern = pattern.length();
        int lengthText = text.length();
        int[] M = new int[lengthPattern];

        M[0] = -1;
        M[1] = 0;

        int k = 0;
        int i = 0;
        int j = 0;
        int m = 0; // Position in MisMatch table
        int n = 0; //Position in text
        int pos = 2;

        while (pos < lengthPattern) {
            if (pattern.charAt(pos - 1) == pattern.charAt(j)) { //substrings ...pos-1 and 0..j match
                M[pos] = j + 1;
                pos++;
                j++;
            }
            else if (j > 0 ) {// mismatch, restart the prefix
                j = M[j];
            }
            else{
                j = 0; // we have run out of candidate prefixes
                M[pos] = 0;
                pos++;
            }
        }

        while ((k + i) < lengthText) {
            if (pattern.charAt(i) == text.charAt(k + i)) { // match
                i = i + 1;
                if (i == lengthPattern) {
                    return k;// found S
                }
            } else if (M[i] == -1) { // mismatch, no self overlap
                k = k + i + 1;
                i = 0;
            } else { // mismatch, with self overlap
                k = k + i - M[i]; // match position jumps forward
                i = M[i];
            }
        }
        return - 1;
    }

}
