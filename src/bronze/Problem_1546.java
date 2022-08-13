package bronze;
import java.util.*;

public class Problem_1546 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		final int SUBJECT_NUMBER = sc.nextInt();
		double[] scores = new double[SUBJECT_NUMBER];
		
		for(int i = 0; i < SUBJECT_NUMBER; i++) { //�� �ʱ�ȭ
			scores[i] = sc.nextDouble();
		}
		
		double maxValue = scores[0];
		for(int i = 1; i < SUBJECT_NUMBER; i++) { //�ִ� Ž��
			if(maxValue < scores[i])
				maxValue = scores[i];
		}
		
		for(int i = 0; i < SUBJECT_NUMBER; i++) { //�迭 �� ���� ����
			scores[i] = scores[i] / maxValue * 100;
		}
		
		double result = 0;
		for(int i = 0; i < SUBJECT_NUMBER; i++) { //����� ���� �迭 �� ��� ���� ������
			result += scores[i];
		}
		
		System.out.printf("%f", result/SUBJECT_NUMBER);
	}
}