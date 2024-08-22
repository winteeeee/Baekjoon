package gold;

import java.io.*;
import java.util.*;

public class Problem_32133 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        String[] a = new String[n];
        for (int i = 0; i < n; i++)
            a[i] = br.readLine();

        var sb = new StringBuilder();
        boolean 답을찾음 = false;
        for (int i = 0; i < m; i++) {
            Map<String, Integer> map = new HashMap<>();
            for (int j = 0; j < n; j++) {
                String curStr = a[j].substring(0, i + 1);

                if (map.containsKey(curStr))
                    map.put(curStr, map.get(curStr) + 1);
                else
                    map.put(curStr, 1);
            }

            for (Map.Entry<String, Integer> e : map.entrySet()) {
                if (e.getValue() <= k) {
                    String 문자열 = e.getKey();
                    sb.append(i + 1).append('\n').append(지는문자열찾기(문자열));
                    답을찾음 = true;
                    break;
                }
            }

            if (답을찾음) {
                break;
            }
        }

        bw.write(sb.length() == 0 ? "-1" : sb.toString());
        bw.flush();
        bw.close();
    }

    public static String 지는문자열찾기(String 문자열) {
        var 스트링빌더 = new StringBuilder();
        for (int i = 0; i < 문자열.length(); i++) {
            if (문자열.charAt(i) == 'R') {
                스트링빌더.append('S');
            } else if (문자열.charAt(i) == 'S') {
                스트링빌더.append('P');
            } else {
                스트링빌더.append('R');
            }
        }

        return 스트링빌더.toString();
    }
}
