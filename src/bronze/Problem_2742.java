package bronze;
import java.io.*;

public class Problem_2742 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < n; i++) {
			sb.append(String.format("%d\n", n-i));
		}
		
		System.out.print(sb);
	}
}