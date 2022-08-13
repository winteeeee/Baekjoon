package silver;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_1697 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Queue<Integer> q = new LinkedList<>();
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = new int[2*Math.max(n, k)];

        for(int i = 0; i < arr.length; i++) {
            arr[i] = -1;
        }


        if(!(n == 0 && k == 0)) {
            arr[n] = 0;
            q.add(n);
            while (true) {
                int temp = q.remove();
                if (temp - 1 >= 0) {
                    if (arr[temp - 1] == -1) {
                        arr[temp - 1] = arr[temp] + 1;
                        q.add(temp - 1);
                    }
                }

                if (temp + 1 < arr.length) {
                    if (arr[temp + 1] == -1) {
                        arr[temp + 1] = arr[temp] + 1;
                        q.add(temp + 1);
                    }
                }

                if (2 * temp < arr.length) {
                    if (arr[2 * temp] == -1) {
                        arr[2 * temp] = arr[temp] + 1;
                        q.add(2 * temp);
                    }
                }

                if (temp - 1 == k || temp + 1 == k || 2 * temp == k) {
                    break;
                }
            }
            bw.write(String.valueOf(arr[k]));
        }

        else {
            bw.write(String.valueOf(0));
        }
        bw.flush();
        bw.close();
    }
}
