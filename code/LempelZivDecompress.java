import java.awt.*;
import java.util.*;
import java.io.*;

public class LempelZivDecompress {

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

                System.out.println(decompress(fileText.toString()));
            } catch (FileNotFoundException e) {
                System.out.println("Unable to find file called " + args[0]);
            }
        }
    }
    
    /**
     * Take compressed input as a text string, decompress it, and return it as a
     * text string.
     */
    public static String decompress(String compressed) {
        // TODO (currently just returns the argument).

        StringBuilder sb = new StringBuilder();
        int cursor = 0;
        int goBack = 0;
        int forwardCount = 0;
        String character = "";
        int compressedlength = compressed.length();

        Scanner scan = new Scanner(compressed);
        scan.useDelimiter("\\[|\\]|\\|");

        while (cursor < compressedlength && scan.hasNext()) {

        if (scan.hasNextInt()) {
           goBack =  scan.nextInt();
        }
        if (scan.hasNextInt()) {
            forwardCount =  scan.nextInt();
            }
        if (scan.hasNext()) {
            character = scan.next();
            }

        if (scan.hasNext()) {
            scan.next();
        }

        if (goBack == 0 && forwardCount == 0) {
            sb.append(character);
            cursor++;
        }
        else {
            for (int j = 0; j < forwardCount; j++) {
               sb.append(sb.charAt(cursor - goBack));
               cursor++;
            }
            sb.append(character);
            cursor++;
        }
        }

        return sb.toString();
    }
}
