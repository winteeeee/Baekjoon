package silver;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_1780 {
    static int zero = 0;
    static int one = 0;
    static int minus = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][n];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        paper(0, 0, n, arr);
        bw.write(String.valueOf(minus)+"\n"+String.valueOf(zero)+"\n"+String.valueOf(one));
        bw.flush();
        bw.close();
    }

    public static void paper(int x, int y, int n, int[][] arr) {
        boolean recursiveOn = false;

        int temp = arr[x][y];

        FOR1:
        for(int i = x; i < x+n; i++) {
            for(int j = y; j < y+n; j++) {
                if(arr[i][j] != temp) {
                    recursiveOn = true;
                    break FOR1;
                }
            }
        }

        if(recursiveOn) {
            int newN = n/3;
            paper(x, y, newN, arr);
            paper(x, y+newN, newN, arr);
            paper(x, y+newN+newN, newN, arr);
            paper(x+newN, y, newN, arr);
            paper(x+newN, y+newN, newN, arr);
            paper(x+newN, y+newN+newN, newN, arr);
            paper(x+newN+newN, y, newN, arr);
            paper(x+newN+newN, y+newN, newN, arr);
            paper(x+newN+newN, y+newN+newN, newN, arr);
        }

        else {
            if(temp == 0)
                zero++;

            else if(temp == 1)
                one++;

            else
                minus++;
        }
    }
}
