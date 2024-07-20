package bronze;

import java.io.*;

/*
간단한 사칙연산 문제
첫 번째 답은 그냥 연산한 후 출력하면 되고
두 번째 답은 A와 B를 String으로 형변환하여 concat한 후 다시 정수형으로 바꿔서 계산하면 된다.
 */

public class Problem_31403 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int a = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());
        int c = Integer.parseInt(br.readLine());

        int firstAnswer = a + b - c;
        String aString = String.valueOf(a);
        String bString = String.valueOf(b);
        int secondAnswer = Integer.parseInt(aString + bString) - c;

        bw.write(String.valueOf(firstAnswer) + '\n' + String.valueOf(secondAnswer));
        bw.flush();
        bw.close();
    }
}
