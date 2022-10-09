package week3.robot2174;

import java.io.*;
import java.util.*;

public class Main {

    public static class Robot{
        int x, y, dir;

        public Robot(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    public static int a, b, n;
    public static int[][] map;
    public static Robot[] robots;

    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};  // S, E, N, W

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        map = new int[b+1][a+1];

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        robots = new Robot[n+1];
        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int dir = getDir(st.nextToken().charAt(0));

            robots[i] = new Robot(x, y, dir);
            map[x][y] = i;
        }

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            char comd = st.nextToken().charAt(0);
            int times = Integer.parseInt(st.nextToken());

            if(!move(num, comd, times)) return;
        }

        System.out.println("OK");
    }

    public static boolean move(int num, char comd, int times){
        Robot robot = robots[num];
        int x = robot.x;
        int y = robot.y;
        int dir = robot.dir;

        int nx = x;
        int ny = y;

        while(times-->0){
            if(comd == 'L') dir = (dir + 1) % 4;
            else if(comd == 'R') dir = (dir - 1 + 4) % 4;
            else{
                nx += dx[dir];
                ny += dy[dir];

                if(!isRange(nx, ny)) {
                    crashWall(num);
                    return false;
                }

                if(map[nx][ny] != 0){
                    crashRobot(num, map[nx][ny]);
                    return false;
                }
            }
        }

        robot.x = nx;
        robot.y = ny;
        robot.dir = dir;

        map[x][y] = 0;
        map[nx][ny] = num;

        return true;
    }

    public static int getDir(char c){
        if(c == 'S') return 0;
        else if(c == 'E') return 1;
        else if(c == 'N') return 2;
        else return 3;
    }

    public static void crashWall(int num){
        StringBuilder sb = new StringBuilder();
        sb.append("Robot ").append(num).append(" crashes into the wall");
        System.out.println(sb.toString());
    }

    public static void crashRobot(int numX, int numY){
        StringBuilder sb = new StringBuilder();
        sb.append("Robot ").append(numX).append(" crashes into robot ").append(numY);
        System.out.println(sb.toString());
    }

    public static boolean isRange(int x, int y){
        return x >= 1 && x <= b && y >= 1 && y <= a;
    }

}
