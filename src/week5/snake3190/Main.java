import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Baek3190 {
  static int[] dx = {0, 1, 0 ,-1};
  static int[] dy = {1, 0, -1, 0};
  static int time;
  static int N;
  static int[][] map;
  static Deque<move> moves = new ArrayDeque<move>();
  static Deque<snake> snakes = new ArrayDeque<snake>();
  static class snake {
    int r, c, dir;

    public snake(int r, int c, int dir) {
      this.r = r;
      this.c = c;
      this.dir = dir;
    }
    @Override
    public boolean equals(Object obj) { //ArrayDeque의 contians메서드 사용을 위해 비교 기준을 만들어준다.
      snake s = (snake) obj;
      if (this.r == s.r && this.c == s.c) {
        return true;
      }
      else
        return false;
    }
  }

  static class move {
    int time;
    char dir;

    public move(int time, char dir) {
      this.time = time;
      this.dir = dir;
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    map = new int[N + 1][N + 1];
    StringTokenizer st;
    int K = Integer.parseInt(br.readLine());
    for (int i = 0; i < K; i++) {//사과 위치 표시
      st = new StringTokenizer(br.readLine());
      int r = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      map[r][c] = 1;
    }
    int L = Integer.parseInt(br.readLine());
    for (int i = 0; i < L; i++) {
      st = new StringTokenizer(br.readLine());
      int time = Integer.parseInt(st.nextToken())+1;
      char dir = st.nextToken().charAt(0);
      moves.add(new move(time, dir));
    }
    snakes.add(new snake(1, 1, 0));
    game();
    System.out.println(time);
  }

  static void game() {
    // time = 1;
    while (true) {
      snake cur = snakes.getFirst();
      int cur_r = cur.r, cur_c = cur.c, cur_dir = cur.dir; //현재 뱀머리 위치와 방향을 넣어준다.
      int next_r, next_c, next_dir;
      move temp1 = null;
      time += 1; //시간 증가시켜주고
      if(moves.size()>0)
        temp1 = moves.getFirst();  //자료구조 가장 앞의 이동지시를 가져온다.
      if (temp1!=null &&temp1.time == time) { //이동지시를 수행해야할 시간 초라면
        if (temp1.dir == 'L') {
          if (cur_dir == 0) {
            next_dir = 3;
          } 
          else 
            next_dir = cur_dir - 1;
        } 
        else {//방향이 R이라면
          if (cur_dir == 3) {
            next_dir = 0;
          }
          else
            next_dir = cur_dir + 1;
        }
        moves.pollFirst();//앞의 이동명령을 빼준다.
      }//이동지시 수행할 타이밍이 아니라면
      else {
        next_dir = cur_dir;
      }
      next_r = cur_r + dx[next_dir];
      next_c = cur_c + dy[next_dir];
      if (next_r < 1 || next_r > N || next_c < 1 || next_c > N) //이동한 뱀이 맵을 벗어나면 탈출
        break;
      //늘어난 머리 위치가 몸과 충돌하는지 확인한다.
      snake temp2 = new snake(next_r, next_c, next_dir);
      if (snakes.contains(temp2))
        break;    //자신과 충돌하면 탈출
      
      snakes.addFirst(temp2); //뱀의 머리 위치를 늘려준다.
      if (map[next_r][next_c] == 1) { //사과가 있는 위치라면 먹고 꼬리를 줄이지 않는다.
        map[next_r][next_c] = 0;
      } else {  //사과가 없다면 꼬리를 줄여준다.
        snakes.pollLast();
      }   
    }
  }

}
