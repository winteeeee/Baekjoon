package silver;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_11403 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[][] graph = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
                graph[i][j] = Integer.parseInt(st.nextToken());
        }

        int[][] adMatrix = graph.clone();

        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    if(graph[i][j] == 0 && adMatrix[i][k] + adMatrix[k][j] == 2) //k를 경유해서 갈 수 있다면 j로 갈 수 있는 것이니 1 대입.
                        graph[i][j] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                bw.write(String.valueOf(graph[i][j]) + " ");
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }
}
