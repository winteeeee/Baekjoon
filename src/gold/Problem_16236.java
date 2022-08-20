package gold;

import java.io.*;
import java.util.*;

public class Problem_16236 {
    static Queue<Integer> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
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

            while (!q.isEmpty()) {
                int currentX = q.remove();
                int currentY = q.remove();
                timeTemp = q.remove();

                if (currentY - 1 >= 0) {
                    if (!visited[currentY - 1][currentX]) {
                        if (space[currentY - 1][currentX] == 0 || space[currentY - 1][currentX] <= size) { //그냥 이동
                            q.add(currentX);
                            q.add(currentY - 1);
                            q.add(timeTemp + 1);
                            visited[currentY - 1][currentX] = true;

                            if (space[currentY - 1][currentX] != 0 && space[currentY - 1][currentX] < size) {
                                space[sharkY][sharkX] = 0;
                                sharkX = currentX;
                                sharkY = currentY - 1;
                                sizeUpCount++;
                                help = false;
                                if(size == sizeUpCount) {
                                    size++;
                                    sizeUpCount = 0;
                                }
                                break;
                            }
                        }
                    }
                }

                if (currentX - 1 >= 0) {
                    if (!visited[currentY][currentX - 1]) {
                        if (space[currentY][currentX - 1] == 0 || space[currentY][currentX - 1] <= size) { //그냥 이동
                            q.add(currentX - 1);
                            q.add(currentY);
                            q.add(timeTemp + 1);
                            visited[currentY][currentX - 1] = true;

                            if (space[currentY][currentX - 1] != 0 && space[currentY][currentX - 1] < size) {
                                space[sharkY][sharkX] = 0;
                                sharkX = currentX - 1;
                                sharkY = currentY;
                                sizeUpCount++;
                                help = false;
                                if(size == sizeUpCount) {
                                    size++;
                                    sizeUpCount = 0;
                                }
                                break;
                            }
                        }
                    }
                }

                if (currentX + 1 < n) {
                    if (!visited[currentY][currentX + 1]) {
                        if (space[currentY][currentX + 1] == 0 || space[currentY][currentX + 1] <= size) { //그냥 이동
                            q.add(currentX + 1);
                            q.add(currentY);
                            q.add(timeTemp + 1);
                            visited[currentY][currentX + 1] = true;

                            if (space[currentY][currentX + 1] != 0 && space[currentY][currentX + 1] < size) {
                                space[sharkY][sharkX] = 0;
                                sharkX = currentX + 1;
                                sharkY = currentY;
                                sizeUpCount++;
                                help = false;
                                if(size == sizeUpCount) {
                                    size++;
                                    sizeUpCount = 0;
                                }
                                break;
                            }
                        }
                    }
                }

                if (currentY + 1 < n) {
                    if (!visited[currentY + 1][currentX]) {
                        if (space[currentY + 1][currentX] == 0 || space[currentY + 1][currentX] <= size) { //그냥 이동
                            q.add(currentX);
                            q.add(currentY + 1);
                            q.add(timeTemp + 1);
                            visited[currentY + 1][currentX] = true;

                            if (space[currentY + 1][currentX] != 0 && space[currentY + 1][currentX] < size) {
                                space[sharkY][sharkX] = 0;
                                sharkX = currentX;
                                sharkY = currentY + 1;
                                sizeUpCount++;
                                help = false;
                                if(size == sizeUpCount) {
                                    size++;
                                    sizeUpCount = 0;
                                }
                                break;
                            }
                        }
                    }
                }
            }

            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    System.out.print(space[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
            System.out.println(time + " " + size);
            System.out.println();

            while(!q.isEmpty())
                q.remove();

            if(!help) {
                time += (timeTemp + 1);
            }
        }

        bw.write(String.valueOf(time));
        bw.flush();
        bw.close();
    }

    public static void priorityCheck(int time, int x, int y, int[][] space) { //거리를 가인수로 받고 BFS하여 똑같은 거리에 있는 먹이를 찾음

    }
}
