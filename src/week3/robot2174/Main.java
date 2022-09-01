package week3.robot2174;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int C, R; //가로, 세로
	static int[][] map; //움직일 판
	static List<Robot> robot; //Robot 객체를 받는 robot 리스트
	static int[][] deltas = {{-1, 0}, {0,1}, {1,0}, {0,-1}}; //상, 우, 하, 좌

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		C = Integer.parseInt(st.nextToken()); //가로
		R = Integer.parseInt(st.nextToken()); //세로
		
		map = new int[R+1][C+1]; //1부터 사용하려고 한칸 크게
		
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); //로봇의 개수
		int M = Integer.parseInt(st.nextToken()); //로봇에게 내릴 명령의 개수
		
		robot = new ArrayList<>(); //robot 리스트 생성
		int Rcnt = 1; //입력된 로봇 순서 카운드
		for(int i=0; i<N; i++) { //로봇 개수만큼 반복
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken()); //y좌표 
			int x = Integer.parseInt(st.nextToken()); //x좌표
			int d = direction(st.nextToken()); //방향

			map[x][y] = Rcnt++; //해당하는 좌표에 로봇 번호 입력
			robot.add(new Robot(x, y, d)); //로봇 객체 생성해서 리스트에 저장
		}
		
		//System.out.println(robot); //확인
		
		for(int i=0; i<M; i++) { //명령 개수만큼 반복
			st = new StringTokenizer(br.readLine()); 
			int robotNum = Integer.parseInt(st.nextToken()); //수행할 로봇 번호
			String order = st.nextToken(); //이동 지시
			int times = Integer.parseInt(st.nextToken()); //반복 횟수
			
			for(int j=0; j<times; j++) { //반복 횟수만큼 반복
				switch(order) { //이동 지시가 무엇인지에 따라
				case "L" : //L이면 왼쪽으로 회전
					robot.get(robotNum-1).direction = (robot.get(robotNum-1).direction + 1) % 4;
					break;
				case "R" : //R이면 오른쪽으로 회전
					robot.get(robotNum-1).direction = (robot.get(robotNum-1).direction + 3)%4;
					break;
				case "F" : //F이면 전진
					if(move(robotNum)) { //전진해서 나온 결과가 true이면
						return; //부딪히는 일이 생겼으므로 종료
					} 
					break;
				}
			}
		}
		
		System.out.println("OK"); //충돌 없이 명령의 모두 수행했다면 OK 출력
		

	}
	
	//로봇을 전진시키며 충돌했는지 여부를 체크하는 메서드
	private static boolean move(int num) {
		int tmpX = robot.get(num-1).x + deltas[robot.get(num-1).direction][0]; //방향지시에 맞춰 좌표 변경
		int tmpY = robot.get(num-1).y + deltas[robot.get(num-1).direction][1]; //방향지시에 맞춰 좌표 변경
		
		//출력 확인
//		System.out.println("cx " + robot.get(num-1).x + " cy " + robot.get(num-1).y);
//		System.out.println("x " + tmpX + " y " + tmpY);
		
		if(tmpX <= 0 || tmpY <= 0 || tmpX >R || tmpY>C) { //범위를 벗어나는 경우라면
			//벽에 부딪히는 것이므로 해당 로봇의 번호와 정해진 메세지 출력  ##메세지 오타 조심!##
			System.out.println("Robot " + map[robot.get(num-1).x][robot.get(num-1).y] + " crashes into the wall");
			return true; //충돌하는 상황이 발생했으니까 true 반환
		}
		
		if(map[tmpX][tmpY] > 0) { //해당하는 곳에 다른 숫자가 이미 있다면
			//다른 로봇과 부딪히는 것이므로 현재 로봇의 번호, 부딪힌 로봇의 번호와 함께 정해진 메세지 출력  ##메세지 오타 조심!!##
			System.out.println("Robot " + map[robot.get(num-1).x][robot.get(num-1).y] + " crashes into robot " + map[tmpX][tmpY]);
			return true; //충돌하는 상황이 발생했으니까 true 반환
		}
		
		map[tmpX][tmpY] = map[robot.get(num-1).x][robot.get(num-1).y]; //이동한 좌표에 현재 로봇의 번호 입력
		map[robot.get(num-1).x][robot.get(num-1).y] = 0; //이전 좌표에는 로봇의 번호 지우기
		robot.get(num-1).x = tmpX; //이동한 좌표로 현재좌표 바꾸기
		robot.get(num-1).y = tmpY;
		
		return false; //충돌이 없었다면 false 반환
	}
	
	//방향 지시를 문자에서 숫자로 변경하기 위한 메서드
	static int direction(String d) {
		int dir = 0; //값을 리턴해줄 변수
		
		//판의 위아래를 뒤집어서 볼 예정
		if(d.equals("S")) {
			dir = 0; //위쪽
		} else if(d.equals("E")) {
			dir = 1; //오른쪽
		} else if(d.equals("N")) {
			dir = 2; //아래쪽
		} else if(d.equals("W")) {
			dir = 3; //왼쪽
		}
		
		return dir; //값을 리턴
	}

	//로봇의 x좌표, y좌표, 방향지시를 저장하기 위해 만든 Robot class
	static class Robot {
		int x; //x좌표
		int y; //y좌표
		int direction; //방향지시
		
		public Robot(int x, int y, int direction) {
			super();
			this.x = x;
			this.y = y;
			this.direction = direction;
		}

		@Override
		public String toString() { //출력 확인용
			return "Robot [x=" + x + ", y=" + y + ", direction=" + direction + "]";
		}
	}
	
}
