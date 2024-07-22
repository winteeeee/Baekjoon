package gold;

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

/*
이 문제에서 중요한 점은 LCS가 아니다.
LCS는 수열의 조건으로서 걸려있는 것이고 LCS라는 단어를 보고 LCS 알고리즘을 그대로 적용시키면 풀 수 없다.
LCS의 알고리즘은 최우선 순위가 사전 순서가 아닌 길이이기 때문에 사전 순서가 나중인 길이가 짧은 CS가 알고리즘 진행 중 밀리게 되어
오답이 발생하게 된다.

LCS에 집중하지 않고 단순히 사전 순서 최대 공통 수열의 특징을 생각해볼 때
생각할 수 있는 것은 두 가지,
1. CS의 조건을 만족할 것
2. 반드시 큰 수가 앞에 올 것
이며 길이는 전혀 중요하지 않다. 조건 2)에 의거하여 숫자를 그리디하게 선택하면 답이 나올 수 있음을 직관적으로 추론할 수 있다.

수의 범위가 100으로 좁기 때문에 입력을 받으며 문제를 푸는데 필요한 정보를 배열로 관리할 수 있다.
결론적으로 각 수열의 원소들이 어떤 인덱스를 지니고 있는지 기록한 뒤
100부터 1까지 탐색하며 수열 A와 수열 B 모두 존재하는 원소의 경우 CS의 조건에 맞도록 결과 배열로 기록한 뒤 출력하여 풀 수 있다.

문제는 LCS지만 풀이법은 LCS가 아니라서 푸는데 시간이 좀 걸렸다.
 */

public class Problem_30805 {
    static class Element {
        PriorityQueue<Integer> aIndexes = new PriorityQueue<>();
        PriorityQueue<Integer> bIndexes = new PriorityQueue<>();
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Element[] eArr = new Element[101];
        for (int i = 0; i < 101; i++) {
            eArr[i] = new Element();
        }

        int n = Integer.parseInt(br.readLine());
        String[] aArr = br.readLine().split(" ");
        int m = Integer.parseInt(br.readLine());
        String[] bArr = br.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            int aInt = Integer.parseInt(aArr[i]);
            eArr[aInt].aIndexes.add(i);
        }
        for (int i = 0; i < m; i++) {
            int bInt = Integer.parseInt(bArr[i]);
            eArr[bInt].bIndexes.add(i);
        }

        ArrayList<Integer> result = new ArrayList<>();
        int aIdxCursor = -1;
        int bIdxCursor = -1;
        for (int i = 100; i > 0; i--) {
            if (!(eArr[i].aIndexes.isEmpty() && eArr[i].bIndexes.isEmpty())) {
                while (!eArr[i].aIndexes.isEmpty() && !eArr[i].bIndexes.isEmpty()) {
                    int aIdx = eArr[i].aIndexes.peek();
                    if (aIdxCursor >= aIdx) {
                        eArr[i].aIndexes.remove();
                    }
                    int bIdx = eArr[i].bIndexes.peek();
                    if (bIdxCursor >= bIdx) {
                        eArr[i].bIndexes.remove();
                    }

                    if (aIdxCursor < aIdx && bIdxCursor < bIdx) {
                        aIdxCursor = aIdx;
                        bIdxCursor = bIdx;
                        result.add(i);
                    }
                }
            }
        }

        var sb = new StringBuilder();
        sb.append(result.size()).append('\n');
        result.forEach(e -> sb.append(e).append(" "));

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}