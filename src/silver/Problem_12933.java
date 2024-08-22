package silver;

import java.io.*;

public class Problem_12933 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String sound = br.readLine();
        boolean[] deleted = new boolean[sound.length()];
        int deletedCount = 0;
        int quackingDuck = 0;
        int maxQuackingDuck = 0;
        char[] map = {'k', 'c', 'a', 'u', 'q'};

        for (int i = 0; i < sound.length(); i++) {
            char curChar = sound.charAt(i);

            if (curChar == 'q')
                maxQuackingDuck = Math.max(++quackingDuck, maxQuackingDuck);
            if (curChar == 'k') {
                int mapIdx = 0;

                for (int j = i; j >= 0; j--) {
                    char reverseTraceChar = sound.charAt(j);

                    if (!deleted[j] && reverseTraceChar == map[mapIdx]) {
                        deleted[j] = true;
                        deletedCount++;

                        if (map[mapIdx++] == 'q') {
                            quackingDuck--;
                            break;
                        }
                    }
                }

                if (mapIdx != 5) {
                    deletedCount = sound.length() - 1;
                    break;
                }
            }
        }

        bw.write(deletedCount != sound.length() ? "-1" : String.valueOf(maxQuackingDuck));
        bw.flush();
        bw.close();
    }
}
