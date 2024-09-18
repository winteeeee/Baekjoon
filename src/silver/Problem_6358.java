package silver;

import java.awt.*;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/*
[답]
Round   A wins    B wins    Tie
    1   43.7500%  18.7500%  37.5000%
    2   56.6406%  22.2656%  21.0938%
    3   62.3535%  22.7051%  14.9414%
    4   65.9134%  22.0657%  12.0209%
    5   68.6609%  21.0665%  10.2726%
    6   70.9815%  19.9854%   9.0332%
    7   73.0088%  18.9226%   8.0686%
    8   74.8079%  17.9104%   7.2816%
    9   76.4212%  16.9569%   6.6219%
   10   77.8796%  16.0617%   6.0587%
   11   79.2070%  15.2222%   5.5709%
   12   80.4219%  14.4346%   5.1434%
   13   81.5393%  13.6954%   4.7653%
   14   82.5709%  13.0010%   4.4280%
   15   83.5266%  12.3482%   4.1251%
   16   84.4146%  11.7339%   3.8515%
   17   85.2417%  11.1553%   3.6030%
   18   86.0137%  10.6098%   3.3764%
   19   86.7359%  10.0952%   3.1690%
   20   87.4124%   9.6092%   2.9784%
 */

public class Problem_6358 {
    public static void main(String[] args) throws IOException {
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var sb = new StringBuilder();
        var map = new HashMap<Point, BigDecimal>();
        map.put(new Point(0, 0), BigDecimal.ZERO);

        sb.append("Round   A wins    B wins    Tie\n");
        for (int i = 1; i <= 20; i++) {
            var newMap = new HashMap<Point, BigDecimal>();
            for (Map.Entry<Point, BigDecimal> e : map.entrySet()) {
                Point gameResult = e.getKey();

                BigDecimal multi = e.getValue().equals(BigDecimal.ZERO) ? BigDecimal.ONE : e.getValue();
                updateMap(newMap,  new Point(gameResult.x + 1, gameResult.y), multi.multiply(BigDecimal.valueOf(3)));
                updateMap(newMap,  new Point(gameResult.x + 1, gameResult.y - 1), multi.multiply(BigDecimal.valueOf(2)));
                updateMap(newMap,  new Point(gameResult.x + 2, gameResult.y - 1), multi);
                updateMap(newMap,  new Point(gameResult.x, gameResult.y + 1), multi.multiply(BigDecimal.valueOf(2)));
                updateMap(newMap,  new Point(gameResult.x, gameResult.y), multi.multiply(BigDecimal.valueOf(6)));
                updateMap(newMap,  new Point(gameResult.x -1, gameResult.y + 2), multi);
                updateMap(newMap,  new Point(gameResult.x, gameResult.y - 1), multi);
            }

            BigDecimal count = BigDecimal.ZERO;
            BigDecimal aCount = BigDecimal.ZERO;
            BigDecimal bCount = BigDecimal.ZERO;
            BigDecimal tieCount = BigDecimal.ZERO;
            for (Map.Entry<Point, BigDecimal> e : newMap.entrySet()) {
                Point gameResult = e.getKey();
                count = count.add(e.getValue());

                if (gameResult.x > gameResult.y) {
                    aCount = aCount.add(e.getValue());
                } else if (gameResult.x == gameResult.y) {
                    tieCount = tieCount.add(e.getValue());
                } else {
                    bCount = bCount.add(e.getValue());
                }
            }

            map = newMap;
            var aWins = aCount.divide(count, 6, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100))
                    .setScale(4, RoundingMode.HALF_UP);
            var bWins = bCount.divide(count, 6, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100))
                    .setScale(4, RoundingMode.HALF_UP);
            var tie = tieCount.divide(count, 6, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100))
                    .setScale(4, RoundingMode.HALF_UP);

            sb.append("    ").append(i).append("   ");
            sb.append(aWins).append("%").append("  ");
            sb.append(bWins).append("%").append("  ");
            sb.append(tie).append("%").append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void updateMap(Map<Point, BigDecimal> map, Point p, BigDecimal v) {
        map.put(p, map.getOrDefault(p, BigDecimal.ZERO).add(v));
    }
}
