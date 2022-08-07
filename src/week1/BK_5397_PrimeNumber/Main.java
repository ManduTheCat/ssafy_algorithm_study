package week1.BK_5397_PrimeNumber;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

public static void main(String[] args) throws Exception {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(bf.readLine());
    int M = Integer.parseInt(st.nextToken());
    int N = Integer.parseInt(st.nextToken());
    for(int i=M; i<=N; i++){
        isPrime(i);
    }

}
private static void isPrime(int num){	//소수인지 아닌지 판단하는 메서드
    if(num==1){		//전달 받은 숫자가 1이라면 소수가 아니므로 리턴
        return;
    }
    for(int i=2; i<=Math.sqrt(num); i++){	// 전달받은 숫자의 제곱근까지 나눔으로 소수인지 아닌지 판단
        if((num%i) == 0){
            return;
        }
    }
    System.out.println(num);		//소수라면 출력

}

}

