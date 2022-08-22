package week3.countingPaper1789;

import java.awt.*;
import java.io.*;
import java.util.*;

//	13156KB	96ms

/**
 * 좌표, 현제 방향을 가지고 있는 로봇 클래스
 */
class Robot {

    public Point point;
    public char curDirection;

    public Robot(Point point, char curDirection) {
        this.point = point;
        this.curDirection = curDirection;
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Robot{");
        sb.append("point=").append(point);
        sb.append(", curDirection=").append(curDirection);
        sb.append('}');
        return sb.toString();
    }
}

/**
 * 1. 명령어 를 F R L로 분류한다
 * 2. R L 일경우 순서 대로 돌려서 해당 로봇객체의 방향 WSEN 중하나로 바꿔준다
 * 3. F 일경우 반복 횟수만큼 이동 맵밖으로 나갔는지 || 충돌이 났는지 확인한다.
 */
public class Main {
    static int N;
    static int M;
    static int R;
    static int C;
    static ArrayList<Robot> robotList;
    static boolean crashFlag;
    static boolean outFlag;

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("resources/study/week3/BJ2174/input1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //A, B 지만 햇갈려서 R C 로 대채함
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        robotList = new ArrayList<>();
        // 입력받은 로봇들을 robotList 에 순서대로 넣는다.
        // 로봇은 무조건 순서대로주기 때문에 인덱스 번호 는 로봇의 번호이다.
        for (int n = 1; n < N + 1; n++) {
            st = new StringTokenizer(br.readLine());
            int pointCol = Integer.parseInt(st.nextToken()) - 1;
            int pointRow = Integer.parseInt(st.nextToken()) - 1;
            robotList.add(new Robot(
                    new Point(pointRow, pointCol),
                    st.nextToken().charAt(0))
            );
        }
        // 명령어 받고 처리하는 함수로 보넴

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            // 인덱스 번호 0번부터 시작하게 변경
            int targetRobot = Integer.parseInt(st.nextToken()) - 1;
            char command = st.nextToken().charAt(0);
            int repeat = Integer.parseInt(st.nextToken());
            runCommand(targetRobot, command, repeat);
            if(crashFlag || outFlag)System.exit(0);
        }

        System.out.println("OK");

    }

    // FLR 명령어 분류
    static void runCommand(int targetRobot, char command, int repeat) {
        switch (command) {
            case 'F':
                goForward(targetRobot, repeat);
                break;
            case 'L':
                turnLeft(targetRobot, repeat);
                break;
            case 'R':
                turnRight(targetRobot, repeat);
                break;
        }
    }

    // 앞으로 반복 횟수만큼 이동하는 함수
    // 1.벽부딪치는지 || 로봇 끼리 부딪치는지 확인.
    // 2.curDirection 방향으로 repeat 만큼 이동
    // 우리가 자주쓰던 방향에서 위아래 반대이다 = 기존쓰던 방향에서 N, S 만 바꿔주면된다.
    static void goForward(int targetRobot, int repeat) {
        Map<Character, Integer[]> direction = new HashMap<>();
        direction.put('N', new Integer[]{1, 0});
        direction.put('W', new Integer[]{0, -1});
        direction.put('S', new Integer[]{-1, 0});
        direction.put('E', new Integer[]{0, 1});
        Robot currRobot = robotList.get(targetRobot);
        Integer[] currDirection = direction.get(currRobot.curDirection);
        for (int t = 0; t < repeat; t++) {
            currRobot.point.x += currDirection[0];
            currRobot.point.y += currDirection[1];
            if (isCrash(targetRobot)|| isOut(currRobot,targetRobot)) {
                return;
            }
        }
    }

    public static boolean isCrash(int targetRobot) {

        for (int i = 0; i < N; i++) {
            if (i != targetRobot &&
                    robotList.get(targetRobot).point.x == robotList.get(i).point.x &&
                    robotList.get(targetRobot).point.y == robotList.get(i).point.y
            ) {
                System.out.println("Robot " + (targetRobot+1) + " crashes into robot " + (i + 1));
                crashFlag = true;
                return true;
            }
        }
        return false;
    }

    // 지도 밖으로 나가는지 확인
    public static boolean isOut(Robot currRobot, int targetNumber) {
        if(currRobot.point.x < R && currRobot.point.x >= 0 && currRobot.point.y < C && currRobot.point.y >=0){
            return false;
        }
        outFlag = true;
        System.out.println("Robot " +(targetNumber+1)+ " crashes into the wall");
        return true;

    }

    static void turnLeft(int targetRobot, int repeat) {
        // 스팰링이 왼쪽 방향 순서 에 맞게 바뀜
        // N -> W -> S -> E
        char[] leftDirection = new char[]{'N', 'W', 'S', 'E'};
        Robot curRobot = robotList.get(targetRobot);
        int curIndex = -1;
        // 방향 전환을 위해 대상 로봇 leftDirection 에 맞는 index 찾음
        for (int i = 0; i < 4; i++) {
            if (leftDirection[i] == curRobot.curDirection) {
                curIndex = i;
            }
        }
        int nextIndex = (curIndex + repeat) % 4;
        curRobot.curDirection = leftDirection[nextIndex];
    }

    static void turnRight(int targetRobot, int repeat) {
        // 스팰링이 오른쪽 방향 순서에 따라 바뀜
        // N->E->S->E
        char[] rightDirection = new char[]{'N', 'E', 'S', 'W'};
        Robot curRobot = robotList.get(targetRobot);
        int curIndex = -1;

        // 방향 전환을 위해 대상 로봇 rightDirection 에 맞는 index 찾음
        for (int i = 0; i < 4; i++) {
            if (rightDirection[i] == curRobot.curDirection) {
                curIndex = i;
            }
        }
        int nextIndex = (curIndex + repeat) % 4;
        curRobot.curDirection = rightDirection[nextIndex];
    }
}
