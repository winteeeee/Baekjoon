import java.io.*;

public class StartAssistant {
    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        String path = "src/";

        System.out.print("난이도: ");
        String inputDifficulty = br.readLine();
        String difficulty = getDifficulty(inputDifficulty);

        System.out.print("문제 번호: ");
        int problemNumber = Integer.parseInt(br.readLine());

        path = path + difficulty + "/Problem_" + problemNumber + ".java";

        var bw = new BufferedWriter(new FileWriter(path));
        var sb = new StringBuilder();

        sb.append("package ").append(difficulty).append(";\n\n");
        sb.append("import java.io.*;").append("\n\n");
        sb.append("public class Problem_").append(problemNumber).append("{\n");
        sb.append("    public static void main(String[] args) throws IOException {\n");
        sb.append("        var br = new BufferedReader(new InputStreamReader(System.in));\n");
        sb.append("        var bw = new BufferedWriter(new OutputStreamWriter(System.out));\n\n");
        sb.append("        bw.flush();\n");
        sb.append("        bw.close();\n");
        sb.append("    }\n");
        sb.append("}\n");


        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static String getDifficulty(String str) {
        if (str.equals("b") || str.equals("B")) {
            return "bronze";
        } else if (str.equals("s") || str.equals("S")) {
            return "silver";
        } else if (str.equals("g") || str.equals("G")) {
            return "gold";
        } else if (str.equals("p") || str.equals("P")) {
            return "platinum";
        } else if (str.equals("d") || str.equals("D")) {
            return "diamond";
        } else if (str.equals("r") || str.equals("R")) {
            return "ruby";
        } else {
            return "";
        }
    }
}
