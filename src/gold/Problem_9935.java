package gold;

import java.io.*;

public class Problem_9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        String str = br.readLine();
        String bomb = br.readLine();

        FOR1:
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            sb.append(c);
            if (c == bomb.charAt(bomb.length() - 1)) {
                for(int j = 1; j < bomb.length(); j++) {
                    if(sb.length() - (1 + j) >= 0) {
                        if (sb.charAt(sb.length() - (1 + j)) != bomb.charAt(bomb.length() - (1 + j))) {
                            continue FOR1;
                        }
                    }

                    else {
                        continue FOR1;
                    }
                }

                sb.setLength(sb.length() - bomb.length());
            }
        }

        if(sb.length() == 0)
            bw.write("FRULA");

        else
            bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}