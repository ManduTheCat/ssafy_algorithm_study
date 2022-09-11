package week5.snake3190;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	//뱀의 방향 변환 정보를 저장할 수 있도록 Move 클래스 생성
	static class Move {
		int time; //방향을 변환하는 시간
		String direction; //변환되는 방향
		
		public Move(int time, String direction) {
			super();
			this.time = time;
			this.direction = direction;
		}
	}
	
	static List<Move> moveList; //뱀의 방향 변환 정보를 저장할 리스트
	static int N, gameTime; //보드의 크기, 게임 진행 시간
	static int[][] deltas = {{0,1}, {1,0}, {0, -1}, {-1,0}}; //오른쪽, 아래, 왼쪽, 위
	static Queue<Point> snake; //뱀이 현재 위치한 좌표를 저장하기 위한 큐

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); //보드의 크기
		int[][] board = new int[N][N]; //보드를 보드 크기만큼 초기화
		
		int K = Integer.parseInt(br.readLine()); //사과의 개수
		
		for(int i=0; i<K; i++) { //사과의 개수만큼 반복
			StringTokenizer st = new StringTokenizer(br.readLine());

			int x = Integer.parseInt(st.nextToken()); //사과 x좌표
			int y = Integer.parseInt(st.nextToken()); //사과 y좌표
			board[x-1][y-1] = 1; //사과가 있는 자리를 보드에 미리 1로 표시
		}
		
		int L = Integer.parseInt(br.readLine()); //뱀의 방향 변환 횟수
		moveList = new ArrayList<>(); //뱀의 방향 변환 정보를 저장할 리스트
		for(int i=0; i<L; i++) { //뱀의 방향 변환 횟수만큼 반복
			StringTokenizer st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken()); //방향을 변환하는 시간
			String d = st.nextToken(); //변환되는 방향
			
			moveList.add(new Move(t, d)); //뱀의 방향 변환 정보를 저장
		}
		
		gameTime = 0; //게임 진행 시간
		int curX = 0; //현재 뱀의 x좌표
		int curY = 0; //현재 뱀의 y좌표
		int dir = 0; //뱀이 이동할 방향
		snake = new LinkedList<>(); //뱀의 정보를 저장할 큐 초기화
		snake.offer(new Point(curX, curY)); //초기값 
		int chk = 0; //현재까지 진행된 뱀의 방향 변환 개수를 저장
		
		while(true) {
			gameTime++; //시간 증가
			
			//현재좌표를 이용해서 임시좌표 생성 //초기 방향은 오른쪽
			int tmpX = curX + deltas[dir][0];
			int tmpY = curY + deltas[dir][1];
			
			if(check(tmpX, tmpY)) { //벽에 부딪히는 경우
				break; //반복문 종료
			}
			
			//자기 몸에 부딪치는 경우 종료
			if(snake.contains(new Point(tmpX, tmpY))) { //큐안에 지금 좌표가 존재하면 뱀이 자기 몸에 부딪히므로
				break; //반복문 종료
			}
			
			//다음칸으로 이동햇는데 사과가 있는 경우와 없는 경우
			if(board[tmpX][tmpY] == 1) { //사과가 있는 경우
				board[tmpX][tmpY] = 0; //사과를 먹어서 치우고
				snake.offer(new Point(tmpX, tmpY)); //현재 위치를 큐 안에 넣기
			} else { //사과가 없다면
				snake.poll(); //뱀의 꼬리를 제거, 큐에서 제일 앞에 있는 좌표 제거
				snake.offer(new Point(tmpX, tmpY)); //새로 움직인 곳의 좌표 넣기
			}
			
			//x초 후 방향 전환, L이 왼쪽으로 90도, D가 오른쪽으로 90도
			//오른쪽으로 회전은 +, 왼쪽으로 회전은 -
			if(chk<L) { //현재 방향변환 개수가 총 방향변환 개수보다 작다면
				if(moveList.get(chk).time == gameTime) { //방향변환 시간이 현재 시간과 같다면
					if(moveList.get(chk).direction.equals("L")) { //변환해야 되는 방향이 L이라면
						if(dir == 0) { //dir이 0이라면 
							dir = 3; //3으로 이동
						} else {
							dir--; //왼쪽으로 90도 회전
						}
					} else if(moveList.get(chk).direction.equals("D")) { //변환해야 되는 방향이 D이라면
						if(dir == 3) { //dir이 0이라면
							dir = 0; //0으로 이동
						} else { 
							dir++; //오른쪽으로 90도 회전
						}
					}
					chk++; //현재 방향변환 개수 증가
				}
			}
			
			//임시 좌표를 현재 좌표로 저장
			curX = tmpX; 
			curY = tmpY; 
			
		}
		
		System.out.println(gameTime); //게임 진행 시간을 출력

	}

	//범위를 벗어나는지 체크하는 메서드
	private static boolean check(int x, int y) {
		if(x<0 || y<0 || x>=N || y>=N) {
			return true;
		}
		return false;
	}
	
}
