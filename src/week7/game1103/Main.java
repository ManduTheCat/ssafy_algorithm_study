import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void setArea(int[][] board, int x1, int y1, int x2, int y2, int value) {

        for(int x = Math.min(x1, x2); x <= Math.max(x1, x2); ++x) {
            for(int y = Math.min(y1, y2); y <= Math.max(y1, y2); ++y) {
                board[y][x] = value;
            }
        }

    }

    public static boolean isValid(int x, int y, int[][] board) {
        return x >= 0 && x < board[0].length && y >= 0 && y < board.length;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1} ;

        int N = Integer.parseInt(br.readLine());

        int[][] board = new int[501][501];
        for(int n = 0; n < N; ++n) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            setArea(board, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 1);
        }

        N = Integer.parseInt(br.readLine());
        for(int n = 0; n < N; ++n) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            setArea(board, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), -1);
//            print(board);
        }


        PriorityQueue<int[]> checkInfo = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        checkInfo.add(new int[] {0, 0, 0}); // y, x, life
        boolean[][] visit = new boolean[501][501];
        visit[0][0] = true; board[0][0] = 0;

        int life = Integer.MAX_VALUE;
        while(!checkInfo.isEmpty()) {

            int[] info = checkInfo.poll();

            for(int d = 0; d < dx.length; ++d) {
                int cx = info[1] + dx[d], cy = info[0] + dy[d];
                if (isValid(cx, cy, board) && board[cy][cx] != -1 && !visit[cy][cx]) {
                    if(board[cy][cx] == 1) {
                        checkInfo.add(new int[]{cy, cx, info[2] + 1});
                        board[cy][cx] = info[2] + 1;
                    } else {
                        checkInfo.add(new int[]{cy, cx, info[2]});
                        board[cy][cx] = info[2];
                    }
                    visit[cy][cx] = true;
                }
            }
        }

//        print(board);

        if(visit[500][500]) {
            System.out.println(board[500][500]);
        } else {
            System.out.println(-1);
        }

    }

    public static void print(int[][] board) {
        for(int[] b : board){
            System.out.println(Arrays.toString(b));
        }
        System.out.println();
    }
}
