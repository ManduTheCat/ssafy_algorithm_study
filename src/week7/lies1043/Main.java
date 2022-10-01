package week7.lies1043;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int M;
    static int N;
    static int [] parents ;
    static ArrayList<Integer> [] party;
    static  int initTureRoot;
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("resources/study/week7/1043/input6.txt"));
        //1. initTrueSet 에 있다면 unionFind 를 실행하여  trueSet을 만든다
        //2 tureSet 에 find 연산을 수행해 없다면 count++
        int count = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        parents = new int[N + 1];
        party = new ArrayList[M];
        for (int index = 0; index < M ; index++) {
            party[index] = new ArrayList<>();
        }
        for (int n = 1; n <= N; n++) {
            parents[n] = n;
        }
        int initTrueSetLen = Integer.parseInt(st.nextToken());
        if (initTrueSetLen >0){
            initTureRoot = Integer.parseInt(st.nextToken());
            for (int t = 1; t < initTrueSetLen; t++) {
                union(initTureRoot, Integer.parseInt(st.nextToken()));
            }
        }
        else{
            System.out.println(M);
            System.exit(0);
        }

        for (int m = 0; m < M; m++) {
            // 0번파티 부터 M  번파티 까지
            st = new StringTokenizer(br.readLine());
            int partyLen = Integer.parseInt(st.nextToken());
            // m 번째 파티에서
            for (int p = 0; p < partyLen; p++) {
                int val = Integer.parseInt(st.nextToken());
                party[m].add(val);
                // 각파티는 무조건 1명 이상 온다
                // sub union 합쳐지면서 진실 을 아는 친구들이랑 합쳐진다
                int partyRoot = party[m].get(0);
                for(Integer invitedP :party[m]){
                    union(partyRoot, invitedP);
                }
            }
        }
        for (int m = 0; m < M; m++) {
            if(find(initTureRoot)!=find(party[m].get(0))){
                count++;
            }
        }
        System.out.println(count);
    }
    public static int find(int a){
        if(parents[a] == a){
            return a;
        }
        return parents[a] = find(parents[a]);
    }
    public static void union(int x, int y){
        int parentX = find(x);
        int parentY = find(y);
        parents[parentY] = parentX;
    }

}