package week2.meetingRoomAssignment1931;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BJ_1931_회의실배정_list {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(in.readLine());
		ArrayList<int[]> timesList = new ArrayList<>();

		int cnt = 1;

		for (int idx = 0; idx < N; idx++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int startTime = Integer.parseInt(st.nextToken());
			int endTime = Integer.parseInt(st.nextToken());

			int[] times = new int[] { startTime, endTime };

			timesList.add(idx, times);
		}
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

		int endTime = timesList.get(0)[1];

		for (int idx = 1; idx < timesList.size(); idx++) {
			if (endTime > timesList.get(idx)[0]) {
				continue;
			} else {
				endTime = timesList.get(idx)[1];
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}
