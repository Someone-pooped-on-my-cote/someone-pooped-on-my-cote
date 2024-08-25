import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++)
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int answer = -1;

        for (int i = 0; i < 2; i++) {
            int x = dx[i];
            int y = dy[i];

            if (x < N && y < M) {
                int cnt = 2;
                boolean[][] ch = new boolean[N][M];

                while (cnt <= N * M) {
                    boolean flag = false;
                    for (int j = 0; j < 4; j++) {
                        int nx = x + dx[j];
                        int ny = y + dy[j];

                        if (nx >= 0 && nx < N && ny >= 0 && ny < M && !ch[nx][ny] && map[nx][ny] == 0) {
                            x = nx;
                            y = ny;
                            cnt++;
                            flag = true;
                            ch[x][y] = true;
                            break;
                        }
                    }

                    if (!flag) break;
                    if (x == N - 1 && y == M - 1) {
                        answer = cnt;
                        break;
                    }
                }
            }
        }

        System.out.println(answer);
    }
}