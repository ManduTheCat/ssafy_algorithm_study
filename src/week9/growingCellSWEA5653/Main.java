package week9.growingCellSWEA5653;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	static int[][] map;
	static int N, M, K, map_size;
	static boolean[][] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static class Cell implements Comparable<Cell>{
		int x,y,k,kstart,kend;

		public Cell(int x, int y, int k, int kstart, int kend) {
			super();
			this.x = x;
			this.y = y;
			this.k = k;
			this.kstart = kstart;
			this.kend = kend;
		}

		@Override
		public String toString() {
			return "Cell [x=" + x + ", y=" + y + ", k=" + k + ", kstart=" + kstart + ", kend=" + kend + "]";
		}

		// 내림차순 - 생명력 강한애들부터 처리
		@Override
		public int compareTo(Cell o) {
			// TODO Auto-generated method stub
			return o.k - this.k;
		}
		
		
	}
	static PriorityQueue<Cell> cell;
	static Queue<Cell> que;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 세로
			M = Integer.parseInt(st.nextToken()); // 가로
			K = Integer.parseInt(st.nextToken()); // 배양 시간

			
			// map 크기 : n*m , 위아래 최대 K커질 수 있음
			map = new int[2*K+N][2*K+M];
			visited = new boolean[2*K+N][2*K+M];			
			que = new ArrayDeque<>();
			cell = new PriorityQueue<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[K+i][K+j] = Integer.parseInt(st.nextToken());
					if(map[K+i][K+j]>0) {
						visited[K+i][K+j]=true;
						que.add(new Cell(K+i, K+j, map[K+i][K+j], map[K+i][K+j], map[K+i][K+j]*2));
					}
				}
			}
			
//			print();
//			// 배양 시간만큼 반복
			for(int i=1;i<=K;i++) {
//				System.out.println(i+" 시간 후");
//				print();
				check(i);
				bfs(i);
			}
			sb.append("#"+tc+" "+que.size()).append("\n");
		}
		System.out.println(sb);

	}
	static void bfs(int time) {
		while(!cell.isEmpty()) {
			Cell temp = cell.poll();
//			System.out.println("bfs : "+ cell.toString());
			
			// 아직 배양 시간이 남은 애들은 큐에 다시 집어 넣기
			if(time <temp.kend) {
				que.offer(temp);
			}
			
			// 사방 번식을 해보자
			for(int d=0;d<4;d++) {
				int nx = temp.x + dx[d];
				int ny = temp.y + dy[d];
				
				if(!visited[nx][ny]) {
					visited[nx][ny] = true;
					map[nx][ny] = temp.k;
					que.add(new Cell(nx, ny, temp.k, time+temp.k, time+temp.k*2));
				}
			}
			
		}
	}
	//상태 확인 해야지 
	static void check(int time) {
		int size = que.size();
		
		for(int i=0;i<size;i++) {
			Cell temp = que.poll();
//			System.out.println("check : "+que.toString());
			// 활성화 전 - 막 태어난 애들도 활성화 전 상태임 
			if(time <=temp.kstart) {
				que.offer(temp);
			}// 활성화 
			else if(time == temp.kstart+1) {
				cell.offer(temp);
			}// 죽기 직전 - 실행중
			else if(time> temp.kstart && time <temp.kend){
				que.offer(temp);
			}
//			System.out.println("check : "+que.toString());
		}
	}


	/* 출력 확인용 */
	static void print() {
		System.out.println("=================================================");
		for (int i = 0; i < 2*K+N; i++) {
			for (int j = 0; j < 2*K+M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

}
