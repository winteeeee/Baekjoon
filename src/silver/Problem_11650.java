package silver;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Problem_11650 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n][2];

        for(int i = 0; i < n; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] t0, int[] t1) {
                return t0[1] - t1[1];
            }
        });
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] t0, int[] t1) {
                return t0[0] - t1[0];
            }
        });

        for(int i = 0; i < n; i++) {
            System.out.println(arr[i][0] + " " + arr[i][1]);
        }
    }
}
