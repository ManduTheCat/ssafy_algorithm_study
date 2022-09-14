import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek11497 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());

    for (int tc = 1; tc <= T; tc++) {
      int N = Integer.parseInt(br.readLine());
      int max = Integer.MIN_VALUE;
      int[] arr = new int[N];
      int[] ans = new int[N];

      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i = 0; i < N; i++) {
        arr[i] = Integer.parseInt(st.nextToken());
      }
      Arrays.sort(arr);
      int left = 0;
      int right = N - 1;
      int idx = 0;
      while (left <= right) {
        if (right == left) {
          ans[left] = arr[idx];
          break;
        }
        ans[left] = arr[idx++];
        ans[right] = arr[idx++];
        left++;
        right--;
      }    
     
      for (int i = 0; i < N - 1; i++) {
        int temp = Math.abs(ans[i] - ans[i + 1]);
        max = Math.max(temp, max);
      }
      int temp = Math.abs(ans[0] - ans[N - 1]);
      max = Math.max(temp, max);
      System.out.println(max);
    }
  }
}
