import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Map<Integer, Integer> map = new HashMap<>();

        int now = -1;
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine().trim());
            map.put(num, map.getOrDefault(num, 0) + 1);

            if (map.get(num) >= M) {
                if (now == -1 || num < now) now = num;
            } else if (now != -1) {
                if (map.get(now) < M) {
                    System.out.println(-1);
                    continue;
                }
            }

            System.out.println(now);
        }
    }
}