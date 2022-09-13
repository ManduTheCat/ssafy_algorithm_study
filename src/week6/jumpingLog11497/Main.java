package week6.jumpingLog11497;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());	// 테스트 케이스의 수

		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());	// 통나무의 수
			int logs[] = new int[N];	// 각 통나무의 높이
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				logs[i] = Integer.parseInt(st.nextToken());	// 각 통나무의 높이 입력
			}
			
			Arrays.sort(logs);
			
			// 난이도 구하기
			int maxDiff = -1;
			for(int i = 2; i < N; i++) {
				int diff = logs[i] - logs[i-2];
				maxDiff = Math.max(maxDiff, diff);
			}
			
			System.out.println(maxDiff);
		}
	}
}