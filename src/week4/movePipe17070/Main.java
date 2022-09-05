import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int[][] map;
  static int cnt;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    StringTokenizer st;
    map = new int[N + 1][N + 1];
    for (int r = 1; r <= N; r++) {
      st = new StringTokenizer(br.readLine());
      for (int c = 1; c <= N; c++) {
        map[r][c] = Integer.parseInt(st.nextToken());
      }
    }
    dfs(1, 2, 0);
    System.out.println(cnt);

  }

  static void dfs(int r, int c, int position) {// position이 0이면 가로, 1이면 세로, 2이면 대각선
    if (r == N && c == N) {

      cnt++;
      return;
    }

    switch (position) {
      case 0:// 가로일때
        if (c + 1 <= N && map[r][c + 1] != 1) {// 범위 밖으로 나가지 않았고 벽이 아니면 가로로 이동
          dfs(r, c + 1, 0);// 그대로 가로 모양으로 보내준다.

        }
        break;
      case 1:// 세로일때
        if (r + 1 <= N && map[r + 1][c] != 1) {// 맵 안이고 벽이 아니면 세로로 이동
          dfs(r + 1, c, 1);// 그대로 세로 모양으로 다음턴으로 보내줌.

        }
        break;
      case 2:// 대각선일때
        if (c + 1 <= N && map[r][c + 1] != 1)
          dfs(r, c + 1, 0); // 대각선에서 가로 모양으로 이동할 때
        if (r + 1 <= N && map[r + 1][c] != 1)
          dfs(r + 1, c, 1); // 대각선에서 세로 모양으로 이동할 때

        break;
    }
    // 대각선으로 이동할때는 공통, 대각선은 겹쳐지는 양 옆의 두칸 또한 벽인지 아닌지 확인해줘야한다.
    if (r + 1 <= N && c + 1 <= N && map[r + 1][c + 1] != 1 && map[r + 1][c] != 1 && map[r][c + 1] != 1) {
      dfs(r + 1, c + 1, 2);
    }

  }

}
