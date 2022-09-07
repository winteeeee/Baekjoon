package gold;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_13172 {
    static final long MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int m = Integer.parseInt(br.readLine());
        long[][] dice = new long[m + 1][2];
        int result = 0;

        for(int i = 1; i <= m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            dice[i][0] = Long.parseLong(st.nextToken());
            dice[i][1] = Long.parseLong(st.nextToken());
        }

        for(int i = 1; i <= m; i++) {
            if(dice[i][1] % dice[i][0] == 0) {
                result += dice[i][1] / dice[i][0];
            }

            else {

            }
        }
    }

    public static long findModInverse(int a, int b) {
        /*
        (a * result) % MOD == 1을 만족시키는 result를 찾아야 함

         */

        int[][] EEA = new int[3][4];

    }
}
