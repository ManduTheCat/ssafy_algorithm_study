package week1.BK_5397_Keylogger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
/**
 * 
 * @author 이채은
 *
 */
public class Main {
public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());	// 테스트케이스 수
		
		for(int t = 0; t < T; t++) {
			String s = br.readLine();
			/** 커서앞에 있는 문자들 */
			Stack<Character> passwordFront = new Stack<>();	
			/** 커서뒤에 있는 문자들 */
			Stack<Character> passwordBack = new Stack<>();	

			/** 문자를 처리하는 반복문 */
			for(int i = 0; i < s.length(); i++) {
				if(s.charAt(i) == '>') {	// 커서를 뒤로 보내야 할 때
					if(passwordBack.size() > 0) {	// 현재 입력된 값
						passwordFront.push(passwordBack.pop());
					}
				} else if(s.charAt(i) == '<') {	// 커서를 앞으로 보내야 할 때
					if(passwordFront.size() > 0) {
						passwordBack.push(passwordFront.pop());
					}
				} else if(s.charAt(i) == '-') {	// 커서 앞에 있는 글자를 지워야할 때
					if(passwordFront.size() > 0) {
						passwordFront.pop();
					}
				} else {
					passwordFront.push(s.charAt(i));
				}
			}
			int frontSize = passwordFront.size();
			// Front을 pop하여 출력하면 거꾸로 나오므로 Back에 넣는다.
			for(int f = 0; f < frontSize; f++) {
				passwordBack.push(passwordFront.pop());
			}
			// Back의 크기만큼 pop하여 출력한다.
			int backSize = passwordBack.size();		
			for(int b = 0; b < backSize; b++) {
				sb.append(passwordBack.pop());
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}