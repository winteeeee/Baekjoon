package silver;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_11659 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        int[] prefixSum = new int[n];
        for(int i = 0; i < n; i++) {
            int temp = Integer.parseInt(st.nextToken());
            if(i >= 1)
                prefixSum[i] += prefixSum[i - 1] + temp;

            else
                prefixSum[i] = temp;
        }

        for(int k = 0; k < m; k++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken()) - 1;
            int j = Integer.parseInt(st.nextToken()) - 1;

            if(i == 0)
                bw.write(String.valueOf(prefixSum[j]));

            else
                bw.write(String.valueOf(prefixSum[j] - prefixSum[i - 1]));
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }
}
