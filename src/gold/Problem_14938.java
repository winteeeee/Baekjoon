package gold;

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Problem_14938 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int[] item = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++)
            item[i] = Integer.parseInt(st.nextToken());

        ArrayList<Integer[]>[] edges = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++)
            edges[i] = new ArrayList<>();

        for(int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int idx2 = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            Integer[] temp = new Integer[2];
            Integer[] temp2 = new Integer[2];
            temp[0] = idx2; temp[1] = distance;
            temp2[0] = idx; temp2[1] = distance;
            edges[idx].add(temp);
            edges[idx2].add(temp2);
        }

        int result = Integer.MIN_VALUE;
        for(int i = 1; i <= n; i++) {
            PriorityQueue<Node_14938> h = new PriorityQueue<>();
            boolean[] visited = new boolean[n + 1];
            int numberOfItem = 0;
            h.add(new Node_14938(i, 0));

            while(!h.isEmpty()) {
                Node_14938 node = h.remove();
                int curIdx = node.getN();
                int curDistance = node.getDistance();
                int size = edges[curIdx].size();
                if(visited[curIdx])
                    continue;

                else {
                    numberOfItem += item[curIdx];
                    visited[curIdx] = true;
                }

                for(int j = 0; j < size; j++) {
                    int nextIdx = edges[curIdx].get(j)[0];
                    int nextDistance = edges[curIdx].get(j)[1];
                    if(curDistance + nextDistance <= m && !visited[nextIdx]) {
                        h.add(new Node_14938(nextIdx, curDistance + nextDistance));
                    }
                }
            }

            if(result < numberOfItem)
                result = numberOfItem;
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }
}

class Node_14938 implements Comparable<Node_14938> {
    private int n;
    private int distance;

    public Node_14938(int n, int d) {
        this.n = n;
        distance = d;
    }

    public int getN() {
        return n;
    }

    public int getDistance() {
        return distance;
    }

    public int compareTo(Node_14938 o) {
        return Integer.compare(distance, o.distance);
    }
}

