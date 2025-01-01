package silver;

import java.io.*;
import java.util.*;

public class Problem_9855 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var sb = new StringBuilder();
        for (int caseCnt = 1; true; caseCnt++) {
            var st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if (n1 == 0 && n2 == 0 && m == 0)
                break;

            var list = new ArrayList<Integer>();
            while (list.size() < m) {
                list.addAll(Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).boxed().toList());
            }

            var t1 = new TreeMap<Integer, Integer>();
            var t2 = new TreeMap<Integer, Integer>();

            for (int a : list) {
                boolean isT1 = true;
                while (a != -1) {
                    if (isT1)
                        a = insertTable(a, n1, t1);
                    else
                        a = insertTable(a, n2, t2);
                    isT1 = !isT1;
                }
            }

            sb.append("Case ").append(caseCnt).append(":\n");
            if (!t1.isEmpty()) {
                sb.append("Table 1\n");
                for (Map.Entry<Integer, Integer> e : t1.entrySet())
                    sb.append(e.getKey()).append(':').append(e.getValue()).append('\n');
            }
            if (!t2.isEmpty()) {
                sb.append("Table 2\n");
                for (Map.Entry<Integer, Integer> e : t2.entrySet()) {
                    sb.append(e.getKey()).append(':').append(e.getValue()).append('\n');
                }
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static int insertTable(int a, int n, TreeMap<Integer, Integer> t) {
        int mod1 = a % n;
        int prevValue = t.getOrDefault(mod1, -1);
        t.put(mod1, a);

        return prevValue;
    }
}
