package silver;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_7568 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int weight, height;
        int[][] human = new int[n][2];
        StringTokenizer st;
        String temp;
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < n; i++) {
            temp = br.readLine();
            st = new StringTokenizer(temp);
            weight = Integer.parseInt(st.nextToken());
            height = Integer.parseInt(st.nextToken());
            human[i][0] = weight;
            human[i][1] = height;
        }

        int count = 0;
        for(int i = 0; i < n; i++, count = 0) {
            for(int j = 0; j < n; j++) {
                if(human[i][0] < human[j][0] && human[i][1] < human[j][1])
                    count++;
            }
            sb.append(count+1 + " ");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
