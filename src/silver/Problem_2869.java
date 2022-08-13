package silver;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_2869 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        double result = (double)(v-a)/(a-b);

        if(result <= 0)
            result = 1;

        if((v-a) <= 0)
            result = 0;

        bw.write(String.valueOf((int)(Math.ceil(result)+1)));
        bw.flush();
        bw.close();
    }
}
