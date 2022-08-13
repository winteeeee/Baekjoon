package silver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem_1018 {
    public static boolean[][] base;
    public static boolean[][] x =
            { {true, false, true, false, true, false, true, false}
            , {false, true, false, true, false, true, false, true}
            , {true, false, true, false, true, false, true, false}
            , {false, true, false, true, false, true, false, true}
            , {true, false, true, false, true, false, true, false}
            , {false, true, false, true, false, true, false, true}
            , {true, false, true, false, true, false, true, false}
            , {false, true, false, true, false, true, false, true} };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] size = br.readLine().split(" ");
        int ans = Integer.MAX_VALUE;

        int rowMax = Integer.parseInt(size[0]);
        int culMax = Integer.parseInt(size[1]);
        base = new boolean[rowMax][culMax];

        String line;
        for (int i = 0; i < rowMax; i++) {
            line = br.readLine();
            for (int j = 0; j < culMax; j++) {
                base[i][j] = (line.charAt(j) == 'W');
            }
        }

        for (int i = 0; i < rowMax; i++) {
            for (int j = 0; j < culMax; j++) {
                try {
                    ans = Math.min(ans, check(base[i][j], i, j));
                } catch (Exception e) {
                }
            }
        }

        System.out.print(ans);
    }

    public static int check(boolean first, int row, int cul) throws Exception{
        int ans1 = 0;
        int ans2 = 0;


        if (first) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (base[row + i][cul + j] != x[i][j]) {
                        ans1++;
                    }
                }
            }
        }
        else {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (base[row + i][cul + j] == x[i][j]) {
                        ans1++;
                    }
                }
            }
        }

        if (first) {
            for (int i = 7; i >= 0; i--) {
                for (int j = 7; j >= 0; j--) {
                    if (base[row + i][cul + j] == x[i][j]) {
                        ans2++;
                    }
                }
            }
        }
        else {
            for (int i = 7; i >= 0; i--) {
                for (int j = 7; j >= 0; j--) {
                    if (base[row + i][cul + j] != x[i][j]) {
                        ans2++;
                    }
                }
            }
        }

        return Math.min(ans1, ans2);
    }
}