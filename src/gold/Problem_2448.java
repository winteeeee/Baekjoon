package gold;

import java.io.*;

public class Problem_2448 {
    static char[][] star;
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        star = new char[n][2 * n - 1];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < 2 * n - 1; j++) {
                star[i][j] = ' ';
            }
        }

        makeStar(n, 0, n);
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < 2 * n - 1; j++) {
                bw.write(star[i][j]);
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

    public static void makeStar(int x, int y, int n)  {
        if(n == 3) {
            star[y][x - 1] = '*';
            star[y + 1][x - 2] = '*';
            star[y + 1][x] = '*';
            for (int i = -2; i < 3; i++)
                star[y + 2][x + i - 1] = '*';
            return;
        }

        makeStar(x, y, n / 2);
        makeStar(x - n / 2, y + n / 2, n / 2);
        makeStar(x + n / 2, y + n / 2, n / 2);
    }
}
