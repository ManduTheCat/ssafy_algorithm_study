package week6.jumpingLog11497;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; ++t) {

            int N = Integer.parseInt(br.readLine());
            ArrayList<Integer> log = new ArrayList<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int n = 0; n < N; ++n){
                log.add(Integer.parseInt(st.nextToken()));
            }

            Collections.sort(log);

            int result = 0;
            for(int l = log.size() - 1; l >= 2; --l) {
                result = Math.max(result, log.get(l) - log.get(l-2));
            }

            System.out.println(result);


        }
    }
}
