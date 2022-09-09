package silver;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_15657 {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        var br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] nArr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++)
            nArr[i] = Integer.parseInt(st.nextToken());

        arr = new int[m];
        Arrays.sort(nArr);

        findNAndM(0, 0, n, m, nArr, bw);
        bw.flush();
        bw.close();
    }

    public static void findNAndM(int idx, int start, int n, int m, int[] nArr, BufferedWriter bw) throws IOException {
        if(idx == m) {
            for(int i = 0; i < m; i++) {
                bw.write(arr[i] + " ");
            }
            bw.write("\n");
            return;
        }

        for(int i = start; i < n; i++) {
            arr[idx] = nArr[i];
            findNAndM(idx + 1, i, n, m, nArr, bw);
        }
    }
}