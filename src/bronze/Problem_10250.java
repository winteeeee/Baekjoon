package bronze;
import java.util.Scanner;

public class Problem_10250 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int t = sc.nextInt();

        for(int i = 0; i < t; i++) {
            int h = sc.nextInt();
            int w = sc.nextInt();
            int n = sc.nextInt();

            int count = 0;
            for(int j = 1; j <= w; j++) {
                for(int k = 1; k <= h; k++) {
                    count++;

                    if(count == n) {
                        sb.append(k);

                        if(j < 10) {
                            sb.append(0);
                            sb.append(j);
                        }

                        else
                            sb.append(j);
                    }
                }
            }
            System.out.println(sb);
            sb.delete(0, sb.length());
        }
    }
}
