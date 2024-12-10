package silver;

import java.io.*;
import java.util.*;

public class Problem_30949 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        var names = new TreeSet<String>();
        var map = new TreeMap<String, Integer>();
        var map2 = new TreeMap<String, Integer>();

        while (true) {
            String line = br.readLine();
            if (line.equals("------")) break;
            String[] info = line.split(" ");
            map.put(info[2], map.getOrDefault(info[2], 0) + Integer.parseInt(info[1]) - Integer.parseInt(info[0]));
            names.add(info[2]);
        }

        while (true) {
            String line = br.readLine();
            if (line.equals("======")) break;
            String[] info = line.split(" ");
            map2.put(info[2], map2.getOrDefault(info[2], 0) + Integer.parseInt(info[1]) - Integer.parseInt(info[0]));
            names.add(info[2]);
        }

        var sb = new StringBuilder();
        for (String name : names) {
            int diff = map2.getOrDefault(name, 0) - map.getOrDefault(name, 0);
            if (diff == 0) continue;
            sb.append(name).append(' ').append(diff > 0 ? '+' : "").append(diff).append('\n');
        }

        if (sb.length() == 0)
            sb.append("No differences found.");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
