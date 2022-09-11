package week6.jumpingLog11497;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * 통나무로 만들 수 있는 최소 난이도 
 * 난이도 : 인접한 두 통나물 간의 높이의 차의 최댓값 
 * 통나무 높이 입력 받은 배열을  양쪽 끝(왼쪽부터 오른쪽) 높이가 가장 작은 순으로  넣는다면 ?
 * 2     
 * 2       4
 * 2 5     4
 * 2 5   7 4
 * 2 5 9 7 4
 * - 1.입력받은 배열 정렬  2 4 5 7 9
 * - 2. 인덱스 홀수번째값은 오른쪽 끝 , 아닌 것들은 왼쪽 끝에 들어가겠지 ?
 * 인접한 값들끼리 빼주고 그 값 비교해서 최댓값 찾으면 그게 바로 최소 난이도 
 * */
public class Main {
	static int logNum;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1;tc<=T;tc++) {
			logNum = Integer.parseInt(br.readLine().trim());
			
			int[] logHeight = new int[logNum]; // 통나무 높이 저장할 리스트
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<logNum;i++) {
				logHeight[i]=Integer.parseInt(st.nextToken());
			}
			
			//배열 정렬(오름차순)
			Arrays.sort(logHeight);
			
			// 정렬된 값 저장될 배열 
			int[] log = new int[logNum];
			int left =0;
			int right =logNum-1;
			// 배열의 인덱스 홀수번째 값 : 오른쪽 끝, 짝수번째 값 : 왼쪽 끝 ㄱ
			for(int idx =0;idx<logNum;idx++) {
				if(idx%2!=0) { // 홀수번째 인덱스
					log[right]=logHeight[idx];
					right-=1;
				}else { // 짝수번째 인덱스
					log[left]=logHeight[idx];
					left+=1;
				}
			}
//			System.out.println(Arrays.toString(log));

			
			// 배열의 맨 앞과 끝 빼주는 것도 잊지 않기 ~! 
			int min = Math.abs(log[0]-log[logNum-1]);
			// 인접한 값끼리 빼주기 , 빼주면서 비교는?
			for(int i=0;i<logNum-1;i++) {
				min = Math.max(min, Math.abs(log[i]-log[i+1]));

			}

			System.out.println(min);
		}

	}
	
}
