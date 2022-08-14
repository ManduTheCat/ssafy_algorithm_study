package week2.meetingRoomAssignment1931;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1931_회의실배정_Queue {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(in.readLine());

		int cnt = 1;

		Queue<int[]> timeQueue = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[1] == o2[1]) {
					return Integer.valueOf(o1[0]).compareTo(Integer.valueOf((o2[0])));
				} else {
					return Integer.valueOf(o1[1]).compareTo(Integer.valueOf((o2[1])));
				}
			}
		});

		for (int idx = 0; idx < N; idx++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int startTime1 = Integer.parseInt(st.nextToken());
			int endTime1 = Integer.parseInt(st.nextToken());

			int[] times = new int[] { startTime1, endTime1 };

			timeQueue.add(times);
		}

		int endTime = timeQueue.poll()[1];

		while (!timeQueue.isEmpty()) {
			int[] temp = timeQueue.poll();

			if (endTime > temp[0]) {
				continue;
			} else {
				endTime = temp[1];
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}
