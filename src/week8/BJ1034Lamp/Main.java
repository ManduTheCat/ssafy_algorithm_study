package week8.BJ1034Lamp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int K;
    static String[] input;
    static Map<String, Integer> patternSet = new HashMap<>();

    public static void main(String[] args) throws IOException {
        // k 가 홀수 인경우
        // k 가 짝수 인경우
        // M = 1 이면 1 의 갯수를 찾는다 K = 짝수
        // M = 1 이면 0 의 갯수를 찾는다 K = 홀수
        // N = 1 이면 k 갯수만큼 0을 바꿀수 있다  K + (1의갯수)가  <N 이면 0리턴  == N 이면 1 리턴
        // 이외에는 패턴별로 확인하면서
        // K 가 홀수면 가능 한 패턴의 0의 갯수도 훌수여야 한다. K 가 짝수면 가능 한 패턴의 0의 갯수도 홀수여야한다.
        System.setIn(new FileInputStream("resources/study/week8/1034lamp/input9.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        input = new String[N];
        for (int n = 0; n < N; n++) {
            input[n] = br.readLine();
        }
        K = Integer.parseInt(br.readLine());
        for (String row : input) {
            if (!patternSet.containsKey(row)) {
                patternSet.put(row, 1);
            } else {
                patternSet.put(row, patternSet.get(row) + 1);
            }
        }
        // M 이 1인경우
        if (M == 1) {
            int count = 0;
            // M 이 1이면서 짝수인경우
            if (K % 2 == 0) {
                //짝수면 두번 누르니 어짜피 안바뀐다
                // 1의 갯수 출력
                for (String row : input) {
                    for (int idx = 0; idx < M; idx++) {
                        if (row.charAt(idx) == '1') {
                            count++;
                        }
                    }
                }
                // M 이 1이면서 홀수인경우
            } else {
                // 홀수면 어짜피 바뀐다.
                // 0의 갯수출력
                for (String row : input) {
                    for (int idx = 0; idx < M; idx++) {
                        if (row.charAt(idx) == '0') {
                            count++;
                        }
                    }
                }
            }
            // 출력부분
            System.out.println(count);
        } else {
            int maxPatternCount = 0;
            for (String key : patternSet.keySet()) {
                int count = 0;
                for (int idx = 0; idx < M; idx++) {
                    //0의 갯수를 세고
                    if (key.charAt(idx) == '0') {
                        count++;
                    }
                }
                if (count <= K && K % 2 == count % 2) {
                    // K 가 홀수면 가능 한 패턴의 0의 갯수도 훌수여야 한다. K 가 짝수면 가능 한 패턴의 0의 갯수도 홀수여야한다.
                    maxPatternCount = Math.max(maxPatternCount, patternSet.get(key));
                }
            }
            System.out.println(maxPatternCount);
        }
    }
}
