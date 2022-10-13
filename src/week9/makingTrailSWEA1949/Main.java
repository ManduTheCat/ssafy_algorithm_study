package week9.makingTrailSWEA1949;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author chaeeun
 * @date 2022.10.09
 * @description
 * 
 */
public class Solution {

	private static int result;
	private static int N;
	private static int K;
	private static int[][] map;
	private static boolean[][] visited;
	private static int[] dirR = { -1, 0, 1, 0 };
	private static int[] dirC = { 0, 1, 0, -1 }; // 상우하좌

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			visited = new boolean[N][N];
			result = 2;
			int max = Integer.MIN_VALUE;
			// 최대값을 구한다.
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (map[r][c] > max) {
						max = map[r][c];
					}
				}
			}

			// 정상에서 길찾기
			for (int r = 0, count = 0; r < N && count < 5; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] == max) {
						count++;
						dfs(r, c, true, 1);
					}
				}
			}
			sb.append("#").append(t).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}

	/**
	 * @methodName dfs
	 * @description
	 * @param r
	 * @param c
	 * @param b
	 */
	private static void dfs(int r, int c, boolean cut, int count) {
		visited[r][c] = true;
//		printVisited();
		int nextR;
		int nextC;
		int origin;
		for (int d = 0; d < 4; d++) {
			nextR = r + dirR[d];
			nextC = c + dirC[d];
			if (canGo(nextR, nextC)) { // 갈 수 있음
				if (map[nextR][nextC] < map[r][c]) {
					dfs(nextR, nextC, cut, count + 1);
					continue;
				} else if (cut && map[nextR][nextC] - map[r][c] < K) { // 가려는 곳이 더 높으면서 깎을 수 있음
					origin = map[nextR][nextC];
					map[nextR][nextC] = map[r][c] - 1;
					dfs(nextR, nextC, false, count + 1);
					map[nextR][nextC] = origin;
					continue;
				}
			}
			// 갈 수 없음
			if (result < count) {
				result = count;
			}
		}
		visited[r][c] = false;
	}
    	private static boolean canGo(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < N && !visited[r][c]) {
			return true;
		}
		return false;
	}


}
