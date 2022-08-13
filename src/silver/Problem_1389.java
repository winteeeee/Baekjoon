package silver;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_1389 {
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] relation = new int[m][2];
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            relation[i][0] = Integer.parseInt(st.nextToken());
            relation[i][1] = Integer.parseInt(st.nextToken());
        }
        int[] number = new int[n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                boolean[] visited = new boolean[n];
                count = 0;
                bfs(i+1, j+1, relation, visited);
                number[i] += count;
            }
        }

        int check = Integer.MAX_VALUE;
        int result = 0;
        for(int i = 0; i < n; i++) {
            if(check > number[i]) {
                check = number[i];
                result = i+1;
            }
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }

    public static void bfs(int n, int target, int[][] relation, boolean[] visited) {
        Queue<Integer> q = new LinkedList<>();
        visited[n-1] = true;
        q.add(n);
        q.add(count);

        while(!q.isEmpty()) {
            int temp = q.remove();
            if(count != q.peek())
                count++;
            q.remove();

            if(temp == target)
                return;

            for(int i = 0; i < relation.length; i++) {
                if(relation[i][0] == temp && !visited[relation[i][1] - 1]) {
                    q.add(relation[i][1]);
                    q.add(count+1);
                    visited[relation[i][1] - 1] = true;
                }

                if(relation[i][1] == temp && !visited[relation[i][0] - 1]){
                    q.add(relation[i][0]);
                    q.add(count+1);
                    visited[relation[i][0] - 1] = true;
                }
            }
        }
    }
}
