package week9.growingCellSWEA5653;

import java.io.*;
import java.util.*;

public class Solution {

    public static class Cell implements Comparable<Cell>{
        int x, y;
        int initLife;  // 초기 생명력
        int restLife;  // 지간이 지남에 따라 남은 생명력
        int state;  // 0 : 비활성, 1 : 활성, 2 : 죽음
        int time;

        public Cell(int x, int y, int initLife, int restLife, int state, int time) {
            this.x = x;
            this.y = y;
            this.initLife = initLife;
            this.restLife = restLife;
            this.state = state;
            this.time = time;
        }

        // 활성, 비활성 상태에서 시간이 지남.
        public Cell goTime(){
            this.restLife--;
            this.time++;
            return this;
        }

        // 상태를 변경
        public Cell changeState(int state){
            this.restLife = this.initLife;  // 상태가 변경될 때 restLife 초기화
            this.state = state;
            this.time++;
            return this;
        }

        // 우선순위 : 시간순서 -> 초기 생명력이 큰 순 -> 남은 생명력이 작은 순
        @Override
        public int compareTo(Cell o) {
            if(this.time == o.time){
                if(o.initLife == this.initLife) return this.restLife - o.restLife;
                return o.initLife - this.initLife;
            }
            return this.time - o.time;
        }
    }

    public static int k, ans;
    public static boolean[][] visited;
    public static PriorityQueue<Cell> pq;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= t; tc++) {
            ans = 0;
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            // 배양 용기의 크기는 무한하다고 가정하지만, k가 최대 300이면 크기는 최대 350 * 350
            // 저는 넉넉잡아 500 * 500으로 설정
            visited = new boolean[500][500];
            pq = new PriorityQueue<>();

            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < m; j++){
                    int life = Integer.parseInt(st.nextToken());
                    if(life == 0) continue;

                    int x = i+200;  // 초기 중점 좌표를 (200, 200)으로 설정
                    int y = j+200;

                    visited[x][y] = true;
                    pq.offer(new Cell(x, y, life, life, 0, 0));  // 초기 세포 상태를 우선순위 큐에 저장
                    ans++;  // 활성 + 비활성 세포 개수 카운트
                }
            }

            bfs();

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void bfs(){

        while(!pq.isEmpty()) {
            Cell cell = pq.poll();
            int x = cell.x;
            int y = cell.y;
            int initLife = cell.initLife;
            int restLife = cell.restLife;
            int state = cell.state;
            int time = cell.time;

            if(time == k) return;  // 시간이 k가 되면 종료

            // 비활성 세포
            if (state == 0) {
                if (restLife > 1) pq.offer(cell.goTime());  // 아직 생명력이 남아 있으면 시간만 경과
                else pq.offer(cell.changeState(1));  // 비활성 상태에서 생명력을 다하면 활성상태로 변경
            }
            // 활성 세포
            else if (state == 1) {
                // 활성 세포가 되면 바로 번식
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (!visited[nx][ny]) {  // 방문하지 않았다면
                        visited[nx][ny] = true;
                        // 비활성 상태로 세포 생성
                        // 우선순위를 설정해두었기 때문에 우선순위 큐에서 가장 먼저 나오는 세포로 번식하면 됨.
                        pq.offer(new Cell(nx, ny, initLife, initLife, 0, time+1));
                        ans++;  // 카운트 증가
                    }
                }

                if (restLife > 1) pq.offer(cell.goTime());  // 아직 생명력이 남아 있으면 시간만 경과
                else {
                    cell.changeState(2);  // 활성 상태에서 생명력을 다하면 죽음으로 변경
                    ans--;  // 카운트 감소.
                }

            }
        }
    }

}

