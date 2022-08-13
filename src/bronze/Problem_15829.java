package bronze;

import java.util.Scanner;

public class Problem_15829 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long length = sc.nextInt();
        String str = sc.next();

        long hash = 0;
        long mul = 1;
        for(int i = 0; i < length; i++, mul = 1) {
            for(int j = 0; j < i; j++, mul %= 1234567891) {
                mul *= 31;
            }
            hash += ((long)str.charAt(i)-96)*mul;
        }
        hash %= 1234567891;

        System.out.print(hash);
    }
}
