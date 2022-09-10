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
        long[] tracking = new long[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++)
            sequence[i] = Integer.parseInt(st.nextToken());


        if(n != 1) {
            int toIndex = 2;
            int maxIdx = -1;
            for (int i = 1; i <= n; i++) {
                if (i == 1) {
                    dp[i] = sequence[i];
                    tracking[i] = i;
                } else {
                    int idx = Arrays.binarySearch(dp, 1, toIndex, sequence[i]);
                    if (idx < 0) {
                        idx = (idx * -1) - 1;

                        if (idx == toIndex)
                            toIndex++;
                    }

                    dp[idx] = sequence[i];
                    tracking[i] = idx;

                    if (maxIdx < idx)
                        maxIdx = idx;
                }
            }

            long[] result = new long[maxIdx + 1];
            for (int i = n; i >= 1; i--) {
                if (tracking[i] == maxIdx) {
                    result[(int) tracking[i]] = sequence[i];
                    maxIdx--;
                }
            }

            int size = result.length;
            bw.write(size - 1 + "\n");
            for (int i = 1; i < size; i++)
                bw.write(result[i] + " ");
        }

        else {
            bw.write(1 + "\n");
            bw.write(String.valueOf(sequence[1]));
        }
        bw.flush();
        bw.close();
    }
}
