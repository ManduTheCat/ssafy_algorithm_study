package week6.monkeyWhoWannabeHorse1600;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.concurrent.DelayQueue;

public class Main {
	//원숭이 사방 상하좌우
	static int[] mx= {-1,1,0,0};
	static int[] my = {0,0,-1,1};
	//말 움직임 
	static int[] hx= {1,2,2,1,-1,-2,-2,-1};
	static int[] hy = {-2,-1,1,2,2,1,-1,-2};
	static int h,w,k;
	static boolean[][][] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		k = Integer.parseInt(br.readLine()); // 말처럼 이동할 수 있는 수
		
		st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken()); // 가로
		h = Integer.parseInt(st.nextToken()); // 세로 
		
		map = new int[h][w];
		
		for(int i=0;i<h;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<w;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		
//		for(int i=0;i<h;i++) {
//			for(int j=0;j<h;j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		
		visited = new boolean[h][w][k+1]; // 세로, 가로 , (말처럼 이동, 원숭이처럼 이동)카운트별 방문체크
		System.out.println(bfs());

	}
	static class Monkey{
		int x,y,monkeyCnt,k;

		public Monkey(int x, int y, int monkeyCnt, int k) {
			super();
			this.x = x; // 원숭이 x
			this.y = y; // 원숭이 y
			this.monkeyCnt = monkeyCnt; // 총 원숭이가 움직인 수
			this.k = k; // 말처럼 움직인 수 
		}

		@Override
		public String toString() {
			return "Monkey [x=" + x + ", y=" + y + ", monkeyCnt=" + monkeyCnt + ", k=" + k + "]";
		}
		
		
		
	}
	static int[][] map;
	static int bfs() {
		Queue<Monkey> que = new LinkedList();
		que.add(new Monkey(0, 0, 0, k));
		visited[0][0][k] = true;
		
		while(!que.isEmpty()) {
			Monkey temp = que.poll();
			
			// 목적지에 도착할 경우 원숭의 동작 최솟값 출력 
			if(temp.x== h-1 && temp.y == w-1) {
				return temp.monkeyCnt;
			}


			//원숭이가 말처럼 움직일 수 있다면 (횟수 남음) 
			if(temp.k > 0) {
				//이동 ~
				for(int d=0;d<8;d++) {
					int hdx = temp.x + hx[d];
					int hdy = temp.y + hy[d];
					
					//범위 체크랑 장애물 체크
					if(hdx>=0 && hdx<h && hdy>=0 && hdy<w  && map[hdx][hdy]!=1) {
						if(!visited[hdx][hdy][temp.k-1]) {
							visited[hdx][hdy][temp.k-1] = true;
							// 말처럼 움직이는 횟수를 사용했으니 k -1 ! 
							que.add(new Monkey(hdx, hdy, temp.monkeyCnt+1, temp.k-1));
						}
					}
				}
				
			}
			
			// 원숭이처럼 움직이기 이동 ~
			for(int d=0;d<4;d++) {
				int mdx = temp.x+ mx[d];
				int mdy = temp.y+ my[d];
				
				//범위 체크랑 장애물 체크
				if(mdx>=0 && mdx<h && mdy>=0 && mdy<w  && map[mdx][mdy]!=1) {
					if(!visited[mdx][mdy][temp.k]) {
						visited[mdx][mdy][temp.k] = true;
						que.add(new Monkey(mdx, mdy, temp.monkeyCnt+1, temp.k));
					}
				}
			}
			
		}
		
		// 마지막까지 이동할 수 없다면 -1
		return -1;
		
	}
}
