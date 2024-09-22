package gold;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_2186 {
    static int n, m, k, result;
    static int[][][] dp;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};
    static char[][] map;
    static String word;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j);
            }
        }
        word = br.readLine();
        dp = new int[n][m][word.length() + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k <= word.length(); k++) {
                    dp[i][j][k] = -1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == word.charAt(0)) {
                    dfs(i, j, 1);
                }
            }
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }

    static int dfs(int r, int c, int count) {
        dp[r][c][count] = dp[r][c][count] == -1 ? 0 : dp[r][c][count];
        if (count == word.length()) {
            result++;
            return ++dp[r][c][count];
        }

        char nextChar = word.charAt(count);
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < 4; j++) {
                int newR = r + (dr[j] * i);
                int newC = c + (dc[j] * i);

                if ((0 <= newR && newR < n) && (0 <= newC && newC < m)) {
                    if (dp[newR][newC][count + 1] > 0) {
                        result += dp[newR][newC][count + 1];
                        dp[r][c][count] += dp[newR][newC][count + 1];
                    } else if (dp[newR][newC][count + 1] == -1 && map[newR][newC] == nextChar) {
                        dp[r][c][count] += dfs(newR, newC, count + 1);
                    }
                }
            }
        }
        return dp[r][c][count];
    }
}
