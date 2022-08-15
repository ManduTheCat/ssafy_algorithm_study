package week2.meetingRoomAssignment1931;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Time implements Comparable<Time> {
	int start;
	int end;
	
	public Time(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	@Override
	public int compareTo(Time o) {		// 끝나는 시간 오름차순으로 정렬, 끝나는 시간이 같을 경우 시작 시간 오름차순으로 정렬
		return this.end == o.end ? Integer.compare(this.start, o.start) : Integer.compare(this.end, o.end);
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		ArrayList<Time> meetings = new ArrayList<>();		// N개 회의 시간 저장
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			meetings.add(new Time(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		Collections.sort(meetings);	
		
		int endTime = meetings.get(0).end;	// 회의를 진행할 때마다 끝나는 시간 저장
											// 끝나는 시간이 가장 빠른 첫번째 회의 선택
		meetings.remove(0);
		
		int ans = 1;						// 사용할 수 있는 회의의 최대 개수
		
		while(!meetings.isEmpty()) {
			if(meetings.get(0).start >= endTime) {	// 그 다음으로 끝나는 시간이 가장 빠른 회의 중 시작시간이 이후에 있는 회의
				ans++;
				endTime = meetings.get(0).end;
			} 		
			meetings.remove(0);		// 현재 끝나는 시간보다 시작시간이 이른 경우도 삭제
		}
		
		System.out.println(ans);
	}
}
