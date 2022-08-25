package week3.countingPaper1789;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static int[] paperCnt;
    private static int[][] paper;

    public static void main(String[] args) throws IOException {

//        FileInputStream file = new FileInputStream("");
//        BufferedReader in = new BufferedReader(new InputStreamReader(file));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(in.readLine());
        paperCnt = new int[3];                  // 0: -1갯수 1: 0갯수 2: 1갯수

        paper = new int[N][N];                  // 종이의 크기
        for(int a = 0; a < N; a++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int b = 0; b < N; b++) {
                paper[a][b] = Integer.parseInt(st.nextToken());
            }
        }

        cut(0,0,N);                    // 종이 자르기 

        for(int a = 0; a < 3; a++) {
            System.out.println(paperCnt[a]);
        }
    }

    private static void cut(int start , int row, int col) {

        int minus = 0;          // -1 카운트
        int zero = 0;           // 0 카운트
        int plus = 0;           // 1 카운트

        // 종이의 길이 만큼 row 증가
        for(int a = row; a < row + (col - start); a++) {
            for(int b = start; b < col; b++) {
                if( paper[a][b] == -1 ) {
                    minus++;
                } else if( paper[a][b] == 0) {
                    zero++;
                } else{
                    plus++;
                }
            }
        }
        // 종이의 크기 만큼 -1이면 첫번째 인덱스 증가
        if( minus == (int) Math.pow(col - start,2) ) {
            paperCnt[0]++;
            return;
        }
        // 종이의 크기 만큼 0이면 두번째 인덱스 증가
        if( zero == (int) Math.pow(col  - start,2) ) {
            paperCnt[1]++;
            return;
        }
        // 종이의 크기 만큼 1이면 세번째 인덱스 증가
        if( plus == (int) Math.pow(col - start,2) ) {
            paperCnt[2]++;
            return;
        }
        // 9등분 하는 구간
        for(int a = 0; a < 3; a++) {
            // 나눈 종이의 크기
            int diff = (col - start) / 3;
            for(int b = 0; b < 3; b++) {
                // 왼쪽에서 오른쪽으로 이동 ( 세로 )
                int _start = start + diff * b;
                cut(_start, row + diff * a , _start+ diff);
            }
        }
    }  
}
