package gold;

import java.io.*;
import java.util.*;

public class Problem_1916 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        ArrayList<Integer[]>[] bus = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++)
            bus[i] = new ArrayList<>();

        for(int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            Integer[] temp = new Integer[2];
            temp[0] = b; temp[1] = c;
            bus[a].add(temp);
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int[] graph = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        for(int i = 1; i <= n; i++)
            graph[i] = Integer.MAX_VALUE;
        graph[start] = 0;

        PriorityQueue<Node_1916> h = new PriorityQueue<>();
        h.add(new Node_1916(start, 0));

        while(!h.isEmpty()) {
            Node_1916 node = h.remove();
            int curIdx = node.getIdx();
            if(node.getDistance() > graph[curIdx])
                continue;

            int length = bus[curIdx].size();
            for(int i = 0; i < length; i++) {
                int nextIdx = bus[curIdx].get(i)[0];
                int nextDistance = bus[curIdx].get(i)[1];

                if(!visited[nextIdx]) {
                    if (graph[nextIdx] > graph[curIdx] + nextDistance) {
                        graph[nextIdx] = graph[curIdx] + nextDistance;
                        h.add(new Node_1916(nextIdx, graph[nextIdx]));
                    }
                }
            }
        }

        bw.write(String.valueOf(graph[end]));
        bw.flush();
        bw.close();
    }
}

class Node_1916 implements Comparable<Node_1916> {
    private int idx;
    private int distance;

    public Node_1916(int i, int d) {
        idx = i;
        distance = d;
    }

    public int getIdx() {
        return idx;
    }

    public int getDistance(){
        return distance;
    }

    public int compareTo(Node_1916 other) {
        return Integer.compare(distance, other.distance);
    }
}
