package week5.ship1092;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int M;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		ArrayList<Integer> cranelist = new ArrayList<>();
		ArrayList<Integer> boxlist = new ArrayList<>();

		N = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			cranelist.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(cranelist, Collections.reverseOrder());

		M = Integer.parseInt(in.readLine());
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < M; i++) {
			boxlist.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(boxlist, Collections.reverseOrder());

		int result = 0;
		// 박스를 다 못 옮기는 경우
		if (cranelist.get(0) < boxlist.get(0)) {
			result = -1;
		} else {
			// 박스를 다 옮길때까지
			while (boxlist.size() != 0) {
				int idx = 0;

				int count = 0;
				while (idx < N) {
					if (count == boxlist.size()) {
						break;
					}

					// 현재 크레인이 박스보다 쌔면
					if (cranelist.get(idx) >= boxlist.get(count)) {
						boxlist.remove(count); // 박스 없애고
						idx++; // 다음 크레인으로
					}

					// 박스가 크레인보다 무거우면 다음박스로
					else if (cranelist.get(idx) < boxlist.get(count)) {
						count++;
					}
				}
				// 크레인 한 사이클이 끝나면 ++
				result++;
			}

		}

		System.out.println(result);
	}

}
