package gold;

import java.io.*;

public class Problem_9663 {
    static int result = 0;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {
            boolean[][] queen = new boolean[n][n];
            queen[i][0] = true;
            DFS(i, n, queen, 1);
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }

    public static void DFS(int y, int n, boolean[][] queen, int count) {
        if(count == n) {
            result++;
            return;
        }

        for(int k = 1; y + k < n; k++) {
            for (int i = 0; i < n; i++) {
                if (y < n - 1) {
                    if (isPossible(queen, i, y + k, n)) {
                        queen[y + k][i] = true;
                        DFS(y + k, n, queen, count + 1);
                        break;
                    }

                    else {
                        queen[y + k][i] = false;
                    }
                }
            }
        }
    }

    public static boolean isPossible(boolean[][] queen, int x, int y, int n) {
        for(int i = 1; i <= y; i++) {
            if(y - i>= 0)
                if(queen[y - i][x])
                    return false;

            if(y - i >= 0 && x - i >= 0)
                if(queen[y - i][x - i])
                    return false;

            if(y - i >= 0 && x + i < n)
                if(queen[y - i][x + i])
                  return false;
        }

        return true;
    }
}
