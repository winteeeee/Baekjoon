package silver;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Problem_11292 {
    static class Student implements Comparable<Student> {
        String name;
        double height;

        Student(String n, double h) {
            name = n;
            height = h;
        }

        @Override
        public int compareTo(Student o) {
            return Double.compare(height, o.height);
        }
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        var sb = new StringBuilder();

        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0)
                break;

            ArrayList<Student> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                var st = new StringTokenizer(br.readLine());
                String name = st.nextToken();
                double height = Double.parseDouble(st.nextToken());
                list.add(new Student(name, height));
            }
            Collections.sort(list, Collections.reverseOrder());

            Student tallest = list.get(0);
            sb.append(tallest.name).append(" ");
            for (int i = 1; i < list.size() && list.get(i).height == tallest.height; i++) {
                tallest= list.get(i);
                sb.append(tallest.name).append(" ");
            }
            sb.append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
