package gold;

import java.io.*;
import java.util.*;

public class Problem_11779 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        ArrayList<Integer[]>[] bus = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++)
            bus[i] = new ArrayList<>();

        for(int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            Integer[] temp = new Integer[2];
            temp[0] = b; temp[1] = c;
            bus[a].add(temp);
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int[] graph = new int[n + 1];
        for(int i = 1; i <= n; i++)
            graph[i] = Integer.MAX_VALUE;
        graph[start] = 0;
        
        ArrayList<Integer> result = null;
        boolean resultFind = false;
        PriorityQueue<Node_11779> h = new PriorityQueue<>();
        h.add(new Node_11779(start, 0));

        while(!h.isEmpty()) {
            Node_11779 node = h.remove();
            int curIdx = node.getIdx();
            node.addRoute(curIdx);
            if(!resultFind && curIdx == end) {
                result = node.getRoute();
                resultFind = true;
            }

            if(node.getDistance() > graph[curIdx])
                continue;

            int length = bus[curIdx].size();
            for(int i = 0; i < length; i++) {
                int nextIdx = bus[curIdx].get(i)[0];
                int nextDistance = bus[curIdx].get(i)[1];

                if (graph[nextIdx] > graph[curIdx] + nextDistance) {
                    graph[nextIdx] = graph[curIdx] + nextDistance;
                    Node_11779 newNode = new Node_11779(nextIdx, graph[nextIdx]);
                    newNode.setRoute((ArrayList<Integer>) node.getRoute().clone());
                    h.add(newNode);
                }
            }
        }

        bw.write(graph[end] + "\n");
        bw.write(result.size() + "\n");
        for(int i = 0; i < result.size(); i++)
            bw.write(result.get(i) + " ");
        bw.flush();
        bw.close();
    }
}

class Node_11779 implements Comparable<Node_11779> {
    private int idx;
    private int distance;
    private ArrayList<Integer> route;

    public Node_11779(int i, int d) {
        idx = i;
        distance = d;
        route = new ArrayList<>();
    }

    public int getIdx() {
        return idx;
    }

    public int getDistance(){
        return distance;
    }
    
    public ArrayList<Integer> getRoute() { return route; }
    
    public void addRoute(int i) { route.add(i); }
    
    public void setRoute(ArrayList<Integer> route) { this.route = route; }

    public int compareTo(Node_11779 other) {
        return Integer.compare(distance, other.distance);
    }
}
