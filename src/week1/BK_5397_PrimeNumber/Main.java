package week1.BK_5397_PrimeNumber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {

//        long start = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        boolean[] boo = new boolean[1000001]; // 소수인 index는 true로 변환
        boo[2] = true;      // 미리 할당
        boo[3] = true;      // 미리 할당

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for(int i = n; i <= m; i++) {
            // 1은 소수가 아니니까 건너뛰고
            if( i == 1) continue;
            // 2의 배수와 3의 배수도 건너뛰고
            if( i % 2 == 0 | i % 3 == 0) continue;
            // 자신의 제곱근 보다 낮은 수로 나누었지면 break;
            for( int j = 2; j <= Math.sqrt(i); j++) {
                if( i % j == 0) {
                    boo[i] = false;
                    break;
                }
                boo[i] = true;
            }
        }
        // n ~ m 범위에서 소수체크가 true이면 builder에 추가
        for(int i = n; i <= m; i++) {
            if(boo[i]) {
                sb.append(i).append("\n");
            }
        }

        System.out.print(sb);
        /* 시간 체크하기  */
//        long end = System.currentTimeMillis();
//        System.out.println((end - start) / 1000 + "초");
	}
}
