package gold;

import java.io.*;

public class Problem_30090 {
    static int ans = Integer.MAX_VALUE;
    static int n;
    static String[] viruses;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        viruses = new String[n];
        visited = new boolean[n];
        for (int i = 0; i < n; i++)
            viruses[i] = br.readLine();

        backTracking("", 0);

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }

    public static void backTracking(String vaccine, int cnt) {
        if (cnt == n) {
            ans = Math.min(ans, vaccine.length());
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                String commonPreAndPostfix = getCommonPreAndPostfix(vaccine, viruses[i]);

                visited[i] = true;
                if (!commonPreAndPostfix.isEmpty()) {
                    String newVaccine = vaccine + viruses[i].substring(commonPreAndPostfix.length());
                    backTracking(newVaccine, cnt + 1);
                } else if (vaccine.isEmpty()) {
                    backTracking(viruses[i], cnt + 1);
                }
                visited[i] = false;
            }
        }
    }

    public static String getCommonPreAndPostfix(String a, String b) {
        String ans = "";
        int maxLength = Math.min(a.length(), b.length());
        for (int i = 1; i <= maxLength; i++) {
            String aSubstring = a.substring(a.length() - i);
            String bSubstring = b.substring(0, i);
            if (aSubstring.equals(bSubstring)) {
                ans = aSubstring;
            }
        }

        return ans;
    }
}
