package week5.baseball17281;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int N, score; //이닝의 개수, 최대 점수
	static int[][] player; //각 선수가 각 이닝에서 얻는 결과를 담을 배열
	static boolean[] select; //선택 여부 확인
	static int[] gamePlayer; //타자가 타석에 서는 순서

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); //이닝 수 입력
		
		player = new int[N][9]; //각 선수가 각 이닝에 얻는 결과를 담을 배열 초기화
		
		for(int i=0; i<N; i++) { //이닝 수만큼 반복
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<9; j++) { //선수 수 만큼 반복
				player[i][j] = Integer.parseInt(st.nextToken()); //결과 입력
			}
		}
		
		gamePlayer = new int[9]; //타자가 타석에 서는 순서를 저장할 배열 
		select = new boolean[9]; //선수의 선택 여부를 확인
		
		select[3] = true; //4번타자는 고정된 선수가 존재하니까 선택여부를 true로 변경
		gamePlayer[3] = 0; //4번타자는 1번선수로 고정
		
		score = 0; //얻을 수 있는 최대 점수
		
		perm(1); //고정된 선수를 제외한 8명의 선수 타순 정하기
		
		System.out.println(score); //최대 점수 출력

	}

	//선수 타순을 정할 메서드
	private static void perm(int cnt) {
		if(cnt == 9) { //9명의 순서가 모두 정해지면
			playbaseball(); //야구 경기 시작
			return;
		}
		
		for(int i=0; i<9; i++) {
			if(select[i] == true) continue; //이미 선택되었다면 continue
			
			select[i] = true; //선택 여부를 true로 변경
			gamePlayer[i] = cnt; //타순 저장
			perm(cnt+1); //재귀
			select[i] = false; //재귀가 끝나면 선택 여부를 false로 변경
			
		}
		
	}

	//야구 경기를 진행하는 메서드
	private static void playbaseball() {
		int nowScore = 0; //현재 점수를 저장할 변수
		int playerNum = 0; //현재 타석에 서는 선수의 번호를 저장할 변수
		boolean chk = true; //while문 종료시에 필요한 변수
		boolean[] base = new boolean[4]; //베이스 4개(1루, 2루, 3루, 홈)
		
		//야구 경기 시작
		for(int i=0; i<N; i++) { //이닝수만큼 반복
			int outCnt = 0; //아웃 개수
			base = new boolean[4]; //이닝마다 베이스를 초기화
			chk = true;
			
			while(chk) { //아웃 3개 나올때까지 반복
				for(int j=playerNum; j<9; j++) { //선수 수만큼 반복
					switch(player[i][gamePlayer[j]]) { //현재 타석에 선 선수가 낼 수 있는 결과가
					case 1 : //1루타일 때
						if(base[3]) { //3루에 주자가 있으면
							nowScore++; //1점 획득
							base[3] = false; //3루 비우기
						} 
						if(base[2]) { //2루에 주자가 있으면
							base[2] = false; //2루 비우고
							base[3] = true; //3루로 이동
						}
						if(base[1]) { //1루에 주자가 있으면
							base[1] = false; //1루 비우고
							base[2] = true; //2루로 이동
						}
						
						base[1] = true; //타석에 있던 선수가 1루로 이동 
						break;
						
					case 2: //2루타일 때
						if(base[3]) { //3루에 주자가 있으면
							nowScore++; //1점 획득
							base[3] = false; //3루 비우기
						}
						if(base[2]) { //2루에 주자가 있으면
							nowScore++; //1점 획득
							base[2] = false; //2루 비우기
						}
						if(base[1]) { //1루에 주자가 있으면
							base[1] = false; //1루 비우고
							base[3] = true; //3루로 이동
						}
						
						base[2] = true; //타석에 있던 선수가 2루로 이동 
						break;
						
					case 3: //3루타일 때
						if(base[3]) { //3루에 주자가 있으면
							nowScore++; //1점 획득
							base[3] = false; //3루 비우기
						}
						if(base[2]) { //2루에 주자가 있으면
							nowScore++; //1점 획득
							base[2] = false; //2루 비우기
						}
						if(base[1]) { //1루에 주자가 있으면
							nowScore++; //1점 획득
							base[1] = false; //1루 비우기
						}
						
						base[3] = true; //타석에 있던 선수가 3루로 이동 
						break;
						
					case 4: //홈런
						if(base[3]) { //3루에 주자가 있으면
							nowScore++; //1점 획득
							base[3] = false;  //3루 비우기
						} 
						if(base[2]) { //2루에 주자가 있으면
							nowScore++; //1점 획득
							base[2] = false;  //2루 비우기
						}
						if(base[1]) { //1루에 주자가 있으면
							nowScore++; //1점 획득
							base[1] = false; //1루 비우기
						}
						
						nowScore++; //1점 획득
						break;
						
					case 0: //아웃일 때
						outCnt++; //아웃 개수 증가
						break;
					}
					
					if(outCnt == 3) { //아웃이 3개이면
						//현재 이닝 종료
						playerNum = j+1; //다음으로 타석에 설 순서였던 선수부터 다음 게임 시작
						if(playerNum == 9) { //모든 선수들이 타석에 섰다면
							playerNum = 0; //다시 처음 선수부터
						}
						chk = false; //반복 종료
						break;
					}
				}
				if(!chk) { //반복 종료
					break;
				}
				
				playerNum = 0; //모든 선수들이 타석에 섰다면 다시 처음부터 시작
			}
		}

		score = Math.max(score, nowScore); //최대 점수와 현재 점수를 비교해서 더 큰 점수를 최대 점수로 저장
	}
	
}
