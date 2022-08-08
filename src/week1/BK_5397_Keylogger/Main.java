package week1.BK_5397_Keylogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb  = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken()); //테스트케이스 개수
		
		for(int tc=1;tc<=T;tc++) { //테스트케이스만큼 반복
			st = new StringTokenizer(br.readLine());
			
			//문자열 입력 받기
			String input = st.nextToken();
			
			// 결과 넣을 스택
			Stack<Character> result = new Stack<>();
			// 화살표 입력 스택
			Stack<Character> arrow = new Stack<>();
			
			for(int i=0;i<input.length();i++) { 
				// 뽀족한 부분 앞에 있는 문자 인덱스 뒤로 이동
				if(input.charAt(i)=='<') { 
					if(!result.empty()) { 
						arrow.push(result.peek());
						result.pop();
					}
				// 뽀족한 부분 뒤에 있는 문자 인덱스 앞으로 이동	
				}else if(input.charAt(i)=='>') {
					if(!arrow.empty()) { 
						result.push(arrow.peek());
						arrow.pop();
					}
					
					
				// 앞문자 삭제 ~
				}else if(input.charAt(i)=='-') { 
					if(!result.empty()) {
						result.pop();
					}
					
				}else {
					result.push(input.charAt(i));
				}
			}
			while(!arrow.empty()) {
				result.push(arrow.peek());
				arrow.pop();
			}
			char[] chararr = new char[result.size()];
			int i=0;
			while(!result.empty()) {
				chararr[i++]=(result.pop());
			}
		
			for(int j=chararr.length-1;j>=0;j--) {
				sb.append(chararr[j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
		

	}
}

