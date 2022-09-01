package week1.BK_5397_PrimeNumber;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		//M이상 N이하의 소수 모두 출력프로그램
		Scanner sc = new Scanner(System.in);
		
		int M = sc.nextInt();
		int N = sc.nextInt();
		int[] arr = new int[N+1]; // 배열 N+1개 만들기
		for(int i=2;i<N+1;i++) { //1의 배수 배열 건너뜀,배열 2부터 N까지 배열에 삽입
			arr[i]=i;
		}
		
		// 배수 찾아서 지우기 작업 
		for(int i=2;i<N+1;i++) { //1의 배수 배열 건너뜀,2부터 N까지
			if(arr[i]==0) { // 
				continue;
			}
			
			for(int j=i+i;j<N+1;j+=i) { //j=j+i-> 그 수의 배수로 손쉽게 계산 가능  
				arr[j]=0; // 같은 수를 더하면 (i+i) 그 수의 배수가 되므로 그 수 번째의 배열을 0으로 바꿈
			}

		}
		//M이상 N이하의 소수 모두 출력프로그램
		for(int i=M;i<N+1;i++) {
			if(arr[i]!=0) { // 소수는 0이 아니여야함 
				System.out.print(i+" ");
			}
		}
	}
	
}
