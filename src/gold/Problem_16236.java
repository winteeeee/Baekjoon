package gold;

import java.io.*;
import java.util.*;

public class Problem_16236 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Queue<Integer> q = new LinkedList<>();
        int n = Integer.parseInt(br.readLine());
        int[][] space = new int[n][n];
        boolean[][] visited = new boolean[n][n];
        int size = 2;
        int sizeUpCount = 0;
        int sharkX = 0;
        int sharkY = 0;
        int time = 0;

        for(int i = 0; i < n; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; n++) {
                int temp = Integer.parseInt(st.nextToken());
                space[i][j] = temp;

                if(temp == 9) {
                    sharkX = j;
                    sharkY = i;
                }
            }
        }

        q.add(sharkX);
        q.add(sharkY);
        visited[sharkY][sharkX] = true;

        while(!q.isEmpty()) {
            int currentX = q.remove();
            int currentY = q.remove();

            if(currentY - 1 >= 0) {
                if (!visited[currentY - 1][currentX]) {
                    if (space[currentY - 1][currentX] == 0 || space[currentY - 1][currentX] <= size) { //그냥 이동
                        q.add(currentY - 1);
                        q.add(currentX);
                        visited[currentY - 1][currentX] = true;
                        time++;

                        if(space[currentY - 1][currentX] < size) {
                            sharkX = currentX;
                            sharkY = currentY - 1;
                            sizeUpCount++;
                            break;
                        }
                    }
                }
            }

            if (currentX - 1 >= 0) {
                if (!visited[currentY][currentX - 1]) {
                    if (space[currentY][currentX - 1] == 0 || space[currentY][currentX - 1] <= size) { //그냥 이동
                        q.add(currentY);
                        q.add(currentX - 1);
                        visited[currentY][currentX - 1] = true;
                        time++;

                        if(space[currentY][currentX - 1] < size) {
                            sharkX = currentX - 1;
                            sharkY = currentY;
                            sizeUpCount++;
                            break;
                        }
                    }
                }
            }

            if (currentX + 1 < n) {
                if (!visited[currentY][currentX + 1]) {
                    if (space[currentY][currentX + 1] == 0 || space[currentY][currentX + 1] == size) { //그냥 이동
                        q.add(currentY);
                        q.add(currentX + 1);
                        visited[currentY][currentX + 1] = true;
                        time++;

                        if(space[currentY][currentX + 1] < size) {
                            sharkX = currentX + 1;
                            sharkY = currentY;
                            sizeUpCount++;
                            break;
                        }
                    }
                }
            }

            if(currentY + 1 < n) {
                if (!visited[currentY + 1][currentX]) {
                    if (space[currentY + 1][currentX] == 0 || space[currentY + 1][currentX] == size) { //그냥 이동
                        q.add(currentY + 1);
                        q.add(currentX);
                        visited[currentY][currentX + 1] = true;
                        time++;

                        if (space[currentY + 1][currentX] < size) {
                            sharkX = currentX;
                            sharkY = currentY + 1;
                            sizeUpCount++;
                            break;
                        }
                    }
                }
            }
        }

        if(sizeUpCount == size) {
            sizeUpCount = 0;
            size++;
        }


    }
}
