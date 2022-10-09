package week9.findBead1103;

import java.io.*;
import java.util.*;

public class Main {

    public static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] heavy = new ArrayList[n+1];  // 무거운 구슬 단방향 그래프
        ArrayList<Integer>[] light = new ArrayList[n+1];  // 가벼운 구슬 단방향 그래프

        for(int i = 1; i <= n; i++){
            heavy[i] = new ArrayList<>();
            light[i] = new ArrayList<>();
        }

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            heavy[a].add(b);  // 무거운 구슬 추가
            light[b].add(a);  // 가벼운 구슬 추가
        }

        int[][] arr = new int[n+1][2];

        for(int i = 1; i <= n; i++){
            arr[i][0] = bfs(i, heavy);  // 해당 구슬보다 무거운 구슬 카운트
            arr[i][1] = bfs(i, light);  // 해당 구슬보다 가벼운 구슬 카운트
        }

        int half = n/2+1;
        int ans = 0;
        for(int i = 1; i <= n; i++){
            // 해당 구슬보다 무겁거나 가벼운 구슬의 개수가 절반 이상이라면
            // 중간인 구슬이 될 수 없음.
            if(arr[i][0] >= half || arr[i][1] >= half) ans++;
        }

        System.out.println(ans);
    }

    // bfs 탐색으로 해당 구슬보다 무겁거나, 가벼운 구슬 개수 카운트
    public static int bfs(int start, ArrayList<Integer>[] graph){
        boolean[] visited = new boolean[n+1];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        visited[start] = true;

        int cnt = 0;
        while(!q.isEmpty()){
            int now = q.poll();

            for(int next : graph[now]){
                if(!visited[next]){
                    visited[next] = true;
                    q.offer(next);
                    cnt++;
                }
            }
        }

        return cnt;
    }

}