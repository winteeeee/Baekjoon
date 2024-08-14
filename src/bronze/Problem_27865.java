package bronze;

import java.io.*;
import java.util.Scanner;

/*
처음 풀어본 인터렉티브 문제.
인터렉티브 문제에서는 readLine()과 같은 메소드는 사용하면 안되는듯 하다.
스캐너를 써도 무방한듯 함
 */

public class Problem_27865 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        while (true) {
            System.out.println("? 1");
            System.out.flush();

            String answer = sc.next();

            if (answer.equals("Y")) {
                System.out.println("! 1");
                System.out.flush();

                return;
            }
        }
    }
}
