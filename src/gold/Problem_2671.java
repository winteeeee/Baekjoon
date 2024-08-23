package gold;

import java.io.*;

public class Problem_2671 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        var sb = new StringBuilder();
        final var NOISE = "NOISE";
        final var SUBMARINE = "SUBMARINE";

        String str = br.readLine();
        char status = str.charAt(0);

        for (int i = 0; i < str.length();) {
            if (status == '0') {
                //현재 상태가 0인데 바로 다음 문자가 1이면 올바른 문자열
                if (i + 1 < str.length() && str.charAt(i + 1) == '1') {
                    //i + 2번째 문자가 존재한다면 해당 문자가 다음 상태
                    if (i + 2 < str.length()) {
                        i += 2;
                        status = str.charAt(i);
                    } else {
                        //없으면 반복 종료
                        break;
                    }
                } else {
                    sb.append(NOISE);
                    break;
                }
            } else {
                //현재 상태가 1이라면 일단 00이 무조건 나와야 함
                if (i + 2 < str.length() && str.charAt(i + 1) == '0' && str.charAt(i + 2) == '0') {
                    //0 다음 1을 탐색
                    int j = i + 3;
                    boolean isFind = false;
                    for (; j < str.length(); j++) {
                        if (str.charAt(j) == '1') {
                            isFind = true;
                            break;
                        }
                    }

                    //만일 0 다음 1이 없다면 올바르지 않은 문자열
                    if (!isFind) {
                        sb.append(NOISE);
                        break;
                    }

                    //여기까지 도달했으면 일단 올바른 문자열
                    //다음 상태를 탐색. 먼저 1이 끝나는 지점을 찾음
                    for (; j < str.length(); j++) {
                        if (str.charAt(j) == '1' && j + 1 < str.length() && str.charAt(j + 1) == '0') {
                            break;
                        }
                    }

                    if (j == str.length()) {
                        //뒤에 아무런 문자가 없으면 반복 종료
                        break;
                    } else if (j + 1 == str.length() - 1) {
                        //마지막 1이 발견된 후 바로 다음 문자열이 문자열의 끝이라면 0이든 1이든 잘못된 문자열 확정
                        sb.append(NOISE);
                        break;
                    }

                    //여기까지 왔으면 최소 2개 이상의 문자가 존재함
                    //다음 상태를 결정
                    if (j + 2 < str.length() && str.charAt(j + 1) == '0' && str.charAt(j + 2) == '0') {
                        //100이라면 100~1~의 경우
                        if (str.charAt(j - 1) != '0') {
                            status = '1';
                            i = j;
                        } else {
                            //단, 00 뒤에 1이 하나인 경우에는 올바른 문자열이 아니다.
                            // 1001|1001 과 같이 1이 2개 이상이면 문제가 안되지만
                            // 1001|001 과 같이 1이 하나라면 올바른 문자열로 취급할 수 없다
                            sb.append(NOISE);
                            break;
                        }
                    } else if (j + 2 < str.length() && str.charAt(j + 1) == '0' && str.charAt(j + 2) == '1') {
                        //101이라면 0의 경우
                        status = '0';
                        i = j + 1;
                    }
                } else {
                    //1 다음 00이 없으면 올바른 문자열이 아님
                    sb.append(NOISE);
                    break;
                }
            }
        }

        bw.write(sb.length() == 0 ? SUBMARINE : sb.toString());
        bw.flush();
        bw.close();
    }
}
