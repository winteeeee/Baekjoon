package silver;

import java.util.ArrayList;
import java.util.Scanner;

public class Problem_11866 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        ArrayList<Integer> original = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            original.add(i+1);
        }

        System.out.print("<");
        int count = 0;
        for(int i = 0; !original.isEmpty(); i++) {
            count++;
            if(i == original.size())
                i = 0;

            if(count == k) {
                count = 0;
                if(original.size() == 1)
                    System.out.print(original.get(i));

                else
                    System.out.print(original.get(i) + ", ");
                original.remove(i);
                i--;
            }
        }
        System.out.print(">");
    }
}
