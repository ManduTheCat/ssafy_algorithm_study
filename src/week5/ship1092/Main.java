import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * Baek1092
 */
public class Baek1092 {

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    Integer[] cranes = new Integer[N];
    StringTokenizer st;
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      cranes[i] = Integer.parseInt(st.nextToken());
    }
    Arrays.sort(cranes,Collections.reverseOrder());   //크레인을 내림차순으로 정렬
    int M = Integer.parseInt(br.readLine());
    ArrayList<Integer >boxes = new ArrayList<>();
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i<M; i++){
      boxes.add(Integer.parseInt(st.nextToken()));
    }
    Collections.sort(boxes, Collections.reverseOrder());  //박스를 내림차순으로 정렬
    if (boxes.get(0) > cranes[0]) { //크레인의 용량보다 큰 박스가 있다면 프로그램 종료
      System.out.println(-1);
      return;
    }
    int time = 0;
    while (!boxes.isEmpty()) { //박스가 전부 빌때까지
      time++;
      int idx = 0;
      int i = 0;
      while (i < N) {
        if (boxes.size() == idx) {  //박스를 처리할수 있는 크레인은 앞의 인덱스에 있으므로 브레이크
          break;
        }
        else if (boxes.get(idx) <= cranes[i]) { //현재 인덱스의 크레인에서 처리할 수 있는 크기의 박스라면 제거
          boxes.remove(idx);
          i++;

        }
        else  //현재 크레인에서 해결할 수 없으면 다음 박스를 살핀다.
          idx++;
      }
    }

    System.out.println(time);
  }
  
}