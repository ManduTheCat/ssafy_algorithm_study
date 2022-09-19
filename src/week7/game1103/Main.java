package week7.game1103;

import java.io.*;
import java.util.*;

public class Main {

    public static class Pos implements Comparable<Pos>{
        int x, y, damage;

        public Pos(int x, int y, int damage) {
            this.x = x;
            this.y = y;
            this.damage = damage;
        }

        @Override
        public int compareTo(Pos o) {
            return this.damage - o.damage;
        }
    }

    public static final int size = 500;
    public static int[][] map;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new int[size+1][size+1];

        int n = Integer.parseInt(br.readLine());
        // 위험 구역
        while(n-->0){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            // 위험 구역 = 1
            setZone(x1, x2, y1, y2, 1);
        }

        int m = Integer.parseInt(br.readLine());
        // 죽음 구역
        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            // 죽음 구역 = 2
            setZone(x1, x2, y1, y2, 2);
        }

        System.out.println(bfs());
    }

    // 구역 세팅
    public static void setZone(int x1, int x2, int y1, int y2, int zone){
        int startX = Math.min(x1, x2);
        int endX = Math.max(x1, x2);
        int startY = Math.min(y1, y2);
        int endY = Math.max(y1, y2);

        for(int i = startX; i <= endX; i++){
            for(int j = startY; j <= endY; j++){
                map[i][j] = zone;
            }
        }
    }

    public static int bfs(){
        boolean[][] visited = new boolean[size+1][size+1];
        PriorityQueue<Pos> pq = new PriorityQueue<>();  // 우선순위큐 : 잃은 생명이 작은 순
        pq.offer(new Pos(0, 0, 0));
        visited[0][0] = true;

        while(!pq.isEmpty()){
            Pos p = pq.poll();
            int x = p.x;
            int y = p.y;
            int damage = p.damage;

            if(x == size && y == size) return damage;  // 가장 먼저 (500, 500)에 도착한 damage 리턴

            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(!isRange(nx, ny)) continue;

                if(!visited[nx][ny] && map[nx][ny] != 2){  // 이미 방문했거나, 죽음 구역이면 패스
                    visited[nx][ny] = true;
                    int newDamage = map[nx][ny] == 0 ? damage : damage+1;  // 안전 구역이면 damage는 그대로, 위험 구역이면 damage+1
                    pq.offer(new Pos(nx, ny, newDamage));
                }
            }
        }

        return -1;  // (500, 500)에 도착할 수 없으면 -1 리턴
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x <= size && y >= 0 && y <= size;
    }

}