package silver;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_28733 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int cnt = 0;
        while (a != b) {
            int gm = getGeometricalMean(a, b);
            int am = getArithmeticMean(a, b);

            int aGmDiff = Math.abs(gm - b);
            int bGmDiff = Math.abs(gm - a);
            int aAmDiff = Math.abs(am - b);
            int bAmDiff = Math.abs(am - a);

            if (aGmDiff <= bGmDiff && aGmDiff <= aAmDiff && aGmDiff <= bAmDiff) {
                a = gm;
            } else if (bGmDiff <= aGmDiff && bGmDiff <= aAmDiff && bGmDiff <= bAmDiff) {
                b = gm;
            } else if (aAmDiff <= aGmDiff && aAmDiff <= bGmDiff && aAmDiff <= bAmDiff) {
                a = am;
            } else {
                b = am;
            }

            cnt++;
        }

        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
    }

    public static int getGeometricalMean(int a, int b) {
        return (int) Math.ceil(Math.sqrt(a * b));
    }

    public static int getArithmeticMean(int a, int b) {
        return (int) Math.floor(Math.sqrt((double) (a * a + b * b) / 2));
    }
}
