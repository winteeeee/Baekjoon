package silver;

import java.io.*;

public class Problem_14650 {
    static int n, ans;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        backTracking(0);

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }

    public static void backTracking(int idx) {
        if (idx >= n) {
            if (check()) {
                ans++;
            }
            return;
        }

        for (int i = 0; i < 3; i++) {
            //맨 앞자리 수는 0이 될 수 없음
            if (i == 0 && idx == 0) continue;
            arr[idx] = i;
            backTracking(idx + 1);
        }
    }

    public static boolean check() {
        int sum = 0;
        for (int i : arr)
            sum += i;
        return sum % 3 == 0;
    }
}
