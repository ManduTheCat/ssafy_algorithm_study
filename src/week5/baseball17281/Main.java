import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek17281 {
  static int max = Integer.MIN_VALUE;
  static int cnt = 0;
  static int inning = 0;
  static int[][] results;
  static int[] players = new int[9];
  static boolean[] selected = new boolean[9];
  

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    inning = Integer.parseInt(br.readLine());
    results = new int[inning][9];
    for (int i = 0; i < inning; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 9; j++) {
        results[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    players[3] = 0; //1번 타자 미리 넣어주기
    selected[0] = true;
    permutation(0);
    System.out.println(max);
  }
  
  static void permutation(int cnt) {//1번을 제외한 8명의 타순을 정한다.
    if (cnt == 9) { //선수 명단이 완성되었다면 야구를 시작
      // System.out.println(Arrays.toString(players));
      baseball();
      return;
    }
    if (cnt == 3) { //4번째 타자는 이미 구했으므로 바로 다음 타순으로
      permutation(cnt + 1);
      return;
    }

    for (int i = 0; i < 9; i++) {
      if (selected[i]) //현재 선수가 이미 뽑혔다면
        continue;
      selected[i] = true;
      players[cnt] = i;
      permutation(cnt + 1);
      selected[i] = false;
    }

  }
  
  static void baseball() {
    int play = 0;
    int out = 0;
    int point = 0;
    int order = 0;
    boolean[] stand = new boolean[3]; //타석에 타자가 있는지
    while (play < inning) {
      switch (results[play][players[order]]) {
        case 0:
          out++;
          if (out == 3) { //3아웃이면 다음 이닝으로
            play++;
            stand = new boolean[3];
            out = 0;
          }
          break;
        case 1:
          if (stand[2] == true) {//3루
            point++;
            stand[2] = false;
          }
          if (stand[1] == true) {//2루
            stand[2] = stand[1];
            stand[1] = false;
          }
          if (stand[0] == true) {//1루
            stand[1] = true;
            stand[0] = false;
          }
          stand[0] = true;
          break;
        case 2:
          if (stand[2] == true) {//3루
            point++;
            stand[2] = false;
          }
          if (stand[1] == true) {//2루
            point++;
            stand[1] = false;
          }
          if (stand[0] == true) {//1루
            stand[2] = true;
            stand[0] = false;
          }
          stand[1] = true;
          break;
        case 3:
          if (stand[2] == true) {//3루
            point++;
            stand[2] = false;
          }
          if (stand[1] == true) {//2루
            point++;
            stand[1] = false;
          }
          if (stand[0] == true) {//1루
            point++;
            stand[0] = false;
          }
          stand[2] = true;
          break;
        case 4:
          int count = 0;
          for (int i = 0; i < 3; i++) {
            if (stand[i] == true) {
              count++;
            }
          }
          point += (count + 1);
          stand = new boolean[3];
          break;
      }
      order = (order + 1) % 9;
    }
    
    max = Math.max(max, point);
  }

}
