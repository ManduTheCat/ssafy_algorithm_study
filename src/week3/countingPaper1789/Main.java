package week3.countingPaper1789;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] map; // 숫자를 저장할 맵
	static int minusResult = 0; // -1 개수
	static int zeroResult = 0;  // 0 개수
	static int plusResult = 0;  // 1 개수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		switch (partition(1, 1, N)) {
		case -1:
      // -1로만 되어 있는
			System.out.println(1);
			System.out.println(0);
			System.out.println(0);
			break;
		case 0:
       // 0로만 되어 있는
			System.out.println(0);
			System.out.println(1);
			System.out.println(0);
			break;
		case 1:
       // 1로만 되어 있는
			System.out.println(0);
			System.out.println(0);
			System.out.println(1);
			break;
		default:
        // 섞여있는
			System.out.println(minusResult);
			System.out.println(zeroResult);
			System.out.println(plusResult);

		}

	}

  // 3*3으로 나눠서 개수를 구하는 함수
	private static int partition(int row, int col, int length) {
//		System.out.printf("row: %d col: %d length: %d\n", row, col, length);
		int minusCnt = 0;
		int zeroCnt = 0;
		int plusCnt = 0;
		if (length <= 3) {  // 3*3이 되면 돌면서 카운트한다.
			for (int r = row; r < row + 3; r++) {
				for (int c = col; c < col + 3; c++) {
					switch (map[r][c]) {
					case -1:
						minusCnt++;
						break;
					case 0:
						zeroCnt++;
						break;
					case 1:
						plusCnt++;
						break;
					}
				}
			}

//			System.out.printf("한칸 minusCnt: %d zeroCnt: %d plusCnt: %d\n", minusCnt, zeroCnt, plusCnt);
      // 9칸이면 종이 하나
			if (minusCnt == 9) {
				return -1;
			} else if (zeroCnt == 9) {
				return 0;
			} else if (plusCnt == 9) {
				return 1;
			} else {
//				System.out.println("짬뽕");
				minusResult += minusCnt;
				zeroResult += zeroCnt;
				plusResult += plusCnt;
			}
		} else {  // 3*3보다 큰 경우 더 나눠준다
			length /= 3;
			for (int r = 0; r < 3; r++) {
				for (int c = 0; c < 3; c++) {
					int result = partition(row + length * r, col + length * c, length);
					switch (result) {
					case -1:
						minusCnt++;
						break;
					case 0:
						zeroCnt++;
						break;
					case 1:
						plusCnt++;
						break;
					}
				}

			}
			if (minusCnt == 9) {
				return -1;
			} else if (zeroCnt == 9) {
				return 0;
			} else if (plusCnt == 9) {
				return 1;
			} else {
				minusResult += minusCnt;
				zeroResult += zeroCnt;
				plusResult += plusCnt;
			}
//			System.out.printf("큰애 minusCnt: %d zeroCnt: %d plusCnt: %d\n", minusCnt, zeroCnt, plusCnt);
		}

		return 2;
	}

	static void print(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
