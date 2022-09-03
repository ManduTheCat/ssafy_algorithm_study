package week5.snake3190;

import java.io.*;
import java.util.*;

public class Main {

    public static class Pos{
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Snake{
        Pos head;
        Deque<Pos> body;
        int dir;

        public Snake(Pos head, Deque<Pos> body, int dir) {
            this.head = head;
            this.body = body;
            this.dir = dir;
        }
    }

    public static int n;
    public static int[][] map;
    public static Snake snake;

    public static int[] dx = {0, 1, 0, -1};
    public static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        map = new int[n+1][n+1];
        while(k-->0){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            map[x][y] = 1;
        }

        snake = new Snake(new Pos(1, 1), new ArrayDeque<>(), 0);
        map[1][1] = 2;

        int time = 0;
        int l = Integer.parseInt(br.readLine());
        int[] xs = new int[l+1];
        char[] comds = new char[l+1];

        for(int i = 0; i < l; i++){
            st = new StringTokenizer(br.readLine());
            xs[i] = Integer.parseInt(st.nextToken());
            comds[i] = st.nextToken().charAt(0);
        }

        xs[l] = 10000000;
        comds[l] = 'R';

        int idx = 0;

        while(true){
            int x = xs[idx];
            char comd = comds[idx++];

            while(time < x){
                time++;
                if(!move()){
                    System.out.println(time);
                    return;
                }
            }

            if(comd == 'D') snake.dir = (snake.dir+1) % 4;
            else if(comd == 'L') snake.dir = (snake.dir-1+4) % 4;
        }

    }

    public static boolean move(){
        int dir = snake.dir;
        int x = snake.head.x;
        int y = snake.head.y;

        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if(!isRange(nx, ny) || map[nx][ny] == 2) return false;

        if(map[nx][ny] == 1){
            snake.body.offerFirst(new Pos(x, y));
        } else{
            if(!snake.body.isEmpty()){
                Pos tail = snake.body.pollLast();
                map[tail.x][tail.y] = 0;
                snake.body.offerFirst(new Pos(x, y));
            } else{
                map[x][y] = 0;
            }
        }

        snake.head.x = nx;
        snake.head.y = ny;
        map[nx][ny] = 2;

        return true;
    }

    public static boolean isRange(int x, int y){
        return x > 0 && x <= n && y > 0 && y <= n;
    }

}
