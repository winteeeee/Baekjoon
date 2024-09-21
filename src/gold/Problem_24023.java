package gold;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Problem_24023 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] a = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            a[i] = Integer.parseInt(st.nextToken());


        String kBinary = Integer.toBinaryString(k);
        var zeroIdxes = new ArrayList<Integer>();
        var oneIdxes = new TreeSet<Integer>();
        for (int i = 0; i < kBinary.length(); i++) {
            if (kBinary.charAt(i) == '0')
                zeroIdxes.add(i);
            else
                oneIdxes.add(i);
        }


        var sb = new StringBuilder();
        Integer prevIdx = null;
        var segmentOneStatus = new TreeSet<Integer>();
        for (int i = 0; i < n; i++) {
            String curBinary = Integer.toBinaryString(a[i]);
            if (curBinary.length() > kBinary.length()) {
                segmentOneStatus.clear();
                prevIdx = null;
                continue;
            }

            curBinary = padding(curBinary, kBinary.length());

            boolean checked = true;
            for (int zeroIdx : zeroIdxes) {
                if (curBinary.charAt(zeroIdx) != '0') {
                    checked = false;
                    break;
                }
            }

            if (checked) {
                for (int j = 0; j < curBinary.length(); j++) {
                    if (curBinary.charAt(j) == '1')
                        segmentOneStatus.add(j);
                }


                prevIdx = prevIdx == null ? i : prevIdx;
                if (segmentOneStatus.equals(oneIdxes)) {
                    sb.append(prevIdx + 1).append(" ").append(i + 1);
                    break;
                }
            } else {
                segmentOneStatus.clear();
                prevIdx = null;
            }
        }

        bw.write(sb.length() == 0 ? "-1" : sb.toString());
        bw.flush();
        bw.close();
    }

    static String padding(String s, int n) {
        var sb = new StringBuilder();
        for (int i = s.length(); i < n; i++)
            sb.append("0");
        return sb.append(s).toString();
    }
}
