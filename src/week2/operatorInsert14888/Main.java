package week2.operatorInsert14888;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 시간 제한 : 2초 메모리 : 128MB
 * N : 숫자의 갯수 1 <= N <= 11
 * Ai : 숫자의 범위 1 <= Ai <= 100
 * */
public class Main {

    private static int n, max, min;
    private static LinkedList<String> operand;
    private static List<String> operators;

    public static void main(String[] args) throws IOException {

        FileInputStream file = new FileInputStream("./study_algorithm/res/baekjoon/operator_insert_input.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        long start = System.nanoTime();

        n = Integer.parseInt(in.readLine());            // 숫자의 갯수
        // n개의 숫자 할당
        operand = new LinkedList<>();
        operators = new ArrayList<>();
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;

        StringTokenizer st = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            operand.add(i,st.nextToken());
        }
        // 사용가능한 연산자 갯수 할당
        st = new StringTokenizer(in.readLine());
        getOperator("+",Integer.parseInt(st.nextToken()));
        getOperator("-",Integer.parseInt(st.nextToken()));
        getOperator("*",Integer.parseInt(st.nextToken()));
        getOperator("/",Integer.parseInt(st.nextToken()));

//        System.out.println(operators);
//        System.out.println(operand);
        String[] temp = new String[n-1];        // 연산식을 저장할 공간
        expression(temp,0,0);

        sb.append(max).append("\n").append(min);
        System.out.println(sb);

        long end = System.nanoTime();
        System.out.println((end - start) / 1000000 +"s");
        in.close();

    }

    /**
     * 연산자의 경우의 수를 만들어주는 메서드
     * @param temp 연산자의 경우를 저장할 공간
     * @param cnt  연산자의 개수
     * @param flag 사용여부 체크 플래그
     */
    private static void expression(String[] temp,int cnt,int flag) {

        if( cnt == n - 1 ) {
//            System.out.println(Arrays.toString(temp));
            String result;
            Queue<String> q = new LinkedList<>();
            for(int i = 0; i < n - 1;i++ ) {
                q.offer(operand.get(i));
                q.offer(temp[i]);
            }
            q.offer(operand.get(n-1));
            result = q.poll();
            while ( !q.isEmpty() ) {
                String oper = q.poll();
                int op2 = Integer.parseInt(q.poll());
                result = getOperater(Integer.parseInt(result),op2,oper);
            }
            int tmp = Integer.parseInt(result); // 결과가 String이기 때문에 정수로 변환
            if( max < tmp) max = tmp;       // 최댓값
            if( min > tmp) min = tmp;       // 최솟값
            return;
        }

        for(int i = 0; i < n - 1; i++) {
            if( (flag & 1<<i) != 0) continue;
            temp[cnt] = operators.get(i);
            /*System.out.println(temp);*/
            expression(temp,cnt + 1,flag | 1<<i);
        }
    }

    /**
     *
     * @param op1   피연산자1
     * @param op2   피연산자2
     * @param operator  연산자
     * @return      계산결과
     */
    private static String getOperater(int op1 , int op2, String operator) {
        switch ( operator ) {
            case "+":       // 덧셈
                return String.valueOf(op1 + op2);
            case "-":       // 뺄셈
                return String.valueOf(op1 - op2);
            case "*":       // 곱셈
                return String.valueOf(op1 * op2);
            case "/":       // 나눗셈
                int result = 0;
                if( op1 < 0) {  // 음수일 경우
                    op1 = -op1;
                    result = -(op1 / op2);
                    return String.valueOf(result);
                }
                result = (op1 / op2);   // 양수일 경우
                return String.valueOf(result);
            default:
                return null;
        }
    }

    /**
     * 연산자의 갯수만큼 리스트에 삽입해주는 메소드
     * @param order 연산자의 종류
     * @param count 연산자의 갯수
     */
    private static void getOperator(String order, int count) {
        for(int i = 1; i <= count; i++) {
            operators.add(order);
        }
    }
}
