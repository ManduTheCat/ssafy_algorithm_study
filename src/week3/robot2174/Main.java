package week3.robot2174;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());	// 가로
		int B = Integer.parseInt(st.nextToken());	// 세로
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 로봇 수
		int M = Integer.parseInt(st.nextToken());	// 명령 수
		
		int[][] map = new int[B][A];	// 맵에 로봇 위치 표시
		int[][] robots = new int[N + 1][3];	// 로봇의 좌표, 방향 정보 저장
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;	// 로봇의 초기 위치
			int c = B - Integer.parseInt(st.nextToken());	
			char dir = st.nextToken().charAt(0);	// 로봇의 초기 방향
			
			robots[i][0] = c;
			robots[i][1] = r;
			map[c][r] = i;
			
			if(dir == 'N') {
				robots[i][2] = 0;
			} else if(dir == 'W') {
				robots[i][2] = 1;
			} else if(dir == 'S') {
				robots[i][2] = 2;
			} else {
				robots[i][2] = 3;
			}
		}
		
		final int[] dr = new int[] {-1, 0, 1, 0};
		final int[] dc = new int[] {0, -1, 0, 1};
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());	// 명령을 내리는 로봇
			char inst = st.nextToken().charAt(0);		// 명령의 종류
			int cnt = Integer.parseInt(st.nextToken());	// 명령의 반복 횟수
			
			for(int q = 0; q < cnt; q++) {
				if(inst == 'L') {
					robots[X][2] = (robots[X][2] + 1) % 4;
				} else if(inst == 'R') {
					robots[X][2] = (robots[X][2] + 3) % 4;
				} else {	// inst == 'F'
					int a = robots[X][0];
					int b = robots[X][1];
					int d = robots[X][2];
					
					int na = a + dr[d];
					int nb = b + dc[d];
					
					if(na < 0 || nb < 0 || na >= map.length || nb >= map[0].length) {	// 주어진 땅의 밖으로 벗어나는 경우
						System.out.println("Robot " + X + " crashes into the wall");
						return;
					}
					
					if(map[na][nb] != 0) {		// 다른 로봇에 충돌하는 경우
						System.out.println("Robot " + X + " crashes into robot " + map[na][nb]);
						return;
					}
					
					map[na][nb] = X;
					map[a][b] = 0;
					robots[X][0] = na;
					robots[X][1] = nb;
				}
			}
		}
		System.out.println("OK");	// 문제 없는 경우
	}
}
