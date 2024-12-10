package bronze;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_21176 {
    static class Smoothie {
        int[] needIngredients;
        int price;

        public Smoothie(int[] needIngredients, int price) {
            this.needIngredients = needIngredients;
            this.price = price;
        }

        public int getMaxPrice(int[] stock) {
            int cnt = Integer.MAX_VALUE;
            for (int i = 0; i < stock.length; i++) {
                if (needIngredients[i] == 0) continue;
                cnt = Math.min(cnt, stock[i] / needIngredients[i]);
            }

            return price * cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] ingredients = new int[k];
        for (int i = 0; i < k; i++)
            ingredients[i] = Integer.parseInt(st.nextToken());

        Smoothie[] smoothies = new Smoothie[r];
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int[] needIngredients = new int[k];
            for (int j = 0; j < k; j++)
                needIngredients[j] = Integer.parseInt(st.nextToken());
            smoothies[i] = new Smoothie(needIngredients, Integer.parseInt(st.nextToken()));
        }

        int ans = 0;
        for (Smoothie s : smoothies) {
            ans = Math.max(ans, s.getMaxPrice(ingredients));
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }
}
