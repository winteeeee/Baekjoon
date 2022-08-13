package silver;

import java.io.*;
import java.util.*;

public class Problem_9375 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            HashMap<String, String> map = new HashMap<>();

            for(int j = 0; j < n; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                map.put(st.nextToken(), st.nextToken());
            }

            Set<String> values = new HashSet<>(map.values());
            String[] valuesArr = values.toArray(new String[0]);
            int temp = 0;
            int result = 1;
            for(int j = 0; j < valuesArr.length; j++, temp = 0) {
                for (String key : map.keySet()) {
                    if (map.get(key).equals(valuesArr[j]))
                        temp++;
                }
                result *= temp + 1;
            }
            bw.write(String.valueOf(result - 1) + "\n");
        }
        bw.flush();
        bw.close();
    }
}
