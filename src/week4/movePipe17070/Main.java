package week4.movePipe17070;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
	private static char[][] map;
	/** 지도 저장 */
	private static int N;
	/** 크기 */
	private static int result = 0;

	/** 결과 */

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N + 1][N + 1]; // 시작을 1부터하기 위해 1을 더해준다
		for (int r = 1; r <= N; r++) {
			String s = br.readLine();
			for (int c = 1, index = 0; c <= N; c++, index += 2) {
				map[r][c] = s.charAt(index);
			}
		}
		go(1, 2, 1); // 시작 파이프는 (1,2)인 가로파이프
		System.out.println(result);
	}

	/**
	 * 
	 * @Method Name : go
	 * @Method info : type == 1: 가로, type == 2: 세로, type == 3: 대각선
	 * @param r
	 * @param c
	 * @param type
	 */
	private static void go(int r, int c, int type) {
		switch (type) {
		case 1:
			// 가로
			if (canGo(r, c + 1, 1)) {
				go(r, c + 1, 1);
			}
			// 대각선
			if (canGo(r + 1, c + 1, 3)) {
				go(r + 1, c + 1, 3);
			}
			break;
		case 2:

			// 세로
			if (canGo(r + 1, c, 2)) {
				go(r + 1, c, 2);
			}
			// 대각선
			if (canGo(r + 1, c + 1, 3)) {
				go(r + 1, c + 1, 3);
			}
			break;
		case 3:
			// 가로
			if (canGo(r, c + 1, 1)) {
				go(r, c + 1, 1);
			}

			// 세로
			if (canGo(r + 1, c, 2)) {
				go(r + 1, c, 2);
			}
			// 대각선
			if (canGo(r + 1, c + 1, 3)) {
				go(r + 1, c + 1, 3);
			}

			break;
		}
	}

	/**
	 * 
	 * @Method Name : canGo
	 * @Method info : 파이프를 연결할 수 있는지 확인하는 함수 + 도착여부 확인
	 * @param r
	 * @param c
	 * @param type
	 * @return
	 */
	private static boolean canGo(int r, int c, int type) {
		if (r > N || c > N || map[r][c] == '1' || (type == 3 && (map[r][c - 1] == '1' || map[r - 1][c] == '1'))) {
			return false;
		} else if (r == N && c == N) {
			result++;
			return false;
		}
		return true;
	}
}
