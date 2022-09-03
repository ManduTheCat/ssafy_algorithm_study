package week5.snake3190;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Snake {
		int r, c;

		public Snake(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	// 보드 크기, 사과 개수
	static int N;
	static int K;

	// 보드 = 맵
	static int[][] map;

	// 뱀 정보 큐
	static Deque<Snake> snakeQueue;

	// 언제 , 어느방향으로 바뀔지 담아두기 위한 큐
	static Queue<Integer> changeTimeQueue;
	static Queue<Integer> changeDirQueue;

	// 사과 = 9
	// 뱀 = 1
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(in.readLine());
		K = Integer.parseInt(in.readLine());
		map = new int[N + 1][N + 1];

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(in.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			map[r][c] = 9;
		}

		int L = Integer.parseInt(in.readLine());

		changeTimeQueue = new LinkedList<>();
		changeDirQueue = new LinkedList<>();

		changeDirQueue.add(0); //맨 처음은 오른쪽 방향이기 때문에 따로 추가함
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(in.readLine());
			int time = Integer.parseInt(st.nextToken());
			int dir = 0;
			char temp = st.nextToken().charAt(0);
			if (temp == 'L') {
				dir = -1;
			} else {
				dir = 1;
			}
			changeTimeQueue.add(time);
			changeDirQueue.add(dir);
		}
		snakeQueue = new LinkedList<>();
		snakeQueue.offer(new Snake(1, 1));

		// 현재 뱀 위치
		map[1][1] = 1;
		moveSnake();
	}

	private static void moveSnake() {
		final int[] dx = new int[] { 0, 1, 0, -1 };
		final int[] dy = new int[] { 1, 0, -1, 0 };

		// 처음 뱀의 위치, 방향
		int dr = 1;
		int dc = 1;
		int dir = changeDirQueue.poll();

		dr += dx[dir];
		dc += dy[dir];

		// 시간초 카운트
		int count = 1;
		// 다음 방향이 바뀌는 시간
		int nextTime = changeTimeQueue.poll();		
		
		// 맵을 나가지않고, 자기자신을 만나지 않을 때(1이 아닐때 == 뱀이 아닐 때)까지 반복
		while (isIn(dr, dc)) {			
			// 머리를 다음칸에 넣고
			map[dr][dc] = 1;
			if (map[dr][dc] == 9) { //다음칸이 사과면 -> 꼬리는 그대로.				
				snakeQueue.offerFirst(new Snake(dr, dc));
			} else { //꼬리쪽(큐 끝부분 하나 삭제)
				Snake tempSnake = snakeQueue.pollLast();
				map[tempSnake.r][tempSnake.c] = 0;

				// 머리 추가
				snakeQueue.offerFirst(new Snake(dr, dc));
			}

			// 방향을 바꾸는 시간이 된다면 방향을 바꿔준다.
			// nextTime은 다음 방향 변경시간으로 바꿔줌
			if (count == nextTime) {
				if (!changeTimeQueue.isEmpty()) {
					nextTime = changeTimeQueue.poll();
				} else {
					nextTime = Integer.MAX_VALUE;
				}
				dir += changeDirQueue.poll();
			}
			if (dir == -1) {
				dir = 3;
			}
			dir %= 4;

			dr += dx[dir];
			dc += dy[dir];
			count++;
		}

		System.out.println(count);
	}

	private static boolean isIn(int r, int c) {
		if (r >= 1 && c >= 1 && r <= N && c <= N && map[r][c] != 1) {
			return true;
		}
		return false;
	}

}
