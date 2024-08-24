import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        getPrimeNums(N);

        int lt = 0;
        int rt = 0;
        int sum = 0;
        int answer = 0;

        while (lt <= rt && rt < arr.length) {
            sum += arr[rt];
            if (sum <= N) {
                if (sum == N) answer++;
                rt++;
            } else {
                sum -= arr[rt];
                sum -= arr[lt++];
            }
        }

        System.out.println(answer);
    }

    private static void getPrimeNums(int N) {
        List<Integer> list = new ArrayList<>();
        for (int i = 2; i <= N; i++) {
            boolean flag = true;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    flag = false;
                    break;
                }
            }

            if (flag) list.add(i);
        }

        arr = list.stream().mapToInt(Integer::intValue).toArray();
    }
}
