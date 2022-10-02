package week7.lies1043;

import java.io.*;
import java.util.*;

public class Main {

    // union-find : 부모를 나타낼 배열
    public static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        for(int i = 1; i <= n; i++){
            parent[i] = i;  // 자기 자신으로 초기화
        }

        st = new StringTokenizer(br.readLine());
        int truth = Integer.parseInt(st.nextToken());  // 진실을 알고 있는 인원 수

        // 진실을 알고 있는 사람은 부모를 0으로 해서 집합을 만듬.
        // parent[x] == 0 -> 진실을 알고 있는 인원 집합
        while(truth-->0){
            parent[Integer.parseInt(st.nextToken())] = 0;
        }

        ArrayList<Integer>[] party = new ArrayList[m];  // 파티 인원 저장할 ArrayList 배열
        for(int i = 0; i < m; i++){
            party[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());  // 파티 인원 수

            for(int j = 0; j < count; j++){
                int person = Integer.parseInt(st.nextToken());  // 파티 참석 인원
                party[i].add(person);  // 파티 ArrayList에 추가
            }

            union(party[i]);  // 파티 ArrayList에 있는 인원들을 한 집합으로 union
        }

        int ans = 0;
        for(int i = 0; i < m; i++){  // 파티 수만큼 돌면서 거짓말을 해도 되는지 체킹
            boolean isKnow = false;  // 진실을 아는 사람이 있는지 여부
            for (int person : party[i]) {  // 파티의 참여 인원들을 모두 보면서
                if(find(person) == 0){  // 파티 참여 인원 중 한명이라도 진실을 아는 사람이 있다면 진실을 말해야함.
                    isKnow = true;
                    break;
                }
            }

            if(!isKnow) ans++;  // 파티에 진실을 아는 사람이 없으면 거짓말 가능
        }

        System.out.println(ans);
    }

    public static int find(int x){
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(ArrayList<Integer> list){
        // list에 있는 모든 인원들을 한 집합으로 묶음
        int x = find(list.get(0));

        for(int i = 1; i < list.size(); i++){
            int y = find(list.get(i));

            // 작은 값을 부모로 선정
            if(y < x) parent[x] = y;
            else parent[y] = x;
        }
    }
}
