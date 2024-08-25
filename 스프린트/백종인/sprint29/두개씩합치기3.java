import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static Integer min = null;
    public static int n = 0;
    public static int[][] matrix;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        boolean[] visited = new boolean[n + 1];
        visited[0] = true;
        matrix = new int[n + 1][2];

        for(int i = 0; i < n; i ++) {
            st = new StringTokenizer(br.readLine());
            matrix[i + 1][0] = Integer.parseInt(st.nextToken());
            matrix[i + 1][1] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < n; j ++) {
                if(i != j) {
                    visited[i] = true;
                    dp(visited.clone(), i, j, 0);
                }
            }
        }
        System.out.println(min);
    }

    public static void dp(boolean[] visited, int start, int next, int multiple) {
        if(allVisit(visited)) {
            if(min == null) min = multiple;
        }
        if(visited[next]) return;
        visited[next] = true;

        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < 2; i ++) {
            set.add(matrix[start][i]);
            set.add(matrix[next][i]);
        }

        int temp = 1;
        for(Integer s : set) {
            temp *= s;
        }

        multiple += temp;

        for(int i = 0; i < n; i++) {
            if(i != next && i != start) {
                dp(visited.clone(), next, i, multiple);
            }
        }
    }

    public static Boolean allVisit(boolean[] visited) {
        for(int i = 0; i < visited.length; i++) {
            if(!visited[i]) return false;
        }
        return true;
    }
}
