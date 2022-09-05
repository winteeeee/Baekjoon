package silver;

import java.io.*;
import java.util.*;

public class Problem_11725 {
    static int[] result;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        var br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        result = new int[n];
        ArrayList<Integer>[] edge = new ArrayList[n];
        for(int i = 0; i < n; i++)
            edge[i] = new ArrayList<>();

        for(int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            edge[n1 - 1].add(n2);
            edge[n2 - 1].add(n1);
        }

        visited = new boolean[n];
        BFS(1, edge, bw);
        for(int i = 1; i < n; i++)
            bw.write(result[i] + "\n");

        bw.flush();
        bw.close();
    }

    public static void BFS(int start, ArrayList<Integer>[] edge, BufferedWriter bw) throws IOException {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        q.add(start);
        visited[start - 1] = true;

        while(!q.isEmpty()) {
            int n = q.remove();
            int prev = q.remove();
            if(result[n - 1] == 0)
                result[n - 1] = prev;

            for(int i = 0; i < edge[n - 1].size(); i++) {
                if(!visited[edge[n - 1].get(i) - 1]) {
                    q.add(edge[n - 1].get(i));
                    q.add(n);
                    visited[edge[n - 1].get(i) - 1] = true;
                }
            }
        }
    }
}