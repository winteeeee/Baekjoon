package gold;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_20055 {
    static int n, k, zeroCount;
    static int[] a;
    static boolean[] robot;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        a = new int[2 * n];
        robot = new boolean[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int rank = 0;
        zeroCount = 0;
        while (zeroCount < k) {
            rank++;
            rotate();
            move();
            deploy();
        }

        bw.write(String.valueOf(rank));
        bw.flush();
        bw.close();
    }

    static void rotate() {
        //벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
        int a2n = a[2 * n - 1];
        for (int i = 2 * n - 1; i > 0; i--) {
            a[i] = a[i - 1];
            if (i < n && robot[i - 1]) {
                robot[i] = robot[i - 1];
                robot[i - 1] = false;
            }
        }

        //끝에 위치한 로봇은 즉시 내림
        robot[n - 1] = false;
        a[0] = a2n;
    }

    static void move() {
        //가장 먼저 벨트에 올라간 로봇부터 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동
        for (int i = n - 2; i >= 0; i--) {
            if (robot[i] && isMovable(i + 1)) {
                robot[i + 1] = robot[i];
                robot[i] = false;
                //이동으로 인한 내구도 감소
                if (a[i + 1] == 1) zeroCount++;
                a[i + 1] = Math.max(0, a[i + 1] - 1);
            }
        }

        //끝에 위치한 로봇은 즉시 내림
        robot[n - 1] = false;
    }

    static boolean isMovable(int targetIdx) {
        //이동하려는 칸에 로봇이 없으며 그 칸의 내구도가 1 이상 남아 있어야 한다.
        return !robot[targetIdx] && a[targetIdx] > 0;
    }

    static void deploy() {
        //올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
        if (a[0] > 0) {
            robot[0] = true;
            if (a[0] == 1) zeroCount++;
            a[0] = Math.max(0, a[0] - 1);
        }
    }
}
