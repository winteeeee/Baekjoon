package gold;

import java.io.*;

/*
체감 난이도가 G4가 아니었던 문제

기본적으로는 주어진 문자열과 목표 문자열을 비교 문자열이 다르다면 해당 문자열을 삭제
반복이 종료되면 현재 문자열의 길이와 문자열의 길이의 차를 결과값에 더하면 되나

앞에 K, KS를 추가하는 것으로 최적이 달라질 수 있고
원래 X와 길이가 같아야 된다는 점 때문에 상당히 어려웠다.
 */

public class Problem_31433 {
    static int targetLength;
    static char[] ksa = {'K', 'S', 'A'};

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String x = br.readLine();
        targetLength = x.length();
        String kX = "K" + x;
        String ksX = "KS" + x;

        int result = Integer.MAX_VALUE;

        result = Math.min(result, findResult(x));
        result = Math.min(result, findResult(kX) + 1);
        result = Math.min(result, findResult(ksX) + 2);

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }

    public static int findResult(String x) {
        int count = 0;
        int ksaIdx = 0;

        for (int i = 0; i < x.length(); i++, ksaIdx++) {
            if (x.charAt(i) != ksa[ksaIdx % 3]) {
                count++;
                ksaIdx--;
            }
        }

        return count + Math.abs(targetLength - (x.length() - count));
    }
}