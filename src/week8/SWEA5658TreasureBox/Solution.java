package week8.SWEA5658TreasureBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * [시계 방향]
 * 보물상자에 적힌 숫자로 만들 수 있는 모든 수 중 k번쨰로 큰 수 => 10진수로 만든 수
 * -> 서로 다른 회전 횟수에서 동일한 수가 중복으로 생성할 수 있으니, 크기 순서를 셀 떄 같은 수를 중복으로 세지 않도록 주의
 * 
 * 한 변에 들어가는 값의 개수 : N /4 
 * 회전 N/4 번 = 회전 0번의 상태와 똑같아짐 
 * 
 * */
public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb  = new StringBuilder();
		StringBuilder ans = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			String str = br.readLine();
			char[] num = new char[N];
			for(int i=0;i<str.length();i++) {
				num[i] = str.charAt(i);
			}
			
//			for(int i=0;i<num.length;i++) {
//				System.out.print(num[i]+" ");
//			}
			
			ArrayList<Character> list = new ArrayList<>();
			
			
			for(int i=0;i<num.length;i++) {
				list.add(num[i]);
			
			}
	
			ArrayList<String> result = new ArrayList<>();
			
			
			// 보물상자 뚜껑 회전 N/4
			for(int i=0;i<N/4;i++) {
				
				int jCnt=0; //한변이 갖는 N/4개 숫자 체크하기 위한 변수 
				// N개의 숫자만큼 반복, 한변이 갖는 N/4개 숫자 로 묶기
				for(int j=0;j<N;j++) {
					sb.append(list.get(j));
					jCnt++; 
					if(jCnt==N/4) {
						if(!result.contains(sb.toString())) {
							result.add(sb.toString());
						}
						jCnt=0;
						sb.setLength(0); // StringBuilder 초기화 : 길이 0 
					}
				}
				// 마지막 요소 빼서 앞으로 넣기
				char temp = list.remove(list.size()-1);
				list.add(0,temp);
			}
			
			// 내림차순 나열 
			Collections.sort(result, Collections.reverseOrder());
//			System.out.println(result.toString());
			
			
			for(int i=0;i<result.size();i++) {
				// 인덱스 번호와 크기 순서가 같다면 
				if(i==K-1) {
					ans.append("#"+tc+" "+Integer.parseInt(result.get(i), 16)).append("\n");
					break;
				}
			}
	
		}
		System.out.println(ans.toString());

	}
	
	

}
