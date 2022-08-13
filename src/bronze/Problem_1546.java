package bronze;
import java.util.*;

public class Problem_1546 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		final int SUBJECT_NUMBER = sc.nextInt();
		double[] scores = new double[SUBJECT_NUMBER];
		
		for(int i = 0; i < SUBJECT_NUMBER; i++) { //값 초기화
			scores[i] = sc.nextDouble();
		}
		
		double maxValue = scores[0];
		for(int i = 1; i < SUBJECT_NUMBER; i++) { //최댓값 탐색
			if(maxValue < scores[i])
				maxValue = scores[i];
		}
		
		for(int i = 0; i < SUBJECT_NUMBER; i++) { //배열 내 값을 조작
			scores[i] = scores[i] / maxValue * 100;
		}
		
		double result = 0;
		for(int i = 0; i < SUBJECT_NUMBER; i++) { //평균을 위해 배열 내 모든 값을 더해줌
			result += scores[i];
		}
		
		System.out.printf("%f", result/SUBJECT_NUMBER);
	}
}