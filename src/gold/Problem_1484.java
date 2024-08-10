package gold;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/*
c^2 - r^2 = g를 만족하는 자연수 c, r을 찾는 문제.

c^2 - r^2는 (c + r)(c - r)로 변형시킬 수 있고
c + r과 c - r를 각각 a, b라고 할 때 a * b = g를 찾으면 된다.
두 수의 곱이 g가 나와야하므로 a와 b는 g의 약수여야 한다.
a + b = 2c이고 a - b = 2r이므로 a + b / 2, a - b / 2로 c와 r을 바로 구할 수 있다.

따라서 입력으로 주어진 G의 약수를 모두 찾은 후
약수들을 a, b로 취급하고 조건을 만족하는 c와 r이 존재하는지 확인한다.
존재한다면 c를 결과 리스트에 넣고 최종적으로 결과 리스트를 출력하여 풀 수 있다.

주의사항
a + b와 a - b가 짝수가 아니라면 값이 자연수로 떨어지지 않으므로 제외한다. (제외하라고 문제에 명시되어 있음)
r이 0인 경우 자연수가 아니므로 제외한다.
 */

public class Problem_1484 {
    static class Pair {
        int a, b;

        Pair(int a, int b) {
            if (a > b) {
                this.a = a;
                this.b = b;
            } else {
                this.a = b;
                this.b = a;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int g = Integer.parseInt(br.readLine());
        ArrayList<Pair> aliquot = new ArrayList<>();
        for (int i = 1; i <= Math.sqrt(g); i++) {
            int j = g / i;

            if (i * j == g) {
                aliquot.add(new Pair(i , j));
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (Pair p : aliquot) {
            int c = (p.a + p.b) / 2;
            int r = (p.a - p.b) / 2;

            if (r != 0 && Math.pow(c, 2) - Math.pow(r, 2) == g) {
                result.add(c);
            }
        }
        Collections.sort(result);

        if (result.isEmpty()) {
            bw.write("-1");
        } else {
            var sb = new StringBuilder();
            result.forEach((v) -> {
                sb.append(v).append('\n');
            });

            bw.write(sb.toString());
        }

        bw.flush();
        bw.close();
    }
}
