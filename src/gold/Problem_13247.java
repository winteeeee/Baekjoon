package gold;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
확률과 조합 요소가 아주 살짝 첨가되어 있는 구현 + 시뮬레이션 문제
이런 류의 문제가 그렇듯 인내심을 갖고 꾸준히 구현하면 문제 자체는 쉽게 풀린다.
R 타일의 존재 때문에 이전 위치와 이동 여부를 저장해둬야해서
따로 클래스를 선언하였다.

단순히 배열을 훑으며 토끼들을 이동시키는 경우 토끼가 오른쪽으로 이동할 때
이미 이동한 토끼가 다시 한 번 이동되는 경우가 있을 수 있어
큐를 이용하여 토끼들을 이동시켜줬다.
 */

public class Problem_13247 {
    static String color;
    static int r;
    static double numberOfLeftRabbit = 0;

    static class Rabbit {
        int pos;
        boolean isMoved;
        int prevPos;

        public Rabbit(int p, boolean m, int prev) {
            pos = p;
            isMoved = m;
            prevPos = prev;
        }
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        color = br.readLine();
        r = Integer.parseInt(br.readLine());

        bw.write(String.valueOf(solve()));
        bw.flush();
        bw.close();
    }

    public static double solve() {
        ArrayList<Rabbit>[] rabbitStatus = new ArrayList[color.length()];
        for (int i = 0; i < color.length(); i++) {
            rabbitStatus[i] = new ArrayList<>();
        }

        findNumberOfCaseAndSimulation(rabbitStatus, 0, 0);
        return numberOfLeftRabbit / getTotalNumberOfTrials(color.length(), r);
    }

    public static int getTotalNumberOfTrials(int left, int right) {
        right = Math.min(left - right, right);
        int numberOfMulti = right;
        int a = 1;
        int b = 1;

        for (int i = 0; i < numberOfMulti; i++) {
            a *= left--;
            b *= right--;
        }

        return a / b;
    }

    public static void findNumberOfCaseAndSimulation(ArrayList<Rabbit>[] rabbitStatus, int numberOfRabbit, int idx) {
        if (numberOfRabbit == r) {
            ArrayList<Rabbit>[] newStatus = new ArrayList[rabbitStatus.length];
            for (int i = 0; i < rabbitStatus.length; i++) {
                newStatus[i] = new ArrayList<>();
                for (int j = 0; j < rabbitStatus[i].size(); j++) {
                    Rabbit cur = rabbitStatus[i].get(j);
                    newStatus[i].add(new Rabbit(cur.pos, cur.isMoved, cur.prevPos));
                }
            }

            simulation(newStatus);
            return;
        }

        for (int i = idx; i < rabbitStatus.length; i++) {
            rabbitStatus[i].add(new Rabbit(i, false, -1));
            findNumberOfCaseAndSimulation(rabbitStatus, numberOfRabbit + 1, i + 1);
            rabbitStatus[i].clear();
        }
    }

    public static void simulation(ArrayList<Rabbit>[] rabbitStatus) {
        String curColor = color;
        int numberOfRabbit = r;
        
        while (curColor.length() > 2) {
            int size = curColor.length();
            Queue<Rabbit> q = new LinkedList<>();
            for (int i = 0; i < rabbitStatus.length; i++) {
                for (int j = 0; j < rabbitStatus[i].size(); j++) {
                    q.add(rabbitStatus[i].get(j));
                }
                rabbitStatus[i].clear();
            }

            while (!q.isEmpty()) {
                Rabbit cur = q.remove();

                if (cur.pos == 0) {
                    //0번 칸에 있는 토끼는 항상 1번 칸으로 이동한다.
                    cur.isMoved = true;
                    cur.prevPos = 0;
                    cur.pos = 1;
                    rabbitStatus[1].add(cur);
                } else if (cur.pos == size - 1 || cur.pos == size - 2) {
                    //size - 1이나 size - 2에 있는 토끼는 항상 왼쪽에 있는 칸으로 이동한다.
                    cur.isMoved = true;
                    cur.prevPos = cur.pos;
                    cur.pos = cur.pos - 1;
                    rabbitStatus[cur.pos].add(cur);
                } else {
                    //나머지 토끼는 현재 자신이 있는 칸의 색상에 따라서 이동할 칸을 결정한다.
                    if (color.charAt(cur.pos) == 'W') {
                        //흰색: 왼쪽 칸으로 이동한다.
                        cur.isMoved = true;
                        cur.prevPos = cur.pos;
                        cur.pos = cur.pos - 1;
                        rabbitStatus[cur.pos].add(cur);
                    } else if (color.charAt(cur.pos) == 'B') {
                        //검정색: 오른쪽 칸으로 이동한다.
                        cur.isMoved = true;
                        cur.prevPos = cur.pos;
                        cur.pos = cur.pos + 1;
                        rabbitStatus[cur.pos].add(cur);
                    } else {
                        //빨간색: 아직 한 번도 이동한 적이 없다면 왼쪽 칸으로 이동
                        //그 외의 경우에는 현재 칸에 오기 전에 있었던 칸으로 이동
                        if (!cur.isMoved) {
                            cur.isMoved = true;
                            cur.prevPos = cur.pos;
                            cur.pos = cur.pos - 1;
                            rabbitStatus[cur.pos].add(cur);
                        } else {
                            int temp = cur.prevPos;
                            cur.prevPos = cur.pos;
                            cur.pos = temp;
                            rabbitStatus[temp].add(cur);
                        }
                    }
                }
            }

            //모든 토끼의 이동이 끝난 후에 한 마리보다 많은 토끼가 있는 칸에 있는 토끼는
            //게임에서 제외된다.
            for (int i = 0; i < rabbitStatus.length; i++) {
                if (rabbitStatus[i].size() > 1) {
                    numberOfRabbit -= rabbitStatus[i].size();
                    rabbitStatus[i].clear();
                }
            }

            //가장 오른쪽 칸은 게임판에서 사라진다.
            curColor = curColor.substring(0, curColor.length() - 1);
        }

        numberOfLeftRabbit += numberOfRabbit;
    }
}
