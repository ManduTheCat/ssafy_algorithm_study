package week6.monkeyWhoWannabeHorse1600;

import java.util.ArrayDeque;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{

    static class Info {
        int r;
        int c;
        int hores; //말로 이동할 수 있는 남은 기회
        int move ; //움직인 횟수

        public Info(int r, int c, int hores,int move) {
            this.r=r;
            this.c=c;
            this.hores=hores;
            this.move=move;
        }
    }
    static int [][]map;

    static int K;
    static int R,C;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        C =Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        for(int i = 0 ; i <R ;i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j<C;j++){

                int n = Integer.parseInt(st.nextToken());
                if(n==1){ //장애물일때
                    map[i][j] = Integer.MAX_VALUE;
                }else {
                    //평지일때 -1로 초기화. -> 그 이유는 map에 남은 말처럼 이동할 수 있는 남은 횟수를 저장할꺼기 때문 따라서 0값도 올 수 있기때문에 -1로 초기화.
                    map[i][j] = -1;
                }
            }
        }



        System.out.println(bfs(new Info(0,0,K,0)));



    }

    private static  int  bfs (Info info) {


        int [] dr = {0,0,1,-1};
        int [] dc = {1,-1,0,0};


        int [] hr = {-1,  1, -1, 1, -2,  2, -2, 2};
        int [] hc = {-2, -2,  2, 2, -1, -1,  1, 1};

        Queue<Info> queue = new ArrayDeque<>();
        queue.add(info);

        if (R == 1 && C== 1)   return 0;

        while (!queue.isEmpty()) {

            Info current_Info = queue.poll();


            int curR = current_Info.r;
            int curC = current_Info.c;
            int nextMove = current_Info.move +1;
            int curHourse = current_Info.hores;


            //상 하 좌 우 인접이동
            for(int i = 0 ; i <4 ; i++){

                int nr = curR + dr[i];
                int nc = curC + dc[i];

                //2차원 배열의 범위를 넘어가는 경우는 넘겨준다.
                if (!isRange(nr, nc) )continue;

                //남은 말처럼 이동할 수 있는 횟수가 map[nr][nc]보다 큰 경우
                //이 조건을 한 이유는
                // 예를 들어 어떠한 좌표에 원숭이가 도착했다고 치자. (3,2)좌표라고 해보겠다.
                // 이 좌표에 처음 도착했을때 move값이 그 칸에 갈 수 있는 가장 최소의 값일 것이다.
                // 이때 남은 말의 회수가 1이였다고 치고 map[3][2]=1을 저장하였다.
                //또 어떠한 경로로 인해 map[3][2]에 올 수 있는 경우가 있다. 이때 move값은 처음도착했을때의 값보다 클 것이다.
                //하지만 map[nr][nc]=1이였을 때 , 이 걸로 도착지(정답)에 도착한다는 보장이 없다.
                //따라서 map[nr][nc] <curHourse 말로이동할 수 있는더 큰 경우가 존재한다면 그 경우에도 탐색을 해 주어야하기때문에 이 조건문을 썼다.
                if(map[nr][nc]<curHourse){

                    //도착지에 도착했다면 이동 횟수를 리턴한다.
                    //큐에서 꺼낸 Info의 move값은 최소값으로 수 밖에 없다.
                    //큐에 move가 1일때,2일때,3일때 순으로 차례로 저장되기 때문이다.
                    if(nr==R-1 && nc ==C-1){
                        return nextMove;
                    }
                    //map에 남은 말처럼 이동 할 수 있는 횟수를 저장한다.
                    map[nr][nc] = curHourse;
                    queue.add(new Info(nr, nc, curHourse, nextMove));

                }

            }


            //말처럼 이동 하는 경우
            // 남은 횟수가 0보다 커야한다.
            if(current_Info.hores>0){
                //말로 이동하는 거니까 횟수를 하나 감소시킨다.
                curHourse --;
                //말로 이동
                for(int i = 0 ; i < 8 ; i++){

                    int nr = curR + hr[i];
                    int nc = curC + hc[i];


                    if (!isRange(nr, nc))continue;

                    if(map[nr][nc]<curHourse){
                        if(nr==R-1 && nc ==C-1){

                            return nextMove;
                        }

                        map[nr][nc] = curHourse;
                        queue.add(new Info(nr, nc, curHourse, nextMove));
                    }
                }

            }


        }
        return -1;
    }

    private static boolean isRange (int r,int c){
        return r>=0 && r<R && c>=0 && c<C;
    }
}