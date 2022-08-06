package week1.BK_5397_Keylogger;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < n; i++) {
			String line = br.readLine();
			Stack<Character> beforeCur = new Stack<>();		// 커서 앞에 있는 글자
			Stack<Character> afterCur = new Stack<>();		// 커서 뒤에 있는 글자
			for (int j = 0; j < line.length(); j++) {
				char input = line.charAt(j);		// 키보드 입력 받기
				if (input == '<') {			// 커서를 앞으로 이동
					if (!beforeCur.isEmpty()) {
						afterCur.push(beforeCur.pop());
					}
				} else if (input == '>') {	// 커서를 뒤로 이동
					if (!afterCur.isEmpty()) {
						beforeCur.push(afterCur.pop());
					}
				} else if (input == '-') {	// 커서 앞의 글자 하나 삭제
					if (!beforeCur.isEmpty()) {
						beforeCur.pop();
					}
				} else {					// 새로운 글자 입력 시 커서 앞에 글자 하나 추가
					beforeCur.push(line.charAt(j));
				}
			}
			
			for (int k = 0; k < beforeCur.size(); k++) {	// 커서 앞 글자 출력
				bw.write(beforeCur.get(k));
			}
			int size = afterCur.size();
			for (int k = 0; k < size; k++) {		// 커서 뒤 글자 출력
				bw.write(afterCur.pop());
			}
			bw.write("\n");
		}
		bw.flush();
		bw.close();
	}
}