package week8.BJ1034Lamp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{


    public static void main(String[] args)  throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        int N = Integer.parseInt(st.nextToken()); //행
        int M = Integer.parseInt(st.nextToken()); //열


        int [] offCount = new int[N]; // 그 행에 몇의 램프가 꺼져있는지 저장할 배열
        String [] line = new String[N]; // 그 행의 램프 상태 => ex )'1010110'

        for(int i = 0 ; i < N ; i++){
            String s = br.readLine();
            int zero = 0 ; // 꺼져있는 램프 개수
            for(int j = 0 ; j < M ; j++){
                if(s.charAt(j)=='0'){ //램프가 꺼져있다면
                    zero++;
                }
            }
            //행에 몇개의 램프가 꺼져있는지에 대해 저장한다.
            offCount [i]=zero;
            //해당 행의 문자열을 저장한다.
            //그 이유는 밑에 과정에서 한 행을 완성된 행(모든 램프가 켜져있는)으로 만들 수 있었을때 다른 행들이 현재 행과 똑같은 형태인지 파악하기 위함이다. -> 똑같다면 그 행은 완벽한 행으로 만들 수 있기때문
            line[i]=s;
        }

        int  K = Integer.parseInt(br.readLine()); // 스위치를 눌러야 하는 횟수

        int max = 0; //최소의 경우는 0

        /* 하나의 행에 대하여 완벽한행이 될 수 있다면 다른 행들중에서 같은 경우가 있는지 세어준다. -> 같은 행들도 완벽한 행일 수 밖에 없기때문 */
        for(int i = 0 ; i < N; i++){

            //첫번째로 ,꺼져있는 램프의 개수 , 즉 스위치를 눌러야하는 횟수가 K보다 크면 안된다. 문제 조건에서 K번을 누르라고 했기 때문
            //두번째로 , K-offCount[i] = 더 눌러야 하는 횟수 , 는 짝수여야한다.  짝수여야만 현재상태와 똑같이 만들 수 있기때문이다. 두 번 누르면 원래 상태인것처럼.
            if(offCount[i]<=K&&(K-offCount[i])%2==0){

                int equalCount = 1 ; // 현재 i은 완벽한 행이므로 초기값은 1

                for(int j = 0 ; j < N;j++){
                    if(i==j)continue; //자기 자신 탐색하는건 넘어간다.
                    if(line[i].equals(line[j])){ // i 행과 같은 상황의 행이 있다면 +1
                        equalCount++;
                    }
                }

                max=Math.max(max,equalCount); //최대값 갱신
            }


        }

        System.out.println(max); // 정답 출력

    }

}