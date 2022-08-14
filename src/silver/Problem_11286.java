package silver;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class Problem_11286 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        PriorityQueue<Long> heap = new PriorityQueue<>();
        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {
            long x = Integer.parseInt(br.readLine());

            if(x != 0) {
                if(x > 0)
                    x = x * 10 + 1;

                else
                    x *= 10;

                x = Math.abs(x);
                heap.add(x);
            }

            else
                try {
                    long result = heap.remove();

                    if(result % 10 == 0)
                        result /= -10;

                    else
                        result /= 10;

                    bw.write(String.valueOf(result) + "\n");
                } catch(NoSuchElementException e) {
                    bw.write("0\n");
                }
        }
        bw.flush();
        bw.close();
    }
}
