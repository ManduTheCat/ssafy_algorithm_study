package week1.BK_5397_Keylogger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; ++t) {
			
			
			String key = br.readLine();
			LinkedList<Character> frontList = new LinkedList<Character>();
			LinkedList<Character> backList = new LinkedList<Character>();
			char temp = ' ';
			for(int index = 0; index < key.length(); ++index) {
				if (key.charAt(index) == '<') {
					if (!frontList.isEmpty())
						backList.addFirst(frontList.pollLast());
				} else if (key.charAt(index) == '>') {
					if (!backList.isEmpty())
						frontList.addLast(backList.pollFirst());
				} else if (key.charAt(index) == '-'){
					frontList.pollLast();
				}else {
					frontList.addLast(key.charAt(index));
				}
			}
			
			
			for(char c : frontList)
				sb.append(c);
			for(char c : backList)
				sb.append(c);
			sb.append("\n");
			
		}
		System.out.println(sb);
	}
}
