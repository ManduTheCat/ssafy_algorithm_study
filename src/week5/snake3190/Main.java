package week5.snake3190;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Command {
    int time; // 명령어의 시간
    char direction; // 명령어 회전방향

    public Command(int time, char direction) {
        this.time = time;
        this.direction = direction;
    }
}

public class Main {
    static int N;
    static int K;
    static int L;
    static int count;
    static Set<Point> apples = new HashSet(); // 사과를 담아놓는 set
    static ArrayList<Command> commands;
    static Point curDirection; // 뱀이 보고 있는 방향
    static Deque<Point> snake = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        // 명령어 변환필요 시간이 누적된상태로 입력되기 때문에
        //System.setIn(new FileInputStream("resources/study/week5/BJ3190/input3.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        for (int a = 0; a < K; a++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            apples.add(new Point(row, col));
        }
        L = Integer.parseInt(br.readLine());
        commands = new ArrayList<>();
        for (int l = 0; l < L; l++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            char direction = st.nextToken().charAt(0);
            commands.add(new Command(time, direction));
        }
        // 명령어 시간 중첩된 상태가 아닌게 바꿉니다
        if (commands.size() > 1) {
            for (int i = commands.size() - 1; i >= 1; i--) {
                commands.get(i).time -= commands.get(i - 1).time;
            }
        }
        // 뱀 큐 초기화
        snake.add(new Point(0, 0));
        // 초기방향은 오르쪽
        curDirection = new Point(0, 1);
        // 명령어 순환하며 실행
        for (Command command : commands) {
            runCommand(command);
        }

        // 모든 명령을 수행했지만 현제 방향에서 앞으로 갈수 있으면 가야한다.
        while (true){
            Point currHead = snake.peekLast();
            Point nextHead = new Point(currHead.x + curDirection.x, currHead.y+ curDirection.y);
            if(isIn(nextHead.x, nextHead.y) && !isTouch(nextHead.x, nextHead.y)){
                count++;
                snake.addLast(nextHead);
                if(!apples.contains(nextHead)){
                    snake.pollFirst();
                }
            }
            else {
                count++;
                break;
            }
        }
        System.out.println(count);
    }

    /**
     * 머리를 왼쪽 또는 오른쪽 명령에 따라 회전하는 함수
     * @param command 입력 받은 명령에 따라 회전한다.
     */
    public static void changeDirection(char command) {
        //머리를 돌리는 D L 각각 방향 순서 다르다
        // 오른쪽 일경우 돌리는 부분
        if (command == 'D') {
            int[][] alpha = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
            int curIndex = 0;
            for (int i = 0; i < 4; i++) {
                if (alpha[i][0] == curDirection.x && alpha[i][1] == curDirection.y) {
                    curIndex = i;
                }
            }
            int nextIndex = (curIndex + 1) % 4;
            curDirection = new Point(alpha[nextIndex][0], alpha[nextIndex][1]);
            // 왼쪽일때 돌리는 부분
        } else if (command == 'L') {
            int[][] alpha = new int[][]{{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
            int curIndex = 0;
            for (int i = 0; i < 4; i++) {
                if (alpha[i][0] == curDirection.x && alpha[i][1] == curDirection.y) {
                    curIndex = i;
                }
            }
            int nextIndex = (curIndex + 1) % 4;
            curDirection = new Point(alpha[nextIndex][0], alpha[nextIndex][1]);
        }
    }

    // 명령을 실행하는 메소드
    public static void runCommand(Command command) {
        // 다음 이동 가능하면
        int time = command.time;
        //시간동안 진행한다
        while (time-- > 0) {
            Point curHead = snake.peekLast();
            Point nextHead = new Point(curHead.x + curDirection.x, curHead.y + curDirection.y);
            // 몸에 닿거나 밖으로 나가면 게임끝 진행
            if (isIn(nextHead.x, nextHead.y) && !isTouch(nextHead.x, nextHead.y)) {
                // 자기몸에 닿지 않고 맵안에 있다면 계속 진행
                snake.addLast(nextHead);
                count++;
                if (!apples.contains(nextHead)) {
                    snake.pollFirst();
                    //꼬리 제거
                }
                // 사과를 먹는경우
                else {
                    apples.remove(nextHead);
                }
            }
            else {
                count++;
                // 자기몸에 닿고 밖으로 나가면 시스탬 종료
                System.out.println(count);
                System.exit(0);
            }
        }
        // 시간이 되면 명령의 방향으로 바꾼다.
        changeDirection(command.direction);
    }

    // 안에 있는지 밖에 있는지 판단 하는 메소드
    public static boolean isIn(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < N;
    }

    // 몸에 닿음을 판단한 함수
    public static boolean isTouch(int row, int col) {
        for (Point body : snake) {
            if (body.x == row && body.y == col) {
                return true;
            }
        }
        return false;
    }
}
