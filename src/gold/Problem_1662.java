package gold;

import java.io.*;
import java.util.Stack;

public class Problem_1662 {
    static class Element {
        String s;
        int length;

        public Element(String s, int length) {
            this.s = s;
            this.length = length;
        }
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        var stack = new Stack<Element>();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (cur == ')') {
                int length = 0;
                while (!stack.peek().s.equals("("))
                    length += stack.pop().length;
                stack.pop();
                int repeat = stack.pop().s.charAt(0) - '0';
                stack.push(new Element("", length * repeat));
            } else {
                stack.push(new Element(String.valueOf(s.charAt(i)), 1));
            }
        }

        int result = 0;
        while (!stack.isEmpty())
            result += stack.pop().length;

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }
}
