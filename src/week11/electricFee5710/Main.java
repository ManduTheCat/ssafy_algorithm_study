package week11.electricFee5710;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // 이웃의 사용량과 사용량을 합쳤을때 내야하는 요금
			int b = Integer.parseInt(st.nextToken()); // 이웃의 전기 요금과의 차이 (절댓값)
			if(a==0 || b==0) return;
			int total = calWatt(a); // wat 계산해서 총 wat구하기
			// 상근이는 항상 더 작은 요금이니 A의 요금/2 범위 안에서 이분 탐색 시작
			int left=0;
			int rigth = total/2;
			
			while (left<=rigth) {
				int mid =(left + rigth)/2;
				
				int sangPrice = calPrice(mid); // 상근이 전기요금
				int negPrice = calPrice(total-mid); // 이웃 전기요금 (전체요금- 상근이 요금)
				
				int dif = Math.abs(sangPrice-negPrice); // 상근이 전기요금 - 이웃전기요금 (절댓값)
				
				if(dif < b) { // 계산해서 구한 절댓값이 입력된 절댓값보다 작을 경우 right 범위를 -1 옮겨주기
					rigth = mid-1;
				}else if(dif>b) { // 계산해서 구한 절댓값이 입력된 절댓값보다 큰 경우 letf 범위를 -1 옮겨주기
					left = mid+1;
				}else { // 계산해서 구한 절댓값과 입력된 절댓값이 같을 경우 정답 
					System.out.println(calPrice(mid));
					break;
				}
				
			}
		}
		

	}
	// watt 계산
	static int calWatt(int fee) {
		if(fee <=200) {
			return fee/2;
		}else if(fee <=29900) {
			return (fee-200)/3 +100;
		}else if(fee <=4979900) {
			return (fee-29900)/5 +10000;
		}else{
			return (fee-4979900)/7 +1000000;
		}
	}
	// 요금 계산
	static int calPrice(int watt) {
		if(watt>=1 && watt <=100) {
			return watt*2;
		}else if(watt>=101 && watt <=10000) {
			return 200+(watt-100)*3;
		}else if(watt>=10000 && watt <=1000000) {
			return 200+29700+(watt-10000)*5;
		}else{
			return 200+29700+4950000+(watt-1000000)*7;
		}
	}
	

}
