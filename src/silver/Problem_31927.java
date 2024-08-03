package silver;

import java.io.*;
import java.util.StringTokenizer;

/*
오랜만에 풀어보는 애드 혹 문제라서 엄청 헤맸던 문제
그리디인가? 우선순위 큐인가? 계속 유형이 헷갈렸었는데 태그를 까보니 애드 혹이었다.

1. 배열의 원소로 주어지는 값보다 더하고 뺄 수 있는 값의 수가 매우 크다.
2. 배열은 내림차순이여야 하므로 앞에 있는 원소는 커야하고 뒤에 있는 원소는 작아야 한다.
3. 원소의 범위는 1 <= 값 <= 5000이다.

1, 2, 3의 조건을 생각해볼 때 인덱스에 대칭이 되도록 x값이 가장 클 때부터 더하고 빼주면 된다.
x의 최댓값이 5000이므로 넉넉히 반복이 진행될 때마다 5100정도의 값이 변화하도록 하면
배열의 길이가 홀수든 짝수든 반드시 정렬할 수 있다.
 */

public class Problem_31927 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        var st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }


        int dv = (int) Math.pow(10, 6);
        for (int i = 0; i < n / 2; i++) {
            arr[i] += dv - (5100 * (i + 1));
            arr[n - i - 1] -= dv - (5100 * (i + 1));
            printArr();
        }

        bw.write(String.valueOf(n / 2) + '\n' + sb.toString());
        bw.flush();
        bw.close();
    }

    public static void printArr() {
        for (int i = 0; i < n; i++) {
            sb.append(arr[i]).append(" ");
        }
        sb.append('\n');
    }
}
