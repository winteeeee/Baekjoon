package silver;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Problem_14528 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        var st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String[] spotty = new String[n];
        for (int i = 0; i < n; i++) {
            spotty[i] = br.readLine();
        }

        String[] plain = new String[n];
        for (int i = 0; i < n; i++) {
            plain[i] = br.readLine();
        }

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                for (int k = j + 1; k < m; k++) {
                    ArrayList<Integer> spottyCombination = new ArrayList<>();
                    ArrayList<Integer> plainCombination = new ArrayList<>();

                    for (int l = 0; l < n; l++) {
                        int curValue = 100 * getValue(spotty[l].charAt(i)) +
                                10 * getValue(spotty[l].charAt(j)) +
                                getValue(spotty[l].charAt(k));
                        spottyCombination.add(curValue);
                    }

                    for (int l = 0; l < n; l++) {
                        int curValue = 100 * getValue(plain[l].charAt(i)) +
                                10 * getValue(plain[l].charAt(j)) +
                                getValue(plain[l].charAt(k));
                        plainCombination.add(curValue);
                    }

                    if (isPredictable(spottyCombination, plainCombination)) {
                        count++;
                    }
                }
            }
        }

        bw.write(String.valueOf(count));
        bw.flush();
        bw.close();
    }

    public static int getValue(char c) {
        if (c == 'A') {
            return 1;
        } else if (c == 'C') {
            return 2;
        } else if (c == 'G') {
            return 3;
        } else {
            return 4;
        }
    }

    public static boolean isPredictable(ArrayList<Integer> spotty, ArrayList<Integer> plain) {
        for (int s : spotty) {
            for (int p : plain) {
                if (s == p) {
                    return false;
                }
            }
        }

        return true;
    }
}