package week4.movePipe17070;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * N*N ,(r,c) 
 * 행,열 번호 1부터 , 빈칸이거나 벽
 * 파이프 2개의 연속된 칸 차지
 * 3가지 방향 회전  :  →, ↘, ↓ 
 * 밀면서 회정 , 45도 회전  
 * 미는 방향 오른쪽, 아래 또는 오른쪽 아래 
 * 이동방법
 * - 파이프 가로로 놓인 경우 2가지 →, ↘
 * - 파이프 세로로 놓인 경우 2가지  ↓,↘
 * - 대각선 방향 놓여진 경우 3가지  →, ↘, ↓
 * 
 * 
 * 가장 처음 파이프 (1,1),(1,2) 방향 가로 :
 * 파이프 한쪽 끝 (N,N) 이동시키는 방법 개수 구해라 -> 갈수있는 방향 다해보기 dfs ?????
 * -> 이동시킬수 없을 경우 0
 * 
 * 1 맨 처음은 가로  : 갈수있는 방향 가로, 대각선 중에 이동 
 *  ** 벽 : 1, 빈칸 : 0
 * */
public class Main{
	static int n;
	public static void main(String[] args)throws Exception {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n= Integer.parseInt(br.readLine());
		
		int[][] map = new int[n+1][n+1];
		for(int i=1;i<n+1;i++) { // 행
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<n+1;j++) { //열
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		num=0;
		//처음 파이프 위치 (1,1),(1,2)
		//-> 파이프 마지막 점 기준으로 파이프 옮기기
		dfs(1,2,map,1);
		System.out.println(num);
		
	}
	static int num;
	// 방향에 따라 탐색해야하는 부분이 다름 
	/**@param int r 세로
	 * @param int c 가로
	 * @param int[][] map 파이프 배열 
	 * @param int dir 가로 일때 : 1, 세로일 떄 : 2, 대각선일때 :3
	 * */
	static void dfs(int r,int c,int[][] map,int dir) {
		if(r== n && c==n) { // (n,n)까지 이동했다면 ~
			num++; // 경우의 수 더하기
			return;
		}
		
		switch (dir) {
		case 1: //가로 : 행 +1
			// 가로 갈 경우
			if(c+1<n+1 && map[r][c+1]!=1) {
				dfs(r,c+1,map,1);
			}
			// 대각선 갈 경우-> 밖으로 뺌
			
			break;
		case 2: //세로 : 열 +1
			//세로 갈 경우
			if(r+1<n+1 && map[r+1][c]!=1) {
				dfs(r+1,c,map,2);
			}
			// 대각선 갈 경우-> 밖으로 뺌
			break;
		case 3: //대각선 : 행+1, 열+1
			// 가로갈 경우
			if(c+1<n+1 && map[r][c+1]!=1) {
				dfs(r,c+1,map,1);
			}
			// 세로갈 경우
			if(r+1<n+1 && map[r+1][c]!=1) {
				dfs(r+1,c,map,2);
			}
			//대각선 갈 경우 -> 밖으로 뺌
			break;
		}
		// 가로,세로,대각선일 경우 대각선방향으로 옮기는 건 모두 가능해서 밖으로 빼깅 ~
		if(c+1<n+1&&r+1<n+1 && map[r][c+1]!=1&& map[r+1][c]!=1&& map[r+1][c+1]!=1) {
//			System.out.println(c+" "+r);
			dfs(r+1,c+1,map,3);
		}
		
	}

}
