package silver;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
특정 수의 소수 여부와 행복한 수 여부를 동시에 찾아야 하는 문제
소수 여부는 에라토스테네스의 체를 사용하면 애초에 쉽게 풀리는 문제지만
행복한 수가 문제이다.

문제에 나왔던 수열에 따르면
7 - 49 - 97 - 130 - 10 - 1이기 때문에 7이 행복한 수이다.
이는 7뿐만 아니라 그 뒤에 나오는 49, 97 ... 등의 수에 대해서도 동일하게 적용되므로
소수 판정할 때 사용하는 에라토스테네스의 체의 원리를 그대로 사용할 수 있음을
직관적으로 알 수 있다.

에라토스테네스의 체는 굉장히 빠른 알고리즘인데다가 수의 범위도 10000으로 좁으므로
시간초과없이 충분히 넉넉한 시간안에 통과할 수 있다.
 */

public class Problem_10434 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int p;
    static int caseNumber;
    static int m;
    static StringBuilder sb = new StringBuilder();
    static boolean[] isPrime = new boolean[10001];
    static boolean[] isHappy = new boolean[10001];
    static boolean[] visited = new boolean[10001];

    public static void main(String[] args) throws IOException {
        p = Integer.parseInt(br.readLine());

        for (int i = 2; i < 10001; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i < 10001; i++) {
            if (isPrime[i]) {
                for (int j = 2; i * j < 10001; j++) {
                    isPrime[i * j] = false;
                }
            }
        }

        for (int i = 0; i < 7; i++) {
            visited[i] = true;
        }

        for (int i = 7; i < 10001; i++) {
            if (!visited[i]) {
                int curNumber = i;
                ArrayList<Integer> arr = new ArrayList<>();

                do {
                    int sum = 0;
                    arr.add(curNumber);
                    String curNumberStr = String.valueOf(curNumber);

                    for (int j = 0; j < curNumberStr.length(); j++) {
                        sum += (int) Math.pow(Integer.parseInt(String.valueOf(curNumberStr.charAt(j))), 2);
                    }

                    if (sum != 1 && visited[sum]) {
                        if (isHappy[sum]) {
                            curNumber = 1;
                        }
                        break;
                    }

                    visited[sum] = true;
                    curNumber = sum;
                } while (!(curNumber == i || curNumber == 1));

                if (curNumber == 1) {
                    for (int j = 0; j < arr.size(); j++) {
                        if (arr.get(j) < 10001) {
                            isHappy[arr.get(j)] = true;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < p; i++) {
            var st = new StringTokenizer(br.readLine());
            caseNumber = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            sb.append(caseNumber).append(" ").append(m).append(" ");
            if (isHappy[m] && isPrime[m]) {
                sb.append("YES");
            } else {
                sb.append("NO");
            }
            sb.append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
