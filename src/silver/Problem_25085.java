package silver;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Problem_25085 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        var sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int i = 1; i <= t; i++) {
            long cnt = 0;
            long number = Long.parseLong(br.readLine());

            Set<Long> aliquot = new HashSet<>();
            for (long j = 1; j <= Math.sqrt(number); j++) {
                if (number % j == 0) {
                    aliquot.add(number / j);
                    aliquot.add(j);
                }
            }

            for (long e : aliquot) {
                if (isPalindrome(e)) {
                    cnt++;
                }
            }

            sb.append("Case #").append(i).append(": ").append(cnt).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static boolean isPalindrome(long n) {
        String str = String.valueOf(n);
        int i = 0;
        int j = str.length() - 1 - i;

        while (i < j) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }
}
