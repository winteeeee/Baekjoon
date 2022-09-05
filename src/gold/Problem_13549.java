package gold;

import java.io.*;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Problem_13549 {
    static final int LENGTH = 200001;
    public static void main(String[] args) throws IOException {
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        var br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] space = new int[LENGTH];
        Arrays.fill(space, Integer.MAX_VALUE);

        bw.write(String.valueOf(BFS(n, k, space)));
        bw.flush();
        bw.close();
    }

    public static int BFS(int n, int k, int[] space) {
        Deque<Integer> dq = new LinkedList<>();
        dq.add(n);
        dq.add(0);
        space[n] = 0;
        int time;

        while(!dq.isEmpty()) {
            int cur = dq.remove();
            time = dq.remove();

            if(2 * cur < LENGTH) {
                if (time < space[2 * cur]) {
                    space[2 * cur] = time;
                    dq.add(2 * cur);
                    dq.add(time);
                }
            }

            if(cur + 1 < LENGTH) {
                if (time < space[cur + 1]) {
                    space[cur + 1] = time + 1;
                    dq.addLast(cur + 1);
                    dq.addLast(time + 1);
                }
            }

            if(cur - 1 >= 0) {
                if (time < space[cur - 1]) {
                    space[cur - 1] = time + 1;
                    dq.addLast(cur - 1);
                    dq.addLast(time + 1);
                }
            }
        }

        time = space[k];
        return time;
    }
}
