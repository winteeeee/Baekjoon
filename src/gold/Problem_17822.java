package gold;

import java.awt.*;
import java.io.*;
import java.util.*;

public class Problem_17822 {
    static class Disk {
        int i;
        Integer[] numbers;

        public Disk(int i, Integer[] numbers) {
            this.i = i;
            this.numbers = numbers;
        }

        public void rotate(int d, int k) {
            Integer[] temp = numbers.clone();

            if (d == 0) {
                //시계 방향, 모든 원소를 오른쪽으로
                for (int i = 0; i < numbers.length; i++) {
                    numbers[(i + k) % numbers.length] = temp[i];
                }
            } else {
                //반시계 방향, 모든 원소를 왼쪽으로
                for (int i = 0; i < numbers.length; i++) {
                    numbers[(i - k % numbers.length + numbers.length) % numbers.length] = temp[i];
                }
            }
        }

        public HashSet<Point> numberDelete(Disk prev, Disk next) {
            var result = new HashSet<Point>();

            for (int i = 0; i < numbers.length; i++) {
                if (numbers[i] == null) continue;
                if (prev != null && Objects.equals(prev.numbers[i], numbers[i])) {
                    result.add(new Point(prev.i, i));
                    result.add(new Point(this.i, i));
                }

                if (next != null && Objects.equals(next.numbers[i], numbers[i])) {
                    result.add(new Point(next.i, i));
                    result.add(new Point(this.i, i));
                }

                int nextIdx = i + 1 < numbers.length ? i + 1 : 0;
                if (Objects.equals(numbers[i], numbers[nextIdx])) {
                    result.add(new Point(this.i, i));
                    result.add(new Point(this.i, nextIdx));
                }
            }

            return result;
        }

        public void numberControl(double avr) {
            numbers = Arrays.stream(numbers)
                    .map(n -> n == null ? null : (n > avr ? n - 1 : (n < avr ? n + 1 : n)))
                    .toArray(Integer[]::new);
        }

        public int sumOfNumbers() {
            return Arrays.stream(numbers).map(n -> n == null ? 0 : n).mapToInt(Integer::intValue).sum();
        }
    }

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        Disk[] disks = new Disk[n + 1];
        for (int i = 1; i <= n; i++) {
            Integer[] numbers = new Integer[m];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                numbers[j] = Integer.parseInt(st.nextToken());
            }

            disks[i] = new Disk(i, numbers);
        }

        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            for (int j = 1; j * x <= n; j++) {
                disks[j * x].rotate(d, k);
            }

            var deletedNumbers = new HashSet<Point>();
            for (int j = 1; j <= n; j++) {
                Disk prev = j - 1 > 0 ? disks[j - 1] : null;
                Disk next = j + 1 <= n ? disks[j + 1] : null;
                var delete = disks[j].numberDelete(prev, next);
                deletedNumbers.addAll(delete);
            }

            if (deletedNumbers.isEmpty()) {
                int sum = Arrays.stream(disks)
                        .skip(1)
                        .mapToInt(disk -> Arrays.stream(disk.numbers)
                                        .mapToInt(number -> number == null ? 0 : number)
                                        .sum())
                        .sum();
                long elementCount = Arrays.stream(disks).skip(1).mapToInt(disk -> disk.numbers.length).sum();
                long nullCount = Arrays.stream(disks)
                        .skip(1)
                        .mapToInt(disk -> Arrays.stream(disk.numbers)
                                .mapToInt(number -> number == null ? 1 : 0)
                                .sum())
                        .sum();

                for (int j = 1; j <= n; j++) {
                    disks[j].numberControl((double) sum / (elementCount - nullCount));
                }
            } else {
                for (Point p : deletedNumbers) {
                    disks[p.x].numbers[p.y] = null;
                }
            }
        }

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans += disks[i].sumOfNumbers();
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }
}
