package silver;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_18870 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] original = new int[n];
        int[] coordinate = new int[n];
        int[] compression = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            original[i] = Integer.parseInt(st.nextToken());
            coordinate[i] = original[i];
        }
        Arrays.sort(coordinate);
        if (n != 1) {
            boolean repeat = false;
            int temp = 0;
            for (int i = 0; i < n - 1; i++) {
                if (!repeat && coordinate[i] != coordinate[i + 1]) {
                    if (i >= 1)
                        compression[i] = compression[i - 1] + 1;

                    else
                        compression[i] = 0;
                } else {
                    if (repeat) {
                        compression[i] = temp;
                        if (coordinate[i] != coordinate[i + 1])
                            repeat = false;
                    } else {
                        if (i >= 1) {
                            compression[i] = compression[i - 1] + 1;
                            temp = compression[i - 1] + 1;
                        } else {
                            compression[i] = 0;
                            temp = 0;
                        }
                        repeat = true;
                    }
                }
            }

            if (repeat)
                compression[compression.length - 1] = compression[compression.length - 2];

            else
                compression[compression.length - 1] = compression[compression.length - 2] + 1;

            for (int i = 0; i < n; i++) {
                int result = compression[Arrays.binarySearch(coordinate, original[i])];
                bw.write(result + " ");
            }
        }

        else
            bw.write(String.valueOf(0));

        bw.flush();
        bw.close();
    }
}
