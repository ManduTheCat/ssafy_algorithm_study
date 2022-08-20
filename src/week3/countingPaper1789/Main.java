package week3.countingPaper1789;

/** 분할정복
 * N*N 크기 행렬 
 * 각 칸 -1,0,1 중 하나 저장 
 * 1. 만약 종이가 모두 같은 수로 되어 있다면 종이 그대로 사용
 * 2.(1)가 아닌 경우  종이를 같은 크기의 종이 9개로 자르고 각 잘린 종이에 대해 (1) 과정 반복
 * */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr; // 입력된 값 저장할 배열
	//one_ : -1,zero : 0, one: 1 카운트할 변수 
	static int one_=0;
	static int zero=0;
	static int one=0; 
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		
		arr = new int[n][n];
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				arr[i][j]= Integer.parseInt(st.nextToken());
			}
		}
		
		func1(n,0,0);
		System.out.println(one_);
		System.out.println(zero);
		System.out.println(one);

	}
	/**
	 * @param n 배열의 사이즈 
	 * @param i y좌표,행렬의 r
	 * @param j x좌표,행렬의 c
	 * */
	//1,2,3,4,5,6,7,8,9분면으로 분할
	static void func1(int n,int i,int j) {
		
		//같은 값인지 확인한 후, 같은 값일 때 해당 값의 카운트 올려준다
		if(check(n,i,j)) {
			if(arr[i][j]== -1) {
				one_++;
			}else if(arr[i][j]== 0) {
				zero++;
			}else if(arr[i][j]== 1) {
				one++;
			}
			return;
		}
		int size = n/3; //3분할
		
		func1(size, i, j); //1분면 (0,0) ~(0,2)
		func1(size, i, j+size); //2분면 (0,3)~(0,5)
		func1(size, i, j+(size*2)); //3분면 (0,6)~(0,8)
		
		func1(size, i+size, j); //4분면 (3,0) ~(3,2)
		func1(size, i+size, j+size); //5분면 (3,3)~(3,5)
		func1(size, i+size, j+(size*2)); //6분면 (3,6)~(3,8)
		
		func1(size, i+(size*2), j); //7분면 (6,0) ~(6,2)
		func1(size, i+(size*2), j+size); //8분면 (6,3)~(6,5)
		func1(size, i+(size*2), j+(size*2)); //9분면 (6,6)~(6,8)
		
		
	}
	/**
	 * @param n 배열의 사이즈 
	 * @param i y좌표,행렬의 r
	 * @param j x좌표,행렬의 c
	 * */
	// 현재 값이 같은 값인지 확인해주는 메서드
	static boolean check(int n,int i,int j) {
		int num = arr[i][j]; //현재 받아온 인덱스의 값을 저장할 변수 
		
		for(int r=i;r<i+n;r++) {
			for(int c=j;c<j+n;c++) {
				if(num != arr[r][c]) {
					return false;
				}
			}
		}
		return true;
	}
}
