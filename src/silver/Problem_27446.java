package silver;

import java.io.*;
import java.util.*;

public class Problem_27446 {
    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        boolean[] isExist = new boolean[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++)
            isExist[Integer.parseInt(st.nextToken())] = true;

        var notExist = new ArrayList<Integer>();
        for (int i = 1; i < isExist.length; i++)
            if (!isExist[i])
                notExist.add(i);

        int ans = 0;
        Collections.sort(notExist);
        for (int i = 0; i < notExist.size(); i++) {
            boolean added = false;
            for (int j = i + 1; j < notExist.size(); j++) {
                if (notExist.get(j) - notExist.get(j - 1) >= 4) {
                    ans += (5 + 2 * (notExist.get(j - 1) - notExist.get(i) + 1));
                    added = true;
                    i = j - 1;
                    break;
                }
            }

            if (!added) {
                ans += (5 + 2 * (notExist.get(notExist.size() - 1) - notExist.get(i) + 1));
                break;
            }
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }
}
