package gold;

import java.io.*;
import java.util.*;

public class Problem_1238 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int[] result = new int[n];

        PriorityQueue<Edge> h = new PriorityQueue<>();
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            Edge e = new Edge(start, end, t);
            h.add(e);
        }

        for(int k = 0; k < n; k++) {
            int[] vertex = new int[n];
            boolean[] visited = new boolean[n];
            for(int i = 0; i < n; i++)
                vertex[i] = Integer.MAX_VALUE;
            vertex[k] = 0;

            for (int i = 0; i < m; i++) {
                Edge e = h.remove();
                int minIndex = e.getStart();
                visited[e.getStart() - 1] = true;

                for (int j = 0; j < m; j++) {
                    if (!visited[e.getEnd() - 1] && (e.getStart() - 1) == minIndex) {
                        if (vertex[minIndex] + e.getT() < vertex[e.getEnd() - 1]) {
                            vertex[e.getEnd() - 1] = vertex[minIndex] + e.getT();
                        }
                    }
                }
            }
            result[k] = vertex[x - 1];
        }

        int[] vertex = new int[n];
        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++)
            vertex[i] = Integer.MAX_VALUE;
        vertex[x - 1] = 0;

        for (int i = 0; i < n; i++) {
            Edge e = h.remove();
            int minIndex = e.getStart();

            for (int j = 0; j < m; j++) {
                if (!visited[e.getEnd() - 1] && (e.getStart() - 1) == minIndex) {
                    if (vertex[minIndex] + e.getT() < vertex[e.getEnd() - 1]) {
                        vertex[e.getEnd() - 1] = vertex[minIndex] + e.getT();
                    }
                }
            }
        }

        for(int i = 0; i < n; i++) {
            result[i] += vertex[i];
        }

        Arrays.sort(result);
        bw.write(String.valueOf(result[n - 1]));
        bw.flush();
        bw.close();
    }
}

class Edge implements Comparable<Edge>{
    private int start;
    private int end;
    private int t;

    public Edge(int s, int e, int t) {
        start = s;
        end = e;
        this.t = t;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getT() {
        return t;
    }

    public int compareTo(Edge e) {
        return Integer.compare(t, e.getT());
    }
}

//우선순위 큐를 이용한 다익스트라 알고리즘 구현중
