package gold;

import java.io.*;
import java.util.*;

/*
문제를 잘 관찰해본 결과 오름차순으로 나타낼 수 있는 명확한 알고리즘은 없다고 판단
N의 제한 역시 매우 작은 것을 보아 그래프 탐색이 필요한 것으로 판단하였다.

따라서 결론적으로 이 문제는 배열으로 그래프 탐색 할 경우 어떻게 해야할 것인가? 를 묻는 문제
배열이 원소인 경우 일반적인 visited 배열을 사용하지 못하므로
Set을 통해 방문 여부를 체크하면 된다. 이때 Primitive 타입 배열은
Set이 Deep Equal 체크를 해주지 않으므로(래퍼 타입은 되는지 모르겠음)
커스텀 래퍼 배열 클래스를 하나 만들고 Equals와 HashCode를 오버라이딩해서 구현하였다.
 */

public class Problem_1327 {
    static int[] arr;
    static int[] answer;
    static int n;
    static int k;
    static int result = Integer.MAX_VALUE;
    static class ArrWrap {
        int[] arr;

        ArrWrap(int[] a) {
            arr = a;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ArrWrap arrWrap = (ArrWrap) o;
            return Objects.deepEquals(arr, arrWrap.arr);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(arr);
        }
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new int[n];
        answer = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            answer[i] = i + 1;
        }

        findResult();

        if (result == Integer.MAX_VALUE) {
            bw.write("-1");
        } else {
            bw.write(String.valueOf(result));
        }
        bw.flush();
        bw.close();
    }

    public static void findResult() {
        Queue<int[]> q = new LinkedList<>();
        Queue<Integer> countQ = new LinkedList<>();
        Set<ArrWrap> s = new HashSet<>();

        q.add(arr);
        countQ.add(0);
        s.add(new ArrWrap(arr));

        while (!q.isEmpty()) {
            int[] cur = q.remove();
            int count = countQ.remove();

            if (Arrays.equals(cur, answer)) {
                result = count;
                break;
            }

            for (int i = 0; i <= n - k; i++) {
                int[] newArr = cur.clone();
                arrSwap(newArr, i);

                ArrWrap newArrWrap = new ArrWrap(newArr);
                if (!s.contains(newArrWrap)) {
                    q.add(newArr);
                    countQ.add(count + 1);
                    s.add(newArrWrap);
                }
            }
        }
    }

    public static void arrSwap(int[] arr, int index) {
        Stack<Integer> s = new Stack<>();

        for (int i = index; i < index + k; i++)
            s.add(arr[i]);
        for (int i = index; i < index + k; i++)
            arr[i] = s.pop();
    }
}
