package week2.meetingRoomAssignment1931;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); //회의의 수
		
		List<meetingRoom> list = new ArrayList<>(); //meetingRoom 타입을 받는 리스트 선언
		
		for(int i=0; i<N; i++) { //회의의 수만큼 반복하면서
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int S = Integer.parseInt(st.nextToken()); //시작 시간
			int E = Integer.parseInt(st.nextToken()); //종료 시간
			
			list.add(new meetingRoom(S,E)); //리스트에 회의실 사용 정보 만들어서 추가
		}
		//System.out.println(list); //출력확인
		
		Collections.sort(list); //리스트 정렬, end 오름차순, end가 같다면 start 오름차순
		//System.out.println(list); //출력확인
		
		
		int idx = 1; //인덱스 시작은 1부터
		int min = list.get(0).end; //정렬된 리스트에서 제일 처음의 end 값이 가장 작은 값이고, 그게 가장 빨리 끝나는 회의
		
		while(list.size() != idx) { //리스트의 크기와 인덱스가 같을 때 종료
			
			if(min <= list.get(idx).start) { //현재 인덱스가 가르키고 있는 회의 시작 시간이 가장 빨리 종료되는 회의 시간과 같거나 크다면
				min = list.get(idx).end; //그 회의의 종료 시간을 min으로 저장
				//System.out.println("min : "+min); //출력 확인
			} else { //아니라면
				list.remove(idx); //그 회의 정보 삭제
				idx--; //그리고 삭제 되면서 한 칸씩 올라간 리스트를 다시 확인 해야되니까 인덱스 크기 줄이기 
				//System.out.println(list); //출력 확인
			}
			
			idx++; //인덱스 증가
		}
		
		System.out.println(idx); //출력 형식에 맞게 출력
		
	}
	
	
	//회의 시작 시간과 종료 시간을 저장하고, 비교를 하기 위해 만든 meetingRoom 클래스
	static class meetingRoom implements Comparable<meetingRoom> { 
		int start; //시작시간
		int end; //종료시간
		
		public meetingRoom(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public String toString() { //출력 확인을 위해 재정의
			return "[" + start + " " + end + "]";
		}

		@Override
		public int compareTo(meetingRoom o) { //회의실 시간 정보 비교
			if(Integer.valueOf(end) == Integer.valueOf(o.end)) { //종료 시간이 동일하다면
				return Integer.valueOf(start).compareTo(Integer.valueOf(o.start)); //시작 시간으로 오름차순 정렬
			} 
			
			return Integer.valueOf(end).compareTo(Integer.valueOf(o.end)); //종료 시간 오름차순 정렬
		}
		
	}
	
	
}
