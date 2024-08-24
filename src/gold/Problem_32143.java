package gold;

import java.io.*;
import java.util.*;

public class Problem_32143 {
    static class Card {
        int sumOfDamage = 0;
        PriorityQueue<Integer> h = new PriorityQueue<>();

        public void addCard(int d, int h) {
            if (sumOfDamage >= h) {
                sumOfDamage += d;
                this.h.add(d);

                while (sumOfDamage - this.h.peek() >= h) {
                    sumOfDamage -= this.h.remove();
                }
            } else {
                sumOfDamage += d;
                this.h.add(d);
            }
        }

        public String getResult(int h) {
            if (sumOfDamage >= h) {
                return String.valueOf(this.h.size());
            } else {
                return "-1";
            }
        }
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var h = Integer.parseInt(br.readLine());
        var st = new StringTokenizer(br.readLine());
        var n = Integer.parseInt(st.nextToken());
        var q = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        var card = new Card();
        for (int i = 0; i < n; i++)
            card.addCard(Integer.parseInt(st.nextToken()), h);


        var sb = new StringBuilder();
        for (int i = -1; i < q; i++) {
            if (i != -1)
                card.addCard(Integer.parseInt(br.readLine()), h);

            sb.append(card.getResult(h)).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
