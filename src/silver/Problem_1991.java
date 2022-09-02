package silver;

import java.io.*;
import java.util.*;

public class Problem_1991 {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        Node_1991 root = new Node_1991('A');

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Node_1991 subRoot = new Node_1991(st.nextToken().charAt(0));
            subRoot.setLeft(new Node_1991(st.nextToken().charAt(0)));
            subRoot.setRight(new Node_1991(st.nextToken().charAt(0)));
            insertNode(root, subRoot);
        }

        preorder(root);
        bw.write(sb.toString() + "\n");
        sb = new StringBuilder();
        inorder(root);
        bw.write(sb.toString() + "\n");
        sb = new StringBuilder();
        postorder(root);
        bw.write(sb.toString() + "\n");
        bw.flush();
        bw.close();
    }

    public static void preorder(Node_1991 root) {
        if(root.getAlphabet() != '.')
            sb.append(root.getAlphabet());
        if(root.getLeft().getAlphabet() != '.')
            preorder(root.getLeft());
        if(root.getRight().getAlphabet() != '.')
            preorder(root.getRight());
    }

    public static void inorder(Node_1991 root) {
        if(root.getLeft().getAlphabet() != '.')
            inorder(root.getLeft());
        if(root.getAlphabet() != '.')
            sb.append(root.getAlphabet());
        if(root.getRight().getAlphabet() != '.')
            inorder(root.getRight());
    }

    public static void postorder(Node_1991 root) {
        if(root.getLeft().getAlphabet() != '.')
            postorder(root.getLeft());
        if(root.getRight().getAlphabet() != '.')
            postorder(root.getRight());
        if(root.getAlphabet() != '.')
            sb.append(root.getAlphabet());
    }

    public static void insertNode(Node_1991 root, Node_1991 n) {
        if(n.getAlphabet() == root.getAlphabet()) {
            root.setLeft(n.getLeft());
            root.setRight(n.getRight());
        }

        else {
            if(root.getLeft() != null)
                insertNode(root.getLeft(), n);

            if(root.getRight() != null)
                insertNode(root.getRight(), n);
        }
    }
}

class Node_1991 {
    private char alphabet;
    private Node_1991 Left;
    private Node_1991 Right;

    public Node_1991(char a) {
        alphabet = a;
        Left = null;
        Right = null;
    }

    public void setLeft(Node_1991 l) {
        Left = l;
    }

    public void setRight(Node_1991 r) {
        Right = r;
    }

    public Node_1991 getLeft() {
        return Left;
    }

    public Node_1991 getRight() {
        return Right;
    }

    public char getAlphabet() {
        return alphabet;
    }
}