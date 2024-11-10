package gold;

import java.io.*;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

public class Problem_17299 {
    static class Element {
        int number;
        int idx;

        public Element(int number, int idx) {
            this.number = number;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        int[] ngf = new int[n];
        var f = new HashMap<Integer, Integer>();
        var st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
            f.put(a[i], f.getOrDefault(a[i], 0) + 1);
        }

        var s = new Stack<Element>();
        s.push(new Element(a[0], 0));
        for (int i = 1; i < n; i++) {
            int cur_f = f.get(a[i]);
            while (!s.isEmpty() && f.get(s.peek().number) < cur_f) {
                var targetElement = s.pop();
                ngf[targetElement.idx] = a[i];
            }
            s.push(new Element(a[i], i));
        }

        while (!s.isEmpty()) {
            ngf[s.pop().idx] = -1;
        }

        var sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(ngf[i]).append(' ');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
