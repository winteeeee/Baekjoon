package silver;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_15652 {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        var br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        arr = new int[m];

        for(int i = 1; i <= n; i++) {
            arr[0] = i;
            findNAndM(i, 1, n, m, bw);
        }
        bw.flush();
        bw.close();
    }

    public static void findNAndM(int start, int idx, int n, int m, BufferedWriter bw) throws IOException {
        if(idx == m) {
            for(int i = 0; i < m; i++) {
                bw.write(arr[i] + " ");
            }
            bw.write("\n");
            return;
        }

        for(int i = start; i <= n; i++) {
            arr[idx] = i;
            findNAndM(i, idx + 1, n, m, bw);
        }
    }
}
