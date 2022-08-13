package gold;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_1107 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int current = 100;
        int target = Integer.parseInt(br.readLine());
        int brokenNumber = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer("");
        boolean[] broken = new boolean[10];
        if(brokenNumber > 0)
            st = new StringTokenizer(br.readLine());
        for(int i = 0; i < brokenNumber; i++) {
            broken[Integer.parseInt(st.nextToken())] = true;
        }

        int count = 0;
        if(target == current)
            bw.write(String.valueOf(count));

        else {
            if(String.valueOf(target).length() > Math.abs(target-current) || brokenNumber == 10) {
                count = Math.abs(target-current);
                bw.write(String.valueOf(count));
            }

            else {
                int min = 100;
                int max = 100;

                FOR1:
                for(int i = target; i >= 0; i--) {
                    int length2 = String.valueOf(i).length();
                    for(int j = 0; j < length2; j++) {
                        String now = String.valueOf(String.valueOf(i).charAt(j));
                        if(broken[Integer.parseInt(now)])
                            continue FOR1;
                    }
                    min = i;
                    break;
                }

                FOR2:
                for(int i = target; true; i++) {
                    if(!broken[0] && brokenNumber == 9) {
                        max = 0;
                        break;
                    }

                    int length2 = String.valueOf(i).length();
                    for(int j = 0; j < length2; j++) {
                        String now = String.valueOf(String.valueOf(i).charAt(j));
                        if(broken[Integer.parseInt(now)])
                            continue FOR2;
                    }

                    max = i;
                    break;
                }

                int temp;
                int temp2;
                temp = String.valueOf(min).length();
                temp += Math.abs(target-min);
                temp2 = count = String.valueOf(max).length();
                temp2 += Math.abs(max-target);


                if(temp < temp2)
                    count = temp;

                else
                    count = temp2;

                if(Math.abs(target-current) > count)
                    bw.write(String.valueOf(count));

                else
                    bw.write(String.valueOf(Math.abs(target-current)));
            }
        }
        bw.flush();
        bw.close();
    }
}
