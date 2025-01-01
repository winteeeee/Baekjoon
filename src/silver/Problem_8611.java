package silver;

import java.io.*;
import java.math.BigInteger;

public class Problem_8611 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String n = br.readLine();
        var sb = new StringBuilder();
        for (int i = 2; i <= 10; i++) {
            String changedNumber = new BigInteger(n).toString(i);
            if (isPalindrome(changedNumber)) {
                sb.append(String.format("%d %s\n", i, changedNumber));
            }
        }

        bw.write(sb.length() != 0 ? sb.toString() : "NIE");
        bw.flush();
        bw.close();
    }

    public static boolean isPalindrome(String n) {
        for (int i = 0; i < n.length() / 2; i++) {
            if (n.charAt(i) != n.charAt(n.length() - i - 1))
                return false;
        }

        return true;
    }
}
