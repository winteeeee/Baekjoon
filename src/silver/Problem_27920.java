package silver;

import java.io.*;

public class Problem_27920 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] b = new int[n];
        int[] c = new int[n];

        int number = n;
        int idx = 0;
        for (int i = 0; i < n; i++) {
            number = number == 1 ? n : number - 1;
            idx = i % 2 == 0 ? i / 2 : n - 1 - i / 2;
            c[i] = idx;
            b[idx] = number;
        }

        var sb = new StringBuilder();
        sb.append("YES\n");
        for (int i = 0; i < n; i++)
            sb.append(b[i]).append(" ");
        sb.append('\n');
        for (int i = 0; i < n; i++)
            sb.append(c[i] + 1).append(" ");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
