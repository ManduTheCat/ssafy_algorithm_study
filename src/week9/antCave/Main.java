package week9.antCave;

import java.io.*;
import java.util.*;

public class Main {

    // 개미굴 class
    public static class Room implements Comparable<Room>{
        String food;  // 음식
        int depth;  // 깊이
        HashMap<String, Room> child = new HashMap<>();  // 자식 개미굴을 저장할 HashMap

        public Room(String food, int depth, HashMap<String, Room> child) {
            this.food = food;
            this.depth = depth;
            this.child = child;
        }

        @Override
        public int compareTo(Room o) {
            if(o.depth == this.depth) return this.food.compareTo(o.food);  // 깊이가 같다면 음식 이름 순
            return o.depth - this.depth;  // 깊이가 깊은 순
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        Room root = new Room("root", -1, new HashMap<>());  // 개미굴 입구 설정, 깊이는 -1로 설정

        while(n-->0){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            Room parent = root;  // 개미굴 입구부터 시작

            for(int i = 0; i < k; i++){
                String food = st.nextToken();
                Room now = new Room(food, i, new HashMap<>());  // 음식이름과 깊이 설정

                if(!parent.child.containsKey(food)){  // parent 개미굴의 자식에 동일한 음식 이름이 없다면
                    parent.child.put(food, now);  // 추가
                    parent = now;  // parent 변경 후 다음 자식 추가
                } else{  // parent 개미굴의 자식에 동일한 음식 이름이 있다면
                    parent = parent.child.get(food);  // 추가하지 않고 parent를 이미 있는 음식 이름의 Room class로 변경
                }
            }
        }

        PriorityQueue<Room> pq = new PriorityQueue<>();
        pq.addAll(root.child.values());  // 우선순위 큐에 개미굴 입구의 자식들을 모두 넣음

        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()){
            Room room = pq.poll();  // 우선순위 큐에서 하나씩 뽑아서
            sb.append("--".repeat(room.depth)).append(room.food).append("\n");  // "--"를 각 Room class의 깊이만큼 반복 후, 음식 이름 출력

            pq.addAll(room.child.values());  // 그 다음, 해당 Room의 자식 객체를 우선순위 큐에 추가
        }

        System.out.println(sb.toString());
    }

}
