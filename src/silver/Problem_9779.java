package silver;

import java.io.*;

public class Problem_9779 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static String[] words;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        input();
        solve();
        output();
    }

    public static void input() {
        words = br.lines().toArray(String[]::new);
    }

    public static void solve() {
        for (String word : words)
            sb.append(word.matches("da+dd?(i|y)") ? "She called me!!!" : "Cooing").append('\n');
    }

    public static void output() throws Exception {
        bw.write(sb.toString());
        bw.flush();
    }
}
