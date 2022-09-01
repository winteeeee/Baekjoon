package gold;

import java.io.*;
import java.util.*;

public class Problem_1967 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        if(n != 1) {
            ArrayList<Integer[]>[] edges = new ArrayList[n];
            for (int i = 0; i < n; i++)
                edges[i] = new ArrayList<>();

            for (int i = 0; i < n - 1; i++) {
                Integer[] temp = new Integer[2];
                Integer[] temp2 = new Integer[2];
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                temp[0] = b;
                temp[1] = c;
                temp2[0] = a;
                temp2[1] = c;
                edges[a - 1].add(temp);
                edges[b - 1].add(temp2);
            }

            boolean[] visited = new boolean[n];
            Integer[] node1 = bfs(1, visited, edges);
            visited = new boolean[n];
            Integer[] node2 = bfs(node1[0], visited, edges);

            bw.write(String.valueOf(node2[1]));
        }

        else
            bw.write("0");
        bw.flush();
        bw.close();
    }

    public static Integer[] bfs(int start, boolean[] visited, ArrayList<Integer[]>[] edges) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        q.add(0);
        visited[start - 1] = true;
        int maxN = Integer.MIN_VALUE;
        int maxDis = 0;

        while(!q.isEmpty()) {
            int n = q.remove();
            int distance = q.remove();
            if(maxDis < distance) {
                maxN = n;
                maxDis = distance;
            }

            int length = edges[n - 1].size();
            for(int i = 0; i < length; i++) {
                if(!visited[edges[n - 1].get(i)[0] - 1]) {
                    q.add(edges[n - 1].get(i)[0]);
                    q.add(distance + edges[n - 1].get(i)[1]);
                    visited[edges[n - 1].get(i)[0] - 1] = true;
                }
            }
        }

        Integer[] result = new Integer[2];
        result[0] = maxN;
        result[1] = maxDis;
        return result;
    }
}
