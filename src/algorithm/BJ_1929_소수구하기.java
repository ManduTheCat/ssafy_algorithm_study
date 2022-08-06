package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1929_소수구하기 {

	/**
	 * @param 박태환 M 이상, N 이하 소수 구하기. M <= X <= N 소수 ? -> 자기 자신으로만 나눠지는 수?
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		//입력 끝
		
		/*3번 방법.
		 *  배수를 이용해서 소수인것들 찾기 */
		boolean [] checkPrime = new boolean[1000000];
		checkPrime[0] = true;
		checkPrime[1] = true;
		//배수들을 true로 처리
		for(int i = 2; i < N; i++) {
			for(int j = 2; i*j < checkPrime.length; j++) {
				checkPrime[i*j] = true;
			}
		}
		
		//false 인것(소수)만 나오도록
		for(int i = M; i<= N; i++) {
			if(checkPrime[i] == false) {
				System.out.println(i);
			}
		}		
		
		/*시관초과로 실패 한 코드*/
		/*
		//소수인 경우에만 출력할 수 있도록 하기 위한 변수
		boolean check = false;		
		
		//M부터 -> N까지
		for (int i = M; i <= N; i++) {
			check = false;
			// 소수인지 확인 ( i까지만 확인 )
			for (int j = 2; j <= i; j++) {
				//나눠지는 값이 있을경우(몫이 있고, 나머지가 없을 때) -> 소수  X
				if (i / j >= 1 && i % j == 0 && i > j) 
				{					
					break;
				}
				//몫이 1이고, 나눠지는 값과, 나누는 값이 같을 때 -> 소수 O
				else if(i == j && i / j == 1) {
					check = true;
					break;
				}				
			}
			
			// 소수이면 check true 로 바꿨기 때문에 소수만 출력되도록.
			if(check == true)
			{
				sb.append(i + "\n");
			}
		}
		
		
		** 시 간 초 과 오류 !! **
		>> stringBuilder 사용		
		** stringBuilder 사용해도 시간초과오류! **
		*/
		
		/*
		 ** 시 간 초 과 오류 !! **
		2. 다른 방법으로 소수인지 검사 
		   소수들로 나눠보면 소수인지 아닌지 판별 가능		
		*/		
		/*
		//   소수를 담는 배열을 생성
		int [] primeNum = new int[N-M];

		//소수인 경우에만 출력할 수 있도록 하기 위한 변수
		boolean check = false;		
		//M부터 -> N까지
		for (int i = M; i <= N; i++) {		
			//1일때는 소수검사 X
			if(i==1)
			{
				continue;
			}
			
			int j = 0;
			//반복문을 돌면서 , 소수들만 소수배열에 집어넣음.
			while(true) {
				//소수로 나눠지는 값이 없을 경우(초기화되어있는 배열의 값까지 왔을 때)
				//소수 배열에 값을 넣어주고 break;
				if(primeNum[j] == 0) {					
					primeNum[j] = i;
					break;
				}
				
				//소수로 나눠지는 값(소수로 나눴을 때 나머지가 0)이라면 소수X -> break 
				if(i > primeNum[j] && i % primeNum[j] == 0) {					
					break;
				}
				
				//다음 소수로 나눠지는지 확인
				j++;
			}
		}
		
		//primeNum == 0이 아닌 것까지 출력되도록
		int i = 0;
		while(primeNum[i] != 0) {
			sb.append(primeNum[i++] + "\n");
		}
		
		System.out.print(sb);
		*/

		
	}
}
	
		

