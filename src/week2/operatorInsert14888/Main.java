package week2.operatorInsert14888;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 
 * @author 이채은
 *
 */
public class Main {
	private static int[] nums;	/**숫자가 저장되는 배열*/
	private static int[] sign;	/**연산자가 저장되는 배열*/
	private static int max;	/**최대값*/
	private static int min;	/**최소값*/
	private static int N;	/**숫자 개수*/

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		nums = new int[N];
		sign = new int[4]; // 덧셈, 뺄셈, 곱셈, 나눗셈 순서로 저장된다

		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < 4; i++) {
			sign[i] = Integer.parseInt(st.nextToken());
		}

		dfs(nums[0], N-1);	// N-1 => 연산자 개수만큼이므로
		System.out.println(max);
		System.out.println(min);
	}

	/**
	 * 경우의 수 뽑는 함수
	 * @param num
	 * @param index
	 */
	static void dfs(int num, int index) {
		// 경우의 수가 나왔으면 최대 최소값 구하기
		if(index == 0) {
			if(max < num) {
				max = num;
			}
			if(min > num) {
				min = num;
			}
		}
		for (int i = 0; i < 4; i++) {
			if (sign[i] > 0) {
				sign[i]--;	// 선택하면 빼준다
				switch (i) {
				case 0:
					 dfs(num + nums[N-index], index-1);
					 break;
				case 1:
					dfs(num - nums[N-index], index-1);
					break;
				case 2:
					dfs(num * nums[N-index], index-1);
					break;
				case 3:
					dfs(num / nums[N-index], index-1);
					break;
				}
				sign[i]++;	// 되돌리기
			}
		}
	}
}

