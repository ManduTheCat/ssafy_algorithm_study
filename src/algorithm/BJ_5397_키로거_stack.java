package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * @author 박태환 BJ 5397 키로거, 키보드로 입력한 키는 알파벳 대,소문자, 숫자 , 백스페이스( - ) ,화살표 ( < > )
 *         Stack 을 2개 만들고, 두 스택 사이에 cursor 있다고 생각
 */

public class BJ_5397_키로거_stack {
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int Tc = Integer.parseInt(br.readLine());
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();

		// 두 스택 사이에 cursor가 있다고 생각하고
		// 커서의 값을 바꾸는 것이 아니라 , 
		// < (커서가 줄어드는 경우에 왼쪽에서 오른쪽으로 옮김)
		// > (커서가 커지는 경우에는 오른쪽에서 왼쪽으로 옮김)
		// - (커서가 줄어드는 경우 왼쪽에서 삭제, 커서기준 왼쪽으로 삭제되기 때문)
		
		Stack<Character> leftStack = new Stack<>();
		Stack<Character> rightStack = new Stack<>();
		for (int tc = 0; tc < Tc; tc++) {
			sb1 = new StringBuilder();
			leftStack.clear();
			rightStack.clear();

			String inputStr = br.readLine();

			for (int i = 0; i < inputStr.length(); i++) {
				char inputChar = inputStr.charAt(i);

				char moveChar;

				switch (inputChar) {
				case '<':
					if (leftStack.isEmpty() == false) {
						moveChar = leftStack.pop();
						rightStack.push(moveChar);
					}
					break;
				case '>':
					if (rightStack.isEmpty() == false) {
						moveChar = rightStack.pop();
						leftStack.push(moveChar);
					}
					break;
				case '-':
					if (leftStack.isEmpty() == false) {
						leftStack.pop();
					}
					break;
				default:
					leftStack.push(inputChar);
					break;
				}
			}			
			while (!leftStack.isEmpty()) {
				sb1.append(leftStack.pop());
			}
			sb1.reverse();

			while (!rightStack.isEmpty()) {
				sb1.append(rightStack.pop());
			}
			System.out.println(sb1);
		}
	}
}
