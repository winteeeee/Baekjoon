package gold;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Problem_1684 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        br.readLine();
        var arr = new ArrayList<Integer>();
        Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).forEach(arr::add);
        int min = arr.stream().mapToInt(Integer::intValue).min().orElse(0);
        arr = arr.stream().map(i -> i - min).collect(Collectors.toCollection(ArrayList::new));

        int ans = arr.get(0);
        for (int i = 1; i < arr.size(); i++) {
            ans = getGcd(ans, arr.get(i));
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }

    public static int getGcd(int a, int b) {
        return BigInteger.valueOf(a).gcd(BigInteger.valueOf(b)).intValue();
    }
}
