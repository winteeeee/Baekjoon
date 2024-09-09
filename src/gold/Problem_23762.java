package gold;

import java.io.*;
import java.util.*;

public class Problem_23762 {
    static class Person implements Comparable<Person> {
        int rank;
        int index;

        Person(int r, int i) {
            rank = r;
            index = i;
        }

        @Override
        public int compareTo(Person o) {
            return Integer.compare(rank, o.rank);
        }
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        Person[] m = new Person[n];

        var st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int rank = Integer.parseInt(st.nextToken());
            m[i] = new Person(rank, i);
        }

        Arrays.sort(m);

        int[] dp = new int[n];
        HashSet<Integer>[] notEntered = new HashSet[n];
        for (int i = 0; i < n; i++) {
            notEntered[i] = new HashSet<>();
        }
        notEntered[0].add(0);
        notEntered[1].add(0); notEntered[1].add(1);
        notEntered[2].add(0); notEntered[2].add(1); notEntered[2].add(2);
        dp[3] = m[3].rank - m[0].rank;

        for (int i = 4; i < n; i++) {
            int a = dp[i - 1];
            int b = dp[i - 4] + m[i].rank - m[i - 3].rank;

            if ((i + 1) % 4 == 0) {
                dp[i] = b;
            } else {
                if (a < b) {
                    dp[i] = dp[i - 1];

                    var curNotEntered = new HashSet<>(notEntered[i - 1]);
                    curNotEntered.add(m[i].index);
                    notEntered[i] = curNotEntered;
                } else {
                    dp[i] = b;
                    var curNotEntered = new HashSet<>(notEntered[i - 4]);
                    notEntered[i] = curNotEntered;
                }
            }
        }

        var sb = new StringBuilder();
        sb.append(dp[n - 1]).append('\n');
        for (int i : notEntered[n - 1])
            sb.append(i).append('\n');

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}