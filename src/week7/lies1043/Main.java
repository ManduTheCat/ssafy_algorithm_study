import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        HashMap<Integer, Integer> truth = new HashMap<>(N+1);
        ArrayList<ArrayList<Integer>> parties = new ArrayList<>();

        for(int n = 0; n < N + 1; ++n){
            truth.put(n, 1);
        }

        for(int t = 0; t < T; ++t) {
            truth.put(Integer.parseInt(st.nextToken()), -1);
        }

        LinkedList<ArrayList<Integer>> check = new LinkedList<>();
        boolean[] partVisit = new boolean[M+1];

        for(int m = 0; m < M; ++m) {
            st = new StringTokenizer(br.readLine());
            T = Integer.parseInt(st.nextToken());
            ArrayList<Integer> temp = new ArrayList<>();

            boolean flag = false;
            for(int t = 0; t < T; ++t) {
                temp.add(Integer.parseInt(st.nextToken()));
                if(!flag && truth.get(temp.get(t)) == -1) {
                    flag = true;
                }
            }
            if(flag) {
                check.add(temp);
                partVisit[m] = true;
            }

            parties.add(temp);
        }

        while(!check.isEmpty()) {

            int size = check.size();

            for(int s = 0; s < size; ++s) {

                ArrayList<Integer> temp = check.pollFirst();

                for(int t = 0; t < temp.size(); ++t) {
                    truth.put(temp.get(t), -1);
                }

                for(int p = 0; p < parties.size(); ++p) {
                    for(int t = 0; t < parties.get(p).size(); ++t) {
                        if(!partVisit[p] && truth.get(parties.get(p).get(t)) == -1) {
                            check.add(parties.get(p));
                            partVisit[p] = true;
                        }
                    }
                }
            }
        }

        int answer = 0;
        for(int p = 0; p < parties.size(); ++p) {
            boolean flag = true;
            for(int t = 0; t < parties.get(p).size(); ++t) {
                if(truth.get(parties.get(p).get(t)) == -1) {
                    flag = false;
                    break;
                }
            }
            if(flag)
                answer++;
        }

        System.out.println(answer);


    }
}
