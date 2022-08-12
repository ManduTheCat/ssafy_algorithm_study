package Day_0812;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1931_회의실배정_강혜성 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>(){

			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[1] == o2[1]) {
					return Integer.compare(o1[0], o2[0]);
				}
				return Integer.compare(o1[1], o2[1]);
			}
			
		});
		
		for(int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			int[] temp = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			pq.add(temp);
		}
		
		int count = 1;
		int[] time = pq.poll();
		while(!pq.isEmpty()) {
			
			int[] temp = pq.poll();
			if (time[1] <= temp[0]) {
				time = temp;
				count++;
			}
		}
		
		System.out.println(count);
		
	}
}
