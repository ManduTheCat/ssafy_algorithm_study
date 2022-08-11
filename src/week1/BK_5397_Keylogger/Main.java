package week1.BK_5397_Keylogger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/* 키로거 */
public class Main {

    private static final char BACKSPACE = '-';	// 가독성을 위해서 백스페이스를 상수로 표현하였습니다.
    private static final char RIGHT     = '>';  // 가독성을 위해서 오른쪽버튼를 상수로 표현하였습니다.
    private static final char LEFT      = '<';  // 가독성을 위해서 왼쪽버튼를 상수로 표현하였습니다.

    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("./study_algorithm/res/baekjoon/keylogger_input.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(fis));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());			// 테스트 케이스 횟수를 입력받는 부분
        StringBuilder sb = new StringBuilder();				// 출력값을 저장하는 부분
        /* 테스트 케이스만큼 반복 수행 */
        for(int t = 1; t <= T; t++) {
            List<Character> out = new LinkedList<>();		// 입력된 암호를 입력하는 공간 ( - , < , > ) 제외
            char[] input = in.readLine().toCharArray();		// 사용자가 입력한 문자들을 저장하는 부분
            int curser = 0;									// list의 index를 커서로 보고 0으로 초기화

            for(char l : input) {							// 사용자가 입력한 문자를 1개씩 받아오기
//                System.out.print(l + " => ");
                if( l == BACKSPACE && curser != 0) {		// 백스페이스일 경우
//                    System.out.println("백스페이스 : " + l);
                    --curser;
                    out.remove(curser);
//                    System.out.println(" " + curser);
                    continue;
                } else if ( l == LEFT ) {					// < 왼쪽일 경우
//                    System.out.print("좌로 이동 : " + l);
                    --curser;
                    curser = curser <= 0 ? 0 : curser;
//                    System.out.println(" " + curser);
                    continue;
                } else if( l == RIGHT ) {					// > 오른쪽일 경우
//                    System.out.print("우로 이동 : " + l);
                    curser = curser < out.size() ? ++curser : out.size() ;
//                    System.out.println(" " + curser);
                    continue;
                }
                out.add(curser++,l);						// - , < , > 가 아니면 저장
//                System.out.println("입력 :" + l + " 커서 값 : " + curser);
            }

            // 완성된 암호를 저장
            for(char c : out) {
                sb.append(c);
            }
            sb.append("\n"); // 개행
        }
        // 일괄 출력
        System.out.print(sb);
    }
}
