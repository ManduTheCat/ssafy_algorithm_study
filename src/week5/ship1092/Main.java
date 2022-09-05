package week5.ship1092;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine()); // 크레인 개수
		Integer[] crain = new Integer[N]; // 크레인 최대 무게 담기
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			crain[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(crain, Collections.reverseOrder());	// 내림차순
		int M = Integer.parseInt(br.readLine()); // 상자 개수
		Integer[] box = new Integer[M];
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < M; i++) {
			box[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(box, Collections.reverseOrder());	// 내림차순
		
		// 그룹짓기
		if (box[0] > crain[0]) {
			System.out.println(-1);	// 옮길 수 없는 박스가 있으면 -1출력
		} else {
			LinkedList<LinkedList> crains = new LinkedList<>();	// crain을 그룹짓기 위해서 이차원(?)LinkedList
			crains.add(new LinkedList<Integer>());
			int index = 0;
			int i = 0;
			ArrayList<Integer> starts = new ArrayList<>();
			int start = 0;
			while (index < N) {
				if (crain[index] >= box[i]) {
					if (start == i) {	// 같은 그룹이면 같은 곳에 저장
						crains.get(crains.size() - 1).add(crain[index]);
						index++;
					} else {	// 다른 그룹이면 새로운 LinkedList 생성 후 저장
						starts.add(i - start);
						start = i;
						crains.add(new LinkedList<Integer>());
						crains.get(crains.size() - 1).add(crain[index]);
						index++;
					}
					continue;

				}
				i++;
				if (i >= M) {	// index를 다 돌기 전에 i가 끝나면 마지막에 나머지 crain들 넣어주기
					crains.add(new LinkedList<Integer>());
					while (index < N) {
						crains.get(crains.size() - 1).add(crain[index]);
						index++;
					}
				}
			}
			starts.add(M - start);
			
			// 물건빼기작업
			int result = 0;
			boolean isFinished = false;
			while (true && !isFinished) {
				isFinished = true;
				result++;
				for (int c = 0; c < crains.size(); c++) {
					int cnt = crains.get(c).size();
					for (int s = c; s < starts.size(); s++) {
						if (starts.get(s) == 0) {	// 남은 박스가 0개면 다른 칸으로 넘어간다.
							continue;
						} else if (cnt > starts.get(s)) {	 // 남은 박스가 크레인 수보다 적으면 모두 옮긴 다음 나머지 크레인은 다른 칸으로 넘어간다.
							cnt -= starts.get(s);
							starts.set(s, 0);
						} else {	// 남은 박스가 해당 그룹 크레인 수보다 같거나 많으면 크래인 수만큼 빼기
							starts.set(s, starts.get(s) - cnt);
							break;
						}
					}
				}
				// 모두 다 옮겼는지 확인한다.
				for (int c = 0; c < starts.size(); c++) {
					if (starts.get(c) != 0) {
						isFinished = false;
					}
				}
			}
			System.out.println(result);
		}

	}
}
