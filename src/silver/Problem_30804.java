package silver;

import java.io.*;
import java.util.StringTokenizer;

/*
문제의 조건을 잘 생각해보면 결국 2과일의 묶음이 가장 긴 것을 찾는 문제가 된다.
N의 제한이 200,000이므로 N^2으로 풀 수 없다.
따라서 슬라이딩 윈도우 + 투 포인터를 활용하여 가장 긴 2과일의 묶음을 찾는다.

포인터를 이동할 때 포인터의 위치에 유의
 */

public class Problem_30804 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        var st = new StringTokenizer(br.readLine());
        int[] fruit = new int[n];
        for (int i = 0; i < n; i++) {
            fruit[i] = Integer.parseInt(st.nextToken());
        }

        int result = 1;
        if (n != 1) {
            int a = 0;
            int b = 1;
            int firstFruit = fruit[a];
            int secondFruit = fruit[b];

            for (; b < n; b++) {
                int curFruit = fruit[b];

                if (firstFruit != curFruit && secondFruit != curFruit) {
                    result = Math.max(result, b - a);
                    for (int i = b - 1; i >= a; i--) {
                        if (fruit[i] != fruit[b - 1]) {
                            a = i + 1;
                            break;
                        }
                    }
                    firstFruit = fruit[a];
                    secondFruit = curFruit;
                }
            }
            result = Math.max(result, b - a);
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }
}