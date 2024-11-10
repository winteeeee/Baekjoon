package silver;

import java.io.*;
import java.util.*;

public class Problem_12042 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int 테스트케이스 = Integer.parseInt(br.readLine());
        var sb = new StringBuilder();
        for (int i = 0; i < 테스트케이스; i++) {
            String 단어 = br.readLine();
            int 답 = 1;
            for (int j = 0; j < 단어.length(); j++) {
                var 집합 = new HashSet<Character>();
                for (int k = j - 1; k <= j + 1; k++) {
                    if (k < 0 || k >= 단어.length()) continue;
                    집합.add(단어.charAt(k));
                }

                답 *= 집합.size();
            }

            sb.append("Case #").append(i + 1).append(": ").append(답).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
