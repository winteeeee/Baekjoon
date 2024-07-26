package silver;

import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

/*
문제의 조건을 살펴보면
거리가 최소가 되는 지점은 현재 위치거나 수선의 발을 내린 곳 둘 중 하나가 될 수밖에 없음
점과 철로의 이동 거리간의 최단 거리는 수선의 발을 내린 곳이지만
수선의 발까지 도달 못하는 경우일 경우 현재 위치가 된다.

문제에서 분명 답이 정수인 입력만 주어진다고 하여 수선의 발의 좌표가 정수인 경우만 테스트케이스로 있는 줄 알았지만
그게 아니였다. 따라서 그에 따른 추가적인 처리가 필요했다. 문제 조건에 대한 서술이 잘못된 듯.

수의 범위가 적어서 그냥 브루트포스로 접근했으면 훨씬 쉬웠을 듯
 */

public class Problem_1393 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var st = new StringTokenizer(br.readLine());
        double xs = Double.parseDouble(st.nextToken());
        double ys = Double.parseDouble(st.nextToken());

        st = new StringTokenizer(br.readLine());
        double xe = Double.parseDouble(st.nextToken());
        double ye = Double.parseDouble(st.nextToken());
        double dx = Double.parseDouble(st.nextToken());
        double dy = Double.parseDouble(st.nextToken());

        double x;
        double y;

        if (approachable(xs, ys, xe, ye, dx, dy)) {
            if (dx == 0) {
                x = xe;
                y = ys;
            } else if (dy == 0) {
                x = xs;
                y = ye;
            } else {
                BigInteger a = BigInteger.valueOf((int)dx);
                BigInteger b = BigInteger.valueOf((int)dy);
                int gcd = a.gcd(b).intValue();

                double distance = Math.sqrt(Math.pow(xe - xs, 2) + Math.pow(ye - ys, 2));
                int count = 0;
                while (true) {
                    count++;
                    double curDistance = Math.sqrt(Math.pow(xs - (xe + (dx / gcd) * count), 2) + Math.pow(ys - (ye + (dy / gcd) * count), 2));

                    if (distance < curDistance) {
                        x = xe + (dx / gcd) * (count - 1);
                        y = ye + (dy / gcd) * (count - 1);
                        break;
                    } else {
                        distance = curDistance;
                    }
                }
            }
        } else {
            x = xe;
            y = ye;
        }

        bw.write((int) x + " " + (int) y);
        bw.flush();
        bw.close();
    }

    public static boolean approachable(double xs, double ys, double xe, double ye, double dx, double dy) {
        if (dx == 0 && dy == 0) {
            return xe == xs && ye == ys;
        } else if (dx == 0) {
            return (ys - ye) / dy >= 0;
        } else if (dy == 0) {
            return (xs - xe) / dx >= 0;
        } else {
            double x = (((dx / dy) * xs + ys) + ((dy / dx) * xe - ye)) / ((dy / dx) + (dx / dy));
            double y = (dy / dx) * x - (dy / dx) * xe + ye;

            return (x - xe) / dx >= 0 && (y - ye) / dy >= 0;
        }
    }
}
