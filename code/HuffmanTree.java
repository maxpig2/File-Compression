import java.util.HashMap;
import java.util.PriorityQueue;

public class HuffmanTree {

    Node root;


    public HuffmanTree() {

    }

    public Node findChar(char c, Node n) {
        if (c == '0') {
            return n.left;
        } else {
            return n.right;
        }

    }

    public void checkNode(String s, Node n, HashMap<Character,String> h) {
        if (n.left == null && n.right == null) {
            h.put(n.character,s);
        }
        if (n.left != null){
            checkNode(s+"0", n.left,h);
        }
        if (n.right != null){
            checkNode(s+"1", n.right,h);
        }

    }


    public static class Node implements Comparable{
    Node left;
    Node right;
    public char character;
    public int frequency;

    public Node (Node l, Node r, char c, int f) {
        left = l;
        right = r;
        character = c;
        frequency = f;
    }

        @Override
        public int compareTo(Object o) {
            Node n = (Node)o;
            if (frequency == n.frequency) {
                return Character.compare(character,n.character);
            }
           return frequency - n.frequency;
        }


    }
}

