package gold;

import java.io.*;
import java.util.*;

public class Problem_1504 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        long[] result = new long[2];

        ArrayList<Integer[]> edges = new ArrayList<>();
        for(int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            Integer[] temp = new Integer[3];
            temp[0] = Integer.parseInt(st.nextToken());
            temp[1] = Integer.parseInt(st.nextToken());
            temp[2] = Integer.parseInt(st.nextToken());
            edges.add(temp);
        }

        ArrayList<Integer[]>[] vertex = new ArrayList[n];
        for(int i = 0; i < n; i++)
            vertex[i] = new ArrayList<>();

        for(int i = 0; i < e; i++) {
            Integer[] temp = edges.get(i);
            vertex[temp[0] - 1].add(temp);
            vertex[temp[1] - 1].add(temp);
        }
        
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int[] graph = new int[n];
        for(int i = 1; i < n; i++) {
            graph[i] = Integer.MAX_VALUE;
        }
        boolean[] visited = new boolean[n];
        dijkstra(0, vertex, graph, visited);
        result[0] += graph[v1 - 1];
        result[1] += graph[v2 - 1];

        graph = new int[n];
        for(int i = 0; i < n; i++) {
            if(i != v1 - 1)
                graph[i] = Integer.MAX_VALUE;

            else
                graph[i] = 0;
        }
        visited = new boolean[n];
        dijkstra(v1 - 1, vertex, graph, visited);
        result[0] += graph[v2 - 1];
        result[1] += graph[n - 1];

        graph = new int[n];
        for(int i = 0; i < n; i++) {
            if(i != v2 - 1)
                graph[i] = Integer.MAX_VALUE;

            else
                graph[i] = 0;
        }
        visited = new boolean[n];
        dijkstra(v2 - 1, vertex, graph, visited);
        result[0] += graph[n - 1];
        result[1] += graph[v1 - 1];

        Arrays.sort(result);
        if(result[0] < Integer.MAX_VALUE)
            bw.write(String.valueOf(result[0]));

        else
            bw.write("-1");
        bw.flush();
        bw.close();
    }

    public static void dijkstra(int start, ArrayList<Integer[]>[] vertex, int[] graph, boolean[] visited) {
        PriorityQueue<Node_1504> h = new PriorityQueue<>();
        h.add(new Node_1504(start, 0));

        while(!h.isEmpty()) {
            Node_1504 node = h.remove();
            while(!h.isEmpty())
                h.remove();
            int curIdx = node.getIdx();
            visited[curIdx] = true;

            for(int i = 0; i < vertex[curIdx].size(); i++) {
                int nextIdx;
                if(vertex[curIdx].get(i)[0] - 1 == curIdx)
                    nextIdx = vertex[curIdx].get(i)[1] - 1;

                else
                    nextIdx = vertex[curIdx].get(i)[0] - 1;

                if(!visited[nextIdx]) {
                    h.add(new Node_1504(nextIdx, graph[nextIdx]));
                    if(graph[curIdx] + vertex[curIdx].get(i)[2] < graph[nextIdx]) {
                        graph[nextIdx] = graph[curIdx] + vertex[curIdx].get(i)[2];
                    }
                }
            }
        }
    }
}

class Node_1504 implements Comparable<Node_1504> {
    private int idx;
    private int distance;
    
    public Node_1504(int idx, int distance) {
        this.idx = idx;
        this.distance = distance;
    }

    public int getIdx() {
        return idx;
    }
    
    public int compareTo(Node_1504 other) { return Integer.compare(distance, other.distance); }
}