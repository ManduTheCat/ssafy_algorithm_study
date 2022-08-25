package week3.countingPaper1789;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int arr[][];
	public static int cnt1 = 0;	// -1로만 채워진 종이의 개수
	public static int cnt2 = 0;	// 0으로만 채워진 종이의 개수
	public static int cnt3 = 0;	// 1로만 채워진 종이의 개수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		
		for (int i = 0; i < arr.length; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < arr.length; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		divide(0, 0, N);
		
		System.out.println(cnt1 + "\n" + cnt2 + "\n" + cnt3);
	}
	
	static void divide(int r, int c, int n) {
		if(isSame(r, c, n)) {
			if(arr[r][c] == -1) cnt1++;
			else if(arr[r][c] == 0) cnt2++;
			else cnt3++;
			return;
		}
		
		divide(r, c, n/3);
		divide(r, c + n/3, n/3);
		divide(r, c + 2*n/3, n/3);
		
		divide(r + n/3, c, n/3);
		divide(r + n/3, c + n/3, n/3);
		divide(r + n/3, c + 2*n/3, n/3);
		
		divide(r + 2*n/3, c, n/3);
		divide(r + 2*n/3, c + n/3, n/3);
		divide(r + 2*n/3, c + 2*n/3, n/3);
	}
	
	static boolean isSame(int r, int c, int n) {
		int temp = arr[r][c];
		
		for(int i = r; i < r + n; i++) {
			for(int j = c; j < c + n; j++) {
				if(temp != arr[i][j]) {
					return false;
				}
			}
		}
		
		return true;
	}
}