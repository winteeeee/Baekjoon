package silver;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_30412 {
    static int n, x;
    static long 시행횟수 = Integer.MAX_VALUE;
    static int[] a;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        a = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            a[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++)
            시행횟수_구하기(i);

        bw.write(String.valueOf(시행횟수));
        bw.flush();
        bw.close();
    }

    static void 시행횟수_구하기(int 인덱스) {
        long 최솟값 = Integer.MAX_VALUE;

        if (인덱스 == 0) {
            최솟값 = Math.max(a[인덱스], a[인덱스 + 1]) - Math.min(a[인덱스], a[인덱스 + 1]);
            최솟값 = 최솟값 >= x ? 0 : x - 최솟값;
        } else if (인덱스 == n - 1) {
            최솟값 = Math.max(a[인덱스 - 1], a[인덱스]) - Math.min(a[인덱스 - 1], a[인덱스]);
            최솟값 = 최솟값 >= x ? 0 : x - 최솟값;
        } else {
            long _ㅡ_인경우차이 = a[인덱스] - Math.max(a[인덱스 - 1], a[인덱스 + 1]);
            최솟값 = Math.min(최솟값, _ㅡ_인경우차이 >= x ? 0 : x - _ㅡ_인경우차이);

            long ㅡ_ㅡ인경우이전차이 = a[인덱스 - 1] - a[인덱스];
            long ㅡ_ㅡ인경우다음차이 = a[인덱스 + 1] - a[인덱스];
            long ㅡ_ㅡ인경우이전높이 = ㅡ_ㅡ인경우이전차이 >= x ? a[인덱스 - 1] : a[인덱스 - 1] + x - ㅡ_ㅡ인경우이전차이;
            long ㅡ_ㅡ인경우다음높이 = ㅡ_ㅡ인경우다음차이 >= x ? a[인덱스 + 1] : a[인덱스 + 1] + x - ㅡ_ㅡ인경우다음차이;
            최솟값 = Math.min(최솟값, ㅡ_ㅡ인경우이전높이 - a[인덱스 - 1] + ㅡ_ㅡ인경우다음높이 - a[인덱스 + 1]);

            long 현재이전차이 = a[인덱스] - a[인덱스 - 1];
            long 증가할경우현재높이 = 현재이전차이 >= x ? a[인덱스] : a[인덱스] + x - 현재이전차이;
            long 다음현재차이 = a[인덱스 + 1] - 증가할경우현재높이;
            long 증가할경우다음높이 = 다음현재차이 >= x ? a[인덱스 + 1] : a[인덱스 + 1] + x - 다음현재차이;
            최솟값 = Math.min(최솟값, 증가할경우현재높이 - a[인덱스] + 증가할경우다음높이 - a[인덱스 + 1]);

            long 현재다음차이 = a[인덱스] - a[인덱스 + 1];
            long 감소할경우현재높이 = 현재다음차이 >= x ? a[인덱스] : a[인덱스] + x - 현재다음차이;
            long 이전현재차이 = a[인덱스 - 1] - 감소할경우현재높이;
            long 감소할경우이전높이 = 이전현재차이 >= x ? a[인덱스 - 1] : a[인덱스 - 1] + x - 이전현재차이;
            최솟값 = Math.min(최솟값, 감소할경우이전높이 - a[인덱스 - 1] + 감소할경우현재높이 - a[인덱스]);
        }

        시행횟수 = Math.min(시행횟수, 최솟값);
    }
}
