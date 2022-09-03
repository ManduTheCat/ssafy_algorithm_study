package week3.countingPaper1789;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import javax.lang.model.util.ElementScanner6;

public class Main{
    static int N;
    static int[][] paper;
    static int m_count, z_count, p_count;//-1카운트, 0카운트, 1카운트
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        paper = new int[N][N];
        StringTokenizer st;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N; j++){
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        district(0,0,N);
        System.out.println(m_count);
        System.out.println(z_count);
        System.out.println(p_count);

    }

    static void district(int r, int c, int size){
        if(isPossible(r, c, size)){//모두 같은 수인지 확인해주고
            return;
        }
        //아니라면 나눠주는 재귀를 실행!
        int sep = size/3;
        district(r, c, sep); district(r, c+sep, sep); district(r, c+(sep*2), sep);//왼쪽 가운데 오른쪽, 첫번째 라인
        district(r+sep, c, sep); district(r+sep, c+sep, sep); district(r+sep, c+(sep*2), sep);//왼쪽 가운데 오른쪽, 두번째 라인
        district(r+(sep*2), c, sep); district(r+(sep*2),c+sep, sep); district(r+(sep*2),c+(sep*2), sep);//왼쪽 가운데 오른쪽, 세번째 라인


    }

    static boolean isPossible(int r, int c, int size){// 전부 같은 요소라면 true, 다른 거 섞여있으면 false
        int value = paper[r][c];
        for(int i=r; i<r+size; i++){
            for(int j=c; j<c+size; j++){
                if(value!=paper[i][j]){
                    return false;
                }
            }
        }
        if(value==-1)   m_count++;	//전부 -1이면 
        else if(value==0)   z_count++;	//전부 0이면
        else    p_count++;	//전부 1이면
        return true;
    }

}

