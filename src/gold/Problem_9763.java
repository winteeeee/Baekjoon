package gold;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_9763 {
    static class Village {
        int x;
        int y;
        int z;

        Village(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public int getDistance(Village v) {
            return Math.abs(x - v.x) + Math.abs(y - v.y) + Math.abs(z - v.z);
        }
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        Village[] villages = new Village[n];
        for (int i = 0; i < n; i++) {
            var st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            villages[i] = new Village(x, y, z);
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int min = Integer.MAX_VALUE;
            int semiMin = Integer.MAX_VALUE;

            for (int j = 0; j < n; j++) {
                if (i != j) {
                    int curDistance = villages[i].getDistance(villages[j]);

                    if (min > curDistance) {
                        semiMin = min;
                        min = curDistance;
                    } else if (semiMin > curDistance) {
                        semiMin = curDistance;
                    }
                }
            }

            result = Math.min(result, min + semiMin);
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }
}
