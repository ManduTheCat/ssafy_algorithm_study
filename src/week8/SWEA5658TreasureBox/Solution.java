package week8.SWEA5658TreasureBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int Tc;
    static int N, K, numLen;
    static Deque<Character> dq;
    static Set<Integer> resSet;

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("resources/study/week8/swea5658/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Tc = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < Tc; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            numLen = N / 4;
            int numLenClone = numLen;
            dq = new ArrayDeque<>();
            resSet = new HashSet<>();
            String input = br.readLine();
            for (int idx = 0; idx < N; idx++) {
                dq.offerLast(input.charAt(idx));
            }
            // 입력 끝
            // 16진수 문자 의 길이 보다 더이동하면 같은글자가 나오기 떄문에 글자수만큼만 이동
            while (numLen-- > 0) {
                // 데큐를 활용해 한칸 시계방향으로 이동
                dq.offerFirst(dq.pollLast());
                String temp = "";
                int count = 0;
                // dequeue 를 보존하면서 16진수 길이만큼자르며 10진수로 변환하여 set에 넣는다.
                for (char c : dq) {
                    temp += c;
                    count++;
                    if (count == numLenClone) {
                        resSet.add(Integer.parseInt(temp.toLowerCase(), 16));
                        temp = "";
                        count = 0;
                    }
                }
            }
            // set을 정렬가능한 collection 에넣어 K번째 수를 뽑는다.
            List<Integer> res = new ArrayList<>(resSet);
            Collections.sort(res);
            System.out.printf("#%d %d\n", tc + 1, res.get(res.size() - K));
        }
    }
}
