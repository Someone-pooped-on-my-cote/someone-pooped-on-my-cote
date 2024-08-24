import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[] unf;

    static class Edge implements Comparable<Edge> {
        int v1;
        int v2;
        int cost;

        public Edge(int v1, int v2, int cost) {
            this.v1 = v1;
            this.v2 = v2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        unf = new int[N + 1];
        for (int i = 1; i <= N; i++) unf[i] = i;

        List<Edge> list = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            list.add(new Edge(info[0], info[1], info[2]));
        }

        Collections.sort(list);

        List<Edge> mst = new ArrayList<>();
        for (Edge edge : list) {
            int child = edge.v1;
            int parent = edge.v2;

            if (find(child) != find(parent)) {
                union(child, parent);
                mst.add(edge);
            }
        }

        int answer = 0;
        for (Edge edge : mst) answer += edge.cost;
        System.out.println(answer);
    }

    private static void union(int child, int parent) {
        child = find(child);
        parent = find(parent);
        unf[child] = parent;
    }

    private static int find(int vertex) {
        if (unf[vertex] == vertex) return vertex;
        else return unf[vertex] = find(unf[vertex]);
    }
}
