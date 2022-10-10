package week9.makingTrailSWEA1949;

import java.io.*;
import java.util.*;

public class Solution {

    public static class Pos{
        int x, y;
        int rest;  // 산을 깍을 수 있나 없나. 1 : 깍을 수 있음, 0 : 깍을 수 없음.
        int dist;  // 이동한 거리

        public Pos(int x, int y, int rest, int dist) {
            this.x = x;
            this.y = y;
            this.rest = rest;
            this.dist = dist;
        }
    }

    public static int n, k, ans;
    public static int[][] map;
    public static boolean[][] visited;

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
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            int max = 0;  // 가장 높은 봉우리 높이
            map = new int[n][n];
            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    max = Math.max(max, map[i][j]);  // 가장 높은 봉우리 높이 저장
                }
            }

            visited = new boolean[n][n];
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(map[i][j] == max){  // 가장 높은 봉우리라면 dfs로 탐색해본다.
                        visited[i][j] = true;
                        dfs(new Pos(i, j, 1, 1));
                        visited[i][j] = false;
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void dfs(Pos pos){
        ans = Math.max(ans, pos.dist);  // 매번 최장 거리 갱신

        int x = pos.x;
        int y = pos.y;
        int rest = pos.rest;
        int dist = pos.dist;

        for(int i = 0; i < 4; i++){  // 4방 탐색
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(!isRange(nx, ny)) continue;

            if(!visited[nx][ny]){
                if(map[x][y] > map[nx][ny]) {  // 산을 깍지 않고 갈 수 있다면, rest가 1이든 0이든 갈 수 있음
                    visited[nx][ny] = true;
                    dfs(new Pos(nx, ny, rest, dist+1));
                    visited[nx][ny] = false;
                }
                else{  // 산을 깍아야지만 갈 수 있다면
                    if(rest == 0) continue;  // 산을 이미 깍아서 rest == 0이라면 못감. 패스

                    if(map[x][y] > map[nx][ny] - k){  // k를 깍아서 갈 수 있다면
                        visited[nx][ny] = true;
                        int tmp = map[nx][ny];
                        map[nx][ny] = map[x][y] - 1;  // k만큼 다 깍을 필요가 없음. map[x][y]보다 1 작게만 깍음.

                        dfs(new Pos(nx, ny, 0, dist+1));

                        map[nx][ny] = tmp;  // 원상 복구
                        visited[nx][ny] = false;
                    }
                }
            }
        }
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < n;
    }

}