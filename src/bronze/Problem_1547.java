package bronze;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_1547 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int m = Integer.parseInt(br.readLine());
        int ballPos = 1;
        for (int i = 0; i < m; i++) {
            var st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int curBallPos = ballPos;
            if (x == ballPos) curBallPos = y;
            if (y == ballPos) curBallPos = x;
            ballPos = curBallPos;
        }

        bw.write(String.valueOf(ballPos));
        bw.flush();
        bw.close();
    }
}
