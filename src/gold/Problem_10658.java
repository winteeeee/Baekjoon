package gold;

import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class Problem_10658 {
    static int a;
    static int b;
    static TreeMap<Integer, Boolean> map = new TreeMap<>();
    static TreeSet<Segment> set = new TreeSet<>();

    static class Segment implements Comparable<Segment> {
        int left;
        int right;

        public Segment(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Segment segment) {
            int comp1 = Integer.compare(left, segment.left);
            int comp2 = Integer.compare(right, segment.right);

            if (comp1 != 0)
                return comp1;
            return comp2;
        }
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            boolean isS = st.nextToken().equals("S");
            int idx = Integer.parseInt(st.nextToken());
            map.put(idx, isS);
        }

        int left = a - 1;
        for (int idx : map.keySet()) {
            setAdd(left + 1, Math.min(idx - 1, b - 1));
            setAdd(idx, idx);
            left = set.last().right;
        }
        setAdd(left + 1, b);

        int ans = 0;
        for (Segment segment : set) {
            ans += getPredictionValue(segment);
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }

    public static void setAdd(int left, int right) {
        if (left > right)
            return;

        if (left < a)
            left = a;
        if (right < a)
            right = a;
        if (left > b)
            left = b;
        if (right > b)
            right = b;

        set.add(new Segment(left, right));
    }

    public static int getPredictionValue(Segment segment) {
        if (segment.left == segment.right)
            return predict(segment.left) ? 1 : 0;

        int ans = 0;
        int mid = (segment.right + segment.left) / 2;
        if (map.containsKey(segment.left - 1) && map.containsKey(segment.right + 1)) {
            if ((segment.right - segment.left) % 2 == 0) {
                //구간의 길이가 홀수
                ans += predict(segment.left - 1) ? mid - segment.left : 0;
                ans += predict(segment.left - 1) || predict(segment.right + 1) ? 1 : 0;
                ans += predict(segment.right + 1) ? segment.right - mid : 0;
            } else {
                //구간의 길이가 짝수
                ans += predict(segment.left - 1) ? mid - segment.left + 1 : 0;
                ans += predict(segment.right + 1) ? segment.right - mid : 0;
            }
        } else {
            for (int i = segment.left; i <= segment.right; i++)
                ans += predict(i) ? 1 : 0;
        }

        return ans;
    }

    public static boolean predict(int idx) {
        if (idx < map.firstKey())
            idx = map.firstKey();
        if (idx > map.lastKey())
            idx = map.lastKey();

        if (map.containsKey(idx))
            return map.get(idx);

        int higherKey = map.higherKey(idx);
        int lowerKey = map.lowerKey(idx);
        int higherDiff = Math.abs(idx - higherKey);
        int lowerDiff = Math.abs(idx - lowerKey);

        if (higherDiff > lowerDiff)
            return map.get(lowerKey);
        else if (higherDiff == lowerDiff)
            return map.get(lowerKey) || map.get(higherKey);
        else
            return map.get(higherKey);
    }
}
