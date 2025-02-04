package platinum;

import java.io.*;
import java.util.*;

public class Problem_13306 {
    static class Query {
        int order;
        int a;
        int b;

        Query(int o, int a, int b) {
            order = o;
            this.a = a;
            this.b = b;
        }
    }

    static class Node {
        int num;
        Node parent;
        Node temp;

        public Node(int num, Node temp) {
            this.num = num;
            parent = null;
            this.temp = temp;
        }

        public Node getParent() {
            if (parent == this) {
                return this;
            }

            return parent = parent.getParent();
        }
    }
    static Node[] nodes;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        nodes = new Node[n + 1];
        for (int i = 0; i <= n; i++) {
            nodes[i] = new Node(i, null);
        }
        for (int i = 0; i <= n; i++) {
            nodes[i].parent = nodes[i];
        }

        for (int i = 2; i <= n; i++) {
            int p = Integer.parseInt(br.readLine());
            nodes[i].temp = nodes[p];
        }

        ArrayList<Query> list = new ArrayList<>();
        //쿼리 입력
        for (int i = 0; i < (n - 1) + q; i++) {
            st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());

            if (order == 1) {
                //경로 쿼리
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());
                list.add(new Query(order, v1, v2));
            } else {
                //간선 제거
                int v = Integer.parseInt(st.nextToken());
                list.add(new Query(order, v, -1));
            }
        }

        ArrayList<String> result = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            Query cur = list.get(i);
            if (cur.order == 1) {
                if (nodes[cur.a].getParent() == nodes[cur.b].getParent()) {
                    result.add("YES");
                } else {
                    result.add("NO");
                }
            } else {
                nodes[cur.a].parent = nodes[cur.a].temp;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = result.size() - 1; i >= 0; i--) {
            sb.append(result.get(i));
            sb.append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
    }
}
