import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    public static int R;
    public static int C;
    public static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[][] _d = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        ArrayDeque<int[]> q = new ArrayDeque<>();

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        boolean[][] visited = new boolean[R][C];
        visited[0][0] = true;

        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        ArrayDeque<boolean[][]> vq = new ArrayDeque<>();
        vq.add(visited.clone());
        q.add(new int[]{0, 0, 0});

        while (!q.isEmpty()) {
            boolean[][] curVisited = vq.poll();
            int[] cur = q.poll();
            for (int[] d : _d) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];
                int cnt = cur[2];
                if (map[cur[0]][cur[1]] == 1) {
                    nr = cur[0] + d[0];
                    nc = cur[1] + d[1];
                }

                if (nr == R - 1 && nc == C - 1) {
                    System.out.println(cnt);
                    return;
                }

                // 1칸 지나침
//                if (-1 < nr && nr < R && -1 < nc && nc < C) {
//                    if (map[nr][nc] == 1) {
//                        nr += d[0];
//                        nc += d[1];
//                    }
//                }

                if (-1 < nr && nr < R && -1 < nc && nc < C) {
                    if (!curVisited[nr][nc]) {
                        curVisited[nr][nc] = true;
                        q.add(new int[]{nr, nc, cnt + 1});
                        vq.add(curVisited.clone());
                    }
                }

            }
        }
        System.out.println(-1);
    }
}
