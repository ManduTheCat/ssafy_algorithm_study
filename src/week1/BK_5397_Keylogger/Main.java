package week1.BK_5397_Keylogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Stack;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); //테스트 케이스
		
		for(int tc = 0; tc<T; tc++) { //테스트 케이스만큼 반복
			String str = br.readLine(); //입력한 문자열
			
			Stack<Character> password = new Stack<>(); //비밀번호를 담을 스택
			Stack<Character> temp = new Stack<>(); //커서 이동시 임시로 비밀번호를 담을 스택
			
			for(int i=0; i<str.length(); i++) { //문자열의 길이만큼 반복
				if(str.charAt(i) == '<') { //읽은 문자가 '<'(왼쪽으로 이동하는 화살표)일 경우
					if(!password.isEmpty()) { //비밀번호 스택이 비어있지 않은 경우에만
						temp.push(password.pop()); //비밀번호 스택에서 문자를 꺼내서 임시 스택에 추가
					}
				} else if(str.charAt(i) == '>') { //읽은 문자가 '>'(오른쪽으로 이동하는 화살표)일 경우
					if(!temp.isEmpty()) { //임시 스택이 비어있지 않은 경우에만
						password.push(temp.pop()); //임시 스택에서 문자를 꺼내서 비밀번호 스택에 추가
					}
				} else if(str.charAt(i) == '-') { //읽은 문자가 '-'(백스페이스)일 경우
					if(!password.isEmpty()) { //비밀번호 스택이 비어있지 않은 경우에만(문자가 있을 경우)
						password.pop(); //비밀번호 스택에 있는 문자를 꺼내서 삭제
					}
				} else { //읽은 문자가 위의 것을 제외한 문자라면
					password.push(str.charAt(i)); //비밀번호 스택에 읽은 문자 추가
				}
				
				//출력 확인
//				System.out.println("pwd : " + password);
//				System.out.println("tmp : " + temp);
//				System.out.println();
			}
			
			if(!temp.isEmpty()) { //임시 스택에 문자가 남아 있을 경우
				while(temp.size()!=0) { //임시 스택의 크기가 0이 될때까지 반복하며
					password.push(temp.pop()); //임시 스택에 있는 문자를 꺼내서 비밀번호 스택에 추가
				}
			}
			
			for(int i=0; i<password.size(); i++) { //비밀번호 스택의 크기만큼 반복
				sb.append(password.get(i)); //StringBuilder에 비밀번호 스택에 있는 문자 가져와서 추가
			}
			sb.append("\n");

		}
		System.out.println(sb); //출력

	}
}
