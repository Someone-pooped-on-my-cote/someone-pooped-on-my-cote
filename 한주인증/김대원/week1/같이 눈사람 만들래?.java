import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(arr);

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < N - 2; i++) {
            for (int j = N - 1; j > i; j--) {
                int snow1 = arr[i] + arr[j];

                int lt = i + 1;
                int rt = j - 1;
                while (lt < rt) {
                    if (lt == j) {
                        lt++;
                        continue;
                    }

                    if (rt == i) {
                        rt--;
                        continue;
                    }
                    
                    int snow2 = arr[lt] + arr[rt];
                    answer = Math.min(answer, Math.abs(snow1 - snow2));

                    if (snow1 > snow2) lt++;
                    else if (snow1 < snow2) rt--;
                    else {
                        System.out.println(0);
                        return;
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
