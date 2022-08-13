package silver;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Problem_10814 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[][] member = new String[n][2];

        for(int i = 0; i < n; i++) {
            member[i][0] = sc.next();
            member[i][1] = sc.next();
        }
        Arrays.sort(member, new Comparator<String[]>() {
            @Override
            public int compare(String[] t0, String[] t1) {
                return Integer.parseInt(t0[0]) - Integer.parseInt(t1[0]);
            }
        });

        for(int i = 0; i < n; i++) {
            System.out.println(member[i][0] + " " + member[i][1]);
        }
    }
}
