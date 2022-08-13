package silver;

import java.io.*;
import java.util.*;

public class Problem_1260 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[n];
        boolean[] visited2 = new boolean[n];
        int[][] graph = new int[m][2];
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int temp = Integer.parseInt(st.nextToken());
            int temp2 = Integer.parseInt(st.nextToken());

            if(temp > temp2) {
                graph[i][1] = temp;
                graph[i][0] = temp2;
            }

            else {
                graph[i][0] = temp;
                graph[i][1] = temp2;
            }
        }
        Arrays.sort(graph, new Comparator<int[]>() {
            @Override
            public int compare(int[] t0, int[] t1) {
                return Integer.compare(t0[0], t1[0]);
            }
        });

        Arrays.sort(graph, new Comparator<int[]>() {
            @Override
            public int compare(int[] t0, int[] t1) {
                if(t0[0] == t1[0])
                    return Integer.compare(t0[1], t1[1]);

                else
                    return Integer.compare(t0[0], t1[0]);
            }
        });

        dfs(v, graph, visited);
        bw.write("\n");
        bfs(v, graph, visited2);
        bw.flush();
        bw.close();
    }

    public static void dfs(int v, int[][] graph, boolean[] visited) throws IOException {
        bw.write(String.valueOf(v)+" ");
        visited[v-1] = true;

        for(int i = 0 ; i < graph.length; i++) {
            if(graph[i][0] == v && !visited[graph[i][1] - 1])
                dfs(graph[i][1], graph, visited);

            else if(graph[i][1] == v && !visited[graph[i][0] - 1])
                dfs(graph[i][0], graph, visited);
        }
    }

    public static void bfs(int v, int[][] graph, boolean[] visited) throws IOException {
        Queue<Integer> q = new LinkedList<>();
        visited[v-1] = true;
        q.add(v);

        while(!q.isEmpty()) {
            int temp = q.remove();
            bw.write(String.valueOf(temp)+" ");
            ArrayList<Integer> arrList = new ArrayList<>();

            for(int i = 0; i < graph.length; i++) {
                if(graph[i][0] == temp && !visited[graph[i][1] - 1]) {
                    arrList.add(graph[i][1]);
                    visited[graph[i][1] - 1] = true;
                }
                else if(graph[i][1] == temp && !visited[graph[i][0] - 1]) {
                    arrList.add(graph[i][0]);
                    visited[graph[i][0] - 1] = true;
                }
            }

            Integer[] arr = arrList.toArray(new Integer[arrList.size()]);
            Arrays.sort(arr);

            for(int i = 0; i < arr.length; i++)
                q.add(arr[i]);
        }
    }
}
