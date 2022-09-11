package week5.snake3190;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	//우하좌상
	static int[] dirb= {1,0,-1,0}; //가로
	static int[] dira = {0,1,0,-1}; //세로
	static int N;
	static ArrayList<int[]> snake;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine().trim()); //배열의 크기 
		int K = Integer.parseInt(br.readLine().trim()); // 사과의 개수
		
		int[][] map = new int[N+1][N+1];
		
		// 사과 위치 (행,열)
		for(int k=0;k<K;k++) {
			st = new StringTokenizer(br.readLine());
			int apple_a = Integer.parseInt(st.nextToken()); // 사과위치 (행)
			int apple_b = Integer.parseInt(st.nextToken()); // 사과위치 (열)
			
			map[apple_a][apple_b]=2;// 사과 위치 마킹
		}

		int L = Integer.parseInt(br.readLine().trim()); //뱀의 방향 변환 횟수
		
		Map<Integer , Character> Baminfo = new HashMap<>();
		// 뱀의 방향 변환 정보 (정수 x, 문자 c)
		//x 초가 끝난 뒤 C (왼쪽, 오른쪽방향 회전시킨다)
		for(int l = 0;l<L;l++) {
			st = new StringTokenizer(br.readLine());
			int second = Integer.parseInt(st.nextToken()); // 초
			char bamDir = st.nextToken().charAt(0); // 회전시킬 방향
		
			Baminfo.put(second, bamDir);
		}
		
		// 뱀의 위치 (1,1)
		snake = new ArrayList<>();
		snake.add(new int[] {1,1});
		
		int dir=0;
		int time=0;
		int ca=1; //현위치x
		int cb=1; //현위치y
		
		while(true) {
			time++;
			
			int na = ca+dira[dir];
			int nb = cb + dirb[dir];
			
			//벽, 뱀몸 쳌
			if(bodyCheck(na, nb)) break;
			
			//사과 쳌 
			//1. 사과 있삼  머리 한칸 늘리고 꼬리 냅두기
			if(map[na][nb]==2) {
				map[na][nb]=0; // 사과 냠
				snake.add(new int[] {na,nb}); //머리 정보 추가
			}else {  // 2.사과 없삼 머리 한칸 늘리고, 꼬리 지우기
				snake.add(new int[] {na,nb}); // 머리 정보 추가
				snake.remove(0); // 꼬리 인덱스는 0 , 꼬리 지우기
			}
			
			ca = na;
			cb = nb;
			
			// 시간 끝났으면 방향 바꾸기 
			// 뱀정보의 초 정보와 현재 초가 같으면
			if(Baminfo.containsKey(time)) {
				//오른쪽 dir ++ 하여 4이면 반복 횟수때문에 다시 0으로 돌아오게끔
				if(Baminfo.get(time)=='D') { 
					dir++;
					if(dir==4) dir=0; // 돌고 돌아 0,1,2,3 
				}
				//왼쪽 dir--하여 -1이면 dir 3으로 이동
				if(Baminfo.get(time)=='L') { 
					dir--;
					if(dir==-1) dir=3;
				}
			}
		}
		System.out.println(time);
		
	}
	
	static boolean bodyCheck(int na,int nb) {
		if(na<1 || nb<1 || na>N || nb>N ) {
			return true;
		}
		
		for(int i=0;i<snake.size();i++) {
			if(na==snake.get(i)[0] && nb == snake.get(i)[1]) {
				return true;
			}
		}
		return false;
	}
}
