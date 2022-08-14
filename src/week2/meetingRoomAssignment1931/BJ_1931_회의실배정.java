package week2.meetingRoomAssignment1931;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * 회의실 배정. Comparator를 써서 정렬해놓고 나서 재귀로???
 *  * 
 * 처음에는 우선순위 큐를 이용해 풀어보려고 하였다. 
 * 그런데 데이터를 다 넣고 정렬하는 것을 큐에서는 할 수가 없다고 생각했기 때문에
 * ArrayList로 바꿨다.
 * 
 * 처음에는 정렬을 시작시간을 기준으로 정렬하였는데, 이렇게 하니
 * 시작시간 순서대로 정렬을 한 뒤 결국에는 반복을 통해 최고 많은 회의를 할 수 있는 경우를 구해야했다. *
 * 예를 들면 0,6 이면 1,4부터 검색하여 6부터 시작하는 것을 찾도록 하였다 재귀함수를 통해 구하도록 하였다
 * 이렇게 하다보면 결국에는 완전탐색이나 다를게 없었다. *  
 * ex)
 * 0 6
 * 1 4
 * 2 13
 * 3 5
 * 3 8
 * 4 5
 * 4 7
 * 6 15
 * '
 * ' 
 * 테케는 답이 나왔지만 제출했을 때는 역시 시간초과 ㅠ (재귀를 잘 못 만들었을 수도 있음 ㅠ) 
 * 
 * 그래서 다시 생각해봤는데..
 * 시작시간, 종료시간이 있을 때.
 * 결국에는 회의가 시작되는 기준은 종료시간이기 때문에
 * 이 종료시간을 오름차순으로 정렬해두고 (이 때 종료시간이 같으면, 시작시간이 낮은게 먼저 오도록 정렬에 조건 추가) 
 * 종료시간을 오름차순으로 정렬해뒀기 때문에 , 종료시간과 다음 회의의 시작시간만 비교하면서 카운팅해주면
 * 최대 회의 갯수를 찾을 수 있다.
 * 그러면 1,4 -> 4,5 -> 6, 15 
 *   
 * ex)
 * 1 4
 * 3 5
 * 4 5
 * 4 7
 * 0 6
 * 3 8
 * 2 13
 * 6 15
 * 
 * @author 박태환
 *
 */
public class BJ_1931_회의실배정 {
	static int N;;
	static ArrayList<int[]> timesList;

	static int cnt = 1;
	static int result = 0;

	static ArrayList<Integer> templist;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());

		timesList = new ArrayList<>();
		for (int idx = 0; idx < N; idx++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int startTime = Integer.parseInt(st.nextToken());
			int endTime = Integer.parseInt(st.nextToken());

			int[] times = new int[] { startTime, endTime };

			timesList.add(idx, times);
		}
		sortList(timesList);
		
		int endTime = timesList.get(0)[1];
		
		for (int idx = 1; idx < timesList.size(); idx++) {		
			if(endTime > timesList.get(idx)[0]) {
				continue;
			}
			else {
				endTime = timesList.get(idx)[1];
				cnt++;
			}			
		}
		System.out.println(cnt);
	}

	//종료시간 오름차순으로 정렬, 만약 종료시간이 같으면 시작시간이 낮은것부터 나오도록 정렬
	public static void sortList(ArrayList<int[]> timesList) {
		Collections.sort(timesList, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[1] == o2[1]) {
					return Integer.valueOf(o1[0]).compareTo(Integer.valueOf((o2[0])));
				} else {
					return Integer.valueOf(o1[1]).compareTo(Integer.valueOf((o2[1])));
				}

			}
		});
	}
	
	
	/*
	private static void comb(int cur, int start, int endtime) {
		for (int i = start; i < N; i++) {
			if (timesList.get(i)[0] >= endtime) {
				cnt++;
				templist.add(cur, i);
				comb(cur, i + 1, timesList.get(i)[1]);
			} else {
				continue;
			}
		}
		System.out.println(templist);
		templist.clear();
		result = Math.max(result, cnt);
		cnt = 0;
		return;
	}

	//출력용 메서드
	private static void print(ArrayList<int[]> timesList) {
		for (int i = 0; i < timesList.size(); i++) {
			System.out.println(timesList.get(i)[0] + " " + timesList.get(i)[1]);
		}
	}
	*/
}
