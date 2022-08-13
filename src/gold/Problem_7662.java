package gold;

import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Problem_7662 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());

        for(int i = 0; i < t; i++) {
            TreeMap<Integer, Integer> map = new TreeMap<>();
            int k = Integer.parseInt(br.readLine());
            for(int j = 0; j < k; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int temp;
                if(st.nextToken().equals("I")) {
                    temp = Integer.parseInt(st.nextToken());
                    map.put(temp, map.getOrDefault(temp, 0) + 1);
                }

                else {
                    if(!map.isEmpty()) {
                        temp = Integer.parseInt(st.nextToken());
                        if (temp == 1) {
                            if(map.lastEntry().getValue() == 1)
                                map.remove(map.lastKey());

                            else
                                map.put(map.lastEntry().getKey(), map.lastEntry().getValue() - 1);
                        }
                        else {
                            if (map.firstEntry().getValue() == 1)
                                map.remove(map.firstKey());

                            else
                                map.put(map.firstEntry().getKey(), map.firstEntry().getValue() - 1);
                        }
                    }
                }
            }
            if(map.isEmpty())
                bw.write("EMPTY\n");

            else
                bw.write(String.valueOf(map.lastEntry().getKey()) + " " + String.valueOf(map.firstEntry().getKey()) + "\n");
        }
        bw.flush();
        bw.close();
    }
}
