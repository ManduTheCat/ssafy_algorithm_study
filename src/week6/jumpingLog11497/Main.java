package week6.jumpingLog11497;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static Deque<Integer> resultDeque;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(in.readLine());

		for (int tc = 0; tc < t; tc++) {
			int n = Integer.parseInt(in.readLine());
			resultDeque = new LinkedList<Integer>();

			StringTokenizer st = new StringTokenizer(in.readLine());
			// 가장 큰 값을 기준으로 양 옆 큰 순서대로로 붙이기
			PriorityQueue<Integer> pQueue = new PriorityQueue<>(Collections.reverseOrder());
			for (int i = 0; i < n; i++) {
				pQueue.offer(Integer.parseInt(st.nextToken()));
			}

			resultDeque.add(pQueue.poll());
			while (!pQueue.isEmpty()) {
				resultDeque.addFirst(pQueue.poll());

				if (pQueue.isEmpty()) break;

				resultDeque.addLast(pQueue.poll());
			}
			
			
			int minDiff = 0;
			
			int first = resultDeque.getFirst();
			int last = resultDeque.getLast();
			
			int temp = resultDeque.poll();
			while(!resultDeque.isEmpty()) {
				int pollNum = resultDeque.poll();
				int diff = Math.abs(temp - pollNum);
				if(minDiff <= diff) {
					minDiff = diff;					
				}
				temp = pollNum;
			}
			
			if(minDiff <= Math.abs(first - last)) {
				minDiff = Math.abs(first - last);
			}
			
			sb.append(minDiff).append("\n");			
		}
		System.out.print(sb);
	}

}
