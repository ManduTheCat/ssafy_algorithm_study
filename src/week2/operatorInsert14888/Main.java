package week2.operatorInsert14888;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	
	static int[] operands;
	static int[] operators = new int[4];
	
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 입력 받기
		N = Integer.parseInt(br.readLine());
		operands = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			operands[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) {
			operators[i] = Integer.parseInt(st.nextToken());
		}
		
		// 결과 계산
		myMethod(operands[0], 1);
		
		System.out.println(max);
		System.out.println(min);
	}
	
	static void myMethod(int res, int idx) {
		if(idx == N) {
			if(res <= min) {
				min = res;
			}
			if(res >= max) {
				max = res;
			}
			return;
		}
		for(int j = 0; j < 4; j++) {
			if(operators[j] > 0) {		// 해당 연산자가 있으면
				operators[j]--;	
				switch(j) {
				case 0:		// 덧셈
					myMethod(res + operands[idx], idx + 1);	// 재귀 호출
					break;
				case 1:		// 뺄셈
					myMethod(res - operands[idx], idx + 1);
					break;
				case 2:		// 곱셈
					myMethod(res * operands[idx], idx + 1);
					break;
				case 3:		// 나눗셈
					myMethod(res / operands[idx], idx + 1);
					break;
				}
				operators[j]++;
			}
		}
		return;
	}
}