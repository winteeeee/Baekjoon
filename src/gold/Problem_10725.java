package gold;

import java.awt.*;
import java.io.*;
import java.util.StringTokenizer;

public class Problem_10725 {
    static int n, m, x, y, result, questionCount;
    static char[][] toy;
    static final int MOD = 10007;

    static class Laser {
        Point p;
        char direction;

        public Laser(Point p, char direction) {
            this.p = p;
            this.direction = direction;
        }
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        toy = new char[n][m];

        questionCount = 0;
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < str.length(); j++) {
                toy[i][j] = str.charAt(j);
                if (toy[i][j] == '?') questionCount++;
            }
        }

        if (questionCount > 0) {
            solve(0);
        } else {
            if (test()) {
                result++;
            }
        }
        bw.write(String.valueOf(result % MOD));
        bw.flush();
        bw.close();
    }

    static Point getUp(Point p) {
        return new Point(p.x - 1, p.y);
    }

    static Point getDown(Point p) {
        return new Point(p.x + 1, p.y);
    }

    static Point getRight(Point p) {
        return new Point(p.x, p.y + 1);
    }

    static Point getLeft(Point p) {
        return new Point(p.x, p.y - 1);
    }

    static Laser nextPos(Laser l) {
        if (!(0 <= l.p.x && l.p.x < n && 0 <= l.p.y && l.p.y < m)) {
            return new Laser(null, l.direction);
        }

        if ((toy[l.p.x][l.p.y] == '/' && l.direction == 'l') ||
                (toy[l.p.x][l.p.y] == '\\' && l.direction == 'r') ||
                (toy[l.p.x][l.p.y] == '.' && l.direction == 'd')) {
            //이하 아래의 direction은 다음 칸 기준이므로 반대 방향으로 입력해줌
            //EX) Up으로 들어온 레이저는 현재 블록 기준으로 밑쪽에서 온 것임
            return new Laser(getUp(l.p), 'd');
        } else if ((toy[l.p.x][l.p.y] == '/' && l.direction == 'r') ||
                (toy[l.p.x][l.p.y] == '\\' && l.direction == 'l') ||
                (toy[l.p.x][l.p.y] == '.' && l.direction == 'u')) {
            return new Laser(getDown(l.p), 'u');
        } else if ((toy[l.p.x][l.p.y] == '/' && l.direction == 'd') ||
                (toy[l.p.x][l.p.y] == '\\' && l.direction == 'u') ||
                (toy[l.p.x][l.p.y] == '.' && l.direction == 'l')) {
            return new Laser(getRight(l.p), 'l');
        } else if ((toy[l.p.x][l.p.y] == '/' && l.direction == 'u') ||
                (toy[l.p.x][l.p.y] == '\\' && l.direction == 'd') ||
                (toy[l.p.x][l.p.y] == '.' && l.direction == 'r')) {
            return new Laser(getLeft(l.p), 'r');
        }

        return null;
    }

    static Laser numberToLaser(int num) {
        if (1 <= num && num <= m) {
            return new Laser(new Point(0, num - 1), 'u');
        } else if (m + 1 <= num && num <= m + n) {
            return new Laser(new Point(num - m - 1, 0), 'l');
        } else if (m + n + 1 <= num && num <= m + n + n) {
            return new Laser(new Point(num - m - n - 1, m - 1), 'r');
        } else {
            return new Laser(new Point(n - 1, num - m - n - n - 1), 'd');
        }
    }

    static int LaserToNumber(Laser l) {
        if (l.direction == 'd') {
            return l.p.y + 1;
        } else if (l.direction == 'r') {
            return l.p.x + m + 1;
        } else if (l.direction == 'l') {
            return l.p.x + m + n + 1;
        } else {
            return l.p.y + m + n + n + 1;
        }
    }

    static boolean test() {
        //x로 들어가 y로 나오는지 체크
        var l = numberToLaser(x);
        Laser prev = null;
        for (; l.p != null; l = nextPos(l)) {
            prev = new Laser(new Point(l.p.x, l.p.y), l.direction);
        }

        return y == LaserToNumber(prev);
    }

    static void solve(int count) {
        if (count == questionCount) {
            if (test()) {
                result++;
                result %= MOD;
            }
            return;
        }

        boolean exit = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (toy[i][j] == '?') {
                    toy[i][j] = '/';
                    solve(count + 1);

                    toy[i][j] = '\\';
                    solve(count + 1);

                    toy[i][j] = '.';
                    solve(count + 1);

                    toy[i][j] = '?';
                    exit = true;
                    break;
                }
            }

            if (exit) break;
        }
    }
}
