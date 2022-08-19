package week2.meetingRoomAssignment1931;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
/**
 * 
 * @author 이채은
 *
 */
public class Main {
	public static class Meeting {
		int start;
		int end;

		public Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Meeting [start=");
			builder.append(start);
			builder.append(", end=");
			builder.append(end);
			builder.append("]");
			builder.append("\n");
			return builder.toString();
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<Meeting> meetings = new ArrayList<>();
		StringTokenizer st;
		// 리스트에 넣어준다
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			meetings.add(new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}

		// 끝나는 시간으로 올림차순, 끝나는 시간이 같으면 시작 시간 기준으로 올림차순
		Collections.sort(meetings, new Comparator<Meeting>() {

			@Override
			public int compare(Meeting o1, Meeting o2) {
				if (o1.end == o2.end) {
					return Integer.valueOf(o1.start).compareTo(Integer.valueOf(o2.start));
				}
				return Integer.valueOf(o1.end).compareTo(Integer.valueOf(o2.end));
			}
		});
//		System.out.println(meetings);
		int result = 0;
		int endtime = 0;

		for (int i = 0; i < meetings.size(); i++) {
			if (endtime <= meetings.get(i).start) {
				endtime = meetings.get(i).end;
				result++;
			}
		}
		System.out.println(result);
	}
}


