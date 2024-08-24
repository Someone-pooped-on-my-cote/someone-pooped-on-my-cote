import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Node implements Comparable<Node> {
        int to;
        int cost;

        public Node(int to, int cost) {
            super();
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        String[] types = new String[V + 1];
        ArrayList<Node>[] nodes = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
			nodes[i] = new ArrayList<>();
		}
        boolean[] visited = new boolean[V + 1];
        visited[0] = true;
        int result = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= V; i++) {
            types[i] = st.nextToken();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            if (!types[from].equals(types[to])) {
                nodes[to].add(new Node(from, p));
                nodes[from].add(new Node(to, p));
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if(visited[cur.to]) continue;
            visited[cur.to] = true;
            result += cur.cost;
            while(nodes[cur.to].size() > 0) {
                Node next = nodes[cur.to].remove(0);
                pq.add(next);
            }
        }
        if(allVisited(visited)) {
            System.out.println(result);
        } else {
            System.out.println(-1);
        }
    }

    public static Boolean allVisited(boolean[] visited) {
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }
}
