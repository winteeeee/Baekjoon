package gold;

import java.io.*;
import java.util.*;

public class Problem_7453 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        double[] a = new double[n]; double[] b = new double[n];
        double[] c = new double[n]; double[] d = new double[n];

        for (int i = 0; i < n; i++) {
            double[] temp = Arrays.stream(br.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
            a[i] = temp[0]; b[i] = temp[1];
            c[i] = temp[2]; d[i] = temp[3];
        }

        double[] ab = getSet(a, b);
        double[] cd = getSet(c, d);

        long res = 0;
        res += getRes(ab, cd);

        bw.write(String.valueOf(res));
        bw.flush();
    }

    public static double[] getSet(double[] a, double[] b) {
        int n = a.length;
        double[] res = new double[a.length * b.length];

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[cnt++] = a[i] + b[j];
            }
        }

        Arrays.sort(res);
        return res;
    }

    public static long getRes(double[] a, double[] b) {
        long res = 0;
        for (double n : a) {
            double target = -n;
            int lowerIdx = -(Arrays.binarySearch(b, target - 0.5) + 1);
            int upperIdx = -(Arrays.binarySearch(b, target + 0.5) + 1);
            if (lowerIdx == b.length || b[lowerIdx] != target) continue;

            res += Math.abs(upperIdx - lowerIdx);
        }

        return res;
    }
}