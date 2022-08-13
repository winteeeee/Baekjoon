package silver;

import java.io.*;
import java.util.*;

public class Problem_1074 {
    static int count = 0;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        Z(n, 0, 0, r, c);
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }

    public static void Z(int n, int x, int y, int r, int c) {
        if(n == 1) {
            if(x == c && y == r)
                result = count;
            count++;
            if((x+1) == c && y == r)
                result = count;
            count++;
            if(x == c && (y+1) == r)
                result = count;
            count++;
            if((x+1) == c && (y+1) == r)
                result = count;
            count++;
        }

        else {
            if (c <= x + (int) Math.pow(2, n - 1) - 1 && r <= y + (int) Math.pow(2, n - 1) - 1) {
                Z(n - 1, x, y, r, c);
            }
            else if (c <= x + (int) Math.pow(2, n - 1) * 2 - 1 && r <= y + (int) Math.pow(2, n - 1) - 1) {
                count += (int)Math.pow(4, n-1);
                Z(n - 1, x + (int) Math.pow(2, n - 1), y, r, c);
            }
            else if(c <=  x + (int)Math.pow(2, n-1)-1 && r <= y + (int)Math.pow(2, n-1)*2-1) {
                count += (int)Math.pow(4, n-1)*2;
                Z(n - 1, x, y + (int) Math.pow(2, n - 1), r, c);
            }
            else {
                count += (int)Math.pow(4, n-1)*3;
                Z(n - 1, x + (int) Math.pow(2, n - 1), y + (int) Math.pow(2, n - 1), r, c);
            }
        }
    }
}