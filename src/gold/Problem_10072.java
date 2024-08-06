package gold;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
곤돌라의 숫자 케이스를 두 가지로 나눠보자

1) 숫자가 주어진 n보다 작거나 같은 경우
초기 곤돌라이므로 다음 원소가 1인 경우, 초기 곤돌라가 아닌 경우를 제외하고
다음 곤돌라는 반드시 현재 곤돌라보다 1 커야한다.

2) 숫자가 주어진 n보다 큰 경우
이 경우 특정한 초기 곤돌라가 고장나 새로운 곤돌라로 교체된 경우이다.
만일 다음 원소로 i가 기대되는 상황에서 이 케이스를 만난 경우 i가 교체된 것으로 간주할 수 있다.

따라서 배열 내 모든 원소를 탐색하며 초기 곤돌라를 찾고 새로운 곤돌라를 초기 곤돌라 번호로 간주하며
곤돌라 수열이 올바른지 올바르지 않은지 판단하면 된다.

주의사항
만일 모든 원소가 2번 케이스인 경우 주어진 수열을 가능하게 하는 교체 수열이 반드시 존재하므로 1을 출력한다.
새로운 곤돌라까지 포함하여 모든 곤돌라는 값이 모두 다르다. 곤돌라의 값이 같은게 있다면 0을 출력한다.
 */

public class Problem_10072 {
    static Set<Integer> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] inputSeq = new int[n];

        var st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            inputSeq[i] = Integer.parseInt(st.nextToken());
            set.add(inputSeq[i]);
        }

        bw.write(String.valueOf(valid(n, inputSeq)));
        bw.flush();
        bw.close();
    }

    public static int valid(int n, int[] inputSeq) {
        if (set.size() != n) {
            return 0;
        }

        boolean isExistInit = false;
        int initIdx = 0;
        for (int i = 0; i < n; i++) {
            if (isReal(n, inputSeq[i])) {
                isExistInit = true;
                initIdx = i;
            }
        }

        if (isExistInit) {
            int[] realVal = new int[n];
            int curRealVal = inputSeq[initIdx];
            for (int i = initIdx; i < n; i++, curRealVal++) {
                curRealVal = curRealVal > n ? 1 : curRealVal;
                realVal[i] = !isReal(n, inputSeq[i]) ? curRealVal : inputSeq[i];
            }

            for (int i = 0; i < initIdx; i++, curRealVal++) {
                curRealVal = curRealVal > n ? 1 : curRealVal;
                realVal[i] = !isReal(n, inputSeq[i]) ? curRealVal : inputSeq[i];
            }

            for (int i = 0; i < n; i++) {
                int nextIdx = i == n - 1 ? 0 : i + 1;
                int cur = realVal[i];
                int next = realVal[nextIdx];

                if (!(cur == n && next == 1) && cur + 1 != next) {
                    return 0;
                }
            }
        } else {
            return 1;
        }

        return 1;
    }

    public static boolean isReal(int length, int number) {
        return 0 < number && number <= length;
    }
}
