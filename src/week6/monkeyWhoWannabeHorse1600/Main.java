package week6.monkeyWhoWannabeHorse1600;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


class Monkey {
    int row;
    int col;
    int depth;
    int remainHorseMode;


    public Monkey(int row, int col, int depth, int remainHorseMode) {
        this.row = row;
        this.col = col;
        this.depth = depth;
        this.remainHorseMode = remainHorseMode;
    }

}

public class Main {
    static int K;
    static int W, H;
    static boolean[][][] visited;
    static int[][] horseModeAlpha = new int[][]{{-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}};
    static int[][] monkeyAlpha = new int[][]{{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("resources/study/week6/BJ1600/input3.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        visited = new boolean[H][W][K + 1];
        for (int row = 0; row < H; row++) {
            st = new StringTokenizer(bf.readLine());
            for (int col = 0; col < W; col++) {
                int val = Integer.parseInt(st.nextToken());
                if (val == 1) {
                    for (int i = 0; i <= K; i++) {
                        visited[row][col][i] = true;
                    }
                }
            }
        }
        bfs();
        // bfs로 해결할거다
        // 단 두가지 (가지)경우에 대해 본다
        // 1. 말로 이동가능한경우 이동한다 넘길때 horseMode count 내린다;
        // 2. 원숭이로 이동가능한경우 이동한다 넘길때 horseModeCount 건들지 않는다.
        System.out.println(-1);
    }

    public static void bfs() {
        Queue<Monkey> q = new LinkedList<>();
        visited[0][0][K] = true;
        q.add(new Monkey(0, 0, 0, K));

        while (!q.isEmpty()) {
            Monkey currMonkey = q.poll();
            if (currMonkey.row == H - 1 && currMonkey.col == W - 1) {
                System.out.println(currMonkey.depth);
                System.exit(0);
            }
            // 말처럼 이동하는 부분 말처럼 이동하고 이동할수 있는 remainHorseMode 를 하나 내린다.
            if (currMonkey.remainHorseMode > 0) {
                for (int dh = 0; dh < 8; dh++) {
                    int nextRow = currMonkey.row + horseModeAlpha[dh][0];
                    int nextCol = currMonkey.col + horseModeAlpha[dh][1];
                    if (isIn(nextRow, nextCol) && !visited[nextRow][nextCol][currMonkey.remainHorseMode - 1]) {
                        visited[nextRow][nextCol][currMonkey.remainHorseMode - 1] = true;
                        q.add(new Monkey(nextRow, nextCol, currMonkey.depth + 1, currMonkey.remainHorseMode - 1));
                    }
                }
            }

            for (int mh = 0; mh < 4; mh++) {
                int nextRow = currMonkey.row + monkeyAlpha[mh][0];
                int nextCol = currMonkey.col + monkeyAlpha[mh][1];
                if (isIn(nextRow, nextCol) && !visited[nextRow][nextCol][currMonkey.remainHorseMode]) {
                    visited[nextRow][nextCol][currMonkey.remainHorseMode] = true;
                    q.add(new Monkey(nextRow, nextCol, currMonkey.depth + 1, currMonkey.remainHorseMode));
                }
            }
        }
    }

    public static boolean isIn(int row, int col) {
        return row < H && row >= 0 && col < W && col >= 0;
    }

}
