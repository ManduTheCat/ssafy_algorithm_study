package week7.addBracket16637;

import java.io.*;
import java.util.*;

public class Main {

    public static int n, ans = Integer.MIN_VALUE;
    public static char[] arr;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = br.readLine().toCharArray();

        solve(2, arr[0]-'0');
        System.out.println(ans);
    }

    public static void solve(int idx, int sum){
        if(idx >= n){
            ans = Math.max(ans, sum);
            return;
        }

        // 괄호 X
        // 현재까지의 sum 에 arr[idx] 계산
        solve(idx+2, calc(sum, arr[idx-1], arr[idx]-'0'));

        // 현재 idx 기준으로 오른쪽 숫자와 괄호
        if(idx < n-2){  // 오른쪽에 숫자가 있을 경우에만 계산
            // 괄호부터 계산 후 지금까지의 sum과 계산
            solve(idx+4, calc(sum, arr[idx-1], calc(arr[idx]-'0', arr[idx+1], arr[idx+2] - '0')));
        }
    }

    public static int calc(int a, char op, int b){
        if(op == '+') return a + b;
        else if(op == '-') return a - b;
        else return a * b;
    }

}

