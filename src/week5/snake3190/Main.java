package week5.snake3190;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int N;	// 보드의 크기
	static int board[][];	// 보드(0: 빈칸, 1: 뱀, 2: 사과)
	static char turn[] = new char[10001];	// 뱀의 방향 변환 정보
	
	static Deque<Point> snake;	// 뱀의 머리~꼬리 좌표 저장
	static int dir[][] = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}}; // 상 좌 하 우 (반시계방향)
	static int snakeDir;	// 뱀의 현재 방향
	static int time = 0;		// 게임 종료 시각

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());	// 보드의 크기
		board = new int[N + 1][N + 1];	// 보드 초기화 (인덱스 0은 사용 X)
		board[1][1] = 1;	// 뱀 시작 위치
		snake = new ArrayDeque<>();
		snake.addFirst(new Point(1, 1));
		snakeDir = 3;
		int K = Integer.parseInt(br.readLine());	// 사과의 개수
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			board[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 2;	// 사과의 위치 표시
		}
		int L = Integer.parseInt(br.readLine());	// 뱀의 방향 변환 횟수
		for(int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			turn[Integer.parseInt(st.nextToken())] = st.nextToken().charAt(0);	// 뱀의 방향 변환 정보 저장
		}
		
		play();
		
		System.out.println(time);
	}
	
	private static void play() {
		while(true) {
			time++;	// 시간 경과
			if(!moveSnake()) {	// 벽 또는 자기 자신의 몸과 부딪히면
				break;	// 게임 종료
			}
			// 왼쪽으로 90도 방향 회전(반시계)
			if(turn[time] == 'L') {
				snakeDir = (snakeDir + 1) % 4;
			}
			// 오른쪽으로 90도 방향 회전(시계)
			else if(turn[time] == 'D') {
				snakeDir = ((snakeDir - 1) + 4) % 4;
			}
		}
	}
	
	private static boolean moveSnake() {
		Point head = snake.getFirst();
		// 머리 다음 칸 계산
		int nr = head.x + dir[snakeDir][0];
		int nc = head.y + dir[snakeDir][1];
		// 벽 또는 자기 자신의 몸과 부딪히면 게임 종료
		if(nr < 1 || nc < 1 || nr > N || nc > N || board[nr][nc] == 1) return false;
		// 머리를 다음 칸에 위치시킴 (이동한 칸에 사과가 있다면 사과 없어짐)
		snake.addFirst(new Point(nr, nc));
		// 이동한 칸에 사과가 없다면 꼬리가 위치한 칸 비움
		if(board[nr][nc] == 0) {
			Point temp = snake.pollLast();
			board[temp.x][temp.y] = 0;
		}
		board[nr][nc] = 1;
		return true;
	}
}
