package gold;

import java.io.*;
import java.math.BigInteger;

public class Problem_11444 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BigInteger n = new BigInteger(br.readLine());

    }

    public static BigInteger fibonacci(BigInteger n) {
        BigInteger result;
        if(n.equals(1)) {

        }

        if(n.equals(2)) {

        }

        if(n.mod(BigInteger.valueOf(2)).equals(0)) {
            BigInteger temp = fibonacci(n.divide(BigInteger.valueOf(2)));
            result = temp.multiply(temp);
        }

        else {
            BigInteger temp = fibonacci(n.divide(BigInteger.valueOf(2)));
            result = temp.multiply(temp).multiply(fibonacci(BigInteger.valueOf(1)));
        }

        return result;
    }
}
