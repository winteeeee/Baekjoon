package silver;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_11724 {
    static int count = 1;
    static int visitedCount = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] line = new int[m][2];
        boolean[] visited = new boolean[n+1];
        visited[0] = true;

        for(int i = 0; i < m; i ++) {
            st = new StringTokenizer(br.readLine());
            line[i][0] = Integer.parseInt(st.nextToken());
            line[i][1] = Integer.parseInt(st.nextToken());
        }

        if(m == 0)
            bw.write(String.valueOf(n));

        else {
            dfs(0, 0, line, visited, m);
            count += n-visitedCount;
            bw.write(String.valueOf(count));
        }
        bw.flush();
        bw.close();
    }

    public static void dfs(int row, int column, int[][] line, boolean[] visited, int m) {
        visited[line[row][column]] = true;
        visitedCount++;

        for(int i = row; i < m; i++) {
            if(visited[line[i][0]] && !visited[line[i][1]])
                dfs(i, 1, line, visited, m);

            else if(!visited[line[i][0]] && visited[line[i][1]])
                dfs(i, 0, line, visited, m);
        }

        for(int i = 0; i < m; i++) {
            if(!visited[line[i][0]] && !visited[line[i][1]]) {
                dfs(i, 0, line, visited, m);
                count++;
            }
        }
    }
}
