package gold;

import java.io.*;
import java.util.*;

public class Problem_12851 {
    static int LENGTH;
    static int count = 0;
    static int min = 0;
    public static void main(String[] args) throws IOException {
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        var br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        LENGTH = 2 * Math.max(n, k) + 1;
        int[] visited = new int[LENGTH];

        BFS(n, k, visited);
        bw.write(min + "\n" + count);
        bw.flush();
        bw.close();
    }

    public static void BFS(int n, int k, int[] visited) {
        Queue<Integer> q = new LinkedList<>();
        q.add(n);
        visited[n] = 4;
        q.add(0);
        int time;
        int minTime = 0;
        boolean isMin = true;

        while(!q.isEmpty()) {
            int cur = q.remove();
            time = q.remove();
            if(cur == k && isMin) {
                isMin = false;
                minTime = time;
                min = minTime;
            }

            if(cur == k && time == minTime)
                count++;

            if(2 * cur < LENGTH && cur != 0) {
                if(visited[2 * cur] < 4) {
                    q.add(2 * cur);
                    q.add(time + 1);
                    if(2 * cur != k)
                        visited[2 * cur]++;
                }
            }

            if(cur + 1 < LENGTH) {
                if(visited[cur + 1] < 4) {
                    q.add(cur + 1);
                    q.add(time + 1);
                    if(cur + 1 != k)
                        visited[cur + 1]++;
                }
            }

            if(cur - 1 >= 0) {
                if(visited[cur - 1] < 4) {
                    q.add(cur - 1);
                    q.add(time + 1);
                    if(cur - 1 != k)
                        visited[cur - 1]++;
                }
            }
        }
    }
}