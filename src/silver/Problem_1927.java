package silver;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class Problem_1927 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        PriorityQueue<Integer> h = new PriorityQueue<>();
        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {
            int temp = Integer.parseInt(br.readLine());
            try {
                if (temp != 0)
                    h.add(temp);

                else
                    bw.write(String.valueOf(h.remove()) + "\n");
            } catch(NoSuchElementException e) {
                bw.write(String.valueOf(0+ "\n"));
            }
        }
        bw.flush();
        bw.close();
    }
}
