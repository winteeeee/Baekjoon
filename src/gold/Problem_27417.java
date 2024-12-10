package gold;

import java.io.*;
import java.util.Scanner;

public class Problem_27417 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("query " + 0);
        System.out.flush();

        long x = sc.nextLong();
        int msb = 17;
        for (; msb >= 0; msb--) {
            long value = 9 * (long)Math.pow(10, msb);
            System.out.println("query " + value);
            System.out.flush();

            long response = sc.nextLong();
            if (x == response) {
                break;
            }
        }

        if (msb < 0)
            msb = 0;

        var sb = new StringBuilder();
        for (; msb >= 0; msb--) {
            int number = binarySearch(sc, x, msb);
            sb.append(number);
        }

        System.out.println("answer " + sb);
        System.out.flush();
    }

    public static int binarySearch(Scanner sc, long x, int number) {
        int start = 0;
        int end = 9;
        int mid = 4;

        while (start <= end) {
            long value = mid * (long) Math.pow(10, number);
            System.out.println("query " + value);
            System.out.flush();

            long response = sc.nextLong();
            if (x >= response) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }

            mid = (start + end) / 2;
        }

        return 9 - mid;
    }
}
