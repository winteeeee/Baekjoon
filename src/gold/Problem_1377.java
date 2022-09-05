package gold;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class Problem_1377 {
    public static void main(String[] args) throws IOException {
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        var br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];

        for(int i = 0; i < n; i++) {
            arr[i][0] = Integer.parseInt(br.readLine());
            arr[i][1] = i;
        }

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });

        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++) {
            if(arr[i][1] - i + 1 > max)
                max = arr[i][1] - i + 1;
        }

        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
    }
}
