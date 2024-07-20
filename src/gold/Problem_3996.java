package gold;

import java.io.*;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

/*
0~n까지의 수 중 k진법과 -k진법이 동일하려면
수를 비트로 생각하였을 때 짝수 번째 승수에 해당하는 자릿수만 값을 가져야 한다.
홀수 번째 승수에 해당하는 자릿수에 값이 들어가는 순간 음의 진법에서 값이 빠지게 되므로 절대 양의 진법과 값이 같아질 수 없기 때문이다.
이를 고려하여 수를 세어주면 된다.

이것저것 고려할게 많아 까다로웠던 문제
 */

public class Problem_3996 {
    //10진수 이상의 수가 들어갈 수 있으므로 커스텀 수 클래스 정의
    public static class Number {
        ArrayList<Long> element;

        public Number(ArrayList<Long> e) {
            element = e;
        }
        
        public int length() {
            return element.size();
        }
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        var st = new StringTokenizer(br.readLine());

        long n = Long.parseLong(st.nextToken());
        long k = Long.parseLong(st.nextToken());

        long nLength = findRadixLength(n, k);
        long evenBit = nLength % 2 == 0 ? nLength / 2 : nLength / 2 + 1;
        Number number = findRadixNumber(n, k);
        long result = 0;

        if (!(number.length() % 2 == 0)) {
            //먼저 직전 자릿수까지의 경우의 수를 미리 구한다.
            for (int i = 0; i < evenBit - 1; i++) {
                result += i == 0 ? k : (k - 1) * (long)Math.pow(k, i);
            }

            //현재 자릿수의 경우의 수를 센다.
            for (int i = 0; i < number.length(); i++) {
                long curNumber = Long.parseLong(String.valueOf(number.element.get(i)));
                if (i % 2 != 0 && curNumber != 0) {
                    //만일 홀수 번째자리가 0이 아니라면 해당 비트 오른쪽의 모든 경우의 수를 더하고 반복 종료
                    result += (long) Math.pow(k, ((number.length() - i) / 2));
                    break;
                }

                //MSB(자릿수가 1인 경우 제외)의 경우 0이 허용되지 않으므로 i가 0인 경우면 1을 뺀다.
                long curVal = i == 0 && number.length() != 1 ? curNumber - 1 : curNumber;
                result += (long) (curVal * Math.pow(k, ((number.length() - i) / 2)));

                if (i == number.length() - 1) {
                    //자기 자신의 경우의 수도 더한다.
                    result++;
                }
            }
        } else {
            //만일 MSB가 홀수 번째라면 영향을 주는 짝수 비트의 개수를 이용하여 바로 구하면 된다.
            for (int i = 0; i < evenBit; i++) {
                result += i == 0 ? k : (k - 1) * (long)Math.pow(k, i);
            }
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }

    public static long findRadixLength(long n, long radix) {
        long cnt = 0;
        while (n > 0) {
            n /= radix;
            cnt++;
        }

        return cnt;
    }

    public static Number findRadixNumber(long n, long radix) {
        Stack<Long> s = new Stack<>();
        while (n > 0) {
            s.push(n % radix);
            n /= radix;
        }

        ArrayList<Long> e = new ArrayList<>();
        while (!s.empty()) {
            e.add(s.pop());
        }

        return new Number(e);
    }
}
