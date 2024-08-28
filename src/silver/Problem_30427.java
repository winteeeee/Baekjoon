package silver;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Problem_30427 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        br.readLine();
        int n = Integer.parseInt(br.readLine());
        TreeMap<String, Boolean> names = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            String name = br.readLine();
            names.put(name, true);
        }

        int m = Integer.parseInt(br.readLine());
        String[] clues = new String[m];
        for (int i = 0; i < m; i++)
            clues[i] = br.readLine();

        for (String c : clues)
            names.replace(c, false);

        ArrayList<String> candidates = new ArrayList<>();
        for (Map.Entry<String, Boolean> friend : names.entrySet()) {
            if (friend.getValue())
                candidates.add(friend.getKey());
        }

        if (isRule1(names)) {
            bw.write("dongho");
        } else if (isRule2(candidates)) {
            bw.write(candidates.get(0));
        } else if (isRule3(candidates)) {
            bw.write("bumin");
        } else if (isRule4(candidates)) {
            bw.write("cake");
        } else if (isRule5(candidates)) {
            bw.write("lawyer");
        } else {
            Collections.sort(candidates);
            if (!candidates.isEmpty())
                bw.write(candidates.get(0));
            else
                bw.write("swi");
        }

        bw.flush();
        bw.close();
    }

    static boolean isRule1(TreeMap<String, Boolean> names) {
        return names.containsKey("dongho");
    }

    static boolean isRule2(ArrayList<String> c) {
        return c.size() == 1;
    }

    static boolean isRule3(ArrayList<String> c) {
        for (String n : c)
            if (n.equals("bumin"))
                return true;

        return false;
    }

    static boolean isRule4(ArrayList<String> c) {
        for (String n : c)
            if (n.equals("cake"))
                return true;

        return false;
    }

    static boolean isRule5(ArrayList<String> c) {
        for (String n : c)
            if (n.equals("lawyer"))
                return true;

        return false;
    }
}
