package week5.ship1092;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); //크레인 대수
		
		List<Integer> crane = new ArrayList<>(); //크레인들의 제한 무게를 저장하기 위한 리스트 선언
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) { //크레인 대수만큼 반복
			crane.add(Integer.parseInt(st.nextToken())); //크레인 제한 무게를 리스트에 저장
		}
		Collections.sort(crane, Collections.reverseOrder()); //저장된 무게들을 내림차순 정렬(큰 숫자가 앞으로)
		
		int M = Integer.parseInt(br.readLine()); //박스 개수
		
		List<Integer> box = new ArrayList<>(); //박스의 무게들을 저장하기 위한 리스트 선언
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) { //박스 개수만큼 반복
			box.add(Integer.parseInt(st.nextToken())); //박스들의 무게를 리스트에 저장
		}
		Collections.sort(box, Collections.reverseOrder()); //저장된 무게들을 내림차순 정렬(큰 숫자가 앞으로)
		
		int chk = 0; //박스의 번호
		int num = 0; //크레인 번호
		int times = 0; //모든 박스를 배로 옮기는데 걸리는 시간
		
		if(crane.get(0) < box.get(0)) { //크레인의 가장 큰 제한 무게보다 박스의 가장 큰 무게가 더 크다면 
			System.out.println(-1); //배로 옮길 수 없으므로 -1 출력
			return; //리턴
		}
		
		while(box.size() != 0) { //박스의 개수가 0이면 종료
			chk = 0; //박스 번호를 0으로 초기화
			for(int i=0; i<crane.size(); i++) { //크레인 대수만큼 반복
				if(chk >= box.size()) { //현재 박스 순서가 박스의 개수보다 크거나 같다면
					break; //반복 종료
				} else if(crane.get(i) >= box.get(chk)) { //크레인의 제한무게가 박스의 무게보다 크거나 같다면
					box.remove(chk); //박스를 배로 옮겼으므로 박스 삭제
				} else { //현재 박스 순서가 박스의 개수보다 작고, 박스의 무게가 크레인의 제한무게보다 크다면
					chk++; //다음 박스로 이동
					i--; //현재 크레인 다시 사용
				}
			}
			
			times++; //시간 증가
			
		}
		System.out.println(times); //모든 박스를 배로 옮기는데 걸리는 시간 출력
	}
	
}
