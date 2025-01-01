package gold;

import java.io.*;
import java.util.*;
import java.util.stream.LongStream;

public class Problem_4123 {
    static ArrayList<Path>[] edges;
    static class Result {
        long outsideCost;
        long totalCost;

        public Result(long outsideCost, long totalCost) {
            this.outsideCost = outsideCost;
            this.totalCost = totalCost;
        }
    }

    static class Path {
        int to;
        int cost;
        int outsideCost;

        public Path(int to, int cost, int outsideCost) {
            this.to = to;
            this.cost = cost;
            this.outsideCost = outsideCost;
        }
    }

    static class Node implements Comparable<Node> {
        int idx;
        long cost;
        long outsideCost;

        public Node(int idx, long cost, long outsideCost) {
            this.idx = idx;
            this.cost = cost;
            this.outsideCost = outsideCost;
        }

        @Override
        public int compareTo(Node node) {
            int comp1 = Long.compare(outsideCost, node.outsideCost);
            int comp2 = Long.compare(cost, node.cost);
            return comp1 != 0 ? comp1 : comp2;
        }
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        edges = new ArrayList[n];
        for (int i = 0; i < n; i++)
            edges[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int vertex1 = Integer.parseInt(st.nextToken());
            int vertex2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            int outsideCost = st.nextToken().equals("I") ? 0 : cost;

            edges[vertex1].add(new Path(vertex2, cost, outsideCost));
            edges[vertex2].add(new Path(vertex1, cost, outsideCost));
        }

        var sb = new StringBuilder();
        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            var curResult = dijkstra(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), n);
            if (curResult.totalCost != Long.MAX_VALUE)
                sb.append(curResult.outsideCost).append(" ").append(curResult.totalCost).append('\n');
            else
                sb.append("IMPOSSIBLE").append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static Result dijkstra(int from, int to, int n) {
        var h = new PriorityQueue<Node>();
        long[] costArr = LongStream.range(0, n).map(i -> i == from ? 0 : Long.MAX_VALUE).toArray();
        long[] outSideCostArr = LongStream.range(0, n).map(i -> i == from ? 0 : Long.MAX_VALUE).toArray();

        h.add(new Node(from, 0, 0));
        while (!h.isEmpty()) {
            var cur = h.remove();
            if (outSideCostArr[cur.idx] < cur.outsideCost) continue;
            if (costArr[cur.idx] < cur.cost) continue;

            for (Path p : edges[cur.idx]) {
                if ((outSideCostArr[p.to] > cur.outsideCost + p.outsideCost) ||
                        (outSideCostArr[p.to] == cur.outsideCost + p.outsideCost && costArr[p.to] > cur.cost + p.cost)) {
                    costArr[p.to] = cur.cost + p.cost;
                    outSideCostArr[p.to] = cur.outsideCost + p.outsideCost;
                    h.add(new Node(p.to, costArr[p.to], outSideCostArr[p.to]));
                }
            }
        }

        return new Result(outSideCostArr[to], costArr[to]);
    }
}
