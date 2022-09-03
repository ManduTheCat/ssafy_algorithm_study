package week5.snake3190;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dirR = { 0, 1, 0, -1 }; // 우하좌상
	static int[] dirC = { 1, 0, -1, 0 };
	static int curR = 1;	// 뱀 머리위치
	static int curC = 1;	// 뱀 머리위치
	static int curDir = 0;	// 뱀 방향
	static LinkedList<Point> apples = new LinkedList<>(); // 사과를 담는 배열
	static Queue<Point> snake = new LinkedList<>(); // 뱀이 있는 위치를 담는 큐
	private static int N;	// 맵 크기
	static boolean isFinished = false;	// 게임이 끝났는지 체크
	
	// 위치 저장을 위한 Point 클래스
	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + c;
			result = prime * result + r;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Point other = (Point) obj;
			if (c != other.c)
				return false;
			if (r != other.r)
				return false;
			return true;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine()); // 보드의 크기
		int K = Integer.parseInt(br.readLine()); // 사과 수
		// 사과 위치 저장
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			apples.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		int L = Integer.parseInt(br.readLine()); // 방향 변환 횟수
		int start = 0;
		snake.add(new Point(1, 1));	// 뱀 초기 위치 추가
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken()); // X초
			String C = st.nextToken();  // 바꾸는 뱡향
			go(start, X);	// X시간까지 정해진 방향으로 간다.
			if (isFinished) {	// 게임이 끝나면 반복문을 멈춰준다.
				break;
			}
      // X초 이후 바뀔 방향 설정
			start = X;	// 시작 시간을 X로 맞춰준다.
			switch (C) {	// X시간 이후에는 방향을 바꿔서 가야하므로 바뀐 방향을 적용해준다.
			case "L":
				curDir = curDir > 0 ? curDir - 1 : 3;
				break;
			case "D":
				curDir = curDir < 3 ? curDir + 1 : 0;
				break;
			}
		}
		// 방향을 바꾼 후에는 쭉 간다.
		if (!isFinished) {
			go(start, 100);
		}
	}

	private static void go(int start, int x) {
		for (int i = start + 1; i <= x; i++) {
			curR += dirR[curDir];
			curC += dirC[curDir];
			if (curR > N || curC > N || curR < 1 || curC < 1) { // 벽에 닿았을 경우 게임 끝.
				System.out.println(i);
				isFinished = true;
				return;
			}
			Point curPoint = new Point(curR, curC);
			if (!snake.contains(curPoint)) {
				snake.add(new Point(curR, curC));	// 현재 위치를 저장해준다.
			} else { // 자기 몸에 닿았을 경우 게임 끝.
				System.out.println(i);
				isFinished = true;
				return;
			}
			if(!apples.contains(curPoint)) {	// 사과 없으면 꼬리 위치 제거
				snake.remove();
			} else {
				apples.remove(curPoint);	// 먹은 사과를 없애준다.
			}
		}

	}
}
