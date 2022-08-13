package bronze;

import java.util.Arrays;
import java.util.Scanner;

public class Problem_4153 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a, b, c;
        int[] triangle = new int[3];

        while(true) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();

            if(a == 0 && b == 0 && c == 0)
                return;

            triangle[0] = a;
            triangle[1] = b;
            triangle[2] = c;
            Arrays.sort(triangle);

            if(Math.pow(triangle[2], 2) == Math.pow(triangle[1], 2) + Math.pow(triangle[0], 2))
                System.out.println("right");

            else
                System.out.println("wrong");
        }
    }
}
