package week3.robot2174;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
    private static int A, B;
    private static int[][] pos = {{1,0},{0,1},{-1,0},{0,-1}}; // 상좌하우
    private static int[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());

        for ( int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            boolean valiable = true;                // 계속 명령을 수행해도 되는지 확인판단
            A = Integer.parseInt(st.nextToken());   // 땅(지도)의 가로
            B = Integer.parseInt(st.nextToken());   // 땅(지도)의 세로

            map = new int[B + 1][A + 1];
//        printMap();

            st = new StringTokenizer(in.readLine());
            int N = Integer.parseInt(st.nextToken());   // 로봇의 수 시간:1
            int M = Integer.parseInt(st.nextToken());   // 명령의 수 시간:1

            List<int[]> robot = new ArrayList<>();
            robot.add(new int[]{0, 0});
            for (int a = 1; a <= N; a++) {
                st = new StringTokenizer(in.readLine());
                int x = Integer.parseInt(st.nextToken());   // 로봇의 x축 위치
                int y = Integer.parseInt(st.nextToken());   // 로봇의 y축 위치
                int z = getPostion(st.nextToken());         // 가리키고 있는 방향
                map[y][x] = a;                              // 로봇 번호 기입
                robot.add(new int[]{x, y, z});               // 시간:1
            }

            // 명령어 입력
            List<String[]> command = new ArrayList<>();
            for (int b = 0; b < M; b++) {
                st = new StringTokenizer(in.readLine());
                command.add(new String[]{st.nextToken(), st.nextToken(), st.nextToken()});
            }
            
            // 명령수행
            for (int a = 0; a < M; a++) {
                valiable = getCommand(0, command.get(a), robot);
                if (!valiable) {
                    break;
                }
            }
            if (valiable) {
                System.out.println("OK");
            }
        }
    }
    // 입력된 방향을 숫자로 반환
    private static int getPostion(String s) {
        switch ( s ) {
            case "N": return 0;
            case "E": return 1;
            case "S": return 2;
            case "W": return 3;
            default: return -1;
        }
    }

    private static boolean getCommand(int cnt, String[] command , List<int[]> robot) {

        int robot_NM = Integer.parseInt(command[0]);
        if( cnt != 0 ) {
            // 벽에 붙이쳐서 명령 종료!!
            if (robot.get(robot_NM)[1] <= 0 || robot.get(robot_NM)[1] > B ||
                    robot.get(robot_NM)[0] <= 0 || robot.get(robot_NM)[0] > A) {
                System.out.println("Robot " + robot_NM + " crashes into the wall");
                return false;
            }
            // 로봇과 충돌하여 명령 종료!!
            if (map[robot.get(robot_NM)[1]][robot.get(robot_NM)[0]] != 0) {
                System.out.println("Robot " + robot_NM + " crashes into robot " + map[robot.get(robot_NM)[1]][robot.get(robot_NM)[0]]);
                return false;
            } else {    // 충돌하지 않는다면 로봇 이동
                map[robot.get(robot_NM)[1]][robot.get(robot_NM)[0]] = robot_NM;
            }

        }
        // 명령을 모두 수행하면 종료
        if( cnt == Integer.parseInt(command[2]) ) {
            return true;
        }

        map[robot.get(robot_NM)[1]][robot.get(robot_NM)[0]] = 0;

        switch ( command[1] ) {
            case "L":   // 왼쪽으로 90도 회전
                robot.get(robot_NM)[2] += 3;
                if( robot.get(robot_NM)[2] >= 4) robot.get(robot_NM)[2] %= 4;
                return getCommand(cnt + 1, command, robot);
            case "R":  // 오른쪽으로 90도 회전
                robot.get(robot_NM)[2] += 1;
                if( robot.get(robot_NM)[2] >= 4) robot.get(robot_NM)[2] %= 4;
                return getCommand(cnt + 1, command, robot);
            case "F":  // 가리키고 있는 방향 앞으로 한칸 전진
                int way = robot.get(robot_NM)[2];
                robot.get(robot_NM)[0] += pos[way][1];
                robot.get(robot_NM)[1] += pos[way][0];
                return getCommand(cnt + 1, command, robot);
        }

        return true;
    }
}