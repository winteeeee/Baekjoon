package gold;

import java.io.*;
import java.util.Arrays;

public class Problem_24053 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] a = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] b = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        bw.write(String.valueOf(insertionSort(a, b)));
        bw.flush();
        bw.close();
    }

    public static int insertionSort(int[] a, int[] b) {
        for (int i = 1; i < a.length; i++) {
            if (Arrays.equals(a, b))
                return 1;

            int loc = i - 1;
            int newItem = a[i];

            while (loc >= 0 && newItem < a[loc]) {
                a[loc + 1] = a[loc];
                loc--;

                if (Arrays.equals(a, b))
                    return 1;
            }
            if (loc + 1 != i)
                a[loc + 1] = newItem;
        }

        return Arrays.equals(a, b) ? 1 : 0;
    }
}