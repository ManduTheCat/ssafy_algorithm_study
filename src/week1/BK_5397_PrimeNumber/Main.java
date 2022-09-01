package week1.BK_5397_PrimeNumber;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int startNum = Integer.parseInt(st.nextToken());
		int endNum = Integer.parseInt(st.nextToken());
		
		// 0과 1은 소수가 아니므로 빼주어야 한다.
		if(startNum < 2 && endNum >= 2) {
			startNum = 2;
		} else if(startNum < 2 && endNum < 2){
			System.exit(0);
		}
		
		// 소수찾기 시작
		for(int i = startNum; i <= endNum; i++) {
			boolean isPrime = true;
			for(int j = 2; j <= Math.sqrt(i); j++) {
				if(i % j == 0) {
					isPrime = false;
					break;
				}
			}
			if(isPrime) {
				sb.append(i+"\n");
			}
		}
		System.out.println(sb);
	}
}
