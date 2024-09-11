package gold;

import java.io.*;
import java.util.*;

public class Problem_3116 {
    static class IntersectionPoint {
        int x;
        int y;

        IntersectionPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            IntersectionPoint that = (IntersectionPoint) o;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static class Bacteria {
        int x;
        int y;
        int d;

        Bacteria(String str) {
            var st = new StringTokenizer(str);
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
        }

        IntersectionPoint getIntersectionPoint(Bacteria b) {
            //두 직선의 방정식을 구하고
            //교점이 발생한다면
            //아래 메소드를 호출
            //두 박테리아의 시간이 같다면 객체 생성하여 반환 없으면 null

            int interX;
            int interY;


            if (d == 1 || d == 5) {
                //y = -x + a + b
                if (b.d == 2 || b.d == 6) {
                    interX = b.x;
                    interY = -interX + x + y;
                } else if (b.d == 3 || b.d == 7) {
                    interX = x + y + b.x - b.y;
                    //1초 단위로 움직이므로 교점은 반드시 정수로 발생함. 교점의 x좌표가 실수라면 만나지 않는 것
                    if (interX % 2 != 0) return null;
                    interX /= 2;
                    interY = -interX + x + y;
                } else if (b.d == 4 || b.d == 8) {
                    interY = b.y;
                    interX = x + y - interY;
                } else {
                    if (d == b.d) return null;
                    if (Math.abs(x - b.x) != Math.abs(y - b.y)) return null;
                    interX = x + b.x;
                    interY = y + b.y;
                    if (interX % 2 != 0 || interY % 2 != 0) return null;
                    interX /= 2; interY /= 2;
                }
            } else if (d == 2 || d == 6) {
                //x = a
                interX = x;
                if (b.d == 1 || b.d == 5) {
                    interY = -x + b.x + b.y;
                } else if (b.d == 3 || b.d == 7) {
                    interY = x - b.x + b.y;
                } else if (b.d == 4 || b.d == 8) {
                    interY = b.y;
                } else {
                    if (d == b.d) return null;
                    if (x != b.x) return null;
                    interY = y + b.y;
                    if (interY % 2 != 0) return null;
                    interY /= 2;
                }
            } else if (d == 3 || d == 7) {
                //y = x - a + b
                if (b.d == 1 || b.d == 5) {
                    interX = x - y + b.x + b.y;
                    if (interX % 2 != 0) return null;
                    interX /= 2;
                    interY = -interX + b.x + b.y;
                } else if (b.d == 2 || b.d == 6) {
                    interX = b.x;
                    interY = interX - x + y;
                } else if (b.d == 4 || b.d == 8) {
                    interY = b.y;
                    interX = interY + x - y;
                } else {
                    if (d == b.d) return null;
                    if (Math.abs(x - b.x) != Math.abs(y - b.y)) return null;
                    interX = x + b.x;
                    interY = y + b.y;
                    if (interX % 2 != 0 || interY % 2 != 0) return null;
                    interX /= 2; interY /= 2;
                }
            } else {
                //y = b
                interY = y;
                if (b.d == 1 || b.d == 5) {
                    interX = b.x + b.y - interY;
                } else if (b.d == 2 || b.d == 6) {
                    interX = b.x;
                } else if (b.d == 3 || b.d == 7) {
                    interX = interY + b.x - b.y;
                } else {
                    if (d == b.d) return null;
                    if (y != b.y) return null;
                    interX = x + b.x;
                    if (interX % 2 != 0) return null;
                    interX /= 2;
                }
            }

            int thisTime = getTimeWhenCoordinate(interX, interY);
            int bTime = b.getTimeWhenCoordinate(interX, interY);

            if (thisTime != -1 && thisTime == bTime) return new IntersectionPoint(interX, interY);
            return null;
        }

        int getTimeWhenCoordinate(int x, int y) {
            if (d == 1 || d == 5) {
                if (d == 1) {
                    if (this.x - x != y - this.y) return -1;
                    return Math.max(this.x - x, -1);
                } else {
                    if (x - this.x != this.y - y) return -1;
                    return Math.max(x - this.x, -1);
                }
            } else if (d == 2 || d == 6) {
                if (this.x != x) return -1;
                if (d == 2) {
                    return Math.max(y - this.y, -1);
                } else {
                    return Math.max(this.y - y, -1);
                }
            } else if (d == 3 || d == 7) {
                if (d == 3) {
                    if (x - this.x != y - this.y) return -1;
                    return Math.max(x - this.x, -1);
                } else {
                    if (this.x - x != this.y - y) return -1;
                    return Math.max(this.x - x, -1);
                }
            } else {
                //4 or 8의 경우
                if (this.y != y) return -1;
                if (d == 4) {
                    return Math.max(x - this.x, -1);
                } else {
                    return Math.max(this.x - x, -1);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        Bacteria[] bacterias = new Bacteria[n];
        for (int i = 0; i < n; i++)
            bacterias[i] = new Bacteria(br.readLine());

        HashMap<IntersectionPoint, HashSet<Short>> intersectionPoints = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                IntersectionPoint intersectionPoint = bacterias[i].getIntersectionPoint(bacterias[j]);
                if (intersectionPoint != null) {
                    if (intersectionPoints.containsKey(intersectionPoint)) {
                        var set = intersectionPoints.get(intersectionPoint);
                        set.add((short) i);
                        set.add((short) j);
                        intersectionPoints.put(intersectionPoint, set);
                    } else {
                        var set = new HashSet<Short>();
                        set.add((short) i);
                        set.add((short) j);
                        intersectionPoints.put(intersectionPoint, set);
                    }
                }
            }
        }

        short maxCount = 0;
        int maxTime = Integer.MAX_VALUE;
        for (Map.Entry<IntersectionPoint, HashSet<Short>> e : intersectionPoints.entrySet()) {
            TreeMap<Integer, Integer> tempMap = new TreeMap<>();
            for (Short idx : e.getValue()) {
                int time = bacterias[idx].getTimeWhenCoordinate(e.getKey().x, e.getKey().y);
                if (tempMap.containsKey(time)) {
                    tempMap.put(time, tempMap.get(time) + 1);
                } else {
                    tempMap.put(time, 1);
                }
            }

            for (Map.Entry<Integer, Integer> e2 : tempMap.entrySet()) {
                if (e2.getValue() > maxCount) {
                    maxCount = e2.getValue().shortValue();
                    maxTime = e2.getKey();
                } else if (e2.getValue() == maxCount) {
                    maxTime = Math.min(maxTime, e2.getKey());
                }
            }
        }

        bw.write(String.valueOf(maxCount) + '\n' + String.valueOf(maxTime));
        bw.flush();
        bw.close();
    }
}
