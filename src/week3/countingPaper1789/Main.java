package week3.countingPaper1789;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, minusOne=0, zero=0, one=0; //종이의 크기, -1로만 채워진 종이 개수, 0으로만 채워진 종이 개수, 1로만 채워진 종이 개수
	static int[][] paper; //종이를 나타내는 배열

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); //종이의 크기 입력
		paper = new int[N][N]; //종이를 나타내는 배열을 입력 받은 크기로 설정
		for(int i=0; i<N; i++) { //종이의 크기만큼 반복
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken()); //종이에 있는 정보 입력
			}
		}
		
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<N; j++) {
//				System.out.print(paper[i][j] + " ");
//			} System.out.println();
//		} //확인
		
		find(N, 0, 0); //find 메서드 호출, (종이의 크기, x좌표, y좌표)
		
		System.out.println(minusOne); //-1로만 채워진 종이의 개수 출력
		System.out.println(zero); //0으로만 채워진 종이의 개수 출력
		System.out.println(one); //1로만 채워진 종이의 개수 출력

	}

	//종이를 9개의 동일한 크기로 잘라서 같은 수가 적힌 종이가 몇개가 있는지 찾는 메서드 
	private static void find(int n, int x, int y) { //매개변수는 종이의 크기, x좌표, y좌표
		boolean chk = sameNumber(n, x, y); //종이에 있는 수가 모두 같은 수 인지 확인하여 값을 chk에 저장
		
		if(chk) { //하나의 종이에 있는 값이 모두 동일 할 때
			int tmp = paper[x][y]; //종이의 왼쪽 상단의 값을 임시로 저장 
			switch(tmp) { //tmp에 있는 숫자를 기준으로
			case -1: //-1이면
				minusOne++; //-1 종이 개수 증가
				break;
			case 0: //0이면
				zero++; //0 종이 개수 증가
				break;
			case 1: //1이면
				one++; //1종이 개수 증가
			}
			
			return;
			
		} else { //하나의 종이에 있는 값이 모두 동일하지 않으면
			int size = n/3; //9개의 종이로 잘라야 하니 각 3줄씩 잘라야 함
			
			find(size, x, y); //중앙의 왼쪽 위
			find(size, x, y+size); //중앙의 위쪽
			find(size, x, y+size*2); //중앙의 오른쪽 위
			
			find(size, x+size, y); //중앙의 왼쪽
			find(size, x+size, y+size); //중앙
			find(size, x+size, y+size*2); //중앙의 오른쪽
			
			find(size, x+size*2, y); //중앙의 왼쪽 아래
			find(size, x+size*2, y+size); //중앙의 아래쪽
			find(size, x+size*2, y+size*2); //중앙의 오른쪽 아래
		}
		
	}

	//하나의 종이 안에 있는 숫자가 모두 같은 숫자인지 확인하는 메서드
	private static boolean sameNumber(int size, int x, int y) {
		int tmp = paper[x][y]; //종이의 왼쪽 상단의 값을 임시로 저장 
		
		for(int i=x; i<size+x; i++) { //주어진 좌표부터 시작해서 크기에 좌표 더한 값까지 반복
			for(int j=y; j<size+y; j++) {
				if(tmp != paper[i][j]) { //만약 임시로 저장된 값과 현재 탐색한 값이 다르다면
					return false; //종이가 같은 숫자로 이루어진 것이 아니니까 false 반환
				}
			}
		}
		return true; //위에서 걸리지 않았으면 종이 하나가 모두 동일한 숫자로 되어 있으니까 true 반환
	}
	
}
