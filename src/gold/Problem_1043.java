package gold;

import java.io.*;
import java.util.*;

public class Problem_1043 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] truthDistinguish = new int[n + 1];
        int m = Integer.parseInt(st.nextToken());
        party[] partys = new party[m];

        st = new StringTokenizer(br.readLine());
        int truth = Integer.parseInt(st.nextToken());
        for(int i = 0; i < truth; i++) {
            int temp = Integer.parseInt(st.nextToken());
            truthDistinguish[temp] = 1;
        }

        int count = 0;
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int partyN = Integer.parseInt(st.nextToken());
            party p = new party(partyN);

            for(int j = 0; j < partyN; j++) {
                int temp = Integer.parseInt(st.nextToken());
                p.setMember(j, temp);
            }
            partys[i] = p;
        }

        for(int l = 0; l < m; l++) {
            for (int i = 0; i < m; i++) {
                int[] members = partys[i].getMember();

                for (int j = 0; j < members.length; j++) {
                    if (truthDistinguish[members[j]] == 1) {
                        for (int k = 0; k < members.length; k++) {
                            truthDistinguish[members[k]] = 1;
                        }
                        break;
                    }
                }
            }
        }

        FOR1:
        for(int i = 0; i < m; i++) {
            int[] members = partys[i].getMember();

            for(int j = 0; j < members.length; j++) {
                if (truthDistinguish[members[j]] == 1) {
                    continue FOR1;
                }
            }

            count++;
        }

        bw.write(String.valueOf(count));
        bw.flush();
        bw.close();
    }
}

class party {
    private int n;
    private int[] member;

    public party(int n) {
        this.n = n;
        member = new int[n];
    }

    public int getN() {
        return n;
    }

    public int[] getMember() {
        return member;
    }
    
    public void setMember(int index, int value) {
        member[index] = value;
    }
}
