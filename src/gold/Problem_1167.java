package gold;

import java.io.*;
import java.util.*;

public class Problem_1167 {
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int v = Integer.parseInt(br.readLine());
        Queue<Integer> q = new LinkedList<>();
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
        int nodeNum = findNodeNum(q, 0, tree, visited);
        visited = new boolean[v];
        int nodeNum2 = findNodeNum(q, nodeNum, tree, visited);
        visited = new boolean[v];
        q.add(nodeNum);
        q.add(0);

        while(!q.isEmpty()) {
            int node = q.remove();
            int distance = q.remove();
            if(node == nodeNum2) {
                result = distance;
                break;
            }
            ArrayList<Integer> edge = tree[node].getEdges();

            for(int i = 0; i < edge.size(); i += 2) {
                if(!visited[edge.get(i) - 1]) {
                    q.add(edge.get(i) - 1);
                    q.add(distance + edge.get(i + 1));
                    visited[edge.get(i) - 1] = true;
                }
            }
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }

    public static int findNodeNum(Queue<Integer> q, int idx, Vertex[] tree, boolean[] visited) {
        int result = 0;
        int max = 0;
        q.add(idx);
        q.add(0);
        visited[idx] = true;

        while(!q.isEmpty()) {
            int node = q.remove();
            int distance = q.remove();
            if(max < distance) {
                max = distance;
                result = node;
            }
            ArrayList<Integer> edge = tree[node].getEdges();

            for(int i = 0; i < edge.size(); i += 2) {
                if(!visited[edge.get(i) - 1]) {
                    q.add(edge.get(i) - 1);
                    q.add(distance + edge.get(i + 1));
                    visited[edge.get(i) - 1] = true;
                }
            }
        }

        return result;
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
