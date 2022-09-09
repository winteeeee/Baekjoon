package silver;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Problem_15666 {
    static int[] arr;
    static HashSet<String> set = new HashSet<>();
    public static void main(String[] args) throws IOException {
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        var br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] nArr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            nArr[i] = num;
        }

        arr = new int[m];
        Arrays.sort(nArr);

        findNAndM(0, n, m, nArr, bw);
        bw.flush();
        bw.close();
    }

    public static void findNAndM(int idx, int n, int m, int[] nArr, BufferedWriter bw) throws IOException {
        if(idx == m) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < m; i++) {
                sb.append(arr[i] + " ");
            }
            sb.append("\n");
            if(!set.contains(sb.toString())) {
                set.add(sb.toString());
                bw.write(sb.toString());
            }

            return;
        }

        for(int i = 0; i < n; i++) {
            if(idx > 0) {
                if(arr[idx - 1] > nArr[i])
                    continue;
            }

            arr[idx] = nArr[i];
            findNAndM(idx + 1, n, m, nArr, bw);
        }
    }
}