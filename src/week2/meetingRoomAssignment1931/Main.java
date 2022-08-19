package week2.meetingRoomAssignment1931;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 한 개의 회의실 , 사용하고자 하는 N개의 회의 => 회의실 사용표
 * 회의에 대한 시작시간과 끝나는 시간
 * 각 회의가 겹치지 않게 하면서 회의실을 사용할 수 있는 회의의 최대 개수 
 * 
 * ## 회의 한번 시작하면 중간에 중단 x,끝남과 동시에 다음회의 시작 가능 
 * 회의 시작시간 = 회의 끝나는 시간 (시작하자마자 끝나는 것)
 * 
 * 
 * - 우선순위 큐를 이용해서  종료 시간 오름차순 정리 
 * - 종료시간 같으면 시작 시간 빠른 순으로 오름차순 정렬
 * - 큐에서 값 꺼내기 
 * ---꺼낸 값 시작시간 >= 현재 종료시간 : 다음회의 배정
 * ---다음 회의 배정하는 경우, 현재 종료시간을 꺼낸 값의 종료시간으로 바꾼 후 카운트 ++
 * 
 * 
 * */
class Meet{
	int startTime;
	int endTime;
	public Meet(int startTime,int endTime) {
		this.startTime= startTime;
		this.endTime = endTime;
	}
	@Override
	public String toString() {
		return "startTime=" + startTime + " endTime=" + endTime+"\n";
	}
	
}
/**
 * Integer.parseInt VS Integer.valueOf 차이?
 * Integer.parseInt : 원시데이터인 int 타입 반환
 * Integer.valueOf : Integer 래퍼(wrapper)객체를 반환
 * */

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine()); // 회의 수 
		PriorityQueue<Meet> que = new PriorityQueue<>(new Comparator<Meet>() {

			@Override
			public int compare(Meet o1, Meet o2) {
				if(o1.endTime == o2.endTime) {
					return Integer.valueOf(o1.startTime).compareTo(Integer.valueOf(o2.startTime)); //오름차순
					}
				return Integer.valueOf(o1.endTime).compareTo(Integer.valueOf(o2.endTime));
			}
		});
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			que.offer(new Meet(Integer.valueOf(st.nextToken()),Integer.valueOf(st.nextToken())));
		}
//		System.out.println(que); // 큐 출력용
		
		int cnt=0; // 회의 카운트
		int CompareTime =0; // 처음 비교할 땐 0부터, 그전비교했던 endTime과비교 
		while(!que.isEmpty()) {
			Meet temp = que.poll();
			if(temp.startTime>=CompareTime) {
				CompareTime=temp.endTime; // 현재 endTime 으로 저장 
				cnt++; // 회의 들어갈 때마다 ++
			}
		}
		System.out.println(cnt);
		
	}
}
