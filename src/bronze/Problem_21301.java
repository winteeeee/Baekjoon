package bronze;

import java.io.*;
import java.util.Arrays;

public class Problem_21301 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        double[] temperatures = Arrays.stream(br.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();

        bw.write(getStandardDeviation(temperatures) <= 1 ? "COMFY" : "NOT COMFY");
        bw.flush();
        bw.close();
    }

    public static double getStandardDeviation(double[] temperatures) {
        double avg = Arrays.stream(temperatures).average().orElse(0);
        double sum = Arrays.stream(temperatures).map(t -> Math.pow(t - avg, 2)).sum();
        return Math.sqrt(sum / (temperatures.length - 1));
    }
}
