import java.util.*;
import java.io.*;

public class LempelZivCompress {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please call this program with one argument which is the input file name.");
        } else {
            try {
                Scanner s = new Scanner(new File(args[0]));

                // Read the entire file into one String.
                StringBuilder fileText = new StringBuilder();
                while (s.hasNextLine()) {
                    fileText.append(s.nextLine() + "\n");
                }

                System.out.println(compress(fileText.toString()));
            } catch (FileNotFoundException e) {
                System.out.println("Unable to find file called " + args[0]);
            }
        }
    }
    
    /**
     * Take uncompressed input as a text string, compress it, and return it as a
     * text string.
     */
    public static String compress(String input) {
        // TODO (currently just returns the argument).

        StringBuilder sb = new StringBuilder();
        int cursor = 0;
        int windowSize = 100; // some suitable size


        while (cursor < input.length()) {
            int length = 1;
            int prevMatch = 0;

            while(true) {
             //   int match = stringMatch(text[cursor..cursor + length],text[((cursor < windowSize) ? 0 : cursor - windowSize)..cursor])

                String subString1 = input.substring(cursor, cursor+length);
                int start = (cursor < windowSize) ? 0 : cursor - windowSize;
                int end = (cursor + length < input.length() - 1) ? cursor : input.length() - 1;

                String subString2 = input.substring(start, end);
                int match = subString2.indexOf(subString1);

                if (match != -1){ //If match is successful. ==1 is a placeholder.
                    prevMatch = match;
                    length = length + 1;
                }
            else{
                   // output([a value for prevMatch,length - 1, text[cursor + length - 1]]);
                    if (length == 1) {
                        sb.append("[" + 0 + "|" + 0 + "|" + input.charAt(cursor+length-1) + "]");

                    }
                    else {
                        sb.append("[" + (cursor - (prevMatch + start)) + "|" + (length - 1) + "|" + input.charAt(cursor + length - 1) + "]");
                    }cursor = cursor + length;
                    break;
                }
            }
        }

        return sb.toString();
    }
}
