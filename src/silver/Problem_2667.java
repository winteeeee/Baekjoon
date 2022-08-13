package silver;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Problem_2667 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[][] house = new int[n][n];
        boolean[][] visited = new boolean[n][n];

        for(int i = 0; i < n; i++) {
            String temp = br.readLine();
            for(int j = 0; j < n; j++)
                house[i][j] = Integer.parseInt(String.valueOf(temp.charAt(j)));
        }

        int sumOfComplex = 0;
        ArrayList<Integer> sumOfHouse = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(house[i][j] == 1 && !visited[i][j]) {
                    sumOfComplex++;
                    visited[i][j] = true;
                    sumOfHouse.add(bfs(i, j, n, house, visited));
                }
            }
        }

        Integer[] SOH = sumOfHouse.toArray(new Integer[0]);
        Arrays.sort(SOH);

        StringBuilder sb = new StringBuilder();
        sb.append(sumOfComplex + "\n");
        for(int i = 0; i < SOH.length; i++)
            sb.append(SOH[i] + "\n");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static int bfs(int i, int j, int n, int[][] house, boolean[][] visited) {
        Queue<Integer> q = new LinkedList<>();
        int count = 1;
        q.add(house[i][j]);
        q.add(i);
        q.add(j);

        while(!q.isEmpty()) {
            q.remove();
            i = q.remove();
            j = q.remove();

            if(i + 1 < n) {
                if(house[i + 1][j] == 1 && !visited[i + 1][j]) {
                    q.add(house[i + 1][j]);
                    q.add(i + 1);
                    q.add(j);
                    visited[i + 1][j] = true;
                    count++;
                }
            }

            if(i - 1 >= 0) {
                if(house[i - 1][j] == 1 && !visited[i - 1][j]) {
                    q.add(house[i - 1][j]);
                    q.add(i - 1);
                    q.add(j);
                    visited[i - 1][j] = true;
                    count++;
                }
            }

            if(j + 1 < n) {
                if(house[i][j + 1] == 1 && !visited[i][j + 1]) {
                    q.add(house[i][j + 1]);
                    q.add(i);
                    q.add(j + 1);
                    visited[i][j + 1] = true;
                    count++;
                }
            }

            if(j - 1 >= 0) {
                if(house[i][j - 1] == 1 && !visited[i][j - 1]) {
                    q.add(house[i][j - 1]);
                    q.add(i);
                    q.add(j - 1);
                    visited[i][j - 1] = true;
                    count++;
                }
            }
        }

        return count;
    }
}
