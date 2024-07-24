package silver;

import java.io.*;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

/*
승려가 속삭일 때 멈추는 것은 자신의 추종자들이다.
각 승려는 단 한 명의 스승을 따른다. 하지만 추종자는 여러 명을 거느릴 수 있으므로
이를 List로 관리해주고 자신의 차례일 때 List에 저장한 추종자들을 순회하여 카운트해주면
풀 수 있다.
 */

public class Problem_12524 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());

            //idx의 추종자들
            ArrayList<Integer>[] followers = new ArrayList[n + 1];
            var st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= n; j++) {
                followers[j] = new ArrayList<>();
            }

            for (int j = 1; j <= n; j++) {
                int teacher = Integer.parseInt(st.nextToken());
                followers[teacher].add(j);
            }

            var sb = new StringBuilder();
            sb.append("Case #").append(i + 1).append(":\n");
            for (int day = 1; day <= n; day++) {
                int count = 0;
                Stack<Integer> s = new Stack<>();
                boolean[] visited = new boolean[n + 1];

                s.push(day);
                visited[day] = true;

                while (!s.empty()) {
                    count++;
                    int cur = s.pop();

                    for (int f : followers[cur]) {
                        if (!visited[f]) {
                            visited[f] = true;
                            s.push(f);
                        }
                    }
                }
                sb.append(count).append('\n');
            }
            bw.write(sb.toString());
        }

        bw.flush();
        bw.close();
    }
}
