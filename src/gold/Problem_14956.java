package gold;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_14956 {
    static int count = 1;
    static int resultY;
    static int resultX;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        makeHilbertCurve(0, n - 1, 1, m, n);

        bw.write((resultX + 1) + " " + (n - resultY));
        bw.flush();
        bw.close();
    }

    //스테이터스 : 위1, 오른2, 왼3, 아래4

    public static void makeHilbertCurve(int x, int y, int status, int m, int n) {
        if(n == 1) {
            if(status == 1) {
                if(count == m) {
                    resultY = y;
                    resultX = x;
                }
                count++;
                if(count == m) {
                    resultY = y- 1;
                    resultX = x;
                }
                count++;
                if(count == m) {
                    resultY = y - 1;
                    resultX = x + 1;
                }
                count++;
                if(count == m) {
                    resultY = y;
                    resultX = x + 1;
                }
                count++;
            }

            else if(status == 2) {
                if(count == m) {
                    resultY = y;
                    resultX = x;
                }
                count++;
                if(count == m) {
                    resultY = y;
                    resultX = x + 1;
                }
                count++;
                if(count == m) {
                    resultY = y - 1;
                    resultX = x + 1;
                }
                count++;
                if(count == m) {
                    resultY = y - 1;
                    resultX = x;
                }
                count++;
            }

            else if(status == 3) {
                if(count == m) {
                    resultY = y;
                    resultX = x;
                }
                count++;
                if(count == m) {
                    resultY = y;
                    resultX = x - 1;
                }
                count++;
                if(count == m) {
                    resultY = y + 1;
                    resultX = x - 1;
                }
                count++;
                if(count == m) {
                    resultY = y + 1;
                    resultX = x;
                }
                count++;
            }

            else {
                if(count == m) {
                    resultY = y;
                    resultX = x;
                }
                count++;
                if(count == m) {
                    resultY = y + 1;
                    resultX = x;
                }
                count++;
                if(count == m) {
                    resultY = y + 1;
                    resultX = x - 1;
                }
                count++;
                if(count == m) {
                    resultY = y;
                    resultX = x - 1;
                }
                count++;
            }
        }

        else {
            if(status == 1) {
                makeHilbertCurve(x, y, 2,m, n / 2);
                makeHilbertCurve(x, y - n / 2, 1,m, n / 2);
                makeHilbertCurve(x + n / 2, y - n / 2, 1,m, n / 2);
                makeHilbertCurve(x + n - 1, y - n / 2 + 1, 3,m, n / 2);
            }

            else if(status == 2) {
                makeHilbertCurve(x, y, 1,m, n / 2);
                makeHilbertCurve(x + n / 2, y, 2,m, n / 2);
                makeHilbertCurve(x + n / 2, y - n / 2, 2,m, n / 2);
                makeHilbertCurve(x + n / 2 - 1, y - n + 1, 4,m, n / 2);
            }

            else if(status == 3) {
                makeHilbertCurve(x, y, 4,m, n / 2);
                makeHilbertCurve(x - n / 2, y, 3,m, n / 2);
                makeHilbertCurve(x - n / 2, y + n / 2, 3,m, n / 2);
                makeHilbertCurve(x - n / 2 + 1, y + n - 1, 1,m, n / 2);
            }

            else {
                makeHilbertCurve(x, y, 3,m, n / 2);
                makeHilbertCurve(x, y + n / 2, 4,m, n / 2);
                makeHilbertCurve(x - n / 2, y + n / 2, 4,m, n / 2);
                makeHilbertCurve(x - n + 1, y + n / 2 - 1, 2,m, n / 2);
            }
        }
    }
}
