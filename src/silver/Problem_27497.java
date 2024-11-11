package silver;

import java.io.*;
import java.util.*;

public class Problem_27497 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        var 덱 = new LinkedList<Character>();
        // 앞에 추가시 true, 뒤에 추가 시 false
        var 스택 = new LinkedList<Boolean>();
        for (int i = 0; i < n; i++) {
            var 정보 = br.readLine().split(" ");

            if (정보[0].equals("1")) {
                덱.addLast(정보[1].charAt(0));
                스택.push(false);
            } else if (정보[0].equals("2")) {
                덱.addFirst(정보[1].charAt(0));
                스택.push(true);
            } else {
                if (스택.isEmpty()) continue;
                if (스택.pop())
                    덱.removeFirst();
                else
                    덱.removeLast();
            }
        }

        var sb = new StringBuilder();
        for (char c : 덱)
            sb.append(c);
        if (sb.length() == 0)
            sb.append('0');

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
