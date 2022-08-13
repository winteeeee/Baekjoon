package silver;

import java.io.*;
import java.util.ArrayList;

public class Problem_1541 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ArrayList<Integer> operand = new ArrayList<>();
        ArrayList<String> operator = new ArrayList<>();
        String str = br.readLine();

        int number = 111111;
        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) != '+' && str.charAt(i) != '-') {
                if(number == 111111)
                    number = 0;
                number = number * 10 + Integer.parseInt(String.valueOf(str.charAt(i)));
            }

            else {
                if(number != 111111) {
                    operand.add(number);
                    number = 111111;
                }

                operator.add(String.valueOf(str.charAt(i)));
            }
        }
        operand.add(number);

        int temp = 0;
        int operandIndex = operand.size() - 1;
        for(int i = operator.size() - 1; i >= 0; i--, temp = 0) {
            temp += operand.remove(operandIndex);
            operandIndex--;

            if(operator.get(i).equals("-")) {
                temp *= -1;
                operand.add(operandIndex+1, temp);
            }

            else {
                temp += operand.remove(operandIndex);
                operand.add(operandIndex, temp);
            }
        }

        int result = 0;
        for(int i = 0; i < operand.size(); i++)
            result += operand.get(i);

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }
}
