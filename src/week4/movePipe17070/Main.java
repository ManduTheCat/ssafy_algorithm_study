package week4.movePipe17070;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Pipe {
    int row, col; // 파이프 끝자락 좌표기억
    int position; //파이프의 현재 상태 0 가로 //1 세로 // 2대각선

    public Pipe(int row, int col, int position) {
        this.row = row;
        this.col = col;
        this.position = position;
    }

}

public class Main {
    //갈수 있는 방향은 현재 파이프가 놓인 방향에 따라 정해진다
    //갈수 없을땐 안가는 가지치기가 가능한 dfs 문제라 생각합니다
    //단 경로를 갔던곳도 가기위해 dfs 재귀를 를 하면서 체크를 해재 해야합니다.
    static int N;
    //static int [][] map;
    static boolean[][] check;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("resources/study/week4/BJ17070/input3.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        check = new boolean[N][N];
        for (int row = 0; row < N; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                int val = Integer.parseInt(st.nextToken());
                if (val == 1) {
                    check[row][col] = true;
                }
            }
        }
        check[0][1] = true;
        dfs(new Pipe(0, 1, 0));
        System.out.println(count);

    }

    public static void dfs(Pipe pipe) {

        // dfs 종료 조건 목표 좌표에 도착하면 count 하고 종료
        if (pipe.row == N - 1 && pipe.col == N - 1) {
            count++;
            return;
        }
        // 상태에 따라 갈수 있는 방향이 다르다
        // 가로 일때 갈수 있는 두가지
        if (pipe.position == 0) {
            // 가로 -> 가로
            if (isIn(pipe.row, pipe.col + 1) && !check[pipe.row][pipe.col + 1]) {
                check[pipe.row][pipe.col + 1] = true;
                dfs(new Pipe(pipe.row, pipe.col + 1, 0));
                check[pipe.row][pipe.col + 1] = false;
            }
            // 가로 -> 대각 //3가지 지점이 비어있어야한다.
            if (isIn(pipe.row + 1, pipe.col + 1) && checkDiagonal(pipe.row, pipe.col)) {
                check[pipe.row + 1][pipe.col + 1] = true;
                dfs(new Pipe(pipe.row + 1, pipe.col + 1, 2));
                check[pipe.row + 1][pipe.col + 1] = false;
            }
        }
        if (pipe.position == 1) {
            // 세로일때 갈수 있는 두가지 경우
            if (isIn(pipe.row + 1, pipe.col) && !check[pipe.row + 1][pipe.col]) {
                check[pipe.row + 1][pipe.col] = true;
                dfs(new Pipe(pipe.row + 1, pipe.col, 1));
                check[pipe.row + 1][pipe.col] = false;
            }
            // 세로 -> 대각
            if (isIn(pipe.row + 1, pipe.col + 1) && checkDiagonal(pipe.row, pipe.col)) {
                check[pipe.row + 1][pipe.col + 1] = true;
                dfs(new Pipe(pipe.row + 1, pipe.col + 1, 2));
                check[pipe.row + 1][pipe.col + 1] = false;
            }
        }
        if (pipe.position == 2) {
            // 대각일때 갈수 있는 세가지 경우
            //대각 -> 대각 선택하는경우
            if (isIn(pipe.row + 1, pipe.col + 1) && checkDiagonal(pipe.row, pipe.col)) {
                check[pipe.row + 1][pipe.col + 1] = true;
                dfs(new Pipe(pipe.row + 1, pipe.col + 1, 2));
                check[pipe.row + 1][pipe.col + 1] = false;
            }
            // 대각 -> 세로 선택하는경우
            if (isIn(pipe.row + 1, pipe.col) && !check[pipe.row + 1][pipe.col]) {
                check[pipe.row + 1][pipe.col] = true;
                dfs(new Pipe(pipe.row + 1, pipe.col, 1));
                check[pipe.row + 1][pipe.col] = false;

            }// 대각 -> 가로 선택하는경우
            if (isIn(pipe.row, pipe.col + 1) && !check[pipe.row][pipe.col + 1]) {
                check[pipe.row][pipe.col + 1] = true;
                dfs(new Pipe(pipe.row, pipe.col + 1, 0));
                check[pipe.row][pipe.col + 1] = false;
            }
        }
    }

    // 대각선 가기 전에 칸 3곳이 비엇는지 (갈수 있는 경우면)체크 만약 다 비어 있으면 true 리턴
    public static boolean checkDiagonal(int row, int col) {
        return !(check[row][col + 1] | check[row + 1][col] | check[row + 1][col + 1]);
    }

    // 다음 갈곳이 갈수 있는 곳인지 판단하기 위한 메소드
    public static boolean isIn(int row, int col) {
        return row < N && row >= 0 && col < N && col >= 0;
    }
}
