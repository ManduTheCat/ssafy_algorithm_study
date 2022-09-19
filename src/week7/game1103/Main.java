package week7.game1103;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class area implements Comparable<area>{
		int x,y,life;

		public area(int x, int y,int life) {
			super();
			this.x = x;
			this.y = y;
			this.life=life;
		}

		@Override
		public int compareTo(area o) {
			return this.life-o.life;
		}
		
	}
	// 상하우좌
	static int[] dx = {0,0,1,-1};
	static int[] dy = {-1,1,0,0};
	static boolean[][] visited; // 방문 체크 배열
	static int[][] map = new int[501][501];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		/**위험구역  =1로 표시*/
		int n = Integer.parseInt(br.readLine()); //구역의 수 
		
		// X1 Y1 위험한 구역의 한 모서리 X2 Y2 위험한 구역의 반대 모서리 와 같은 형식으로 위험한 구역의 정보
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			for(int a = Math.min(x1, x2);a<=Math.max(x1, x2);a++) {
				for(int b = Math.min(y1, y2);b<=Math.max(y1, y2);b++) {
					map[a][b] =1;
				}
			}
		}
		
		/**죽음구역 = 2로 표시 */
		int m = Integer.parseInt(br.readLine()); //구역의 수 
		
		// X1 Y1 죽음 구역의 한 모서리 X2 Y2 죽음 구역의 반대 모서리 와 같은 형식으로 위험한 구역의 정보
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			for(int a = Math.min(x1, x2);a<=Math.max(x1, x2);a++) {
				for(int b = Math.min(y1, y2);b<=Math.max(y1, y2);b++) {
					map[a][b] =2;
				}
			}
		}
		
//		출력확인용 ~ 
//		int num=500;
//		for(int a = 0;a<=num;a++) {
//			for(int b =0;b<=num;b++) {
//				System.out.print(map[a][b]+" ");
//			}
//			System.out.println();
//		}
		

		// bfs 최단거리 찾으면 생명의 최솟값 구할 수 있음 ~ 
		visited = new boolean[501][501];
		System.out.println(bfs());

	}
	
	static int bfs() {
		// queue x,y,생명도 넣어야함 디폴트 : 오름차순 
		PriorityQueue<area> que = new PriorityQueue<>();
		// 시작위치(0,0) 필요한 생명 값 0 
		que.add(new area(0, 0, 0));
		visited[0][0]=true; // 방문했다 !
		map[0][0]=0; // (0, 0)이 죽음의 구역이더라도 세준이는 이미 그 곳에 있으므로 세준이에게 영향을 미치지 않는다.
		
		//큐가 빌때까지 반복
		while(!que.isEmpty()) {
			area temp= que.poll();

			// 사방탐색
			for(int d=0;d<4;d++) {
				int nextX = temp.x + dx[d];
				int nextY = temp.y + dy[d];
				
				//범위 확인하기 !!
				if(nextX>=0 && nextY>=0 && nextX <=500 && nextY<=500) {
					// 죽음 지역이 아니고, 방문한적도 없는 곳일때 이동가능
					if(map[nextX][nextY]!=2 &&!visited[nextX][nextY]) {
						//방문체크
						visited[nextX][nextY]=true;
						//위험 지역 =1, 안전 지역 =0 
						// 배열 값 더해주면 될듯?
						map[nextX][nextY]+=temp.life;
						
						//큐에 넣깅
						que.add(new area(nextX, nextY, map[nextX][nextY]));
					}
					
				}
			}
			
		}
		// 맵에 life 저장되니 이대로 반환 
		// 만약 500,500 방문한 적 없다면 -1 반환
		if(visited[500][500]) {
			return map[500][500];
		}else {
			return -1;
		}

		
	}
	
	
}
