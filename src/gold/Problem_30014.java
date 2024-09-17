package gold;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_30014 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        var st = new StringTokenizer(br.readLine());
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(p);
        int[] result = new int[n];
        int leftIdx = 0;
        int rightIdx = n - 1;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                result[leftIdx++] = p[i];
            } else {
                result[rightIdx--] = p[i];
            }
        }

        var sb = new StringBuilder();
        sb.append(getValue(result)).append('\n');
        for (int i : result)
            sb.append(i).append(' ');

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int getValue(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            sum += arr[i] * arr[i + 1];
        }
        sum += arr[arr.length - 1] * arr[0];

        return sum;
    }
}
