package silver;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Problem_9009 {
    static ArrayList<Integer> fibonacci = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        getFibonacci();
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            query(n);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void getFibonacci() {
        fibonacci.add(0);
        fibonacci.add(1);

        while (fibonacci.get(fibonacci.size() - 1) < 1000000000) {
            fibonacci.add(fibonacci.get(fibonacci.size() - 2) + fibonacci.get(fibonacci.size() - 1));
        }
    }

    static void query(int n) {
        var s = new Stack<Integer>();
        var result = new StringBuilder();

        int idx = 0;
        for (int sum = n; sum > 0; sum -= fibonacci.get(idx), s.add(fibonacci.get(idx))) {
            idx = Collections.binarySearch(fibonacci, sum);
            idx = idx > 0 ? idx : -idx - 2;
        }

        while (!s.isEmpty())
            result.append(s.pop()).append(' ');
        sb.append(result).append('\n');
    }
}
