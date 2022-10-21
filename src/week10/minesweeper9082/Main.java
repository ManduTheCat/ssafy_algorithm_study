package week10.minesweeper9082;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N, nums[], bombs[], ans;

    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(in.readLine());

        for (int t = 0; t < T; t++) {
            // 1. 입력 : 받을 때  숫자중에서는 (0,3), 문자 중에서는 () 체킹
            N = Integer.parseInt(in.readLine());
            nums = new int[N];
            bombs = new int[N];
            Arrays.fill(bombs, -1);

            String num = in.readLine();
            for (int i = 0; i < N; i++) {
                nums[i] = num.charAt(i) - '0';
                if(nums[i] == 0 || nums[i] == 3) {
                    bombs[i] = nums[i] == 0? 0: nums[i] == 3? 1: -1;
                    if(i + 1 < N) bombs[i + 1] = nums[i] == 0? 0: nums[i] == 3? 1: -1;
                    if(i - 1 >= 0) bombs[i - 1] = nums[i] == 0? 0: nums[i] == 3? 1: -1;
                }
            }

            String chrs = in.readLine();
            for (int i = 0; i < N; i++) {
                if(chrs.charAt(i) == '*') bombs[i] = 1;
            }

            int[] check = new int[N];
            Arrays.fill(check, -1);

            ans = 0;
            // 2. 폭탄 채워주기? - 1번 먼저 판별해서 2번 돌리기
            // 2-1. 1번이 bombs[i] == -1 일 경우 -> nums[1] == (1, 2) 임
            if(bombs[0] == -1) {
                if(nums[0] == 2) {
                    check[0] = 1;
                    check[1] = 1;
                    setBomb(1, check);
                } else {
                    // 2-1-1. [0, 1, X...] 설정 후 재귀문 돌려주기
                    check[0] = 0;
                    check[1] = 1;
                    setBomb(1, check);

                    // 2-1-2. [1, 0, X...] 설정 후 재귀문 돌려주기
                    Arrays.fill(check, -1);
                    check[0] = 1;
                    check[1] = 0;
                    setBomb(1, check);
                }
            }

            // 2-2. 1번이 bombs[i] == (0, 1) 일 경우 - 이미 판별되어 버림 -> 확실한 값 -> 재귀문 돌려주기
            else {
                check[0] = bombs[0];
                check[1] = nums[0] - check[0];
                setBomb(1, check);
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    // 3. 재귀문 - 1번(depth)부터 체크
    private static void setBomb(int depth, int[] check) {
        // (1) 베이스케이스 : 폭탄 다 채우면? 폭탄 갯수 세서 최댓값 구해주기
        if(depth == N - 1) {
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                if(check[i] == 1) cnt++;
            }
            ans = Math.max(ans, cnt);
            return;
        }

        // (2) 확실한 폭탄 값이 있다면...채울 필요 없이 체크만?
        if(bombs[depth] != -1 && check[depth] != bombs[depth]) return;

        int value = nums[depth];
        if(check[depth-1] == 1) value--;
        if(check[depth] == 1) value--;

        if(value < 0 || value >= 2) return;
        // (3) 확실한 폭탄 값이 없다면(1,또는 2) 가능한 값을 채워주기
        check[depth+1] = value;

        // (4) 다음 값 탐색하러 ㄱㄱ
        setBomb(depth+1, check);
    }
}

