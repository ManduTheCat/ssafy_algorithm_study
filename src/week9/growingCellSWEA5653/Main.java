package week9.growingCellSWEA5653;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Cell {
		//hp -> 생명력
		//hp -> 활성, 비활성인지 파악하기 위한 cnt
		//state -> 활성 ,비활성, 죽었는지 체크
		int r, c, hp, cnt;

		//1 비활성 , 2  활성 , 3 죽음
		int state;

		public Cell(int r, int c, int hp, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.hp = hp;
			this.cnt = cnt;

			if (cnt < hp) {
				state = 1;
			} else if (cnt >= hp && cnt < hp * 2) {
				state = 2;
			} else {
				state = 3;
			}
		}

		//		@Override
		//		public String toString() {
		//			return "Cell [r=" + r + ", c=" + c + ", hp=" + hp + ", cnt=" + cnt + ", state=" + state + "]";
		//		}

		@Override
		public String toString() {
			return hp + " " + state;
		}
	}

	// 맵 r, 맵c, 배양 시간
	static int N, M, K;
	//	static int[][] map;

	static Cell[][] map;

	static ArrayList<Cell> cellList;

	static int mapN, mapM;

	static Queue<Cell> bfsQue;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			int temp = (K / 2) % 2 == 1 ? K + 1 : K;

			//맵 준비
			map = new Cell[N + temp][M + temp];
			mapN = N + temp;
			mapM = M + temp;

			//줄기세포 저장
			cellList = new ArrayList<>();
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(in.readLine());
				for (int c = 0; c < M; c++) {
					int nextHp = Integer.parseInt(st.nextToken());

					if (nextHp > 0) {
						map[temp / 2 + r][temp / 2 + c] = new Cell(temp / 2 + r, temp / 2 + c, nextHp, 0);
						cellList.add(map[temp / 2 + r][temp / 2 + c]);
					}
				}
			}

			//input debug print
			bfsQue = new ArrayDeque<>();
			for (int i = 0; i < cellList.size(); i++) {
				bfsQue.add(cellList.get(i));
			}

//			System.out.println("초기상태");
//			print(map);
//			System.out.println("-----▲상태 -------▼HP-----");
//
//			print2(map);

			for (int i = 1; i <= K; i++) {
				bfs(N + temp, M + temp);

//				System.out.println(i);
//				print(map);
//				System.out.println("-----▲상태 -------▼HP-----");
//				print2(map);
			}

			int resultCnt = 0;
			for (int i = 0; i < mapN; i++) {
				for (int j = 0; j < mapM; j++) {
					if (map[i][j] == null) {
						continue;
					}

					if (map[i][j].state == 2 || map[i][j].state == 1) {
						resultCnt++;
					}
				}
			}

			sb.append("#" + tc + " " + resultCnt + "\n");
		}
		System.out.println(sb);
	}

	static int[][] dArr = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	private static void bfs(int N, int M) {
		Cell[][] newMap = new Cell[N][M];
		boolean[][] isSelected = new boolean[N][M];
		newMap = copy(map);

		int size = bfsQue.size();
		while (size-- > 0) {
			Cell pollCell = bfsQue.poll();

			int r = pollCell.r;
			int c = pollCell.c;
			int hp = pollCell.hp;
			int cnt = pollCell.cnt;
			if (newMap[r][c].hp > pollCell.hp) {
				continue;
			}

			int state = pollCell.state;

			if (state == 3)
				continue;
			//활성상태 -> 확산
			if (state == 2) {
				for (int i = 0; i < 4; i++) {
					int dr = r + dArr[i][0];
					int dc = c + dArr[i][1];
					if (map[dr][dc] != null)
						continue;

					if (isIn(dr, dc)) {
						//확산되는 곳이 비어 있을 때
						if (newMap[dr][dc] == null) {
							newMap[dr][dc] = new Cell(dr, dc, hp, 0);
							isSelected[dr][dc] = true;
//							bfsQue.add(newMap[dr][dc]);
						}
						//확산되는 곳이 비어있지 않을 때
						else {
							//기존 맵에 이미 표시되어 있는 것 -> 이미 생성되어 있는 것임(삭제불가능)
							if (map[dr][dc] != null) {
								continue;
							}
							//먼저 들어간 애가 새로들어온 애보다 쌜 경우
							if (newMap[dr][dc].hp > hp) {
								continue;
							} else {
								newMap[dr][dc] = new Cell(dr, dc, hp, 0);
//								bfsQue.add(newMap[dr][dc]);
							}
						}
					}
				}
				newMap[r][c] = new Cell(r, c, hp, cnt + 1);
				bfsQue.add(newMap[r][c]);
			}
			//비활성상태
			else {
				newMap[r][c] = new Cell(r, c, hp, cnt + 1);
				bfsQue.add(newMap[r][c]);
			}
			
		}

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(isSelected[i][j]) {
					bfsQue.add(newMap[i][j]);							
				}
			}
		}

		map = copy(newMap);
	}

	private static boolean isIn(int dr, int dc) {
		return dr >= 0 && dc >= 0 && dr < mapN && dc < mapM;
	}

	private static Cell[][] copy(Cell[][] map) {
		Cell[][] newMap = new Cell[mapN][mapM];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				newMap[i][j] = map[i][j];
			}
		}
		return newMap;
	}

	private static void print(Cell[][] cell) {
		for (int i = 0; i < mapN; i++) {
			for (int j = 0; j < mapM; j++) {
				if (cell[i][j] == null) {
					System.out.print(0 + " ");
				} else {
					System.out.print(cell[i][j].state + " ");
				}
			}
			System.out.println();
		}
	}

	private static void print2(Cell[][] cell) {
		for (int i = 0; i < mapN; i++) {
			for (int j = 0; j < mapM; j++) {
				if (cell[i][j] == null) {
					System.out.print(0 + " ");
				} else {
					System.out.print(cell[i][j].hp + " ");
				}
			}
			System.out.println();
		}
	}

}
