package gold;

import java.io.*;
import java.util.*;

public class Problem_16928 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Queue<Integer> q = new LinkedList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        boolean[] visited = new boolean[110];
        int[][] ladder = new int[n][2];
        int[][] snake = new int[m][2];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            ladder[i][0] = Integer.parseInt(st.nextToken());
            ladder[i][1] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            snake[i][0] = Integer.parseInt(st.nextToken());
            snake[i][1] = Integer.parseInt(st.nextToken());
        }

        q.add(1);
        q.add(0);
        visited[0] = true;

        int count = 0;
        while(!q.isEmpty()) {
            int position = q.remove();
            int tempCount = q.remove();
            if(position >= 100) {
                count = tempCount;
                break;
            }

            FOR1:
            for(int i = 1; i < 7; i++) {
                if(!visited[position + i]) {
                    for (int j = 0; j < ladder.length; j++) {
                        if (position + i == ladder[j][0]) {
                            q.add(ladder[j][1]);
                            q.add(tempCount + 1);
                            visited[position + i] = true;
                            continue FOR1;
                        }
                    }

                    for (int j = 0; j < snake.length; j++) {
                        if (position + i == snake[j][0]) {
                            q.add(snake[j][1]);
                            q.add(tempCount + 1);
                            visited[position + i] = true;
                            continue FOR1;
                        }
                    }

                    q.add(position + i);
                    q.add(tempCount + 1);
                    visited[position + i] = true;
                }
            }
        }

        bw.write(String.valueOf(count));
        bw.flush();
        bw.close();
    }
}
