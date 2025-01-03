package gold;

import java.io.*;
import java.util.*;

public class Problem_12869 {
    public static class Node {
        int[] scvs;
        int count;

        public Node(int[] scvs, int count) {
            this.scvs = scvs;
            this.count = count;
        }

        public boolean isEnd() {
            for (int i = 0; i < scvs.length; i++) {
                if (scvs[i] > 0)
                    return false;
            }

            return true;
        }

        public ArrayList<Node> getNextNodes() {
            ArrayList<Node> list = new ArrayList<>();
            for (int[] value : dv) {
                int[] temp = new int[scvs.length];
                for (int i = 0; i < scvs.length; i++) {
                    temp[i] = scvs[i] + value[i];
                }

                for (int i = 0; i < scvs.length; i++)
                    if (temp[i] < 0)
                        temp[i] = 0;

                list.add(new Node(temp, count + 1));
            }

            return list;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.deepEquals(scvs, node.scvs);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(scvs);
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[][] dv = {
            { -9, -3, -1 },
            { -9, -1, -3 },
            { -3, -9, -1 },
            { -3, -1, -9 },
            { -1, -9, -3 },
            { -1, -3, -9 }
    };

    public static void main(String[] args) throws Exception {
        br.readLine();
        int[] scvs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        bw.write(String.valueOf(getAns(new Node(scvs, 0))));
        bw.flush();
    }

    public static int getAns(Node init) {
        HashSet<Node> visited = new HashSet<>();
        Queue<Node> q = new LinkedList<>();
        q.add(init);
        visited.add(init);

        while (!q.isEmpty()) {
            Node cur = q.remove();
            if (cur.isEnd()) {
                return cur.count;
            }

            for (Node next : cur.getNextNodes()) {
                if (!visited.contains(next)) {
                    q.add(next);
                    visited.add(next);
                }
            }
        }

        return -1;
    }
}
