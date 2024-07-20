package bronze;

import java.io.*;

/*
나오는 문자열들의 규칙을 살펴보면 3개의 문자열 중 하나 이상의 정수가 반드시 나옴을 알 수 있다.
따라서 세 개의 문자열 중 숫자를 찾고 다음에 나올 숫자를 알아낸 다음 규칙에 맞게 답을 출력하면 된다.
 */

public class Problem_28702 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String a = br.readLine();
        String b = br.readLine();
        String c = br.readLine();

        bw.write(findNextWord(findNextNumber(a, b, c)));
        bw.flush();
        bw.close();
    }

    public static int findNextNumber(String a, String b, String c) {
        try {
            int aInt = Integer.parseInt(a);
            return aInt + 3;
        } catch (Exception e) {
            try {
                int bInt = Integer.parseInt(b);
                return bInt + 2;
            } catch (Exception e2) {
                return Integer.parseInt(c) + 1;
            }
        }
    }

    public static String findNextWord(int i) {
        if (i % 3 == 0 && i % 5 == 0) {
            return "FizzBuzz";
        } else if (i % 3 == 0) {
            return "Fizz";
        } else if (i % 5 == 0) {
            return "Buzz";
        } else {
            return String.valueOf(i);
        }
    }
}
