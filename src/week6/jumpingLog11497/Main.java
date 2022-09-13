package week6.jumpingLog11497;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    public static boolean isValid(int x, int y, int[][] board) {
        return x >= 0 && x < board[0].length && y >= 0 && y < board.length;
    }

    private static void print(int[][] board) {
        for(int[] b : board) {
            System.out.println(Arrays.toString(b));
        }
        System.out.println();
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int W = Integer.parseInt(st.nextToken()), H = Integer.parseInt(st.nextToken());

        int[][] board = new int[H][W];
        for(int h = 0; h < H; ++h) {
            st = new StringTokenizer(br.readLine());
            for(int w = 0; w < W; ++w) {
                board[h][w] = Integer.parseInt(st.nextToken()) * -1;
            }
        }

        LinkedList<int[]> next = new LinkedList<>();
        next.addFirst(new int[] {0, 0, 0});
        // 사방 + 말
        int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
        int[] hdx = {-1, 1, -2, 2, -2, 2, -1, 1}, hdy = {-2, -2, -1, -1, 1, 1, 2, 2};
        boolean[][][] visit = new boolean[board.length][board[0].length][K+1];

        int count = 1;
        visit[0][0][0] = true;
        while(!next.isEmpty()) {

//            next.forEach(a -> System.out.println(Arrays.toString(a)));

            int size = next.size();
            for(int s = 0; s < size; ++s) {
                int[] p = next.pollFirst();

                if(p[0] == board[0].length - 1 && p[1] == board.length - 1) {
                    System.out.println(count - 1);
                    return;
                }

                // 사방 탐색
                for (int d = 0; d < dx.length; ++d) {
                    int nx = p[0] + dx[d], ny = p[1] + dy[d], k = p[2];
                    if (isValid(nx, ny, board) && board[ny][nx] != -1 && !visit[ny][nx][k]) {
                        visit[ny][nx][k] = true;
                        board[ny][nx] = count;
                        next.addLast(new int[]{nx, ny, k});
                    }
                }

                for(int d = 0; d < hdx.length; ++d) {
                    int nx = p[0] + hdx[d], ny = p[1] + hdy[d], k = p[2] + 1;
                    if (isValid(nx, ny, board) && board[ny][nx] != -1 && k <= K && !visit[ny][nx][k]) {
                        visit[ny][nx][k] = true;
                        board[ny][nx] = count;
                        next.addLast(new int[]{nx, ny, k});
                    }
                }
            }

//            System.out.println("=====================");
//            print(board);
            count++;
        }
//        print(board);

        System.out.println(-1);


    }
}
