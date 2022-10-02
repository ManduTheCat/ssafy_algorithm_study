package week8.BJ1034Lamp;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String[] table = new String[n];  // 램프가 들어있는 탁자
        int[] zero = new int[n];  // 각 행마다 0개수 저장 배열

        for(int i = 0; i < n; i++){
            table[i] = br.readLine();
            for(int j = 0; j < m; j++){
                if(table[i].charAt(j) == '0') zero[i]++;
            }
        }

        int ans = 0;
        int k = Integer.parseInt(br.readLine());
        HashSet<String> visited = new HashSet<>();  // 방문 체크

        for(int i = 0; i < n; i++){
            if(visited.contains(table[i])) continue;  // 이미 방문한 곳이면 패스
            visited.add(table[i]);  // 방문 처리

            if(zero[i] > k) continue;  // 각 행의 0의 개수가 k보다 많다면 그 행의 모든 램프를 키는 것은 불가능함.
            if(zero[i] % 2 != k % 2) continue;  // 각 행의 0의 개수와 k가 짝,홀이 같아야 가능한 경우.

            int cnt = 1;
            for(int j = i+1; j < n; j++){  // i 행 밑에 있는 table 중에
                if(table[i].equals(table[j])) cnt++;  // table[i]와 같은 형태의 수를 계산
            }

            ans = Math.max(ans, cnt);  // 최대값 갱신
        }

        System.out.println(ans);
    }

}