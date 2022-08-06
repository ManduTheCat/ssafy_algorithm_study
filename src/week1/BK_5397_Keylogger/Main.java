package week1.BK_5397_Keylogger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/* 키로거 */
public class Main {

    private static final char BACKSPACE = '-';
    private static final char RIGHT     = '>';
    private static final char LEFT      = '<';

    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("./study_algorithm/res/baekjoon/keylogger_input.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(fis));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();
        for(int t = 1; t <= T; t++) {

            Stack<Character> stack1 = new Stack<>();
            Stack<Character> stack2 = new Stack<>();

            char[] input = in.readLine().toCharArray();

            for(char l : input) {

                if( l == BACKSPACE ) {
                    if( !stack1.isEmpty() ) {
                        stack1.pop();
                    }
                    continue;
                }

                if ( l == RIGHT ) {
                    if( !stack2.isEmpty() ) {
                        stack1.push(stack2.pop());
                    }
                    continue;
                }

                if ( l == LEFT ) {
                    if( !stack1.isEmpty() ) {
                        stack2.push(stack1.pop());
                    }
                    continue;
                }

                stack1.push(l);
            }

            while ( !stack1.isEmpty() ) {
                stack2.push(stack1.pop());
            }

            while ( !stack2.isEmpty() ) {
                sb.append(stack2.pop());
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
