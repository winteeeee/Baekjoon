package gold;

import java.io.*;
import java.util.*;

/*

 */

public class Main {
    static class Node {
        int number;
        Node parent;
        ArrayList<Node> child;
        int size;

        Node(int n, Node p, ArrayList<Node> e) {
            number = n;
            parent = p;
            child = e;
        }

        void makeTree(Node p, ArrayList<Integer>[] edges, Node[] nodes) {
            parent = p;
            for (int e : edges[number]) {
                if (parent == null || e != parent.number) {
                    child.add(nodes[e]);
                    nodes[e].makeTree(this, edges, nodes);
                }
            }
        }

        void setSize() {
            size = 1;
            for (Node c : child) {
                c.setSize();
                size += c.size;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] edges = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            edges[v1].add(v2);
            edges[v2].add(v1);
        }

        int[] queries = new int[q];
        for (int i = 0; i < q; i++) {
            queries[i] = Integer.parseInt(br.readLine());
        }

        Node[] nodes = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node(i, null, new ArrayList<>());
        }

        nodes[r].makeTree(null, edges, nodes);
        nodes[r].setSize();
        var sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            int subTreeRoot = queries[i];
            Node subTree = nodes[subTreeRoot];
            sb.append(subTree.size).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
