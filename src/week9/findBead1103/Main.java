package week9.findBead1103;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 	14280	152
public class Main {
    static int N, M;
    static ArrayList<Integer>[] forwardAdjList;
    static ArrayList<Integer>[] backwardAdjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        forwardAdjList = new ArrayList[N + 1]; // 나보다 큰친구들을 찾는 adjList
        backwardAdjList = new ArrayList[N + 1]; // 나보다 작은 친구들을 찾는 adjList
        for (int vertex = 1; vertex <= N; vertex++) {
            forwardAdjList[vertex] = new ArrayList<>();
            backwardAdjList[vertex] = new ArrayList<>();
        }
        for (int edge = 0; edge < M; edge++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            forwardAdjList[from].add(to);
            backwardAdjList[to].add(from);
        }

        int[] forwardMap = new int[N+1];
        int[] backwardMap = new int[N+1];

        for (int startNode = 1; startNode <= N; startNode++) {
            int forwardRes = bfs(forwardAdjList, startNode);
            int backwardRes = bfs(backwardAdjList, startNode);
            forwardMap[startNode] = forwardRes;
            backwardMap[startNode] = backwardRes;
        }
        int count = 0;
        for(int forwardCount : forwardMap){
            // 큰사람들 중에서 전체 절반보다 큰사람이 많이 알고 있다면
            if(forwardCount > N/2)count++;
        }
        for(int backward : backwardMap){
            // 작은 중에서 전체 절반보다 큰사람이 많이 알고 있다면
            if(backward > N/2)count++;
        }
        System.out.println(count);
    }

    private static int bfs(ArrayList<Integer>[] adjList, int startNode) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] check = new boolean[N + 1];
        q.add(startNode);
        check[startNode] = true;
        while (!q.isEmpty()){
            int curNode = q.poll();
            ArrayList<Integer> nextNodeList = adjList[curNode];
            for (Integer nextNode : nextNodeList){
                if(!check[nextNode]){
                    check[nextNode] = true;
                    q.offer(nextNode);
                }
            }
        }
        int count = 0;
        // 탐색한 친구들 갯수 count
        for(boolean el : check){
            if(el)count++;
        }
        // 자기자신은 뺀다
        return count-1;
    }


}
