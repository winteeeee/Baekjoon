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
        int[] item = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++)
            item[i] = Integer.parseInt(st.nextToken());

        ArrayList<Integer[]>[] edges = new ArrayList[r];
        for(int i = 0; i < r; i++)
            edges[i] = new ArrayList<>();

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int idx2 = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            Integer[] temp = new Integer[2];
            Integer[] temp2 = new Integer[2];
            temp[0] = idx2; temp[1] = distance;
            temp2[0] = idx; temp2[1] = distance;
            edges[idx - 1].add(temp);
            edges[idx2 - 1].add(temp2);
        }

        int result = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++) {
            PriorityQueue<Node_14938> h = new PriorityQueue<>();
            boolean[] visited = new boolean[n];
            visited[i] = true;
            int numberOfItem = item[i];
            h.add(new Node_14938(i, 0));

            while(!h.isEmpty()) {
                Node_14938 node = h.remove();
                int size = edges[node.getN() - 1].size();

                for(int j = 0; j < size; j++) {
                    if(node.getDistance() + edges[j].get(j)[1] <= m && !visited[edges[j].get(j)[1]]) {
                        numberOfItem += item[edges[j].get(j)[0] - 1];
                        h.add(new Node_14938(edges[j].get(j)[0], node.getDistance() + edges[j].get(j)[1]));
                        visited[edges[j].get(j)[1]] = true;
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
