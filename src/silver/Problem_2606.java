package silver;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_2606 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int c = Integer.parseInt(br.readLine());
        int[][] arr = new int[c][2];
        boolean[] infected = new boolean[n+1];

        for(int i = 0; i < c; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i <= n; i++) {
            infected[i] = false;
        }
        dfs(1, arr, infected);
        int count = 0;
        for(int i = 0; i <= n; i++) {
            if(i != 1 && infected[i])
                count++;
        }

        bw.write(String.valueOf(count));
        bw.flush();
        bw.close();
    }

    public static void dfs(int i, int[][] arr, boolean[] infected) {
        infected[i] = true;

        for(int j = 0; j < arr.length; j++) {
            if(arr[j][0] == i && !infected[arr[j][1]])
                dfs(arr[j][1], arr, infected);

            if(arr[j][1] == i && !infected[arr[j][0]])
                dfs(arr[j][0], arr, infected);
        }
    }
}
