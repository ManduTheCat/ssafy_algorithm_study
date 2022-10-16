package week8.SWEA5658TreasureBox;
import java.io.*;
import java.util.*;

public class Solution {

    public static int n;
    public static char[] arr;
    public static HashSet<String> set;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            arr = br.readLine().toCharArray();
            set = new HashSet<>();  // 비밀번호가 중복되지 않도록 set을 사용

            for(int i = 0; i < n/4; i++){  // n/4번만 돌려보면 나머지는 중복됨
                makePassword();  // 각 변의 숫자들을 합쳐서 비밀번호를 만든다.
                rotate();  // 시계방향 회전
            }

            ArrayList<String> list = new ArrayList<>(set);  // set을 list로 만든 후 정렬
            Collections.sort(list);

            long ans = Long.parseLong(list.get(list.size()-k), 16);  // 구하고 싶은 k번째 비밀번호를 String -> 16진수로 변경

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void makePassword(){
        int size = n/4;
        for(int i = 0; i < 4; i++){  // 4개의 변에서 비밀번호 생성
            StringBuilder num = new StringBuilder();
            for(int j = i*size; j < (i+1) * size; j++){
                num.append(arr[j]);
            }
            set.add(num.toString());  // 생성된 비밀번호를 set에 추가
        }
    }

    public static void rotate(){
        char tmp = arr[n-1];
        for(int i = n-1; i >= 1; i--){
            arr[i] = arr[i-1];
        }
        arr[0] = tmp;
    }

}

