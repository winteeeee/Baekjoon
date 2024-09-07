package gold;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Problem_1197 {
    static int[] disjointSet;
    static class Edge implements Comparable<Edge> {
        int v1;
        int v2;
        int weight;

        Edge(String str) {
            var st = new StringTokenizer(str);
            v1 = Integer.parseInt(st.nextToken());
            v2 = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        var h = new PriorityQueue<Edge>();
        disjointSet = new int[v + 1];
        for (int i = 1; i <= v; i++) {
            disjointSet[i] = i;
        }

        for (int i = 0; i < e; i++) {
            h.add(new Edge(br.readLine()));
        }

        int sumOfWeight = 0;
        while (!h.isEmpty()) {
            Edge cur = h.remove();

            if (!isCycle(cur.v1, cur.v2)) {
                addMST(cur.v1, cur.v2);
                sumOfWeight += cur.weight;
            }
        }

        bw.write(String.valueOf(sumOfWeight));
        bw.flush();
        bw.close();
    }

    static void addMST(int v1, int v2) {
        disjointSet[find(v2)] = find(v1);
    }

    static int find(int v) {
        if (disjointSet[v] == v) return v;
        return disjointSet[v] = find(disjointSet[v]);
    }

    static boolean isCycle(int v1, int v2) {
        return find(v1) == find(v2);
    }
}
