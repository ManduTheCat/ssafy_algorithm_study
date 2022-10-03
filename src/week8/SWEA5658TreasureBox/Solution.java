package week8.SWEA5658TreasureBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	static Deque<Character> deque;

	static HashSet<Integer> nums;
	static int N, K;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			deque = new LinkedList<>();
			nums = new HashSet<>();

			String inputStr = in.readLine();
			for (int i = 0; i < N; i++) {
				deque.offer(inputStr.charAt(i));
			}
			//입력 끝

			int cycle = N / 4;
			while (cycle-- > 0) {
				int size = deque.size();

				int count = N / 4;
				String temp = "";
				while (size-- > 0) {

					char tempPoll = deque.pollFirst();

					temp += tempPoll;
					deque.offerLast(tempPoll);

					count--;

					if (count == 0) {
						nums.add(Integer.parseInt(temp, 16));
						temp = "";
						count = N / 4;
					}
				}
				char last = deque.pollLast();
				deque.offerFirst(last);
			}
			ArrayList<Integer> temp = new ArrayList<>(nums);
			Collections.sort(temp);
			//로직 끝

			System.out.println("#" + tc + " " + temp.get(temp.size() - K));
		}
	}

}
