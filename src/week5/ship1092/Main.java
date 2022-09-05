package week5.ship1092;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 크레인 N대 , 1분에 박스 하나씩 실을 수 있다. 모든 크레인은 동시에 움직인다
 * 각 크레인은 무게 제한 있음 
 * 무게 제한보다 무거운 박스 안됨 
 * 
 * 모든 박스를 배로 옮기는데 드는 시간의 최솟 값 구하기 !!
 * - 만약 모든 박스를 배로 옮길 수 없으면 -1 출력
 * 
 * */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine().trim()); // 트레인 개수
		
		ArrayList<Integer> train = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			int weightLimit = Integer.parseInt(st.nextToken()); // 각 트레인마다 무게제한 값
			train.add(weightLimit);
		}
		
		int M = Integer.parseInt(br.readLine().trim()); // 박스의 수
		ArrayList<Integer> box = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<M;i++) {
			int boxWeight = Integer.parseInt(st.nextToken()); 
			box.add(boxWeight);
			
		}
		
		//트레인의 무게제한, 박스의 무게 내림차순정렬 
		Collections.sort(train,Collections.reverseOrder());
		Collections.sort(box,Collections.reverseOrder());
		
		//만약 크레인 첫번째 무게가 박스의 첫번째무게보다 작으면 절대 못옮기니 -1 출력
		if(train.get(0)< box.get(0)) {
			System.out.println(-1);
			System.exit(0);
		}
		
		int min =0; // 분
		
		//박스가 텅텅 빌때까지 반복 
		while(!box.isEmpty()) {
			int boxIdx=0;
			
			for(int tra =0; tra<train.size();tra++) { // 트레인 사이즈만큼 반복
				if(boxIdx == box.size()) break; // 박스 사이즈가 박스 인덱스와 같으면 끝내야쥐
				else if(train.get(tra)>=box.get(boxIdx)) { // 트레인값이 박스값보다 크거나 같으면 트레인에 들어갈 수 있으니 박스에서 삭제
						box.remove(boxIdx);
				}else { // 박스가 크레인보다 무게가 더 클 경우
					boxIdx++; // 그 다음 박스 가져오기 
					// 트레인 인덱스는 그대로 두고 싶은데 흠
					tra--;
				}
			}
			min++;
		}
			
		
		System.out.println(min);
		
	}

}
