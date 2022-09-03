package gold;

import java.io.*;

public class Problem_9251 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String str1 = br.readLine();
        String str2 = br.readLine();
        int[][] LCS = new int[str1.length() + 1][str2.length() + 1];

        int max = 0;
        for(int i = 1; i <= str1.length(); i++) {
            for(int j = 1; j <= str2.length(); j++) {
                if(str1.charAt(i - 1) == str2.charAt(j -1)) {
                    LCS[i][j] = LCS[i - 1][j - 1] + 1;

                    if(LCS[i][j] > max)
                        max = LCS[i][j];
                }

                else
                    LCS[i][j] = Math.max(LCS[i - 1][j], LCS[i][j - 1]);
            }
        }

        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
    }
}
