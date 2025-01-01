//자바로는 메모리 초과 로직은 그대로 사용하고 C++로 제출하여 AC
package gold;

import java.io.*;
import java.util.Arrays;

public class Problem_3855 {
    static class House implements Comparable<House> {
        double x;
        double y;

        public House(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getDistance(double x) {
            return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(y, 2));
        }

        @Override
        public int compareTo(House house) {
            int comp1 = Double.compare(x, house.x);
            int comp2 = Double.compare(y, house.y);

            if (comp1 != 0)
                return comp1;
            return comp2;
        }
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        var sb = new StringBuilder();

        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0)
                break;

            House[] houses = new House[n];
            for (int i = 0; i < n; i++) {
                String[] arr = br.readLine().split(" ");
                houses[i] = new House(Float.parseFloat(arr[0]), Float.parseFloat(arr[1]));
            }

            Arrays.sort(houses);
            double left = houses[0].x;
            double right = houses[n - 1].x;
            double p = (left * 2 + right) / 3;
            double q = (left + right * 2) / 3;

            for (int i = 0; i < 100; i++) {
                if (getMaxTime(p, houses) <= getMaxTime(q, houses)) {
                    right = q;
                } else {
                    left = p;
                }

                p = (left * 2 + right) / 3;
                q = (left + right * 2) / 3;
            }

            sb.append(Math.max(left, right)).append(" ").append(getMaxTime(Math.max(left, right), houses)).append('\n');
            br.readLine();
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static double getMaxTime(double x, House[] h) {
        double max = 0;
        for (House house : h)
            max = Math.max(max, house.getDistance(x));

        return max;
    }
}
