package silver;

import java.io.*;

public class Problem_1992 {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        int[][] video = new int[n][n];
        for(int i = 0; i < n; i++) {
            String temp = br.readLine();
            for(int j = 0; j < n; j++) {
                video[i][j] = Integer.parseInt(String.valueOf(temp.charAt(j)));
            }
        }
        quadTree(0, 0, n, video);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void quadTree(int x, int y, int n, int[][] video) {
        boolean recursiveOn = false;
        int temp = video[x][y];

        FOR1:
        for(int i = x; i < x+n; i++) {
            for(int j = y; j < y+n; j++) {
                if(video[i][j] != temp) {
                    recursiveOn = true;
                    break FOR1;
                }
            }
        }

        if(recursiveOn) {
            int newN = n/2;
            sb.append("(");
            quadTree(x, y, newN, video);
            quadTree(x, y+newN, newN, video);
            quadTree(x+newN, y, newN, video);
            quadTree(x+newN, y+newN, newN, video);
            sb.append(")");
        }

        else {
            if(temp == 0)
                sb.append("0");

            else
                sb.append("1");
        }
    }
}
