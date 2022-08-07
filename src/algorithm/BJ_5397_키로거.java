package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author 박태환 BJ 5397 키로거, 키보드로 입력한 키는 알파벳 대,소문자, 숫자 , 백스페이스( - ) ,화살표 ( < > )
 * 
 *         처음 Cursor는 0
 * 
 *         마지막 커서가 끝에 위치하지 않으면 커서 오른쪽에 있는 문자는 오른쪽으로 한 칸 이동
 * 
 *         문자열의 길이 MAX -> 1,000,000
 * 
 *         <<BP<A>>Cd- , 순서가 있고, 추가가 삭제 가능 ? -> list?
 * 
 *         ArrayList는 데이터를 저장하고 검색하는데 더 유리하고
 *         LinkedList는 다량의 데이터를 삽입 삭제하는데 더   유리하다
 */

public class BJ_5397_키로거 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Character> keyArray = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		int Tc = Integer.parseInt(br.readLine());
				
		int cursor = 0; // cursor의 시작 위치 설정
		for(int i = 1; i <= Tc; i++) {
			String inputStr = br.readLine();			
			
			for(int idx = 0; idx < inputStr.length(); idx++) {
				char inputChar = inputStr.charAt(idx);
				
				switch (inputChar) {
				case '<' :
					if(cursor > 0) {
						cursor--;
					}
					break;
				case '>' :
					if(cursor < keyArray.size()) {
						cursor++;
					}
					break;
				case '-' :
					if(cursor > 0 && cursor <= keyArray.size()) {
						keyArray.remove(cursor-1);
						cursor--;						
					}		
					break;
				default:
					keyArray.add(cursor, inputChar);
					cursor++;
					break;
				}
			}
			for(char a : keyArray) {
				sb.append(a);
			}
			sb.append("\n");
			keyArray.clear();
			cursor = 0;
			
		}
		System.out.print(sb);
	}	
}
