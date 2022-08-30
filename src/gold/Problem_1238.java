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
        int[] xVertex = new int[n];

        Edge[] edges = new Edge[m];
        Node[] nodes = new Node[n];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            Edge e = new Edge(start, end, t);
            edges[i] = e;
        }

        for(int i = 0; i < n; i++) {
            ArrayList<Edge> temp = new ArrayList<>();
            for(int j = 0; j < m; j++) {
                if(edges[j].getStart() - 1 == i) {
                    temp.add(edges[j]);
                }
            }
            Edge[] tempArr = temp.toArray(new Edge[0]);
            nodes[i] = new Node(tempArr);
        }

        for (int k = 0; k < n; k++) {
            int[] vertex = new int[n];
            boolean[] visited = new boolean[n];
            for (int i = 0; i < n; i++)
                vertex[i] = Integer.MAX_VALUE;
            vertex[k] = 0;

            int minIdx = k;
            for (int i = 0; i < n; i++) {
                Edge min = nodes[minIdx].getMin();
                visited[min.getStart() - 1] = true;

                Edge[] edgeArr = nodes[min.getStart() - 1].getEdge();
                for(int j = 0; j < edgeArr.length; j++) {
                    if(!visited[edgeArr[j].getEnd() - 1]) {
                        if (vertex[edgeArr[j].getStart() - 1] + edgeArr[j].getT() < vertex[edgeArr[j].getEnd() - 1]) {
                            vertex[edgeArr[j].getEnd() - 1] = vertex[edgeArr[j].getStart() - 1] + edgeArr[j].getT();
                        }
                    }
                }

                int minV = Integer.MAX_VALUE;
                for(int j = 0; j < vertex.length; j++) {
                    if(!visited[j]) {
                        if(vertex[j] < minV) {
                            minV = vertex[j];
                            minIdx = j;
                        }
                    }
                }
            }
            result[k] = vertex[x - 1];

            for(int i = 0; i < n; i++) {
                nodes[i].setMinIdx(0);
            }

            if(k == x - 1) {
                xVertex = vertex.clone();
            }
        }

        for (int i = 0; i < n; i++) {
            result[i] += xVertex[i];
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

    public int getStart() {
        return start;
    }
    public int getEnd() {
        return end;
    }

    public int getT() {
        return t;
    }

    public Edge(int start, int end, int t) {
        this.start = start;
        this.end = end;
        this.t = t;
    }

    public int compareTo(Edge other) {
        return Integer.compare(t, other.t);
    }
}

class Node {
    private Edge[] e;
    private int minIdx;

    public Node(Edge[] e) {
        this.e = e;
        Arrays.sort(e);
        minIdx = 0;
    }

    public Edge getMin() {
        Edge r = e[minIdx];

        if(minIdx + 1 < e.length)
            minIdx++;

        return r;
    }

    public void setMinIdx(int n) {
        minIdx = n;
    }

    public Edge[] getEdge() {
        return e;
    }
}