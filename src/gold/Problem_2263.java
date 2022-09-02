package gold;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_2263 {
    static int idx = 0;
    static int[] inorder;
    static int[] postorder;
    static int[] preorder;
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        inorder = new int[n];
        postorder = new int[n];
        preorder = new int[n];

        for(int i = 0; i < 2; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            if(i == 0) {
                for (int j = 0; j < n; j++)
                    inorder[j] = Integer.parseInt(st.nextToken());
            }

            else {
                for (int j = 0; j < n; j++)
                    postorder[j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[] visited = new boolean[n + 1];
        makePreorder(0, inorder.length, postorder.length - 1, visited);
        for(int i = 0; i < n; i++)
            bw.write(preorder[i] + " ");
        bw.flush();
        bw.close();
    }

    public static void makePreorder(int inStart, int inEnd, int postEnd, boolean[] visited) {
        if(postEnd < 0 || postEnd >= postorder.length)
            return;

        if(visited[postorder[postEnd]])
            return;

        preorder[idx++] = postorder[postEnd];
        visited[postorder[postEnd]] = true;

        int root = 0;
        for(int i = inStart; i < inEnd; i++) {
            if(inorder[i] == postorder[postEnd]) {
                root = i;
                break;
            }
        }

        makePreorder(inStart, root, postEnd - (inEnd - root), visited);
        makePreorder(root + 1, inEnd, postEnd - 1, visited);
    }
}