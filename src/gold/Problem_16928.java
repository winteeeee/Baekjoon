package gold;

import java.io.*;
import java.util.*;

public class Problem_16928 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Queue<Integer> q = new LinkedList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[][] board =  new int[10][10];
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] snake = new int[n + m][2];
        for(int i = 0; i < n + m; i++) {
            st = new StringTokenizer(br.readLine());
            snake[i][0] = Integer.parseInt(st.nextToken());
            snake[i][1] = Integer.parseInt(st.nextToken());
        }


    }
}
