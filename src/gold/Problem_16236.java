package gold;

import java.io.*;
import java.util.*;

class Prey implements Comparable<Prey> {
    private int x;
    private int y;
    private int time;

    Prey(int newX, int newY, int newTime) {
        x = newX;
        y = newY;
        time = newTime;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getTime() {
        return time;
    }

    @Override
    public int compareTo(Prey p) {
        if(time < p.getTime()) {
            return -1;
        }

        else if(time > p.getTime()) {
            return 1;
        }

        else {
            if(y < p.getY()) {
                return -1;
            }

            else if(y > p.getY()) {
                return 1;
            }

            else {
                if(x < p.getX()) {
                    return -1;
                }

                else if(x > p.getX()) {
                    return 1;
                }

                else {
                    return 0;
                }
            }
        }
    }
}

public class Problem_16236 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Queue<Integer> q = new LinkedList<>();
        int n = Integer.parseInt(br.readLine());
        int[][] space = new int[n][n];
        boolean help = false;
        int size = 2;
        int sizeUpCount = 0;
        int sharkX = 0;
        int sharkY = 0;
        int time = 0;

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                int temp = Integer.parseInt(st.nextToken());
                space[i][j] = temp;

                if(temp == 9) {
                    sharkX = j;
                    sharkY = i;
                }
            }
        }

        while(!help) {
            boolean[][] visited = new boolean[n][n];
            int timeTemp = 0;
            help = true;
            q.add(sharkX);
            q.add(sharkY);
            q.add(0);
            visited[sharkY][sharkX] = true;
            ArrayList<Prey> preys = new ArrayList<>();
            boolean preyFind = false;

            while (!q.isEmpty()) {
                int currentX = q.remove();
                int currentY = q.remove();
                timeTemp = q.remove();

                if (currentY - 1 >= 0) {
                    if (!visited[currentY - 1][currentX]) {
                        if (isMovable(currentX, currentY - 1, size, space)) { //그냥 이동
                            q.add(currentX);
                            q.add(currentY - 1);
                            q.add(timeTemp + 1);
                            visited[currentY - 1][currentX] = true;

                            if (isEatable(currentX, currentY - 1, size, space)) { //먹음
                                preyFind = true;
                                preys.add(new Prey(currentX, currentY - 1, timeTemp + 1));
                            }
                        }
                    }
                }

                if (currentX - 1 >= 0) {
                    if (!visited[currentY][currentX - 1]) {
                        if (isMovable(currentX - 1, currentY, size, space)) {
                            q.add(currentX - 1);
                            q.add(currentY);
                            q.add(timeTemp + 1);
                            visited[currentY][currentX - 1] = true;

                            if (isEatable(currentX - 1, currentY, size, space)) {
                                preyFind = true;
                                preys.add(new Prey(currentX - 1, currentY, timeTemp + 1));
                            }
                        }
                    }
                }

                if (currentX + 1 < n) {
                    if (!visited[currentY][currentX + 1]) {
                        if (isMovable(currentX + 1, currentY, size, space)) {
                            q.add(currentX + 1);
                            q.add(currentY);
                            q.add(timeTemp + 1);
                            visited[currentY][currentX + 1] = true;

                            if (isEatable(currentX + 1, currentY, size, space)) {
                                preyFind = true;
                                preys.add(new Prey(currentX + 1, currentY, timeTemp + 1));
                            }
                        }
                    }
                }

                if (currentY + 1 < n) {
                    if (!visited[currentY + 1][currentX]) {
                        if (isMovable(currentX, currentY + 1, size, space)) {
                            q.add(currentX);
                            q.add(currentY + 1);
                            q.add(timeTemp + 1);
                            visited[currentY + 1][currentX] = true;

                            if (isEatable(currentX, currentY + 1, size, space)) {
                                preyFind = true;
                                preys.add(new Prey(currentX, currentY + 1, timeTemp + 1));
                            }
                        }
                    }
                }
            }

            if(preyFind) {
                Prey[] preysArr = preys.toArray(new Prey[0]);
                Arrays.sort(preysArr);
                space[sharkY][sharkX] = 0;
                sharkX = preysArr[0].getX();
                sharkY = preysArr[0].getY();
                space[sharkY][sharkX] = 0;
                sizeUpCount++;
                if(size == sizeUpCount) {
                    size++;
                    sizeUpCount = 0;
                }
                help = false;
                time += preysArr[0].getTime();
            }

            while(!q.isEmpty())
                q.remove();
        }

        bw.write(String.valueOf(time));
        bw.flush();
        bw.close();
    }

    public static boolean isMovable(int currentX, int currentY, int size, int[][] space) {
        return space[currentY][currentX] == 0 || space[currentY][currentX] <= size;
    }

    public static boolean isEatable(int currentX, int currentY, int size, int[][] space) {
        return space[currentY][currentX] != 0 && space[currentY][currentX] < size;
    }
}
