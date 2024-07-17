package silver;

import java.io.*;
import java.util.*;

public class Problem_16712 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        var st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<Integer> aValues = new ArrayList<>();
        ArrayList<Integer> vValues = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            aValues.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            vValues.add(Integer.parseInt(st.nextToken()));
        }

        ArrayList<Integer> queue = new ArrayList<>();
        int aIdx = 0;
        while (aIdx < m) {
            queue.add(aValues.get(aIdx++));
        }

        for (int i = 0; i < vValues.size(); i++) {
            Collections.sort(queue);
            queue.set(vValues.get(i) + i - 1, -1);

            if (aIdx < aValues.size()) {
                queue.add(aValues.get(aIdx++));
            }
        }

        var sb = new StringBuilder();
        for (Integer e : queue) {
            if (e != -1) {
                sb.append(e).append(" ");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
