package gold;

import java.io.*;
import java.util.*;

public class Problem_1918 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String f = br.readLine();
        StringBuilder sb = new StringBuilder();
        HashMap<Integer, Character> map = new HashMap<>();
        for(int i = 0; i < f.length(); i++) {
            if(f.charAt(i) == '+' || f.charAt(i) == '-' || f.charAt(i) == '*' || f.charAt(i) == '/' || f.charAt(i) == '(' || f.charAt(i) == ')') {
                sb.append(f.charAt(i));
            }

            else {
                map.put(i, f.charAt(i));
                sb.append(i);
            }
        }

        PostfixConverter PC = new PostfixConverter();
        PC.formulaInput(sb.toString());
        PC.infixToPostfix();
        String result = PC.getString();

        int number = 0;
        sb = new StringBuilder();
        for(int i = 0; i < result.length(); i++) {
            if(result.charAt(i) == ' ') {
                sb.append(map.get(number));
                number = 0;
            }

            else if(result.charAt(i) == '+' || result.charAt(i) == '-' || result.charAt(i) == '*' || result.charAt(i) == '/' || result.charAt(i) == '(' || result.charAt(i) == ')') {
                sb.append(result.charAt(i));
            }

            else {
                number *= 10;
                number += Integer.parseInt(String.valueOf(result.charAt(i)));
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}

class PostfixConverter {
    private MyStack<Token> stack;
    private MyQueue<Token> queue;
    private final int LEFT = 1;

    public PostfixConverter() {
        stack = new MyStack<Token>();
        stack.push(new Token("#"));	//처음 peek하여 우선순위 비교할 때 NullPointer를 가리키지 않도록 더미 연산자 삽입
        queue = new MyQueue<Token>();
    }

    public MyQueue<Token> getQueue() {
        return queue;
    }

    public void formulaInput(String s) throws Exception { //입력된 중위식을 큐에 저장
        Token temp;
        Token prevToken = new Token("#");
        Token finalOperand;
        int numberTemp = 0;
        int operandTemp = 0;
        boolean inputNumber = false;

        for(int i  = 0; i < s.length(); i++) {
            if(s.substring(i, i+1).equals(" "))
                continue;

            temp = new Token(s.substring(i, i+1));
            if(temp.hasOperator()) {
                if(i == s.length() - 1 && !temp.getOperator().equals(")")) { //식의 마지막에 )가 아닌 연산자가 들어오면 예외발생
                    throw new Exception();
                }

                if(inputNumber) { //숫자를 큐에 추가
                    finalOperand = new Token(Integer.toString((operandTemp)));
                    operandTemp = 0;
                    queue.add(finalOperand);
                    prevToken = finalOperand;
                    inputNumber = false;
                }

                if(temp.mCheck(prevToken)) {
                    temp = new Token("m");
                }

                if(prevToken.isUnaryOperator() && temp.isBinaryOperator()) {
                    throw new Exception();
                }

                queue.add(temp);
                prevToken = temp;
            }

            else {
                numberTemp = Integer.parseInt(s.substring(i, i+1));
                operandTemp *= 10;
                operandTemp += numberTemp;
                inputNumber = true;
            }
        }

        if(inputNumber) { //식의 마지막이 숫자인 경우 숫자를 큐에 추가
            finalOperand = new Token(Integer.toString(operandTemp));
            queue.add(finalOperand);
        }
    }

    public void infixToPostfix() throws NullPointerException { //큐에 저장된 중위식을 후위식으로 변환
        for(int i = queue.size(); i > 0; i--) {
            if(queue.first().hasOperator()) { //연산자일 경우
                if(queue.first().getOperator().equals(")")) { //닫는 괄호를 만나면 여는 괄호를 만날 때까지 pop
                    queue.delete();
                    while(!(stack.peek().getOperator().equals("("))) {
                        queue.add(stack.pop());
                    }
                    stack.pop(); //( pop
                }

                else {
                    while(stack.peek().getISP() < queue.first().getICP() ||
                            (stack.peek().getISP() == queue.first().getICP() && LEFT == queue.first().getPrecedence())) {
                        queue.add(stack.pop());
                    }
                    stack.push(queue.delete());
                }
            }

            else //숫자일 경우
                queue.add(queue.delete());

        }

        for(int i = stack.size() - 1; i > 0; i--) { //식이 종료되면 스택에 있는 연산자를 모두 pop, 처음 삽입된 #을 제외하기 위해 -1
            queue.add(stack.pop());
        }
    }

    public void formulaPrint() {
        Node_1918<Token> temp = queue.getFront();
        for(int i = 0; i < queue.size(); temp = temp.getNext(), i++) {
            if(temp.getData().hasOperator()) {
                System.out.printf("%s", temp.getData().getOperator());
            }

            else {
                System.out.printf("%.0f", temp.getData().getOperand());
            }
        }
    }

    public String getString() {
        Node_1918<Token> temp = queue.getFront();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < queue.size(); temp = temp.getNext(), i++) {
            if(temp.getData().hasOperator()) {
                sb.append(temp.getData().getOperator());
            }

            else {
                sb.append(temp.getData().getOperand() + " ");
            }
        }

        return sb.toString();
    }
}

class Token {
    private String operator;
    private int operand;

    public Token(String s) {
        if(isOperator(s))
            operator = s;

        else
            operand = Integer.parseInt(s);
    }

    public String getOperator() {
        return operator;
    }

    public int getOperand() {
        return operand;
    }

    public boolean isOperator(String s) {
        char c = s.charAt(0);

        if(!(c >= '0' && c <= '9') && s.length() == 1) { //숫자가 음수라면 연산자로 취급될 수도 있으므로 길이가 1일 때만 조건 비교
            return true;
        }

        else {
            return false;
        }
    }

    public boolean hasOperator() {
        return operator != null;
    }

    public boolean isCorrectOperator() {
        return getICP() != 5;
    }

    public boolean isBinaryOperator() {
        return getICP() == 2 || getICP() == 3 || getICP() == 4;
    }

    public boolean isUnaryOperator() {
        if(hasOperator())
            return getICP() == 1;

        else
            return false;
    }

    public boolean mCheck(Token prev) {
        if(prev.hasOperator()) {
            return !prev.getOperator().equals(")") && operator.equals("-");
        }

        else
            return false;
    }

    public int getISP() {
        char ch = operator.charAt(0);

        if(ch == '(' || ch == '#')
            return 5;

        else if(ch == 'm' || ch == '@')
            return 1;

        else if(ch == '^')
            return 2;

        else if(ch == '*' || ch == '/')
            return 3;

        else
            return 4;
    }

    public int getICP() {
        char ch = operator.charAt(0);

        if(ch == '(' || ch == ')') //)의 경우 우선순위 비교가 필요없지만 지정된 연산자 외 다른 연산자가 들어왔을 때를 위해 임시 우선순위 값 배정
            return 0;

        else if(ch == 'm' || ch == '@')
            return 1;

        else if(ch == '^')
            return 2;

        else if(ch == '*' || ch == '/')
            return 3;

        else if(ch == '+' || ch == '-')
            return 4;

        else
            return 5;
    }

    public int getPrecedence() {
        char ch = operator.charAt(0);

        if(ch == 'm' || ch == '@') //우측 결합이면,,
            return 0;

        else //좌측 결합이면,,
            return 1;
    }
}


class MyQueue<E> {
    private Node_1918<E> front, rear;
    private int size;

    public MyQueue() {
        front = rear = null;
        size = 0;
    }

    public int size() { //큐에 존재하는 원소의 개수를 리턴
        return size;
    }

    public boolean isEmpty() { //큐가 비어있는지 검사
        return size == 0;
    }

    public Node_1918<E> getFront() {
        return front;
    }

    public void add(E data) { //큐의 rear에 원소 삽입
        Node_1918<E> newest = new Node_1918(data);

        if(isEmpty()) {
            front = newest;
            rear = newest;
        }

        else {
            rear.setNext(newest);
            rear = newest;
        }

        size++;
    }

    public E first() { //큐의 front에 위치한 원소 반환
        return front.getData();
    }

    public E delete() { //큐의 front에 위치한 원소를 반환하고 삭제
        E temp = front.getData();
        front = front.getNext();
        if(front == null)
            rear = null;
        size--;

        return temp;
    }
}

class MyStack<E> {
    private E[] s; //스택 배열
    private int top; //top의 인덱스

    public MyStack() {
        s = (E[]) new Object[10];
        top = -1;
    }


    public int size() { //스택의 원소 개수 반환
        return top + 1;
    }

    public boolean isEmpty() { //스택이 비어있는지 검사. 비어있으면 True 반환
        return top == -1;
    }

    public void push(E data) { //원소를 스택의 맨 위로 삽입
        if(size() == s.length)
            resize(2 * s.length);

        s[++top] = data;
    }

    public E peek() { //스택의 맨 위에 존재하는 원소 반환. 스택이 비어있다면 null 반환
        if(isEmpty())
            return null;

        return s[top];
    }

    public E pop() { //스택의 맨 위에 존재하는 원소를 반환하고 삭제.
        if(isEmpty())
            return null;

        E temp = s[top];
        s[top--] = null;

        if(size() > 0 && size() == s.length / 4)
            resize(s.length / 2);
        return temp;
    }

    public void resize(int size) { //스택의 길이를 조정
        E[] cloneArray = (E[]) new Object[size * 2];

        for(int i = 0; i < s.length; i++) {
            cloneArray[i] = s[i];
        }

        s = cloneArray;
    }
}

class Node_1918<E> {
    private E data;
    private Node_1918<E> next;

    public Node_1918(E data) {
        this.data = data;
    }

    public Node_1918<E> getNext() {
        return next;
    }

    public E getData() {
        return data;
    }

    public void setNext(Node_1918<E> next) {
        this.next = next;
    }
}

