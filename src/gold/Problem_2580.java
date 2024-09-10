package gold;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_2580 {
    static int[][] a = new int[10][10];
    static boolean[][] c = new boolean[10][10];
    static boolean[][] c2 = new boolean[10][10];
    static boolean[][] c3 = new boolean[10][10];
    static int n = 9;
    static int cnt = 0;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < n; i++) {
            var st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
                if (a[i][j] != 0) {
                    c[i][a[i][j]] = true;
                    c2[j][a[i][j]] = true;
                    c3[square(i,j)][a[i][j]] = true;
                }
            }
        }

        go(0);

        System.out.println(cnt);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int square(int x, int y) {
        return (x / 3) * 3 + (y / 3);
    }

    static boolean go(int z) {
        cnt += 1;

        if (cnt >= 10000000) {
            return true;
        }

        if (z == 81) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    sb.append(a[i][j]).append(' ');
                }
                sb.append('\n');
            }
            return true;
        }

        int x = z / n;
        int y = z % n;

        if (a[x][y] != 0) {
            return go(z + 1);
        } else {
            for (int i = 1; i <= 9; i++) {
                if (!c[x][i] && !c2[y][i] && !c3[square(x,y)][i]) {
                    c[x][i] = c2[y][i] = c3[square(x,y)][i] = true;
                    a[x][y] = i;
                    if (go(z + 1)) {
                        return true;
                    }
                    a[x][y] = 0;
                    c[x][i] = c2[y][i] = c3[square(x,y)][i] = false;
                }
            }
        }
        return false;
    }
}
