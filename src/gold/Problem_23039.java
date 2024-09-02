package gold;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_23039 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int twistableEdge = 0;
        int nonTwistableEdge = 0;
        int[] connectedTwistableEdge = new int[6];
        for (int i = 0; i < n; i++) {
            var st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (!isAdjoin(a, b)) {
                twistableEdge++;
                connectedTwistableEdge[a]++;
                connectedTwistableEdge[b]++;
            } else {
                nonTwistableEdge++;
            }
        }

        int result = getResult(twistableEdge, nonTwistableEdge, connectedTwistableEdge);
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }

    static boolean isAdjoin(int a, int b) {
        if ((a == 5 && b == 1) || (a == 1 && b == 5)) {
            return true;
        }

        return Math.abs(a - b) == 1;
    }

    static int getResult(int twistableEdge, int nonTwistableEdge, int[] connectedTwistableEdge) {
        if (twistableEdge == 0 || twistableEdge == 1) {
            return 0;
        } else if (twistableEdge == 2) {
            boolean isZero = false;
            for (int i = 1; i <= 5; i++) {
                if (connectedTwistableEdge[i] == 2) {
                    isZero = true;
                    break;
                }
            }

            if (isZero) {
                return 0;
            } else {
                return 1;
            }
        } else if (twistableEdge == 3 || twistableEdge == 4) {
            return 1;
        } else {
            if (nonTwistableEdge != 5) {
                return 2;
            } else {
                return -1;
            }
        }
    }
}
