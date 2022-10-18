package week7.lies1043;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int root[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 사람의 수
		int M = Integer.parseInt(st.nextToken());	// 파티의 수
		
		// 루트 초기화
		root = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			root[i] = i;
		}
		
		st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());	// 진실을 아는 사람의 수
		int knowns[] = new int[K];			// 진실을 아는 사람들의 번호
		for(int i = 0; i < K; i++) {
			knowns[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i < K; i++) {
			union(knowns[i - 1], knowns[i]);
		}
		
		List<Integer> parties[] = new ArrayList[M];
		for(int i = 0; i < M; i++) {	// 각 파티마다
			st = new StringTokenizer(br.readLine());
			int P = Integer.parseInt(st.nextToken());	// 오는 사람의 수
			parties[i] = new ArrayList<>();
			for(int j = 0; j < P; j++) {
				parties[i].add(Integer.parseInt(st.nextToken()));	// 오는 사람들의 번호
			}
		}
		
		// 진실을 아는 사람이 아무도 없으면 전체 파티의 수 출력하고 exit
		if(K == 0) {
			System.out.println(M);
			System.exit(0);
		}
		
		// 같은 파티에 온 사람 -> root를 이어줌
		for(int i = 0; i < M; i++) {
			for(int j = 1; j < parties[i].size() ; j++) {
				union(parties[i].get(j - 1), parties[i].get(j));
			}
		}
		
		// 거짓말 할 수 있는 파티의 개수 세기
		int cnt = M;	// 전체 파티의 수에서 진실을 아는 사람이 있는 파티의 수 빼기
		int known = find(knowns[0]);	// 진실을 아는 그룹의 root
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < parties[i].size(); j++) {
				if(find(parties[i].get(j)) == known) {	// 파티에 진실을 아는 사람이 있으면
					cnt--;
					break;	// 다음 파티 탐색
				}
			}
		}
		
		System.out.println(cnt);
	}
	
	private static int find(int x) {
		if(root[x] == x) return x;
		else return root[x] = find(root[x]);
	}
	
	private static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x < y) root[x] = y;
		else root[y] = x;
	}	
}