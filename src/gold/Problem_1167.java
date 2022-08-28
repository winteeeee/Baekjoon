package gold;

import java.io.*;
import java.util.*;

public class Problem_1167 {
    static int result = 0;
    static int temp = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int v = Integer.parseInt(br.readLine());
        boolean[] visited = new boolean[v];
        Vertex[] tree = new Vertex[v];

        for(int i = 0; i < v; i++) {
            Vertex vertex = new Vertex();
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken()) - 1;

            int edge = Integer.parseInt(st.nextToken());
            while(edge != -1) {
                int distance = Integer.parseInt(st.nextToken());
                vertex.addList(edge, distance);
                edge = Integer.parseInt(st.nextToken());
            }

            tree[idx] = vertex;
        }

        for(int i = 0; i < v; i++, temp = 0) {
            if(!visited[i]) {
                dfs(i, tree, visited);
            }

            if(result < temp)
                result = temp;
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }

    public static void dfs(int idx, Vertex[] tree, boolean[] visited) {
        visited[idx] = true;
        ArrayList<Integer> edge = tree[idx].getEdges();

        int maxDis = 0;
        for(int i = 0; i < edge.size(); i += 2) {
            if(!visited[edge.get(i) - 1] && (edge.get(i + 1) > maxDis)) {
                maxDis = edge.get(i + 1);
            }
        }
        temp += maxDis;

        for(int i = 0; i < edge.size(); i += 2) {
            if(!visited[edge.get(i) - 1]) {
                dfs(edge.get(i) - 1, tree, visited);
            }
        }
    }
}

class Vertex {
    private ArrayList<Integer> Edges;

    public Vertex() {
        Edges = new ArrayList<>();
    }

    public void addList(int a, int b) {
        Edges.add(a);
        Edges.add(b);
    }

    public ArrayList<Integer> getEdges() {
        return Edges;
    }
}
