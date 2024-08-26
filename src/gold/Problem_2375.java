package gold;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Problem_2375 {
    static class Village {
        int x;
        int y;
        int p;

        Village(String str) {
            var st = new StringTokenizer(str);

            this.x = Integer.parseInt(st.nextToken());
            this.y = Integer.parseInt(st.nextToken());
            this.p = Integer.parseInt(st.nextToken());
        }
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        Village[] villages = new Village[n];
        for (int i = 0; i < n; i++) {
            villages[i] = new Village(br.readLine());
        }

        int goalX = getGoal(villages, 'x');
        int goalY = getGoal(villages, 'y');

        bw.write(goalX + " " + goalY);
        bw.flush();
        bw.close();
    }

    static int getGoal(Village[] villages, char c) {
        if (c == 'x')
            Arrays.sort(villages, Comparator.comparingInt(o -> o.x));
        else
            Arrays.sort(villages, Comparator.comparingInt(o -> o.y));


        int result = 0;
        long minSum = Long.MAX_VALUE;
        long sum = 0;

        int[] prefixSumOfP = new int[villages.length];
        int minVal = Integer.MAX_VALUE;
        int maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < villages.length; i++) {
            Village v = villages[i];
            prefixSumOfP[i] = i == 0 ? v.p : prefixSumOfP[i - 1] + v.p;

            if (c == 'x') {
                minVal = Math.min(minVal, v.x);
                maxVal = Math.max(maxVal, v.x);
            }
            else {
                minVal = Math.min(minVal, v.y);
                maxVal = Math.max(maxVal, v.y);
            }
        }

        for (int i = 0; i < villages.length; i++) {
            Village v = villages[i];
            if (c == 'x')
                sum += (long) v.p * Math.abs(minVal - 1 - v.x);

            else
                sum += (long) v.p * Math.abs(minVal - 1 - v.y);

        }

        for (int i = minVal; i <= maxVal; i++) {
            int lower_bound = getLowerBound(villages, i, c);
            int upper_bound = getUpperBound(villages, i, c);
            int prefixSumLowerBoundMinusOne = lower_bound == 0 ? 0 : prefixSumOfP[lower_bound - 1];

            sum += prefixSumLowerBoundMinusOne;
            sum -= (prefixSumOfP[upper_bound - 1] - prefixSumLowerBoundMinusOne);
            sum -= (prefixSumOfP[villages.length - 1] - prefixSumOfP[upper_bound - 1]);

            if (minSum > sum) {
                minSum = sum;
                result = i;
            }
        }

        return result;
    }

    static int getLowerBound(Village[] villages, int value, char c) {
        int low = 0;
        int high = villages.length - 1;
        int mid = (low + high) / 2;

        while (low <= high) {
            if (c == 'x') {
                if (villages[mid].x == value) {
                    for (; mid >= 0 && villages[mid].x == value; mid--) {}
                    return mid + 1;
                } else if (villages[mid].x > value) {
                    high = mid - 1;
                    mid = (low + high) / 2;
                } else {
                    low = mid + 1;
                    mid = (low + high) / 2;
                }
            } else {
                if (villages[mid].y == value) {
                    for (; mid >= 0 && villages[mid].y == value; mid--) {}
                    return mid + 1;
                } else if (villages[mid].y > value) {
                    high = mid - 1;
                    mid = (low + high) / 2;
                } else {
                    low = mid + 1;
                    mid = (low + high) / 2;
                }
            }
        }

        return mid + 1;
    }

    static int getUpperBound(Village[] villages, int value, char c) {
        int low = 0;
        int high = villages.length - 1;
        int mid = (low + high) / 2;

        while (low <= high) {
            if (c == 'x') {
                if (villages[mid].x == value) {
                    for (; mid < villages.length && villages[mid].x == value; mid++) {}
                    return mid;
                } else if (villages[mid].x > value) {
                    high = mid - 1;
                    mid = (low + high) / 2;
                } else {
                    low = mid + 1;
                    mid = (low + high) / 2;
                }
            } else {
                if (villages[mid].y == value) {
                    for (; mid < villages.length && villages[mid].y == value; mid++) {}
                    return mid;
                } else if (villages[mid].y > value) {
                    high = mid - 1;
                    mid = (low + high) / 2;
                } else {
                    low = mid + 1;
                    mid = (low + high) / 2;
                }
            }
        }

        return mid + 1;
    }
}
