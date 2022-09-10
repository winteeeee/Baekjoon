package silver;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_23568 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        int position = 0;
        for(int l = 0; l < n; l++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken();
            int temp;
            if(st.nextToken().charAt(0) == 'R')
                temp = Integer.parseInt(st.nextToken());
            else
                temp = Integer.parseInt(st.nextToken()) * -1;
            position += temp;
        }
        int current = Integer.parseInt(br.readLine());
        position += current;

        bw.write(String.valueOf(position));
        bw.flush();
        bw.close();
    }
}