package silver;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Problem_11725 {
    static boolean[] visited;
    static int count = 0;
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] edge = new ArrayList[n - 1];
        for(int i = 0; i < n - 1; i++)
            edge[i] = new ArrayList<>();

        for(int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            edge[n1 - 1].add(n2);
            edge[n2 - 1].add(n1);
        }

        visited = new boolean[n];
    }

    public static void DFS(int n, int target, int prev, ArrayList<Integer>[] edge) {
        if(n == target) {
            count = prev;
            return;
        }

        for(int i = 0; i < edge[n - 1].size(); i++) {
            if(!visited[edge[n - 1].get(i)]) {
                DFS(edge[n - 1].get(i), target, n, edge);
            }
        }
    }
}