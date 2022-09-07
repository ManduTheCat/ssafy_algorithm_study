package week5.baseball17281;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] inputList;

	// 1, 2, 3루
	static int[] baseCheck;

	static boolean[] selected = new boolean[9];
	static int[] nums = new int[9];

	static int outCount = 0;
	static int sum;

	static int result;
	static int N;

	static int count = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());
		inputList = new int[N][9];
		for (int a = 0; a < N; a++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int b = 0; b < 9; b++) {
				inputList[a][b] = Integer.parseInt(st.nextToken());
			}
			baseCheck = new int[3]; // 1-2-3(루)
		}

		// nums -> 1~9번타자의 순열 번호, 1번선수는 4번타자위치 고정
		nums = new int[9];
		nums[3] = 0;

		selected = new boolean[9];

		result = 0;
		sum = 0;
		// 순열만들기
		perm(0);
		
		System.out.println(sum);
	}

	//순열 구하기
	private static void perm(int cur) {
		if (cur == 9) {
			sum = Math.max(sum, playBall(nums));
			return;
		}

		//3번째에는 이미 선수번호를 넣어놨기 때문에 pass
		if (cur == 3) {
			perm(cur + 1);
			return;
		}

		for (int i = 1; i < 9; i++) {
			if (selected[i])
				continue;

			selected[i] = true;
			nums[cur] = i;
			perm(cur + 1);
			selected[i] = false;
		}
	}

	//야구 시작
	private static int playBall(int[] nums) {
		int idx = 0;
		int totalScore = 0;
		//각 이닝 별 접수 집계 1~N이닝까지
		for (int i = 0; i < N; i++) {
			outCount = 0;
			baseCheck = new int[3];
			while (outCount != 3) {
				switch (inputList[i][nums[idx]]) {
				case 0:
					outCount++;
					break;
				case 4:
					totalScore += baseCheck[0];
					totalScore += baseCheck[1];
					totalScore += baseCheck[2];
					baseCheck = new int[3];
					// 자기자신
					totalScore += 1;
					break;
				case 1:
					totalScore += baseCheck[2];
					baseCheck[2] = baseCheck[1];
					baseCheck[1] = baseCheck[0];
					baseCheck[0] = 1;

					break;
				case 2:
					totalScore += baseCheck[2];
					totalScore += baseCheck[1];
					baseCheck[2] = baseCheck[0];
					baseCheck[1] = 1;
					baseCheck[0] = 0;

					break;
				case 3:
					totalScore += baseCheck[2];
					totalScore += baseCheck[1];
					totalScore += baseCheck[0];
					baseCheck[2] = 1;
					baseCheck[1] = 0;
					baseCheck[0] = 0;

					break;
				}
				idx++;

				//선수들이 다 치면 다시 첫번째 선수부터
				if (idx == 9) {
					idx = 0;
				}
			}
		}

		return totalScore;

	}

}
