package silver;

import java.io.*;
import java.util.StringTokenizer;

/*
N의 범위가 매우 작으므로 주어진 수의 순열을 얻은 다음
차이를 구하고 최댓값을 갱신해주면 된다.

최댓값인 8이라도 연산 횟수가 5만을 넘지 않으므로 충분하다.
 */

public class Problem_10819 {
    static int result = 0;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        var st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        result = getValue(arr);
        permutation(arr, 0, 0);

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }

    //순열 구하기
    public static void permutation(int[] arr, int index, int depth) {
        if (depth == arr.length - 1) {
            result = Math.max(result, getValue(arr));
            return;
        }

        //바꾸지 않고 재귀
        permutation(arr, index + 1, depth + 1);
        for (int i = index + 1; i < arr.length; i++) {
            arr[index] ^= arr[i];
            arr[i] ^= arr[index];
            arr[index] ^= arr[i];
            //위치를 바꾸고 재귀
            permutation(arr, index + 1, depth + 1);
            //바꾼 원소 롤백하고 새로운 원소 교체 준비
            arr[index] ^= arr[i];
            arr[i] ^= arr[index];
            arr[index] ^= arr[i];
        }
    }

    public static int getValue(int[] arr) {
        int v = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            v += Math.abs(arr[i] - arr[i + 1]);
        }

        return v;
    }
}
