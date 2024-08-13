package gold;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
너무 고생했던 문제 문제에 함정이 많았다. 솔직히 G3에서 G2~G1 정도로 올려도 된다고 생각한다.
설계도를 수정할 수 없는 경우가 없으며 서브 박스는 새로 추가하지 않아도 된다.
M의 최솟값이 1이므로 모든 박스를 주어진 조건에 맞게 일렬로 쌓으면 어떠한 경우에도 박스 추가 없이
마트료시카 박스를 구성할 수 있다.

주어진 조건에 맞게 일렬로 쌓기 위해 BFS를 수행하여 수행 순서를 기록해두고
해당 수행 순서에 따라 배치해주면 된다.
문제를 잘 관찰해보면 자신과 같은 레벨에 있는 박스들은 어떻게 옮겨도 상관없으나 다른 레벨 간의 이동은
포함 관계가 틀어질 수 있으므로 DFS는 불가능하고 BFS를 이용해야한다.

직접적으로 트리라는 언급이 등장하지 않아도 문제의 환경을 잘살펴 트리로 구성시킬 수 있음에 유념하자
 */

public class Problem_25586 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        LinkedList<Integer>[] subBoxes = new LinkedList[n + 1];
        boolean[] check = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            subBoxes[i] = new LinkedList<>();
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine()); st.nextToken();
            while (st.hasMoreTokens()) {
                int temp = Integer.parseInt(st.nextToken());
                subBoxes[i].add(temp);
                check[temp] = true;
            }
        }

        int root = 0;
        for (int i = 1; i <= n; i++) {
            if (!check[i]) {
                root = i;
                break;
            }
        }

        LinkedList<Integer> visitedOrder = new LinkedList<>();
        Queue<Integer> q = new LinkedList<>();
        q.add(root);
        visitedOrder.add(root);

        while (!q.isEmpty()) {
            int cur = q.remove();

            for (int next : subBoxes[cur]) {
                q.add(next);
                visitedOrder.add(next);
            }
        }

        var sb = new StringBuilder();
        sb.append("1").append('\n');
        sb.append("0").append('\n');

        for (int i = 0; i < visitedOrder.size(); i++) {
            int idx = 0;
            for (int j = 0; j < visitedOrder.size(); j++) {
                if (visitedOrder.get(j) == i + 1) {
                    idx = j;
                    break;
                }
            }

            if (idx == visitedOrder.size() - 1) {
                sb.append("0").append('\n');
            } else {
                sb.append("1").append(" ").append(visitedOrder.get(idx + 1)).append('\n');
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
