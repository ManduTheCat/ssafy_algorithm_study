package week5.ship1092;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] weight = new int[n];
        int[] count = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            weight[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(weight);

        int m = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> boxes = new PriorityQueue<>(Comparator.reverseOrder());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++){
            boxes.offer(Integer.parseInt(st.nextToken()));
        }

        while(!boxes.isEmpty()){
            int box = boxes.poll();

            if(box > weight[n-1]){
                System.out.println(-1);
                return;
            }

            int countMinIdx = -1;
            int minCount = Integer.MAX_VALUE;
            for(int i = n-1; i >= 0; i--){
                if(weight[i] < box) break;

                if(minCount > count[i]){
                    minCount = count[i];
                    countMinIdx = i;
                }
            }

            count[countMinIdx]++;
        }

        int ans = -1;
        for (int c : count) {
            ans = Math.max(ans, c);
        }

        System.out.println(ans);
    }

}