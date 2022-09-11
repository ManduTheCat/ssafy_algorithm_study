package week3.robot2174;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	//왼상하우
	static int[] dirY= {0,-1,1,0};
	static int[] dirX= {-1,0,0,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[b+1][a+1];
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 로봇 초기 위치 및 방향
		int m = Integer.parseInt(st.nextToken()); // 명령 내리는 순서 (로봇,종류,반복횟수)
		
		ArrayList<int[]> robot = new ArrayList<>();
		
		
		//각 로봇의 초기위치 및 방향 입력받기 
		//행번호가 밑에서부터 시작 -> 맵 상하반전시켜서 N과 S를 바꿔줘야함 ㅠㅠ
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			int roboty = Integer.parseInt(st.nextToken());
			int robotx = Integer.parseInt(st.nextToken());
//			System.out.println(robotx+" "+roboty);
			char robotdir = st.nextToken().charAt(0);
			int dir=0;
			switch(robotdir) {
			case 'N': // 상
				dir=3;
				break;
			case 'W': // 좌
				dir=1;
				break;
			case 'E':  // 우
				dir=2;
				break;
			case 'S':  // 하
				dir=0;
				break;
			
			}
			robot.add(new int[] {robotx,roboty,dir});
			map[robotx][roboty]=1;
		}
		
		//각 명령이 명령을 내리는 순서대로 
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int commendRobot = Integer.parseInt(st.nextToken());
			char commend = st.nextToken().charAt(0);
			int commendRepeat = Integer.parseInt(st.nextToken());
			
			/** 명령받는 로봇의 x,y,dir 
//			int commendRobotX=robot.get(commendRobot-1)[0];
//			int commendRobotY=robot.get(commendRobot-1)[1];
//			int commendRobotDir=robot.get(commendRobot-1)[2];
			*/
			// 반복횟수만큼 반복 
			if(commend!='F') {commendRepeat = commendRepeat%4;}
			for(int cmd=0;cmd<commendRepeat;cmd++) {
				switch(commend) { //상 : 3, 좌 : 1, 우 : 2, 하 : 0
				//  ***** 맵을 상하반전시켜서 R과 L을 바꿔줘야함
				case 'R': 
					//상->왼
					if(robot.get(commendRobot-1)[2]==0) {robot.get(commendRobot-1)[2] = 1;}
					//왼->하
					else if(robot.get(commendRobot-1)[2]==1) {robot.get(commendRobot-1)[2] = 3;}
					//하->오
					else if(robot.get(commendRobot-1)[2]==3) {robot.get(commendRobot-1)[2] = 2;}
					//오->상
					else if(robot.get(commendRobot-1)[2]==2) {robot.get(commendRobot-1)[2] = 0;}
					break; 
				case 'L': 
					//상->오
					if(robot.get(commendRobot-1)[2]==0) {robot.get(commendRobot-1)[2] = 2;}
					//오->하
					else if(robot.get(commendRobot-1)[2]==2) {robot.get(commendRobot-1)[2] = 3;}
					//하->왼
					else if(robot.get(commendRobot-1)[2]==3) {robot.get(commendRobot-1)[2] = 1;}
					//왼->상
					else if(robot.get(commendRobot-1)[2]==1) {robot.get(commendRobot-1)[2] = 0;}
					break;
				case 'F': //직진
					//이동할 방향으로 이동
					int nextX = robot.get(commendRobot-1)[0]+dirX[robot.get(commendRobot-1)[2]];
					int nextY = robot.get(commendRobot-1)[1]+dirY[robot.get(commendRobot-1)[2]];
					
					//범위 넘어갔을때 (벽일 경우)
					if(nextX<1 || nextY<1 || nextX>b || nextY>a) {
						System.out.println("Robot "+commendRobot+" crashes into the wall");
						System.exit(0); // 범위가 넘어갔으니 시스템 종료
					}
					//다른 로봇과 부딪히면 
					if(map[nextX][nextY]==1) {
						int sameRobotNumber=0; // 같은 좌표를 가진 로봇의 번호 저장할 변수
						//로봇 좌표 및 방향을 가진 ArrayList<int[]> robot 반복해서 찾기
						for(int robotnum=0;robotnum<robot.size();robotnum++) {
							if(commendRobot-1 == robotnum) {continue;}
							int x=robot.get(robotnum)[0];
							int y=robot.get(robotnum)[1];
						
							// 같은 x,y좌표가 있다면
							if(x==nextX && y == nextY) {

								sameRobotNumber= robotnum+1;
							}
						}
					
						System.out.println("Robot "+commendRobot+" crashes into robot "+sameRobotNumber);
						System.exit(0);
					}
//					초기화
					map[robot.get(commendRobot-1)[0]][robot.get(commendRobot-1)[1]]=0;
					map[nextX][nextY]=1;
					robot.get(commendRobot-1)[0]= nextX;
					robot.get(commendRobot-1)[1]= nextY;
					break;
				}
			}
		}
		System.out.println("OK");
		
	}


}
