package bronze;

import java.io.*;
import java.util.*;

public class Problem_25591 {
    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var st = new StringTokenizer(br.readLine());
        int i1 = Integer.parseInt(st.nextToken());
        int i2 = Integer.parseInt(st.nextToken());

        int a = 100 - i1;
        int b = 100 - i2;
        int c = 100 - (a + b);
        int d = a * b;
        int q = d / 100;
        int r = d % 100;
        int 앞두자릿수 = String.valueOf(d).length() > 1 ? c + q : c;
        int 뒷두자릿수 = String.valueOf(d).length() > 1 ? r : d;

        bw.write(a + " " + b + " " + c + " " + d + " " + q + " " + r + '\n');
        bw.write(앞두자릿수 + " " + 뒷두자릿수);
        bw.flush();
        bw.close();
    }
}
