package platinum;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_14003 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] sequence = new int[n + 1];
        long[] dp = new long[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++)
            sequence[i] = Integer.parseInt(st.nextToken());


        int toIndex = 2;
        for(int i = 1; i <= n; i++) {
            if(i == 1)
                dp[i] = sequence[i];

            else {
                int idx = Arrays.binarySearch(dp, 1, toIndex, sequence[i]);
                if (idx < 0) {
                    idx = (idx * -1) - 1;

                    if(idx == toIndex)
                        toIndex++;
                }

                dp[idx] = sequence[i];
            }
        }

        int size = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 1; true; i++, size++) {
            if(dp[i] == 0)
                break;
            sb.append(dp[i] + " ");
        }

        bw.write(size + "\n");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
