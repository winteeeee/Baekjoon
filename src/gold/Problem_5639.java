package gold;

import java.io.*;

public class Problem_5639 {
    public static void main(String[] args) throws IOException {
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        var br = new BufferedReader(new InputStreamReader(System.in));
        Node_5639 root =  new Node_5639(Integer.parseInt(br.readLine()), null, null);
        String temp = br.readLine();

        while(temp != null) {
            Node_5639 n = new Node_5639(Integer.parseInt(temp), null, null);
            insertionNode(n, root);
            temp = br.readLine();
        }

        postorder(root, bw);
        bw.flush();
        bw.close();
    }

    public static void insertionNode(Node_5639 n, Node_5639 root) {
        if(n.getN() > root.getN()) {
            try {
                insertionNode(n, root.getRight());
            } catch(NullPointerException e) {
                root.setRight(n);
            }
        }
        else if(n.getN() < root.getN()) {
            try {
                insertionNode(n, root.getLeft());
            } catch(NullPointerException e) {
                root.setLeft(n);
            }
        }
    }

    public static void postorder(Node_5639 root, BufferedWriter bw) throws IOException {
        try {
            postorder(root.getLeft(), bw);
        } catch(NullPointerException e) {}
        try {
            postorder(root.getRight(), bw);
        } catch(NullPointerException e) {}
        bw.write(root.getN() + "\n");
    }
}

class Node_5639 {
    private int n;
    private Node_5639 left;
    private Node_5639 right;

    public Node_5639(int n, Node_5639 l, Node_5639 r) {
        this.n = n;
        left = l;
        right = r;
    }
    public int getN() {
        return n;
    }

    public Node_5639 getLeft() {
        return left;
    }

    public Node_5639 getRight() {
        return right;
    }

    public void setLeft(Node_5639 l) {
        left = l;
    }

    public void setRight(Node_5639 r) {
        right = r;
    }
}
