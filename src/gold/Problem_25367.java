package gold;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_25367 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int q = Integer.parseInt(br.readLine());
        var sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            var st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            sb.append(getAnswer(x, y)).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static long getAnswer(long x, long y) {
        var xBinaryBuilder = new StringBuilder(Long.toBinaryString(x));
        var yBinaryBuilder = new StringBuilder(Long.toBinaryString(y));
        int length = Math.max(xBinaryBuilder.length(), yBinaryBuilder.length());

        while (xBinaryBuilder.length() < length)
            xBinaryBuilder.insert(0, '0');
        while (yBinaryBuilder.length() < length)
            yBinaryBuilder.insert(0, '0');

        String xBinary = xBinaryBuilder.toString();
        String yBinary = yBinaryBuilder.toString();
        boolean carryOccur = false;
        long result = 1;
        for (int i = length - 1; i >= 0; i--) {
            char xChar = xBinary.charAt(i);
            char yChar = yBinary.charAt(i);

            if (xChar == '0' && yChar == '0') {
                if (carryOccur) {
                    result = 0;
                    break;
                }

                if (i != 0) {
                    carryOccur = nextCarryNeed(i - 1, xBinary, yBinary);
                }
            } else if (xChar == '0' && yChar == '1') {
                if (!carryOccur || i == 0) {
                    result = 0;
                    break;
                }

                result *= 2;
            } else if (xChar == '1' && yChar == '0') {
                if (!carryOccur) {
                    result = 0;
                    break;
                }

                if (i != 0) {
                    carryOccur = nextCarryNeed(i - 1, xBinary, yBinary);
                }
            } else {
                if (carryOccur) {
                    result = 0;
                    break;
                }

                result *= 2;
            }
        }

        return result;
    }

    static boolean nextCarryNeed(int idx, String x, String y) {
        char xChar = x.charAt(idx);
        char yChar = y.charAt(idx);

        if (xChar == '0' && yChar == '0') {
            return false;
        } else if (xChar == '0' && yChar == '1') {
            return true;
        } else if (xChar == '1' && yChar == '0') {
            return true;
        } else {
            return false;
        }
    }
}
