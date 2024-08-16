package gold;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/*
문제에 존재하는 화학식 문법부터가 처음보면 살짝 난해하다.
근데 결국 말하고자하는 바는 우리가 아는 일반적인 화학식이라는 소리와 동일하다.

문제는 푸는 것 자체는 거의 순수 구현에 가까운 문제로 후위 연산 계산기를 만드는 느낌과 비슷했다.
자잘한 구현 실수가 조금 있어 살짝 헤멨던 문제
 */

public class Problem_4797 {
    static ArrayList<Element> elements = new ArrayList<>();
    static class Element implements Comparable<Element> {
        int c;
        String a;

        Element(int c, String a) {
            this.c = c;
            this.a = a;
        }

        @Override
        public int compareTo(Element o) {
            return a.compareTo(o.a);
        }

        public String toString() {
            if (c != 1)
                return c + a;
            else
                return a;
        }
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        ArrayList<String> chemicalFormulas = new ArrayList<>();
        String formula;

        while ((formula = br.readLine()) != null) {
            //제출 시엔 삭제할 것
            if (formula.equals("1")) {
                break;
            }

            chemicalFormulas.add(formula);
        }

        var sb = new StringBuilder();
        for (String f : chemicalFormulas) {
            //제출 시엔 삭제할 것
            if (f.equals("1")) {
                break;
            }

            analysisElement(f);
            Collections.sort(elements);
            sumDuplicatedElement();
            for (Element e : elements) {
                sb.append(e.toString()).append('+');
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append('\n');
            elements.clear();
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void analysisElement(String formula) {
        var cStr = new StringBuilder();
        var a = new StringBuilder();
        ArrayList<Integer> multiList = new ArrayList<>();

        for (int i = 0; i < formula.length(); i++) {
            char curChar = formula.charAt(i);

            if (isUpperAlphabet(curChar)) {
                if (a.length() != 0) {
                    elementFlush(cStr, multiList, a);
                    a = new StringBuilder();
                    cStr = new StringBuilder();
                }

                a.append(curChar);
            } else if (isLowerAlphabet(curChar)) {
                a.append(curChar);
            } else if (isNumber(curChar)) {
                cStr.append(curChar);
            } else if (curChar == '(') {
                if (a.length() != 0) {
                    elementFlush(cStr, multiList, a);
                    a = new StringBuilder();
                    cStr = new StringBuilder();
                }

                Stack<Character> s = new Stack<>();
                s.add(curChar);
                StringBuilder multiStr = new StringBuilder();

                for (int j = i + 1; j < formula.length() && !s.empty(); j++) {
                    if (formula.charAt(j) == '(') {
                        s.add('(');
                    } else if (formula.charAt(j) == ')') {
                        s.pop();

                        if (s.empty()) {
                            for (int k = j + 1; k < formula.length() && isNumber(formula.charAt(k)); k++)
                                multiStr.append(formula.charAt(k));
                            int multiValue = multiStr.length() == 0 ? 1 : Integer.parseInt(multiStr.toString());
                            multiList.add(multiValue);
                        }
                    }
                }
            } else if (curChar == ')') {
                if (a.length() != 0) {
                    elementFlush(cStr, multiList, a);
                    a = new StringBuilder();
                    cStr = new StringBuilder();
                }
                int number = multiList.get(multiList.size() - 1);
                multiList.remove(multiList.size() - 1);
                if (number != 1)
                    i += String.valueOf(number).length();
            }
        }

        if (a.length() != 0) {
            elementFlush(cStr, multiList, a);
        }
    }

    public static void elementFlush(StringBuilder cStr, ArrayList<Integer> multiList, StringBuilder a) {
        int c = cStr.length() == 0 ? 1 : Integer.parseInt(cStr.toString());
        int multi = 1;
        for (int m : multiList)
            multi *= m;

        elements.add(new Element(c * multi, a.toString()));
    }

    public static void sumDuplicatedElement() {
        if (elements.isEmpty()) {
            return;
        }

        ArrayList<Element> newElements = new ArrayList<>();
        Element prev = elements.get(0);
        if (elements.size() == 1) {
            newElements.add(prev);
        } else {
            for (int i = 1; i < elements.size(); i++) {
                Element cur = elements.get(i);

                if (prev.a.equals(cur.a)) {
                    prev = new Element(prev.c + cur.c, cur.a);
                } else {
                    newElements.add(prev);
                    prev = cur;
                }

                if (i == elements.size() - 1) {
                    newElements.add(prev);
                }
            }
        }

        elements = newElements;
    }

    public static boolean isUpperAlphabet(char c) {
        return 'A' <= c && c <= 'Z';
    }

    public static boolean isLowerAlphabet(char c) {
        return 'a' <= c && c <= 'z';
    }

    public static boolean isNumber(char c) {
        return '0' <= c && c <= '9';
    }
}
