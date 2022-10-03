package week8.BJ1034Lamp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(in.readLine());

		//램프의 상태 (행 열)
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		//램프 상태 저장
		int[][] switchArr = new int[N][M];
		ArrayList<String> list = new ArrayList<>();

		//배열에 현 상태 저장
		for (int r = 0; r < N; r++) {
			String temp = in.readLine();
			for (int c = 0; c < M; c++) {
				switchArr[r][c] = temp.charAt(c) - '0';
			}
		}

		//열 스위치 사용 가능 횟수
		int K = Integer.parseInt(in.readLine());
		//---입력 끝
		
		
		//K가 짝수개인경우 0이 짝수개인 경우만 찾고
		//K가 홀수개인경우 0이 홀수인 경우만 찾음. -> 찾아서 따로 저장해둠
		for (int r = 0; r < N; r++) {
			int zeroCnt = 0;

			String statStr = "";
			for (int c = 0; c < M; c++) {
				if (switchArr[r][c] == 0) {
					zeroCnt++;
				}
				statStr += switchArr[r][c];
			}

			//K가 짝수일 때, 아닐 때
			if (K % 2 == 0 && zeroCnt % 2 == 0 && zeroCnt <= K) {
				list.add(statStr);
			} else if (K % 2 == 1 && zeroCnt % 2 == 1 && zeroCnt <= K) {
				list.add(statStr);
			}
		}

		int result = 0;
		//list에서 가장 많은 패턴을 가지고 있는 값을 찾기
		//Collections.frequency(list, list.get(i)) 빈도수 체크 메서드
		for (int i = 0; i < list.size(); i++) {
			result = Math.max(result, Collections.frequency(list, list.get(i)));
		}
		//로직 끝
		
		System.out.println(result);
	}
}
