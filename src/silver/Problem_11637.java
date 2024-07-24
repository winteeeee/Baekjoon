package silver;

import java.io.*;

/*
입력을 받으며 필요한 정보를 저장하고
문제의 조건에 따라 분기문을 이용하여 결과를 출력하면 끝
 */

public class Problem_11637 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            var sb = new StringBuilder();
            int n = Integer.parseInt(br.readLine());

            int[] arr = new int[n];
            int sum = 0;
            int max = 0;
            int maxIdx = 0;
            boolean isNo = false;

            for (int j = 0; j < n; j++) {
                arr[j] = Integer.parseInt(br.readLine());
                sum += arr[j];

                if (arr[j] > max) {
                    max = arr[j];
                    maxIdx = j;
                    isNo = false;
                } else if (arr[j] == max) {
                    isNo = true;
                }
            }

            if (isNo) {
                sb.append("no winner").append('\n');
            } else {
                if (max > (sum / 2)) {
                    sb.append("majority winner ").append(maxIdx + 1).append('\n');
                } else {
                    sb.append("minority winner ").append(maxIdx + 1).append('\n');
                }
            }

            bw.write(sb.toString());
        }

        bw.flush();
        bw.close();
    }
}
