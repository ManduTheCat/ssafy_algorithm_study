package week5.snake3190;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static LinkedList<int[]> snake;
    private static int n, k,result, map[][];
    // 우 하 좌 상 순으로 방향할당 ( 오른쪽부터 뱀이 시작하기 때문)
    private static int[][] dir = {
            {0,1},{1,0},{0,-1},{-1,0}
    };
    public static void main(String[] args) throws IOException {
        long start = System.nanoTime();
        FileInputStream file = new FileInputStream("./study_algorithm/res/baekjoon/snake.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file ));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in ));

        // 뱀 선언
        snake = new LinkedList<>();
        snake.add(new int[] {1,1,0});
        // 보드의 크기
        n = Integer.parseInt(in.readLine());
        // 사과의 갯수
        k = Integer.parseInt(in.readLine());
        map = new int[n+1][n+1];
        // 사과의 좌표 할당
        for(int i = 1; i <= k; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            map[y][x] = 1;
        }
        // 방향 전환 횟수
        int c = Integer.parseInt(in.readLine());
        // 방향 전환 명령어 저장
        LinkedList<int[]> command = new LinkedList<>();
        for( int j = 1; j <= c; j++ ) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int time = Integer.parseInt(st.nextToken());
            String way = st.nextToken();
            // 오른쪽이면 +1;
            if( way.equals("D") ) {
                command.add(new int[] {time, 1} );
            }
            // 왼쪽이면 +3;
            else if( way.equals("L") ) {
                command.add(new int[] {time, 3} );
            }
        }
        // 보드판 확인
        for(int y = 1; y <= n; y++) {
            for(int x = 1; x <=n; x++) {
                System.out.print(map[y][x] + " ");
            }
            System.out.println();
        }
        // 명령확인
        /*command.forEach(arr -> System.out.println(Arrays.toString(arr)));*/

        // 이동
        move(0,command);
        System.out.println(result);
        long end = System.nanoTime();
        System.out.println((end - start ) / 1_000_000_000 + "s");
    }

    private static void move (int cnt, LinkedList<int[]> command) {

        // 머리만 이동
        int ny = snake.getFirst()[0] + dir[snake.getFirst()[2]][0];
        int nx = snake.getFirst()[1] + dir[snake.getFirst()[2]][1];
        // 벽과 충돌하면 종료 or // 자신의 몸과 충돌해도 종료
        if( wallCrash(ny,nx) || ( snake.size() >= 4 && bodyCrash(ny,nx) ) ) {
//            System.out.println(cnt);
            result = cnt + 1;
            return;
        }

        // 이동한 곳에 사과가 있다면
        if( map[ny][nx] == 1 ) {
            map[ny][nx] = 0;	// 사과를 먹었으니 없애준다.
            snake.addFirst(new int[] { ny, nx, snake.getFirst()[2]});
            // 머리회전
            if( command.size() != 0 && cnt == command.peek()[0] ) {
                snake.getFirst()[2] += command.poll()[1];
                snake.getFirst()[2] %= 4;
            }
            // 다음이동
            move(cnt + 1, command);
        }
        // 사과가 없다면 꼬리도 같이 이동
        else {
            snake.addFirst(new int[] { ny, nx, snake.getFirst()[2]});
            snake.pollLast();
            // 머리회전
            if( command.size() != 0 && cnt == command.peek()[0] ) {
                snake.getFirst()[2] += command.poll()[1];
                snake.getFirst()[2] %= 4;
            }
            // 다음 이동
            move(cnt + 1, command);
        }
    }

    private static boolean wallCrash(int ny, int nx) {
        if( ny > 0 && ny <= n && nx > 0 && nx <= n ) {
            return false;
        }
        return true;
    }

    private static boolean bodyCrash(int ny,int nx) {
            for(int i = 0; i < snake.size(); i++) {
                if( ny == snake.get(i)[0] && nx == snake.get(i)[1] ) {
                    return true;
                }
            }
            return false;
    }
}