package silver;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Problem_17207 {
    static class Friend implements Comparable<Friend> {
        String 이름;
        int 일량;
        int 우선순위;

        Friend(String s, int a, int b) {
            이름 = s;
            일량 = a;
            우선순위 = b;
        }

        @Override
        public int compareTo(Friend o) {
            int 일량비교 = Integer.compare(일량, o.일량);

            if (일량비교 != 0)
                return 일량비교;
            else
                return Integer.compare(우선순위, o.우선순위);
        }
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] map = {"Inseo", "Junsuk", "Jungwoo", "Jinwoo", "Youngki"};
        int[][] a =  new int[5][5];
        int[][] b = new int[5][5];

        for (int i = 0; i < 5; i++) {
            var st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 5; i++) {
            var st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                b[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ArrayList<Friend> result = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int 일량 = 0;

            for (int j = 0; j < 5; j++)
                for (int k = 0; k < 5; k++)
                    일량 += (a[i][k] * b[k][j]);

            result.add(new Friend(map[i], 일량, 5 - i));
        }

        Collections.sort(result);
        bw.write(result.get(0).이름);
        bw.flush();
        bw.close();
    }
}
