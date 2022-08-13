package silver;

import java.io.*;

public class Problem_5525 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int length = Integer.parseInt(br.readLine());
        String s = br.readLine();
        int repeatCount = 0;
        int count = 0;

        for (int i = 0; i < length - 2; i++) {
            if (s.charAt(i) == 'I' && s.charAt(i + 1) == 'O' && s.charAt(i + 2) == 'I')
                repeatCount++;

            else if (!(s.charAt(i) == 'O' && s.charAt(i + 1) == 'I' && s.charAt(i + 2) == 'O')) {
                if (n == 1)
                    count += repeatCount / n;

                else if (repeatCount > n)
                    count += repeatCount - (n - 1);

                else if(repeatCount == n)
                    count++;

                repeatCount = 0;
            }
        }

        if(repeatCount != 0) {
            if (n == 1)
                count += repeatCount / n;

            else if (repeatCount > n)
                count += repeatCount - (n - 1);

            else if(repeatCount == n)
                count++;
        }

        bw.write(String.valueOf(count));
        bw.flush();
        bw.close();
    }
}