package week8.BJ1034Lamp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * [스위치]
 * - 짝수번 누름 : 0번 누름과 같음
 * - 홀수번 누름 : 1번 누름과 같음 
 * K 의 짝수 홀수 
 * - 전체가 켜질 수 있는 행의 수 정해져있음 
 * 
 * -> 0의 숫자에 따라 최대 행의개수가 바뀜
 * -> k가 짝수이면, 0의 개수도 짝수 !!
 * -> 그 행의 불을 켜기 위해 0의 개수 , k개보다 많으면 안됨 그럼 다 킬 수 없는딩?
 * 
 *  - 겹치는 문자열의 개수 세고 0 개수 파악하기
 *  - 반복문 안에서 K 가 0의 수를 넘을 때, 안넘을 떄 구분 
 *  --안넘었을 경우 그 다음 겹치는 문자열로 넘어가기
 *  -- 넘었을 경우 K-0의 수 가 짝수, 홀수 구분
 *  -- 짝수일 경우 답, 홀수 일 경우 문자열로 넘어가기 
 * 
 * */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); // 행의 개수 
		int m = Integer.parseInt(st.nextToken()); // 열의 개수 
		
		String[] str = new String[n]; // 행들 저장할 배열
		int[][] board = new int[n][m];
		for(int i=0;i<n;i++) {
			str[i] = br.readLine();
			for(int j=0;j<m;j++) {
				board[i][j] = str[i].charAt(j)-'0';
			}
		}
		
		int k = Integer.parseInt(br.readLine());
		
//		for(int i=0;i<n;i++) {
//			for(int j=0;j<m;j++) {
//				System.out.print(board[i][j]+ " ");
//			}
//			System.out.println();
//		}
		
		int max =0;
		/* 모든 행을 돌면서 0의 개수 구하기 ~ */
		for(int i=0;i<n;i++) {
			int zeroCnt=0;
			
			for(int j=0;j<m;j++) {
				if(board[i][j]==0) zeroCnt++;
			}
			
			int onRow =0; // 불이 켜진 행의 개수
			// 0개의 개수가 k번 보다 작거나 같아야하고, 0의 개수와 k번의 2로 나눈값이 같으면 짝수일땐 짝수,홀수일땐 홀수인것 확인
			if(zeroCnt <= k && zeroCnt % 2 == k %2) {
				// 현재 행과 같은 패턴(행)이 있으면 onRow 값 올려주기 
				for(int j=0;j<n;j++) {
					if(str[i].equals(str[j])) {
						onRow++;
					}
				}
				// 최댓값 갱신해주기
				max = Math.max(max, onRow);
			}
			
		}
		System.out.println(max);
		
	}
	

}
