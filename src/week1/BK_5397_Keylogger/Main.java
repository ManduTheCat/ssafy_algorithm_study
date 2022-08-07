package week1.BK_5397_Keylogger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception {
        Stack<Character> left = new Stack<>();		//커서를 기준으로 왼쪽 문자들
        Stack<Character> right = new Stack<>();		//커서를 기준으로 오른쪽 문자들
        
        StringBuilder sb = new StringBuilder();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        
        int tc = Integer.parseInt(bf.readLine());
        
        for(int loop=0; loop<tc; loop++){
            String str = bf.readLine();
            char[] chars = new char[str.length()];
            
            for(int i=0; i<str.length(); i++){
                chars[i] = str.charAt(i);
            }
            
            for(int i=0; i<chars.length; i++){
                if(chars[i] == '<'){		//왼쪽스택에 문자가 있다면 오른쪽 스택으로 넘겨준다. -> 커서 왼쪽으로 이동
                    if(!left.isEmpty()){
                        right.push(left.pop());
                        
                    }
                    continue;
                }else if(chars[i] == '>'){		//오른쪽 스택에 문자가 있다면 왼쪽 스택으로 넘겨준다. -> 커서 오른쪽으로 이동
                    if(!right.isEmpty()){
                        left.push(right.pop());
                       
                    }
                    continue;
                }else if(chars[i] == '-'){		//커서의 왼쪽에 문자가 있다면 지워준다.
                    if(!left.isEmpty()){
                        left.pop();
                   
                    }
                    continue;
                }
                left.push(chars[i]);			//일반 문자일때는 커서의 왼쪽에 문자를 넣어준다.
            }
          
            
            while (!left.isEmpty()) {			//순서대로 문자를 출력하기 위해 왼쪽 스택의 모든 문자들을 오른쪽으로 이동
                right.push(left.pop());
            }
            while (!right.isEmpty()) {
                sb.append(right.pop());
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
        
        
    }
}

