package week1.BK_5397_PrimeNumber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        //입력은 1,000,000 을 최대갯수로 가지기 때문에 시간 복잡도를 줄여보기 위해 StringBuilder를 활용했습니다.
        StringBuilder sb = new StringBuilder();
        //한줄을 받아 int 배열로 만들기 위해 stram 사용
        int[] MN = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int M = MN[0];
        int N = MN[1];

        for (int m = M; m <= N; m++) {
            if(m == 1){
                continue;
            }
            // 2는 소수 이기때문에
            if(m == 2){
                sb.append(2).append("\n");
            }
            if (isPrime(m)) {
                sb.append(m).append("\n");
            }
        }
        System.out.println(sb);
    }

    public static boolean isPrime(int src) {


        if (src % 2 == 0) {
            return false;
        }
        // 범위가 (int) Math.sqrt(src) 인 이유는
        // 어떤 정수의 약수는 루트(정수) 범위 넘어서 는 존재하지 않기에
        // 소수를 찾기위해 약수를 찾는 범위를 root(scr) 로 해 시간을 줄였습니다.
        for (int i = 3; i <= (int) Math.sqrt(src); i++) {
            if (src % i == 0) {
                return false;
            }
        }
        return true;
    }
}