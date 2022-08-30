package week4.movePipe17070;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 파이프 옯기기 --> DFS라고 생각
 * @author 박준영
 */
public class Main {

    private static final int[] RIGHT = new int[] {0,1};
    private static final int[] DOWN = new int[] {1,0};
    private static final int[] DIAGONAL = new int[] {1,1};

    private static int n, count;   // 집의 크기
    private static int[][] house; // 집 == 집에 벽의 좌표를 확인하기 위해서
    // 이동 방향은 3가지 오른쪽 : 0 아래 : 1 대각선(아래-오른쪽) : 2
    private static int[][] direction = {
            {0,1},{1,0},{1,1}
    };
    // 파이프 클래스
    static class Pip{

        int[] start;    // 파이프의 뒤 좌표
        int[] end;      // 파이프의 앞 좌표
        int[] status;   // 앞 - 뒤 {0,1}-> 오른쪽 {1,0} -> 아래 {1,1} -> 대각선

        public Pip(int[] start, int[] end) {
            this.start = start;
            this.end = end;
            this.status = getStatus();
        }

        public int[] getStatus() {
            int x = end[0] - start[0];
            int y = end[1] - start[1];
            return new int[] {x, y};
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pip pip = (Pip) o;
            return Arrays.equals(status, pip.status);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(status);
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Pip{");
            sb.append("start=").append(Arrays.toString(start));
            sb.append(", end=").append(Arrays.toString(end));
            sb.append(", status=").append(Arrays.toString(status));
            sb.append('}');
            return sb.toString();
        }
    }

    private static void dfs(Pip pip) {

        // N*N 좌표에 도착하면 종료
        if( pip.end[0] == n && pip.end[1] == n) {
            count++;
            return;
        }

        // 오른쪽일 경우
        if( pip.status[0] == RIGHT[0] && pip.status[1] == RIGHT[1]) {
            // 0:오른쪽 2:대각선
            for( int i = 0; i < 3; i+=2) {
                movePip(pip, i);
            }
        }
        // 아래일 경우
        else if( pip.status[0] == DOWN[0] && pip.status[1] == DOWN[1] ) {
            // 1:아래 2:대각선
            for( int i = 1; i < 3; i++) {
                movePip(pip, i);
            }
        }
        // 대각선일 경우
        else if( pip.status[0] == DIAGONAL[0] && pip.status[1] == DIAGONAL[1] ) {
            // 0:오른쪽 1:아래 2:대각선
            for( int i = 0; i < 3; i++) {
                movePip(pip, i);
            }
        }

    }

    private static void movePip(Pip pip, int i) {
        int x = pip.end[0] + direction[i][0];
        int y = pip.end[1] + direction[i][1];
        // 파이프를 밀었을 때 범위를 벗어나는지 확인
        if( isValiable(x,y) ) return;
        // 이동한 곳이 오른쪽인 경우 이동한 곳이 벽이면 이동불가
        if( (i == 0 || i == 1 ) && house[x][y] == 1) return;
        // 이동한 곳이 대각선일 경우 이동한 곳이 벽이면 이동불가
        if( i == 2 && (house[x - 1][y] == 1 || house[x][y] == 1 || house[x][y - 1] == 1)) return;
//        System.out.println(new Pip(pip.end,new int[] {x,y}));
        dfs(new Pip(pip.end,new int[] {x,y}));
    }

    private static boolean isValiable(int r, int c) {
        if( r <= n && c <= n ) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {

        // 입력 데이터 => 집의 크기 N, 집의 상태 행렬
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(in.readLine());
        long start = System.nanoTime();
        n = Integer.parseInt(input.nextToken());    // 집의 크기 ( 3 <= n <= 16 );
        house = new int[n + 1][ n + 1];             // 파이프가 1,1부터 시작하기 때문에 n + 1로 선언
        Pip pip = new Pip(new int[]{1,1}, new int[]{1,2}); // 파이프 초기화

        for(int r = 1; r <= n; r++) {
            input = new StringTokenizer(in.readLine());
            for(int c = 1; c <= n; c++) {
                house[r][c] = Integer.parseInt(input.nextToken());
            }
//            System.out.println(Arrays.toString(house[r]));
        }
        // 파이프 상태 확인
//        System.out.println(pip);

        dfs(pip);

        System.out.println(count);
        long end = System.nanoTime();
        System.out.println((end - start) / 1_000_000_000 + "s");
    }

}
