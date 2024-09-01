package gold;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Problem_30797 {
    static int[] disjointSet;
    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        long cost;
        long time;

        Edge(String str) {
            var st = new StringTokenizer(str);
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            long t = Long.parseLong(st.nextToken());

            if (from < to) {
                this.from = from;
                this.to = to;
            } else {
                this.from = to;
                this.to = from;
            }

            cost = c;
            time = t;
        }

        @Override
        public int compareTo(Edge o) {
            int compare = Long.compare(cost, o.cost);
            if (compare == 0) return Long.compare(time, o.time);
            return compare;
        }
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());


        PriorityQueue<Edge> h = new PriorityQueue<>();
        for (int i = 0; i < q; i++) {
            h.add(new Edge(br.readLine()));
        }

        disjointSet = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            disjointSet[i] = i;
        }

        long totalTime = 0;
        long totalCost = 0;
        int unionCount = 0;
        while (!h.isEmpty()) {
            Edge curEdge = h.remove();

            if (findParent(curEdge.from) != findParent(curEdge.to)) {
                totalTime = Math.max(totalTime, curEdge.time);
                totalCost += curEdge.cost;
                unionCount++;
                setUnion(curEdge.from, curEdge.to);
            }
        }

        if (unionCount != n - 1) {
            bw.write("-1");
        } else {
            bw.write(totalTime + " " + totalCost);
        }

        bw.flush();
        bw.close();
    }

    static int findParent(int idx) {
        if (disjointSet[idx] != idx) {
            return disjointSet[idx] = findParent(disjointSet[idx]);
        }

        return idx;
    }

    static void setUnion(int f, int t) {
        disjointSet[findParent(t)] = f;
    }
}
