package gold;

import java.io.*;
import java.util.Arrays;

public class Problem_14740 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        long[] a = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        long[] d = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        long[] updatedEdge = new long[n];
        for (int i = 0; i < n; i++)
            updatedEdge[i] = a[i] - d[i];

        long minVal = Integer.MAX_VALUE;
        long[] prefixSum = new long[n]; prefixSum[0] = updatedEdge[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + updatedEdge[i];
            minVal = Math.min(minVal, prefixSum[i]);
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            if (minVal == prefixSum[i]) {
                result++;
            }
        }
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }
}