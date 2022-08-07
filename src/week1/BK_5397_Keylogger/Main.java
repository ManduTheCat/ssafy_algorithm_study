package week1.BK_5397_Keylogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    // 1. 두개의 스택으로 커서의 이동을 표현
    // 2. 두개의 스택이 있고
    // 3. 첫번째 스텍은 커서의 왼쪽을 표현
    // 4. 첫번째 스택의 peek 은 현제 커서
    // 5. 첫번째 스텍의 입구로 새로들어온 입력
    // 6. 두번째 스텍은 커서의 오른쪽을 표현


    // > 연산자는 커서가 오른쪽으로 가니  두번째 스택의 값을 pop해 첫번째 스텍에 넣음
    // < 연사자는 커서가 왼쪽으로 가니 첫번째 스텍의 pop 을 두번째 스택에 넣음
    // 연사자들은 모두 스택이 비어있으면 작동하면 안된다.
    // - 연산자는 첫번째 스택을 팝하여 처리한다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < TC; tc++) {
            String[] inputArray = br.readLine().split("");
            processKeyLogger(inputArray);
        }
    }

    public static void processKeyLogger(String[] input) {
        Stack<String> firstStack = new Stack<>();
        Stack<String> secondStack = new Stack<>();
        for (String command : input) {
            // 이부분을 if 로 처리하면 1772ms
            // 이대로 switch 로 처리하면 1784ms
            switch (command) {
                case "<":
                    if (!firstStack.empty()) {
                        secondStack.add(firstStack.pop());
                    }
                    break;
                case ">":
                    if (!secondStack.empty()) {
                        firstStack.add(secondStack.pop());
                    }
                    break;
                case "-":
                    if (!firstStack.empty()) {
                        firstStack.pop();
                    }
                    break;
                default:
                    firstStack.add(command);
                    break;
            }
        }

        StringBuilder sb = new StringBuilder();
        // 스트림을 사용시 	1944ms
        // 스트림을 사용하지 않을시 1784ms
        //firstStack.parallelStream().forEach(sb::append);
        for (String e : firstStack) {
            sb.append(e);
        }
        for (int i = secondStack.size() - 1; i >= 0; i--) {
            sb.append(secondStack.get(i));
        }
        System.out.println(sb);
    }
}
