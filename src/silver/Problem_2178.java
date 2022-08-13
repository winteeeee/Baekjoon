package silver;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_2178 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Queue<Integer> q = new LinkedList<>();
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] maze = new int[n][m];
        boolean[][] visited = new boolean[n][m];
        for(int i = 0; i < n; i++) {
            String temp = br.readLine();
            for(int j = 0; j < m; j++) {
                maze[i][j] = Integer.parseInt(String.valueOf(temp.charAt(j)));
            }
        }

        q.add(0);
        q.add(0);
        q.add(0);
        visited[0][0] = true;

        int count = 0;
        while(!q.isEmpty()) {
            int column = q.remove();
            int row = q.remove();
            count = q.remove();

            if(column == m-1 && row == n-1)
                break;

            if(column + 1 < m) {
                if(maze[row][column + 1] == 1 && !visited[row][column + 1]) {
                    q.add(column + 1);
                    q.add(row);
                    q.add(count + 1);
                    visited[row][column + 1] = true;
                }
            }

            if(column - 1 >= 0) {
                if(maze[row][column - 1] == 1 && !visited[row][column - 1]) {
                    q.add(column - 1);
                    q.add(row);
                    q.add(count + 1);
                    visited[row][column - 1] = true;
                }
            }

            if(row + 1 < n) {
                if(maze[row + 1][column] == 1 && !visited[row + 1][column]) {
                    q.add(column);
                    q.add(row + 1);
                    q.add(count + 1);
                    visited[row + 1][column] = true;
                }
            }

            if(row - 1 >= 0) {
                if(maze[row - 1][column] == 1 && !visited[row - 1][column]) {
                    q.add(column);
                    q.add(row - 1);
                    q.add(count + 1);
                    visited[row - 1][column] = true;
                }
            }
        }

        bw.write(String.valueOf(count+1));
        bw.flush();
        bw.close();
    }
}
