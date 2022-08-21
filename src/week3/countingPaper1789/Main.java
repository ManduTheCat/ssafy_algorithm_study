package week3.countingPaper1789;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int minusOneCnt;
	static int zeroCnt;
	static int plusOneCnt;
	static int[][] paperMap;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(in.readLine());

		paperMap = new int[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(in.readLine());
			for (int c = 0; c < N; c++) {
				paperMap[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력 끝

		// 분할 정복 시작!!
		solution(0, 0, N);
		
		System.out.println(minusOneCnt);
		System.out.println(zeroCnt);
		System.out.println(plusOneCnt);

	}

	private static void solution(int r, int c, int N) {
		//r~N, c~N까지 돌면서 동일한 숫자인지 확인
		if (isCheck(r, c, N)) {
			switch (paperMap[r][c]) {
			case -1:
				minusOneCnt++;
				break;

			case 0:
				zeroCnt++;
				break;

			case 1:
				plusOneCnt++;
				break;
			}
			return;
		}
		
		//r~N, c~N까지 돌면서 동일한 숫자가 아닐경우 분할해서 다시 확인
		int size = N / 3;
		solution(r, c, size);
		solution(r, c + size, size);
		solution(r, c + (size * 2), size);

		solution(r + size, c, size);
		solution(r + size, c + size, size);
		solution(r + size, c + (size * 2), size);

		solution(r + size * 2, c, size);
		solution(r + size * 2, c + size, size);
		solution(r + size * 2, c + (size * 2), size);
	}

	private static boolean isCheck(int r, int c, int N) {
		int temp = paperMap[r][c];
		for (int i = r; i < r + N; i++) {
			for (int j = c; j < c + N; j++) {
				if (temp != paperMap[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

}
