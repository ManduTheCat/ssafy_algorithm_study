package week6.jumpingLog11497;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int Tc;
    static int N;
    static int[] res;

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("resources/study/week6/BJ11497/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Tc = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < Tc; tc++) {
            PriorityQueue<Integer> pq = new PriorityQueue<>(Integer::compareTo);
            N = Integer.parseInt(br.readLine());
            res = new int[N];
            // 우선순위 큐를 활용해 양쪽 번갈아가면 넣을 스택 두개 선언
            Stack<Integer> rStack = new Stack<>();
            Stack<Integer> lStack = new Stack<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int n = 0; n < N; n++) {
                pq.add(Integer.parseInt(st.nextToken()));
            }
            // 번갈아가면 스텍에 값을 넣는다.
            while (!pq.isEmpty()) {
                rStack.add(pq.poll());
                if (!pq.isEmpty()) {
                    lStack.add(pq.poll());
                }
            }
            // 가장 적은 차를 가지는 순서를 기록하는 부분 N 개의 큰수가 있다면
            // res 에 다음과 같이 배치 된다 res [N-1,N-3,N-5,...N-6,N-4,N-2,N]
            int cursor = 0;
            while (!lStack.isEmpty()) {
                res[cursor++] = lStack.pop();
            }
            for (Integer el : rStack) {
                res[cursor++] = el;
            }
            System.out.println(findMinDiff());
        }
    }

    // 순회하면서 가장 큰 차이를 구하는 함수
    public static int findMinDiff() {
        int result = 0;
        for (int i = 0; i < N - 1; i++) {
            result = Math.max(result, Math.abs(res[i] - res[i + 1]));
        }
        // 마지막으로 첫번째와 마지막 절대값차이 구해서 비교
        result = Math.max(result, Math.abs(res[0] - res[N - 1]));
        return result;
    }
}
