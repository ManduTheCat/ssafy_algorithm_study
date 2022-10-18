package week10.minesweeper9082;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 수
		for(int tc=1;tc<=T;tc++) {
			
			int N = Integer.parseInt(br.readLine()); // 배열의 크기
			
			int[] mineArr = new int[N]; // 숫자 지뢰 배열
			char[] mineChar = new char[N]; // 문자 지뢰 배열 

			int mineCnt =0; // 지뢰 개수 카운트
			
			// 숫자 지뢰 배열
			String str = br.readLine();
			for(int i=0;i<N;i++) {
				mineArr[i]= str.charAt(i);
			}
			
			// 문자 지뢰 배열 
			str = br.readLine();
			for(int i=0;i<N;i++) {
				mineChar[i] = str.charAt(i);
			}
			
			// 숫자로 배열만 돌리기 
			for(int i=0;i<N;i++) {
				if(i==0) { // 0번째일 때 , 확인할 곳은 0번째와 1번째 확인
					if(mineArr[0]!='0'  && mineArr[1]!='0') {
						mineArr[0]--;
						mineArr[1]--;
						mineCnt++; 
					}
				}else if(i == N-1) { // N-1일때, 확인할 곳은 N-1, N-2 확인 
					if(mineArr[N-1]!='0' && mineArr[N-2]!='0') {
						mineArr[N-1]--;
						mineArr[N-2]--;
						mineCnt++;
					}
				}else { // 가운데값일 경우 , 왼쪽 대각선, 본인, 오른쪽 대각선 확인해야함 
					if(mineArr[i-1]!='0' && mineArr[i]!='0' && mineArr[i+1]!='0'){
						mineArr[i-1]--;
						mineArr[i]--;
						mineArr[i+1]--;
						mineCnt++;
					}
				}
			}
			sb.append(mineCnt).append("\n");
		}
		System.out.println(sb);

	}
	

}

