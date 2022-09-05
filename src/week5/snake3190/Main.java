package week5.snake3190;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    /**
     * N => NxN 크기
     * K => 총 사과의 개수
     * L => 뱀의 변환 횟수
     */
    static int N,K ,L ;
    static int [][]map;
    //오 - > 아래  -> 왼  -> 위  (시계방향)
    static int [] dr = {0,0,1,0,-1};
    static int [] dc = {0,1,0,-1,0};

    //뱀 머리 , 꼬리 좌표
    static int headR = 1;
    static int headC = 1;
    static int tailR = 1;
    static int tailC = 1;
    static int totalTime;
    static int snake_Direction ;//뱀 방향
    static boolean isflag ; //정답을 구했는지 안구했는지.
    public static void main(String[] args)  throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        N = Integer.parseInt(br.readLine());  //NxN 크기
        K = Integer.parseInt(br.readLine());  // 사과의 개수

        map = new int[N+1][N+1]; // 맨 왼쪽 위의 좌표가 (1,1) 이므로 1칸 패딩시켜줌

        /** map 에 사과 위치 찍어두기 **/
        for(int i = 0  ; i < K ; i++){ //사과의 개수 만큼 반복
            st = new StringTokenizer(br.readLine());

            int row = Integer.parseInt(st.nextToken()); //사과 행 좌표
            int col = Integer.parseInt(st.nextToken()); //사과 열 좌표

            map[row][col]=5; //해당하는 좌표에 5로 저장 (사과가 있다는 의미)
        }

        int time = 0;
        map[tailR][tailC]=1; //첫 칸은 오른쪽 방향을 보고 있음
        L = Integer.parseInt(br.readLine()); //뱀의 방향 변환 횟수
        totalTime = 0 ;  // 진행된 시간 (초)
        snake_Direction = 1; // 뱀 방향 (초기에 오른쪽을 방향을 보고 있기때문)


     loop: for(int i = 0 ; i < L;i++){
            st = new StringTokenizer(br.readLine());

            int second = Integer.parseInt(st.nextToken()); // 초
            char next_Direction = st.nextToken().charAt(0); //시작 시간으로 부터 second 초 가 끝난 뒤 돌릴 방향

            time = totalTime; // 지금까지 진행된 초로 초기화
            for(int k = 0; k < second-time ; k++){ //입력받은 second 에서 현재 초 뺀거 만큼만 반복해야함
                goSnake(); //뱀 움직이기

              if(isflag){
                  break loop;
              }

            }

            //초가 지난 후에 방향 변경 시키기
            if(next_Direction == 'D'){ // 오른쪽으로 이동
                snake_Direction++; // 1씩 증가시키면 오른쪽 방향으로 회전하게끔 dr ,dc 를 설정했음.
                if(snake_Direction==5) snake_Direction=1;
            }else { //왼쪽으로 이동
                snake_Direction --; //1씩 감소 시키면 왼쪽 방향으로 회전하게끔 dr,dc를 설정했음.
                if(snake_Direction==0) snake_Direction=4;
            }

            map[headR][headC]=snake_Direction; //방향이 전환되었다면 바뀐 좌표에 바뀐 방향을 저장
        }


        while (!isflag){//위에서 정답을 구하지 못한 경우에는 마지막에 설정된 방향으로 계속 뱀을 움직인다.
            goSnake();
            if(isflag)break;
        }

    }

    /** 뱀 이동시키기 */
    private static void goSnake (){

        //다음으로 이동할 좌표
        int nr = headR + dr[snake_Direction];
        int nc = headC + dc[snake_Direction];

        if(!isRange(nr,nc)||map[nr][nc]>=1 &&map[nr][nc]<=4) { //벽에 부딪히거나 자기 몸뚱아리에 닿았을 경우 종료
            System.out.println(totalTime+1);
            isflag=true;
            return;
        }


        if(map[nr][nc]==0){ //사과가 없다면 뱀의 몸 길이는 늘어나지 않는다. (꼬리 이동)
            int tempR = tailR ; int tempC = tailC;

            //꼬리 이동
            tailR += dr[map[tempR][tempC]];
            tailC += dc[map[tempR][tempC]];

            map[tempR][tempC]=0; //꼬리 이동 후 원래 꼬리 좌표는 0으로 다시 만들기

        }

        //사과를 먹거나 먹지 않거나 뱀의 머리는 이동한다.

        //머리 좌표 갱신
        headR = nr;
        headC = nc;
        //그 좌표에 방향을 저장 
        map[nr][nc] = snake_Direction;
        totalTime++; //초 증가
       //  printMap();

    }

    /** 2차원배열 출력 (디버그)**/
    private static void printMap (){
        for(int r =1 ; r<=N;r++){
            for(int c = 1 ; c<=N;c++){
                System.out.print(map[r][c]+" ");
            }
            System.out.println();
        }

        System.out.println("@@@@@");
    }
    /**움직일 수 있는 범위를 넘어갔는지 체크하는 함수**/
    private static boolean isRange (int r ,int c){
        return r >0 && r <=N && c>0 && c<=N;
    }
}
