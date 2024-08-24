package bronze;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_32141 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        String result = "";
        for (int i = 0; i < n; i++) {
            h -= arr[i];

            if (h <= 0) {
                result = String.valueOf(i + 1);
                break;
            }
        }

        bw.write(result.isEmpty() ? "-1" : result);
        bw.flush();
        bw.close();
    }
}
