package week3.robot2174;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken()), B = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());

        int[][] robotPos = new int[N][2];
        char[] robotDir = new char[N];
        char[] direction = {'N', 'E', 'S', 'W'};
        // 로봇 초기 위치 및 방향
        for(int n = 0; n < N; ++n) {
            st = new StringTokenizer(br.readLine());
            robotPos[n][0] = Integer.parseInt(st.nextToken()) - 1;     // x
            robotPos[n][1] = Integer.parseInt(st.nextToken()) - 1;     // y
            robotDir[n] = st.nextToken().charAt(0);
        }

        // 명령을내리는 순서
        for(int m = 0; m < M; ++m) {
            st = new StringTokenizer(br.readLine());
            int robotNum = Integer.parseInt(st.nextToken()) - 1;
            char command = st.nextToken().charAt(0);
            int repeated = Integer.parseInt(st.nextToken());

            if(command == 'L') {    // 왼쪽으로 90도 이동한다
                // 반복 횟수 1이면 90 / 5이면 90
                repeated %= 4;
                int dir = -1;
                for(int i = 0; i < direction.length; ++i) {
                    if(direction[i] == robotDir[robotNum])
                        dir = i;
                }
                dir -= repeated;
                if(dir < 0)
                    dir += 4;
                robotDir[robotNum] = direction[dir];

            } else if (command =='R') { // 오른쪽으로 90도 이동한다.
                repeated %= 4;
                int dir = -1;
                for(int i = 0; i < direction.length; ++i) {
                    if(direction[i] == robotDir[robotNum])
                        dir = i;
                }
                dir += repeated;
                if(dir > 3)
                    dir -= 4;
                robotDir[robotNum] = direction[dir];
            } else if (command == 'F') {    // 로봇이 향하고 있는 방향을 기준으로 앞으로 한 칸
                if(robotDir[robotNum] == 'N') {
                    int crashRobotIndex = -1, distance = Integer.MAX_VALUE;
                    for(int r = 0; r < robotPos.length; ++r) {
                        if(r != robotNum && robotPos[r][0] == robotPos[robotNum][0] && robotPos[robotNum][1] <= robotPos[r][1] && robotPos[robotNum][1] + repeated >= robotPos[r][1]) {
                            // 여러대의 로봇이 있을 경우 가장 가까운 로봇에 충돌 / 부딪치는 것 중에 현재 위치와 차이가 많이 나는 로봇에 충돌
                            if(distance > robotPos[r][1] - robotPos[robotNum][1]) {
                                distance = robotPos[r][1] - robotPos[robotNum][1];
                                crashRobotIndex = r;
                            }
                        }
                    }
                    if(crashRobotIndex != -1) {
                        System.out.printf("Robot %d crashes into robot %d", robotNum + 1, crashRobotIndex + 1);
                        return;
                    }
                    robotPos[robotNum][1] += repeated;

                } else if(robotDir[robotNum] == 'E'){
                    int crashRobotIndex = -1, distance = Integer.MAX_VALUE;
                    for(int r = 0; r < robotPos.length; ++r) {
                        if(r != robotNum && robotPos[r][1] == robotPos[robotNum][1] && robotPos[robotNum][0] <= robotPos[r][0] && robotPos[robotNum][0] + repeated >= robotPos[r][0]) {
                            if(distance > robotPos[r][0] - robotPos[robotNum][0]){
                                distance = robotPos[r][0] - robotPos[robotNum][0];
                                crashRobotIndex = r;
                            }
                        }
                    }
                    if(crashRobotIndex != -1) {
                        System.out.printf("Robot %d crashes into robot %d", robotNum + 1, crashRobotIndex + 1);
                        return;
                    }
                    robotPos[robotNum][0] += repeated;
                } else if(robotDir[robotNum] == 'S'){
                    int crashRobotIndex = -1, distance = Integer.MAX_VALUE;
                    for(int r = 0; r < robotPos.length; ++r) {
                        if(r != robotNum && robotPos[r][0] == robotPos[robotNum][0] && robotPos[robotNum][1] >= robotPos[r][1] && robotPos[robotNum][1] - repeated <= robotPos[r][1]) {
                            if(distance > robotPos[robotNum][1] - robotPos[r][1]){
                                distance = robotPos[robotNum][1] - robotPos[r][1];
                                crashRobotIndex = r;
                            }
                        }
                    }
                    if(crashRobotIndex != -1) {
                        System.out.printf("Robot %d crashes into robot %d", robotNum + 1, crashRobotIndex + 1);
                        return;
                    }
                    robotPos[robotNum][1] -= repeated;
                } else if(robotDir[robotNum] == 'W'){
                    int crashRobotIndex = -1, distance = Integer.MAX_VALUE;
                    for(int r = 0; r < robotPos.length; ++r) {
                        if(r != robotNum && robotPos[r][1] == robotPos[robotNum][1] && robotPos[robotNum][0] >= robotPos[r][0] && robotPos[robotNum][0] - repeated <= robotPos[r][0]) {
                            if(distance > robotPos[robotNum][0] - robotPos[r][0]){
                                distance = robotPos[robotNum][0] - robotPos[r][0];
                                crashRobotIndex = r;
                            }
                        }
                    }
                    if(crashRobotIndex != -1) {
                        System.out.printf("Robot %d crashes into robot %d", robotNum + 1, crashRobotIndex + 1);
                        return;
                    }
                    robotPos[robotNum][0] -= repeated;
                }
            }
            if(robotPos[robotNum][0] > A || robotPos[robotNum][0] < 0 || robotPos[robotNum][1] > B || robotPos[robotNum][1] < 0){

                System.out.printf("Robot %d crashes into the wall\n", robotNum + 1);
                return;
            }
        }

        System.out.println("OK");

    }
}

