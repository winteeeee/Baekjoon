package gold;

import java.io.*;

public class Problem_10026 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        char[][] RGB = new char[n][n];
        boolean[][] visited = new boolean[n][n];
        boolean[][] visited2 = new boolean[n][n];
        int count = 0;
        int count2 = 0;

        for(int i = 0; i < n; i++) {
            String temp = br.readLine();
            for(int j = 0; j < n; j++)
                RGB[i][j] = temp.charAt(j);
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(!visited[i][j]) {
                    DFS(i, j, n, RGB, visited);
                    count++;
                }

                if(!visited2[i][j]) {
                    colorWeaknessDFS(i, j, n, RGB, visited2);
                    count2++;
                }
            }
        }

        bw.write(String.valueOf(count) + " " + String.valueOf(count2));
        bw.flush();
        bw.close();
    }

    public static void DFS(int i, int j, int n, char[][] RGB, boolean[][] visited) {
        char color = RGB[i][j];
        visited[i][j] = true;

        if(i - 1 >= 0)
            if(!visited[i - 1][j] && RGB[i - 1][j] == color)
                DFS(i - 1, j, n, RGB, visited);

        if(i + 1 < n)
            if(!visited[i + 1][j] && RGB[i + 1][j] == color)
                DFS(i + 1, j, n, RGB, visited);

        if(j - 1 >= 0)
            if(!visited[i][j - 1] && RGB[i][j - 1] == color)
                DFS(i, j - 1, n, RGB, visited);

        if(j + 1 < n)
            if(!visited[i][j + 1] && RGB[i][j + 1] == color)
                DFS(i, j + 1, n, RGB, visited);
    }

    public static void colorWeaknessDFS(int i, int j, int n, char[][] RGB, boolean[][] visited) {
        char color = RGB[i][j];
        visited[i][j] = true;

        if(i - 1 >= 0)
            if(!visited[i - 1][j] && (RGB[i - 1][j] == color || (RGB[i - 1][j] == 'R' && color == 'G') ||  (RGB[i - 1][j] == 'G' && color == 'R')))
                colorWeaknessDFS(i - 1, j, n, RGB, visited);

        if(i + 1 < n)
            if(!visited[i + 1][j] && (RGB[i + 1][j] == color || (RGB[i + 1][j] == 'R' && color == 'G') ||  (RGB[i + 1][j] == 'G' && color == 'R')))
                colorWeaknessDFS(i + 1, j, n, RGB, visited);

        if(j - 1 >= 0)
            if(!visited[i][j - 1] && (RGB[i][j - 1] == color || (RGB[i][j - 1] == 'R' && color == 'G') ||  (RGB[i][j - 1] == 'G' && color == 'R')))
                colorWeaknessDFS(i, j - 1, n, RGB, visited);

        if(j + 1 < n)
            if(!visited[i][j + 1] && (RGB[i][j + 1] == color || (RGB[i][j + 1] == 'R' && color == 'G') ||  (RGB[i][j + 1] == 'G' && color == 'R')))
                colorWeaknessDFS(i, j + 1, n, RGB, visited);
    }
}
