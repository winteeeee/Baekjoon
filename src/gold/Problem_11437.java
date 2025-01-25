package gold;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Problem_11437 {
    public static class Node {
        int number;
        int depth;
        Node parent;
        ArrayList<Node> children;

        public Node(int number, int depth, Node parent) {
            this.number = number;
            this.depth = depth;
            this.parent = parent;
            this.children = new ArrayList<>();
        }
    }

    static Node[] nodes;
    static ArrayList<Integer>[] adj;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        nodes = new Node[n + 1];
        for (int i = 0; i <= n; i++) {
            nodes[i] = new Node(i, 0, null);
        }

        adj = IntStream.rangeClosed(0, n).mapToObj(e -> new ArrayList()).toArray(ArrayList[]::new);
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b); adj[b].add(a);
        }
        treeInit(nodes[1]);

        int q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(findLCA(nodes[a], nodes[b])).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
    }

    public static void treeInit(Node parent) {
        for (int next : adj[parent.number]) {
            if (parent.parent != null && parent.parent.number == next) continue;

            nodes[next].depth = parent.depth + 1;
            nodes[next].parent = parent;
            parent.children.add(nodes[next]);
            treeInit(nodes[next]);
        }
    }

    public static int findLCA(Node a, Node b) {
        while (a.number != b.number) {
            if (a.depth > b.depth) {
                a = a.parent;
            } else if (a.depth < b.depth) {
                b = b.parent;
            } else {
                a = a.parent;
                b = b.parent;
            }
        }

        return a.number;
    }
}
