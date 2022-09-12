package week5.ship1092;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 17896kb	352ms
 */
public class Main {
    static int N;
    static int M;
    static Stack<Integer> originCraneStack;
    static int time = 0;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("resources/study/week5/BJ1092/input5.txt"));
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
        for (Integer el : tempCranes) {
            originCraneStack.push(el);
        }

        if (Collections.max(originCraneStack) < Collections.max(tempCargos)) {
            System.out.println(-1);
            System.exit(0);
        }

        while (!tempCargos.isEmpty()) {
            // 돌때마다 크래인 순히하기위해 스택을 새로 만들어준다
            Stack<Integer> roundStack = (Stack<Integer>) originCraneStack.clone();
            time++;
            int cargoIndex = 0;
            while (!roundStack.isEmpty()) {
                // 크래인 하나 뽑고
                int currCrane = roundStack.pop();
                // 처음부터 다시 비교하는게 아니라 마지막으로 제거한 값부터 이어서 비교한다.
                // 왜냐면 정렬되어 있기 떄문에 값이 작아진다.
                //끝에 도달하지 않았다면
                while (cargoIndex != tempCargos.size()){
                    if(currCrane >= tempCargos.get(cargoIndex)){
                        tempCargos.remove(cargoIndex);
                        break;
                    }
                    // 크래인보다 크다면 넘어간다.
                    else cargoIndex++;
                }
                // 기존코드 지우고 나서 처음부터 다시 비교한다
//                for (int i = 0, size = tempCargos.size(); i < size; i++) {
//                    if (currCrane >= tempCargos.get(i)) {
//                        tempCargos.remove(i);
//                        break;
//                    }
//                }

            }
        }
        System.out.println(time);

    }
}
