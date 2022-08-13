package bronze;

import java.util.Scanner;

public class Problem_11050 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int temp1 = 1;
        int temp2 = 1;
        for(int i = 0; i < k; i++) {
            temp1 *= n-i;
            temp2 *= k-i;
        }

        System.out.print(temp1/temp2);
    }
}
