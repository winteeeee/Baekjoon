package gold;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_2263 {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] inorder = new int[n];
        int[] postorder = new int[n];

        for(int i = 0; i < 2; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            if(i == 0)
                for(int j = 0; j < n; j++)
                    inorder[i] = Integer.parseInt(st.nextToken());

            else
                for(int j = 0; j < n; j++)
                    postorder[i] = Integer.parseInt(st.nextToken());
        }

        Node_2263 root = new Node_2263(postorder[n - 1]);
        makeTree(root, inorder, postorder);
        preorder(root);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void preorder(Node_2263 root) {
        if(root.equals(null))
            return;

        sb.append(root.getN() + " ");
        preorder(root.getLeft());
        preorder(root.getRight();
    }

    public static void makeTree(Node_2263 root, int[] inorder, int[] postorder) {
        int n = root.getN();
        int inIdx = Arrays.binarySearch(inorder, n);
        int postIdx = Arrays.binarySearch(postorder, n);
    }
}

class Node_2263 {
    private int n;
    private Node_2263 left;
    private Node_2263 right;

    public Node_2263(int n) {
        this.n = n;
        left = null;
        right = null;
    }

    public void setLeft(Node_2263 l) {
        left = l;
    }

    public void setRight(Node_2263 r) {
        right = r;
    }

    public int getN() {
        return n;
    }

    public Node_2263 getLeft() {
        return left;
    }

    public Node_2263 getRight() {
        return right;
    }
}