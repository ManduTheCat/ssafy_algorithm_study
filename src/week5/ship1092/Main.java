package week5.ship1092;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int crane[] = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			crane[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(crane);
		
		int M = Integer.parseInt(br.readLine());
		ArrayList<Integer> box = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			box.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(box);
		
		if(box.get(M-1) > crane[N-1]) {
			System.out.println(-1);
			System.exit(0);
		}
		
		int ans = 0;
		
		while(!box.isEmpty()) {
			int boxIdx = box.size() - 1;
			for(int c = N - 1; c >= 0;) {
				if(boxIdx == -1) break;
				if(box.get(boxIdx) <= crane[c]) {
					box.remove(boxIdx--);
					c--;
				}
				else boxIdx--;
			}
			ans++;
		}
		System.out.println(ans);
	}
}