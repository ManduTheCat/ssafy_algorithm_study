package week6.monkeyWhoWannabeHorse1600;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static class Monkey {
		int r;	// 행 좌표
		int c;	// 열 좌표
		int k;	// 말처럼 움직인 횟수
		int dist;	// 총 동작수
		public Monkey(int r, int c, int k, int dist) {
			this.r = r;
			this.c = c;
			this.k = k;
			this.dist = dist;
		}
	}
	
	static int K;		// 원숭이가 말처럼 움직일 수 있는 횟수
	static int W, H;	// 가로 길이, 세로 길이
	static int[][] board;	// 격자판
	static int[][][] DP;	// [행 좌표][열 좌표][말처럼 움직인 횟수]
	
	public static void main(String[] args) throws IOException {
		input();
		
		System.out.println(bfs());
	}
	
	// 입력
	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		board = new int[H][W];
		DP = new int[H][W][K+1];
		
		for(int h = 0; h < H; h++) {
			st = new StringTokenizer(br.readLine());
			for(int w = 0; w < W; w++) {
				board[h][w] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	public static int bfs() {
		int[][] dirH = {{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, -2}, {2, -1}, {2, 1}, {1, 2}};
		int[][] dirM = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
		
		Queue<Monkey> q = new ArrayDeque<>();
		q.add(new Monkey(0, 0, 0, 0));
		
		while(!q.isEmpty()) {
			Monkey temp = q.poll();
			
			// 도착지점에 왔으면
			if(temp.r == H - 1 && temp.c == W - 1) {
				return temp.dist;
			}
			
			// 말처럼 움직이기
			if(temp.k < K) {
				for(int i = 0; i < 8; i++) {
					int nr = temp.r + dirH[i][0];
					int nc = temp.c + dirH[i][1];
					// 범위 체크 & 장애물 없는지 체크
					if(isIn(nr, nc) && board[nr][nc] == 0) {
						if(DP[nr][nc][temp.k + 1] == 0) {
							q.offer(new Monkey(nr, nc, temp.k + 1, temp.dist + 1));
							DP[nr][nc][temp.k + 1] = 1;
						}
					}
				}
				
			}
			
			// 인접한 칸으로 움직이기
			for(int i = 0; i < 4; i++) {
				int nr = temp.r + dirM[i][0];
				int nc = temp.c + dirM[i][1];
				// 범위 체크 & 장애물 없는지 체크
				if(isIn(nr, nc) && board[nr][nc] == 0) {
					if(DP[nr][nc][temp.k] == 0) {
						q.offer(new Monkey(nr, nc, temp.k, temp.dist + 1));
						DP[nr][nc][temp.k] = 1;
					}
				}
			}
		}
		return -1;
	}
	
	// 격자판 범위 내인지 체크
	public static boolean isIn(int x, int y) {
		if(x < 0 || y < 0 || x >= H || y >= W) return false;
		else return true;
	}
}