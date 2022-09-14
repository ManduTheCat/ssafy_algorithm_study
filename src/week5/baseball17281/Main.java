package week5.baseball17281;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int[][] player;  // 이닝을 저장하는 2차원 배열
	private static int N; // 이닝 개수
	private static int max = Integer.MIN_VALUE; // 최대 점수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		player = new int[N][10];  
    // 이닝을 저장한다
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i < 10; i++) {
				player[n][i] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] order = new int[10];  // 선수들의 순서를 담을 배열
		boolean[] visited = new boolean[10];
		order[4] = 1;       // 1번타자는 4번째 순서로 정해져있으므로 미리 넣어준다
		visited[1] = true;  // 1번타자는 4번째 순서로 정해져있으므로 방문을 true로 해준다.
		perm(order, visited, 1);

		System.out.println(max);
	}
  
  // 순열을 만드는 함수
	static void perm(int[] order, boolean[] visited, int depth) {
		if (depth == 10) {
			play(order);  // 순열이 만들어지면 게임 시작
			return;
		}
		for (int i = 1; i < 10; i++) {
			if (visited[i] != true) {
				visited[i] = true;
				order[depth] = i;
				if (depth == 3) {
					perm(order, visited, depth + 2);  // 4번째는 1번타자로 정해져있으므로 +2
				} else {
					perm(order, visited, depth + 1);
				}
				visited[i] = false;
			}
		}
	}

	static void play(int[] order) {
		int score = 0;
		int i = 1;  // 시작
    boolean[] base; // 홈, 1루, 2루, 3루
		for (int n = 0; n < N; n++) {
			base = new boolean[4]; // 새로운 이닝이 시작했으므로 초기화를 해준다.
			int out = 0;  // out개수 초기화
      
      // n번째 이닝 시작
			while (true) {  
				int result = player[n][order[i]]; // 해당 플레이어가 어떤 점수를 내는지 받아온다.

					switch (result) {
                    case 0: // 0 이면 아웃
                        out++;
                        break;
                    case 1: // 1루타
                        for (int k = 3; k >= 1; k--) {
                            if (base[k]) {
                                if (k == 3) { // 3루타에 선수가 있으면 점수획득
                                    score++; 
                                    base[k] = false; 
                                    continue;
                                }
                              
                                base[k] = false;  
                                base[k + 1] = true;
                            }
                        }
                        base[1] = true; // 1루타에 추가
                        break;
                    case 2: // 2루타
                        for (int k = 3; k >= 1; k--) {
                            if (base[k]) {
                                if (k == 3 || k == 2) { // 3루타 2루타에 선수가 있으면 점수획득
                                    score++; 
                                    base[k] = false; 
                                    continue;
                                }

                                base[k] = false;
                                base[k + 2] = true; // 2개 이동
                            }
                        }
                        base[2] = true; 
                        break;
                    case 3: // 3루타
                        for (int k = 3; k >= 1; k--) {  // 3루타 2루타에 선수가 있으면 점수획득
                            if (base[k]) {
                                score++;
                                base[k] = false;
                            }
                        }
 
                        base[3] = true;
                        break;
                    case 4: // 홈런
                        for (int k = 1; k <= 3; k++) {
                            if (base[k]) { 
                                score++; 
                                base[k] = false;
                            }
                        }
                        score++;
                        break;
                    }
 
				}
				i++;
				if (i >= 10) {  // 모든 선수가 뛰면 다시 순서대로 다시 시작
					i = 1;
				}
				if (out == 3) { // out이 3개면 멈춘다
					break;
				}
			}
		}
		if (score > max) {  // 최대 점수보다 커졌으면 점수 바꾸기
			max = score;
		}
	}


}
