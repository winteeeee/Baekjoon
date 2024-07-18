package silver;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_15067 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        var sb = new StringBuilder();
        String letters = "";
        int number = 0;
        boolean[] isPrimeNumber = new boolean[10000];

        //소수 찾기(에라토스테네스의 체)
        for (int i = 2; i < 10000; i++)
            isPrimeNumber[i] = true;
        for (int i = 2; i < 10000; i++) {
            for (int j = 2; i * j < 10000; j++) {
                isPrimeNumber[i * j] = false;
            }
        }

        while (true) {
            var st = new StringTokenizer(br.readLine());
            letters = st.nextToken();
            number = Integer.parseInt(st.nextToken());

            if (letters.equals("END")) {
                break;
            }

            // 다음 소수 찾기
            boolean findThisLetters = false;
            for (int i = number; i < 10000; i++) {
                if (isPrimeNumber[i]) {
                    number = i;
                    findThisLetters = true;
                    break;
                }
            }

            // letters를 다음 사전순으로 올리고 첫 소수로 배정
            if (!findThisLetters) {
                number = 2;
                letters = findNextLetters(letters);
            }

            sb.append(letters).append(" ").append(intToString(number)).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static String findNextLetters(String l) {
        char fistChar = l.charAt(0);
        char middleChar = l.charAt(1);
        char lastChar = l.charAt(2);

        if (lastChar == 'Z') {
            lastChar = 'A';
            if (middleChar == 'Z') {
                middleChar = 'A';
                return new String(new char[] {(char)(fistChar + 1), middleChar, lastChar});
            } else {
                return new String(new char[] {fistChar, (char)(middleChar + 1), lastChar});
            }
        } else {
            return new String(new char[] {fistChar, middleChar, (char)(lastChar + 1)});
        }
    }

    public static String intToString(int n) {
        String s = String.valueOf(n);

        if (s.length() == 4) {
            return s;
        } else {
            return "0".repeat(4 - s.length()) + s;
        }
    }
}
