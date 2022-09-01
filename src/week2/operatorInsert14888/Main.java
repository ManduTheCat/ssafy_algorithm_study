package week2.operatorInsert14888;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * N개 수로 이루어진 수열, 수와 수 사이에 끼워넣을 수 있는 N-1개 연산자
 * 연산자 (+,-,*,/)
 * 주어진 수의 순서 바꾸지 않고 수와 수사이 하나씩 연산자를 넣어 수식 만들기
 * 연산자의 우선순위 무시하고 만들기 
 * 음수를 양수로 나눌 때 
 * - 양수로 바꾼 뒤 몫을 취하고 그 몫을 음수로 바꾼다.
 * 만들 수 있는 식의 결과 최대, 최소 구하기 
 * */
public class Main{
	static int N;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	static int[] exp;
	static int[] num;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); // 수의 개수
		
		num = new int[N]; // 수 들어간 배열
		st = new StringTokenizer(br.readLine());
		for(int n=0;n<N;n++) {
			num[n] = Integer.parseInt(st.nextToken());
		}
		
		exp = new int[4]; // 연산자 배열 (+,-,x,÷)
		st = new StringTokenizer(br.readLine());
		for(int e =0;e<4;e++) {
			exp[e] = Integer.parseInt(st.nextToken());
		}
		func1(num[0], 1);
		System.out.println(max);
		System.out.println(min);

	}
	// 숫자 연산자 숫자 => 한세트
	static void func1(int start,int cnt ) { // start : 첫번째 숫자 값(num[0])부터 , cnt : 카운트 (연산자 무조건 있으니 1부터 시작) 
		if(cnt == N) {
			//최대 최소 계산하기 
			if(max<start) max = start;
			if(min>start) min = start;
			return; // 최대 최소 계산하고 함수 끝내기
		}
		//한세트 계산
		int result =0; // 한세트결과 저장 변수
		for(int i=0;i<4;i++) { // 연산자가 들어갈 수 있는 칸의 수 : N-1
			if(exp[i]!=0) {
				exp[i]--; //연산자 사용했으니 카운트 줄이기 ~! 
				if(i==0) { //덧셈
					result = start+num[cnt]; // 처음 읽어온 값 + 숫자 값
				}else if(i==1) { //뺄셈
					result = start-num[cnt];
				}else if(i==2) { //곱셈
					result = start*num[cnt];
				}else if(i==3) { //나눗셈
					if(start<0 && num[cnt]>0) { //(음수 /양수)일 경우 
						start=start*-1; // 음수-> 양수 바꾸기
						result = start/num[cnt];
						result = result *-1; // 양수 -> 음수 바꾸기
					}else { // 걍 일반적인 경우
						result = start/num[cnt];
					}
				}
				func1(result,cnt+1); // 한세트 결과 전달 
				exp[i]++; // 탐색 후, 원상복귀
				
			}
		}
		

	}
	
}
