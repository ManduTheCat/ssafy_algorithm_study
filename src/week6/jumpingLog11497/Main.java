package week6.jumpingLog11497;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(t-->0){
            int ans = 0;

            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr);

            int pre = 0;
            for(int i = 2; i < n; i+=2){
                ans = Math.max(ans, Math.abs(arr[pre] - arr[i]));
                pre = i;
            }

            int end = n-1;
            if(n % 2 == 1) end = n-2;
            for(int i = end; i > 0; i-=2){
                ans = Math.max(ans, Math.abs(arr[pre] - arr[i]));
                pre = i;
            }

            ans = Math.max(ans, Math.abs(arr[pre] - arr[0]));

            sb.append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }

}