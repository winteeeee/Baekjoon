package gold;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_10421 {
    static int[] s;
    static int[] k;
    static boolean[] isUsable = new boolean[10];
    static int[][] formula;
    static int result;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int sLength = Integer.parseInt(br.readLine());
        s = new int[sLength];
        var st = new StringTokenizer(br.readLine());
        for (int i = 0; i < sLength; i++) {
            s[i] = Integer.parseInt(st.nextToken());
        }

        formula = new int[2][];
        for (int i = 0; i < 2; i++) {
            formula[i] = new int[s[i]];
        }

        int kLength = Integer.parseInt(br.readLine());
        k = new int[kLength];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < kLength; i++) {
            k[i] = Integer.parseInt(st.nextToken());
            isUsable[k[i]] = true;
        }

        backTracking(0);

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }

    static void backTracking(int count) {
        if (count == s[0] + s[1]) {
            if (middleCheck() && sumCheck()) result++;
            return;
        }

        for (int i : k) {
            if (count < s[0]) {
                formula[0][count] = i;
            } else {
                formula[1][count - s[0]] = i;
            }

            backTracking(count + 1);
        }
    }

    static boolean numberCheck(int n) {
        //n이 주어진 숫자로만 이루어져 있는지 확인
        String nStr = String.valueOf(n);
        for (int i = 0; i < 10; i++) {
            if (!isUsable[i] && nStr.contains(String.valueOf(i))) return false;
        }

        return true;
    }

    static int arrayToInt(int[] arr) {
        var sb = new StringBuilder();
        for (int i : arr)
            sb.append(i);

        return Integer.parseInt(sb.toString());
    }

    static boolean middleCheck() {
        //중간 계산 과정 검사
        int s0 = arrayToInt(formula[0]);
        for (int i = 0; i < formula[1].length; i++) {
            int middle = s0 * formula[1][i];
            if (!numberCheck(middle) || String.valueOf(middle).length() != s[2 + i]) return false;
        }

        return true;
    }

    static boolean sumCheck() {
        //합 검사
        int s0 = arrayToInt(formula[0]);
        int s1 = arrayToInt(formula[1]);
        int multi = s0 * s1;

        return numberCheck(multi) && String.valueOf(multi).length() == s[s.length - 1];
    }
}
