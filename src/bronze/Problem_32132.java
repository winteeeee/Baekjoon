package bronze;

import java.io.*;

public class Problem_32132 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        String str = br.readLine();

        String result = String.valueOf(str.charAt(0));
        for (int i = 1; i < n; i++) {
            if (!(result.charAt(result.length() - 1) == 'S' && (str.charAt(i) == '4' || str.charAt(i) == '5'))) {
                result += String.valueOf(str.charAt(i));
            }
        }

        bw.write(result);
        bw.flush();
        bw.close();
    }
}
