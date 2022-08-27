package week4.movePipe17070;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] pipeMap;
	static int N;

	static int result = 0;

	// 파이프의 끝점을 기준으로 계산하기
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());

		pipeMap = new int[N][N];
		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int c = 0; c < N; c++) {
				pipeMap[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 처음 파이프의 방향
		dfs(0, 1, 0);
		System.out.println(result);
	}

	private static void dfs(int r, int c, int dir) {
		if (r == N - 1 && c == N - 1) {
			result++;
			return;
		}
		// 가로일때
		if (dir == 0) {
			if (isIn(r, c + 1)) {
				dfs(r, c + 1, 0);
			}
			if (isIn(r + 1, c + 1) && check(r, c)) {
				dfs(r + 1, c + 1, 1);
			}
		}
		// 대각선일 때
		if (dir == 1) {
			if (isIn(r, c + 1)) {
				dfs(r, c + 1, 0);
			}

			if (isIn(r + 1, c + 1) && check(r, c)) {
				dfs(r + 1, c + 1, 1);
			}

			if (isIn(r + 1, c)) {
				dfs(r + 1, c, 2);
			}
		}

		//세로일 때
		if (dir == 2) {
			if (isIn(r + 1, c)) {
				dfs(r + 1, c, 2);
			}
			if (isIn(r + 1, c + 1) && check(r, c)) {
				dfs(r + 1, c + 1, 1);
			}
		}
	}

	// 대각선일 때 1이 있는지 검사
	private static boolean check(int r, int c) {
		if (pipeMap[r + 1][c] == 1 || pipeMap[r][c + 1] == 1) {
			return false;
		}
		return true;
	}

	// 범위를 넘지 않는지 검사
	private static boolean isIn(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < N && pipeMap[r][c] != 1) {
			return true;
		}
		return false;
	}

}
