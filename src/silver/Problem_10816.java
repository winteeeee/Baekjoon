package silver;

import java.io.*;
import java.util.HashMap;

public class Problem_10816 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        HashMap<Integer, Integer> nMap = new HashMap<>();

        String temp = br.readLine();
        String[] arr = temp.split(" ");
        for (int i = 0; i < n; i++) {
            int temp2 = Integer.parseInt(arr[i]);
            if (nMap.containsKey(temp2))
                nMap.put(temp2, nMap.get(temp2) + 1);

            else
                nMap.put(temp2, 1);
        }

        int m = Integer.parseInt(br.readLine());
        int[] mArr = new int[m];

        String mTemp = br.readLine();
        String[] mArr2 = mTemp.split(" ");
        for (int i = 0; i < m; i++) {
            mArr[i] = Integer.parseInt(mArr2[i]);
        }

        for (int i = 0; i < m; i++) {
            try {
                nMap.get(mArr[i]).equals(" ");
                sb.append(nMap.get(mArr[i]) + " ");
            } catch(NullPointerException e) {
                sb.append(0 + " ");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
