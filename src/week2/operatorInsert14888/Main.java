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

    private static int n, max, min;                 // 피연산자 수, 최댓값, 최솟값
    private static String[] operand;      // 피연산자
    private static List<Character> operators = new ArrayList<>();       // 연산자

    public static void main(String[] args) throws IOException {

        FileInputStream file = new FileInputStream("./algorithm_java/res/baekjoon/operator_insert_input.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(file));
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        long start = System.nanoTime();

        n = Integer.parseInt(in.readLine());            // 숫자의 갯수
        // n개의 숫자 할당
        operand = new String[n];
        StringTokenizer st = new StringTokenizer(in.readLine());
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;

        for(int i = 0; i < n; i++) {
            operand[i] = st.nextToken().trim();
        }

        // 사용가능한 연산자 갯수 할당
        st = new StringTokenizer(in.readLine());
        getOperator('+',Integer.parseInt(st.nextToken()));
        getOperator('-',Integer.parseInt(st.nextToken()));
        getOperator('*',Integer.parseInt(st.nextToken()));
        getOperator('/',Integer.parseInt(st.nextToken()));

        Collections.sort(operators);
        do{
            expression();
        } while(np());

        sb.append(max).append("\n").append(min);
        System.out.println(sb);

        long end = System.nanoTime();
        System.out.println((end - start) / 1000000 +"s");
        in.close();

    }

    private static boolean np() {

        int i = n - 2;
        while ( i > 0 && operators.get(i-1) >= operators.get(i) ) --i;

        if( i == 0 ) return false;

        int j = n - 2;
        while ( operators.get(i-1) >= operators.get(j)) --j;

        swap(i -1, j);

        int k = n - 2;
        while ( i < k) swap(i++,k--);

        return true;
    }

    private static void swap(int i, int j) {
        char tmp = operators.get(i);
        operators.set(i,operators.get(j));
        operators.set(j,tmp);
    }
    private static void expression() {
        int result = 0;
        Queue<String> q = new LinkedList<>();
        for(int i = 0; i < n - 1;i++ ) {
            q.offer(operand[i]);
            q.offer(String.valueOf(operators.get(i)));
        }
        q.offer(operand[n-1]);
        result = Integer.parseInt(q.poll());
        while ( !q.isEmpty() ) {
            String oper = q.poll();
            int op2 = Integer.parseInt(q.poll());
            result = getOperater(result,op2,oper);
        }
        if( max < result) max = result;
        if( min > result) min = result;
    }

    private static int getOperater(int op1 , int op2, String operator) {
        switch ( operator ) {
            case "+":
                return op1 + op2;
            case "-":
                return op1 - op2;
            case "*":
                return op1 * op2;
            case "/":
                int result = 0;
                if( op1 < 0) {
                    op1 = -op1;
                    result = -(op1 / op2);
                    return result;
                }
                result = (op1 / op2);
                return result;
            default:
                return 0;
        }
    }

    private static void getOperator(char order, int count) {
        for(int i = 0; i < count; i++) {
            operators.add(order);
        }
    }
}