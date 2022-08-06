package week1.BK_5397_PrimeNumber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[]c) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		StringBuilder sb = new StringBuilder();
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int arr[] = new int[N + 1];		// 0: 소수 , 1: 합성수
		arr[1] = 1;
		
		for (int i = 2; i <= (int)Math.sqrt(N); i++) {
			if (arr[i] == 1) continue;
			for (int j = 2 * i; j <= N; j += i)
				arr[j] = 1;
		}
		
		for (int i = M; i <= N; i++)
			if (arr[i] == 0)
				sb.append(i).append('\n');
		
		System.out.println(sb);
	}
}