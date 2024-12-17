package bronze;

import java.io.*;

public class Problem_27327 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(String.valueOf(Integer.parseInt(br.readLine()) * 24));
        bw.flush();
        bw.close();
    }
}
