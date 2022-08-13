package silver;

import java.io.*;
import java.util.*;

public class Problem_2805 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        String temp = br.readLine();
        st = new StringTokenizer(temp);
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        temp = br.readLine();
        st = new StringTokenizer(temp);
        int[] tree = new int[n];
        for(int i = 0; i < n; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
        }

        double h = 1;
        double log = 0;
        for(double i = 2; i != 1; log = 0) {
            for(int j = 0; j < n; j++) {
                if(h < tree[j])
                    log += tree[j] - h;
            }
            if(log == m)
                break;

            if(log < m) {
                h /= i;
                i = 1 + ((i - 1) / 2);
            }

            if(log > m)
                h *= i;
        }
        bw.write(String.valueOf((long)h));
        bw.flush();
        bw.close();
    }
}
