package gold;

import java.io.*;
import java.util.*;

public class Problem_1865 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int TC = Integer.parseInt(br.readLine());

        for(int i = 0; i < TC; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            ArrayList<Integer[]>[] edges = new ArrayList[N + 1];
            for(int j = 0; j <= N; j++) {
                edges[j] = new ArrayList<>();
            }

            for(int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                Integer[] temp = new Integer[2];
                Integer[] temp2 = new Integer[2];
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                temp[0] = b; temp[1] = c;
                temp2[0] = a; temp2[1] = c;
                edges[a].add(temp);
                edges[b].add(temp2);
            }

            boolean[] existW = new boolean[N + 1];
            for(int j = 0; j < W; j++) {
                st = new StringTokenizer(br.readLine());
                Integer[] temp = new Integer[2];
                int idx = Integer.parseInt(st.nextToken());
                temp[0] = Integer.parseInt(st.nextToken());
                temp[1] = Integer.parseInt(st.nextToken()) * -1;
                edges[idx].add(temp);
                existW[temp[0]] = true;
            }

            boolean isYes = false;
            for(int k = 1; k <= N; k++) {
                long[] graph = new long[N + 1];
                for (int j = 1; j <= N; j++)
                    graph[j] = Integer.MAX_VALUE;
                graph[k] = 0;
                if(!existW[k])
                    continue;

                for(int j = 1; j < N; j++) {
                    for(int curIdx = 1; curIdx < edges.length; curIdx++) {
                        ArrayList<Integer[]> cur = edges[curIdx];
                        for(int o = 0; o < cur.size(); o++) {
                            int nextIdx = cur.get(o)[0];
                            int nextDistance = cur.get(o)[1];
                            if(graph[nextIdx] > graph[curIdx] + nextDistance) {
                                graph[nextIdx] = graph[curIdx] + nextDistance;
                            }
                        }
                    }
                }

                if(graph[k] < 0) {
                    isYes = true;
                    break;
                }
            }

            if(isYes)
                bw.write("YES\n");

            else
                bw.write("NO\n");
        }
        bw.flush();
        bw.close();
    }
}