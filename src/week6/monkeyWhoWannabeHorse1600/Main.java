import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek1600 {
	static int K;
	static int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };
	static int[] hy = { 1, 2, 2, 1, -1, -2, -2, -1 };
	static int[] hx = { 2, 1, -1, -2, -2, -1, 1, 2 };
	static int[][] map;
	static int W, H;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		for (int r = 0; r < H; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < W; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		// for (int[] arr : map) {
		// System.out.println(Arrays.toString(arr));
		// }
		System.out.println(bfs(0, 0));
	}

	static int bfs(int x, int y) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] { x, y, 0, 0 }); // x위치,y위치, 말처럼 간 횟수, 움직인 횟수
		boolean[][][] check = new boolean[H][W][K + 1]; // 이미 간곳과 말처럼 간 경우의 수를 모두 체크하기 위한 배열

		check[x][y][0] = true;
		while (!queue.isEmpty()) {
			int[] pos = queue.poll();
			int px = pos[0], py = pos[1];
			int chance = pos[2], move = pos[3];

			if (px == H - 1 && py == W - 1) { // 목적지에 도착했다면
				return move;
			}

			for (int i = 0; i < 4; i++) { // 인접방향 탐색
				int nx = px + dx[i];
				int ny = py + dy[i];

				if (nx < 0 || nx > H - 1 || ny < 0 || ny > W - 1) // 맵의 범위를 벗어났다면
					continue;
				if (check[nx][ny][chance]) // 이미 왔던 곳이라면
					continue;

				if (map[nx][ny] != 1) { // 도착한 곳이 장애물이 아니라면 큐에 넣어줌
					check[nx][ny][chance] = true;
					queue.add(new int[] { nx, ny, chance, move + 1 });
				}
			}

			if (chance < K) { // 말처럼 갈 수 있는 횟수가 남아있다면
				for (int i = 0; i < 8; i++) { // 말처럼 가셈
					int nx = px + hx[i];
					int ny = py + hy[i];

					if (nx < 0 || nx > H - 1 || ny < 0 || ny > W - 1)
						continue;
					if (check[nx][ny][chance + 1])
						continue;

					if (map[nx][ny] != 1) {
						check[nx][ny][chance + 1] = true; // 말처럼 갔으니까 한번 카운트해주렴
						queue.add(new int[] { nx, ny, chance + 1, move + 1 });
					}
				}
			}

		}

		return -1;
	}
}
