package week9.makingTrailSWEA1949;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Point {
		int r, c, chance, dis, map[][];
		boolean[][] isSelected;

		public Point(int r, int c, int chance, int dis, int map[][], boolean isSelected[][]) {
			super();
			this.r = r;
			this.c = c;
			this.chance = chance;
			this.dis = dis;
			this.map = map;
			this.isSelected = isSelected;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + " chance=" + chance + " dis=" + dis + "]";
		}
	}

	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	static int N, K;
	static int[][] map;

	static ArrayList<Point> highList;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine());

			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			highList = new ArrayList<>();

			int high = 0;
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(in.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					high = Math.max(map[r][c], high);
				}
			}

			boolean[][] isSelected = new boolean[N][N];

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] == high) {
						//한 곳 만 깍을 수 있기 때문에 chance 1로 줌.
						isSelected[r][c] = true;
						highList.add(new Point(r, c, 1, 1, map, isSelected));
						isSelected = new boolean[N][N];

					}
				}
			}

			int result = -1;
			for (int i = 0; i < highList.size(); i++) {
				result = Math.max(result, bfs(highList.get(i)));
			}

			sb.append("#" + tc + " " + result + "\n");
		}
		System.out.println(sb);
	}

	private static int bfs(Point start) {
		final int[][] dArr = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		Queue<Point> bfsQ = new ArrayDeque<>();

		bfsQ.add(start);
		int dis = start.dis;

		while (!bfsQ.isEmpty()) {
			Point point = bfsQ.poll();

			int r = point.r;
			int c = point.c;
			int chance = point.chance;
			dis = point.dis;

			boolean[][] isSelected = copy(point.isSelected);
			int[][] pollMap = copy(point.map);
			int curHigh = pollMap[r][c];

			for (int i = 0; i < 4; i++) {
				int dr = r + dArr[i][0];
				int dc = c + dArr[i][1];

				// 범위 내 들어오는지 확인
				if (isIn(dr, dc)) {
					//다음 위치가 나보다 클 때 높이를 깍아서 들어갈 수 있는 곳인지 확인
					if (pollMap[dr][dc] >= curHigh && pollMap[dr][dc] - K < curHigh && !isSelected[dr][dc]) {
						// 깍을 수 있는 기회가 있다.
						if (chance == 1) {
							// 다음칸이 높아서 올라갈 수 없을 때, 깍아서 갈 수 있는 곳인지 확인
							int[][] nextMap = new int[N][N];
							nextMap = copy(pollMap);
							nextMap[dr][dc] = curHigh - 1;

							boolean[][] nextIsSelected = new boolean[N][N];
							nextIsSelected = copy(isSelected);
							nextIsSelected[dr][dc] = true;
							bfsQ.offer(new Point(dr, dc, 0, dis + 1, nextMap, nextIsSelected));
						}
					}
					// 깍을 수 있는 기회가 없다 -> K만큼 깍을 필요 없음.
					else {
						if (pollMap[dr][dc] < curHigh && !isSelected[dr][dc]) {
							boolean[][] nextIsSelected = new boolean[N][N];
							nextIsSelected = copy(isSelected);
							nextIsSelected[dr][dc] = true;
							bfsQ.offer(new Point(dr, dc, chance, dis + 1, pollMap, nextIsSelected));
						}
					}
				}
			}
		}

		return dis;
	}

	private static int[][] copy(int[][] map) {
		int[][] temp = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				temp[i][j] = map[i][j];
			}
		}
		return temp;
	}

	private static boolean[][] copy(boolean[][] map) {
		boolean[][] temp = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				temp[i][j] = map[i][j];
			}
		}
		return temp;
	}

	private static boolean isIn(int dr, int dc) {
		return dr >= 0 && dc >= 0 && dr < N && dc < N;
	}
}
