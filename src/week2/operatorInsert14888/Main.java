package week2.operatorInsert14888;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] number; //숫자를 담을 배열
	static int[] operator; //연산자 개수를 담을 배열
	
	static int N, minNum, maxNum; //숫자의 개수, 계산한 값 중 가장 작은 값, 가장 큰 값을 저장하는 변수

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); //숫자의 개수
		
		number = new int[N]; //숫자의 개수만큼 숫자 정보를 저장할 배열 선언
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) { //숫자 개수만큼 반복
			number[i] = Integer.parseInt(st.nextToken()); //숫자 정보를 배열에 저장
		}
		
		operator = new int[4]; //각각의 연산자 개수 정보를 저장할 배열
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++) { //사용하는 연산자 개수(4개)만큼 반복
			operator[i] = Integer.parseInt(st.nextToken()); //합, 차, 곱, 나누기 순서로 연산자 개수를 배열에 저장
		}

		//입력된 정보 올바르게 저장 되었는지 확인
//		for(int i=0; i<N; i++) {
//			System.out.print(number[i] + " ");
//		} System.out.println();
//		
//		for(int i=0; i<4; i++) {
//			System.out.print(operator[i] + " ");
//		} 
		

		minNum = Integer.MAX_VALUE;  //가장 작은 값 비교를 위해 정수 최대값으로 초기화
		maxNum = Integer.MIN_VALUE; //가장 큰 값 비교를 위해 정수 최소값으로 초기화
		
		calc(0, number[0]); //계산 메소드 호출, 0부터 시작, 계산 될 첫번째 숫자
		
		//출력 형식에 맞게 출력
		System.out.println(maxNum); //가장 큰 값
		System.out.println(minNum); //가장 작은 값
		

	}
	
	//재귀하면서 연산자에 맞게 계산하여 가장 큰 값과 작은 값을 구하는 메서드
	 static void calc(int cnt, int num) {
			if(cnt == N-1) { //총 사용하는 연산자 개수 N-1개
				minNum = Math.min(minNum, num); //현재 값과 계산된 값을 비교해서 더 작은 값을 저장
				maxNum = Math.max(maxNum, num); //현재 값과 계산된 값을 비교해서 더 큰 값을 저장
				
				return;
			}
			//System.out.println("cnt: "+ cnt + " num: " + num); //출력 확인
			
			for(int i=0; i<4; i++) { //사용하는 연산자 개수(4개)만큼 반복
				if(operator[i] == 0) { //선택된 연산자의 개수가 0개라면
					//System.out.println("continue"); //출력 확인
					continue; //다음 반복 진행
				}
				
				//아니라면
				operator[i]--; //선택된 연산자를 사용했으니 개수 줄이기
				
				switch(i) { //선택된 연산자가
				case 0: //덧셈일 때
					//System.out.println("+"); //출력 확인
					calc(cnt+1, num + number[cnt+1]); //총 사용한 연산자 개수 증가, 현재 값과 다음 숫자를 더한 값으로 재귀호출
					break;
					
				case 1: //뺄셈일 때
					//System.out.println("-"); //출력 확인
					calc(cnt+1, num - number[cnt+1]); //총 사용한 연산자 개수 증가, 현재 값에서 다음 숫자를 뺀 값으로 재귀호출
					break;
					
				case 2: //곱셈일 때
					//System.out.println('*'); //출력 확인
					calc(cnt+1, num * number[cnt+1]); //총 사용한 연산자 개수 증가, 현재 값과 다음 값을 곱한 값으로 재귀호출
					break;
					
				case 3: //나눗셈일 때
					//System.out.println("/"); //출력 확인
					calc(cnt+1, num / number[cnt+1]); //총 사용한 연산자 개수 증가, 현재 값에서 다음 값을 나눈 값으로 재귀호출
					break;
				}
				
				operator[i]++; //선택된 연산자의 사용이 끝났으니 원래대로 되돌리기
				
			}
	}
	 
}
