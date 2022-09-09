import java.util.*;
import java.io.*;

public class BM {

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
     * Perform BM substring search on the given text with the given pattern.
     * 
     * This should return the starting index of the first substring match if it
     * exists, or -1 if it doesn't.
     */
    public static int search(String text, String pattern) {
        // TODO
       int textLength = text.length();
       int patternLength = pattern.length();

       int cursor = patternLength - 1;
       int stringIndex = patternLength - 1;

        while (cursor < textLength - 1) {
            if (pattern.charAt(stringIndex) == text.charAt(cursor)) {
                if (stringIndex == 0) {
                    return cursor;
                }
                else {
                    cursor--;
                    stringIndex--;
                }
            }
            else {
                cursor = cursor + patternLength - minimum(stringIndex, 1+ (lastOccurance(pattern,text.charAt(cursor))));
                stringIndex = patternLength -1;
            }
        }
        return -1;
    }

    public static int lastOccurance(String pattern, char c) {
        for (int i = pattern.length() -1 ; i > 0; i-- ) {
            if (pattern.charAt(i) == c) {
               return i;
            }
        }
        return -1;
    }

   public static int minimum(int a, int b) {
        if (b < a) {
            return b;
        } else {
            return a;
        }
    }
}

//https://gist.github.com/LeafieTutoring/14bb41329f21eecf0dff886ddbbdf6f3
//Used the above to help with Boyer-Moore