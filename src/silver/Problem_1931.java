package silver;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Problem_1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        Integer[][] time = new Integer[n][2];

        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            if(end > max)
                max = end;

            time[i][0] = start;
            time[i][1] = end;
        }

        Arrays.sort(time, new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] t0, Integer[] t1) {
                if(t0[1] == t1[1])
                    return Integer.compare(t0[0], t1[0]);

                else
                    return Integer.compare(t0[1], t1[1]);
            }
        });

        boolean[] deployed = new boolean[max+1];
        for(int i = 0; i < max; i++) {
            deployed[i] = false;
        }

        int count = 0;
        FOR1:
        for(int i = 0; i < n; i++) {
            for(int j = time[i][0]; j <= time[i][1]; j++) {
                if(deployed[j] && j - time[i][0] != 0)
                    continue FOR1;
            }

            for(int j = time[i][0]; j <= time[i][1]; j++) {
                deployed[j] = true;
            }
            count++;
        }

        bw.write(String.valueOf(count));
        bw.flush();
        bw.close();
    }
}
