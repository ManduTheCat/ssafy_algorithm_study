package week4.movePipe17070;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int arr[][];
	static int dp[][][];	// 파이프 끝의 행 좌표, 열 좌표, 상태(가로, 세로, 대각선)
	
	static final int VER = 0;	// 가로
	static final int HOR = 1;	// 세로
	static final int DIG = 2;	// 대각선

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());	// 집의 크기
		arr = new int[N+1][N+1];	// 인덱스 0은 사용 X
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp = new int[N+1][N+1][3];
		
		dp[1][2][VER] = 1;
		for(int i = 1; i <= N; i++) {
			for(int j = 3; j <= N; j++) {
				if(arr[i][j] == 0) {
					dp[i][j][VER] = dp[i][j-1][VER] + dp[i][j-1][DIG];
					dp[i][j][HOR] = dp[i-1][j][HOR] + dp[i-1][j][DIG];
					if(arr[i-1][j] == 0 && arr[i][j-1] == 0) {
						dp[i][j][DIG] = dp[i-1][j-1][VER] + dp[i-1][j-1][HOR] + dp[i-1][j-1][DIG];
					}
				}
			}
		}
		
		System.out.println(dp[N][N][VER] + dp[N][N][HOR] + dp[N][N][DIG]);
	}
}