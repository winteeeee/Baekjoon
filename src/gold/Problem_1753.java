package gold;

import java.io.*;
import java.util.*;

public class Problem_1753 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());

        ArrayList<Node_1753>[] list = new ArrayList[V];
        for(int i = 0; i < V; i++)
            list[i] = new ArrayList<>();

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[u - 1].add(new Node_1753(v - 1, w));
        }

        int[] graph = new int[V];
        boolean[] visited = new boolean[V];
        for(int i = 0; i < V; i++)
            graph[i] = Integer.MAX_VALUE;
        graph[K - 1] = 0;

        PriorityQueue<Node_1753> h = new PriorityQueue<>();
        h.add(new Node_1753(K - 1, 0));
        while(!h.isEmpty()) {
            Node_1753 node = h.remove();
            int curIdx = node.getIdx();

            if(visited[curIdx])
                continue;

            int length = list[curIdx].size();
            for(int i = 0; i < length; i++) {
                Node_1753 next = list[curIdx].get(i);
                int nextIdx = next.getIdx();

                if (graph[curIdx] + next.getDistance() < graph[nextIdx]) {
                    graph[nextIdx] = graph[curIdx] + next.getDistance();
                    h.add(new Node_1753(nextIdx, graph[nextIdx]));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < V; i++) {
            if(graph[i] != Integer.MAX_VALUE)
                sb.append(graph[i] + "\n");

            else
                sb.append("INF\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}

class Node_1753 implements Comparable<Node_1753> {
    private int idx;
    private int distance;

    public Node_1753(int i, int d) {
        idx = i;
        distance = d;
    }

    public int getIdx() {
        return idx;
    }

    public int getDistance(){
        return distance;
    }

    public int compareTo(Node_1753 other) {
        return Integer.compare(distance, other.distance);
    }
}