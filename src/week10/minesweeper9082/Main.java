package week10.minesweeper9082;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());	// 테스트 케이스의 수
		for (int tc = 1; tc <= T; tc++) {
			int sum = 0, ans = 0;	// 주어진 배열의 합, 정답
			int N = Integer.parseInt(br.readLine());	// 배열의 크기
			String str = br.readLine();
			for (int i = 0; i < N; i++) {
				sum += str.charAt(i) - '0';
			}
			ans = sum / 3;
			if (sum % 3 != 0)
				ans++;
			str = br.readLine();
			System.out.println(ans);
		}
	}
}
