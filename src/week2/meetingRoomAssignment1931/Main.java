import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class meeting implements Comparable<meeting>{	//처음과 시작을 저장하는 객체
        int start;
        int end;
        public meeting(int start, int end){
            this.start = start;
            this.end = end;
        }
        @Override
        public int compareTo(meeting o) {	//끝나는 시간으로 정렬, 끝나는 시간 같을 시 시작시간으로 정렬
            if(this.end==o.end) return this.start-o.start;
            return this.end-o.end;
            
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        meeting[] arr = new meeting[N];
        StringTokenizer st;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            arr[i] = new meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(arr);
        int count=1;
        meeting now = arr[0];
        for(int i=1; i<arr.length; i++){	//객체 배열의 맨 앞부터 끝나는 시간과 시작하는 시간을 비교하며 카운트
            if(now.end<=arr[i].start){
                count++;
                now = arr[i];
                continue;
            }
        }
        
        System.out.println(count);

    }
}