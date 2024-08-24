import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[] unf;
    static String[] sex;

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

        sex = new String[N + 1];
        unf = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            sex[i] = st.nextToken();
            unf[i] = i;
        }

        Queue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            pq.offer(new Edge(v1, v2, d));
        }

        List<Edge> mst = new ArrayList<>();
        int cnt = 0;
        while (!pq.isEmpty()) {
            Edge now = pq.poll();

            if (find(now.v1) == find(now.v2)) continue;
            if (sex[now.v1].equals(sex[now.v2])) continue;

            union(now.v1, now.v2);
            mst.add(now);
            cnt++;
        }
        
        if (cnt != N - 1) {
            System.out.println(-1);
            return;
        }

        int answer = 0;
        for (Edge e : mst) answer += e.cost;
        System.out.println(answer);
    }

    private static int find(int vertex) {
        if (unf[vertex] == vertex) return vertex;
        else return unf[vertex] = find(unf[vertex]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        unf[a] = b;
    }
}
