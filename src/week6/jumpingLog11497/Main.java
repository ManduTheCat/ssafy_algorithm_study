package week6.jumpingLog11497;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());	// 통나무 개수
			
			st = new StringTokenizer(br.readLine());
			
			PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());	
			
			// 우선순위 큐에 담아주기
			for (int n = 0; n < N; n++) {
				queue.offer(Integer.parseInt(st.nextToken()));
			}
			
			int[] logs = new int[N];	// 통나무를 순서대로 담을 배열

			for (int n = 0; n < N / 2; n++) {
				logs[n] = queue.poll();
				logs[N - n - 1] = queue.poll();
			}
			
			// 통나무 개수가 홀수개라면 가운데에 마지막 것을 추가
			if (N % 2 == 1) {
				logs[N / 2] = queue.poll();
			}
			
//			System.out.println(Arrays.toString(logs));

			int min = logs[0] - logs[N - 1];	// 초기값을 첫번째 통나무와 마지막 통나무의 차로 놓는다.
			
			// 통나무를 순서대로 비교하면서 min을 바꿔준다.
			for (int i = 0; i < N - 1; i++) {
				min = Math.max(min, Math.abs(logs[i] - logs[i + 1]));
			}
			
			sb.append(min).append("\n");
		}
		System.out.println(sb);
	}
}
