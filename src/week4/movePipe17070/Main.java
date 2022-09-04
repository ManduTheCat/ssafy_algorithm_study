package week4.movePipe17070;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, cnt; //집의 크기, 파이프를 이동시킬 수 있는 방법의 수
	static int[][] map; //집을 나타낼 배열

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); //집의 크기 입력
		map = new int[N+1][N+1]; //1부터 사용하기 위해 집의 크기보다 하나 크게 배열 생성
		for(int i=1; i<=N; i++) { //집의 크기만큼 반복하면서
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); //집의 정보 저장
			}
		}
		
		//파이프 시작 위치 1,1 (1,2)-> 이 중에서 뒤에꺼 하나만 사용해도 될듯
		//가로(H)-> 오른쪽과 대각선, 세로(V)-> 아래쪽과 대각선, 대각선(D)->오른쪽과 아래쪽과 대각선
		cnt = 0; //파이프를 이동시킬 수 있는 방법의 수
		dfs(1, 2, 0); //dfs 메서드 호출, 매개변수로는 파이프의 좌표와 놓인 상태
		
		System.out.println(cnt); //방법의 개수 출력
		

	}

	//파이프를 이동시키면서 이동 가능한 방법의 개수를 증가시키는 메서드
	private static void dfs(int x, int y, int num) { //x좌표, y좌표, 파이프가 놓인 상태
		if(x == N && y == N) { //집의 끝까지 도달하면
			cnt++; //방법의 개수 증가
			return;
		}
		
		//파이프가 놓인 상태에 따라 이동하는 방법을 다르게
		//0은 가로, 1은 세로, 2는 대각선
		switch(num) {
		case 0 : //파이프 상태가 가로 일 때
			if(check(x,y+1) && map[x][y+1] == 0) { //범위를 벗어나지 않고, 이동할 칸에 장애물이 없으면
				dfs(x,y+1, 0); //오론쪽으로 이동
			}
			
			if(check(x+1, y+1) && map[x+1][y] == 0 && map[x][y+1] == 0 && map[x+1][y+1] == 0) { //범위를 벗어나지 않고, 이동할 칸에 장애물이 없으면
				dfs(x+1,y+1, 2); //대각선으로 이동
			}
				
			break;
		case 1 : //파이프 상태가 세로 일 때
			if(check(x+1, y) && map[x+1][y] == 0) { //범위를 벗어나지 않고, 이동할 칸에 장애물이 없으면
				dfs(x+1,y,1); //아래로 이동
			}
			
			if(check(x+1, y+1) && map[x+1][y] == 0 && map[x][y+1] == 0 && map[x+1][y+1] == 0) { //범위를 벗어나지 않고, 이동할 칸에 장애물이 없으면
				dfs(x+1,y+1,2); //대각선으로 이동
			}
			
			break;
		case 2 : //파이프 상태가 대각선 일 때
			if(check(x,y+1) && map[x][y+1] == 0) { //범위를 벗어나지 않고, 이동할 칸에 장애물이 없으면
				dfs(x,y+1,0); //오론쪽으로 이동
			}
			
			if(check(x+1, y) && map[x+1][y] == 0) { //범위를 벗어나지 않고, 이동할 칸에 장애물이 없으면
				dfs(x+1,y,1); //아래로 이동
			}
			
			if(check(x+1, y+1) && map[x+1][y] == 0 && map[x][y+1] == 0 && map[x+1][y+1] == 0) { //범위를 벗어나지 않고, 이동할 칸에 장애물이 없으면
				dfs(x+1,y+1,2); //대각선으로 이동
			}
			
			break;
		}
		
	}
	
	//범위를 벗어나는지 확인하는 메서드
	private static boolean check(int x, int y) {
		if(0<x && 0<y && N>=x && N>=y) {
			return true;
		}
		return false;
	}
	
}
