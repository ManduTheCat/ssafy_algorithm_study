package week5.ship1092;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int N;
    static int M;
    static Stack<Integer> originCraneStack;
    static int time = 0;
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("resources/study/week5/BJ1092/input5.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        originCraneStack = new Stack<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> tempCranes = new ArrayList<>();
        for (int n = 0; n < N; n++) {
            tempCranes.add(Integer.parseInt(st.nextToken()));
        }
        ArrayList<Integer> tempCargos = new ArrayList<>();
        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int m = 0; m < M; m++) {
            tempCargos.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(tempCranes);
        Collections.sort(tempCargos, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        for(Integer el :tempCranes){
            originCraneStack.push(el);
        }

        if(Collections.max(originCraneStack) < Collections.max(tempCargos)){
            System.out.println(-1);
            System.exit(0);
        }
        while (!tempCargos.isEmpty()){
            Stack<Integer> roundStack = (Stack<Integer>) originCraneStack.clone();
            time++;
            while (!roundStack.isEmpty()){
                int currCrane = roundStack.pop();
                for(int i = 0, size = tempCargos.size(); i < size; i++){
                    if(currCrane >= tempCargos.get(i)){
                        tempCargos.remove(i);
                        break;
                    }
                }
            }
        }
        System.out.println(time);

    }
}
