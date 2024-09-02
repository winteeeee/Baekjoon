package bronze;

import java.io.*;

public class Problem_32132 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        String str = br.readLine();

        while (str.contains("PS4") || str.contains("PS5")) {
            str = str.replaceAll("PS4", "PS");
            str = str.replaceAll("PS5", "PS");
        }

        bw.write(str);
        bw.flush();
        bw.close();
    }
}
