package gold;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_11577 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        final String INSOMNIA = "Insomnia";

        var st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int onCount = 0;
        int offCount = 0;

        boolean[] bulb = new boolean[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int curBulb = Integer.parseInt(st.nextToken());

            bulb[i] = curBulb == 1;
            onCount = bulb[i] ? onCount + 1 : onCount;
            offCount = bulb[i] ? offCount : offCount + 1;
        }

        int result = 0;
        for (int i = 0; i <= n - k; i++) {
            if (bulb[i]) {
                result++;
                for (int j = 0; j < k; j++) {
                    bulb[i + j] = !bulb[i + j];
                }
            }
        }

        boolean canDeepSleep = true;
        for (int i = n - k; i < n; i++) {
            if (bulb[i]) {
                canDeepSleep = false;
                break;
            }
        }

        bw.write(canDeepSleep ? String.valueOf(result) : INSOMNIA);
        bw.flush();
        bw.close();
    }
}
