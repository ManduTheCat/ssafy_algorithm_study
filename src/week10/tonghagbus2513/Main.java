package week10.tonghagbus2513;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


class Bus {
    int maxStudent;
    int currStudent;

    public Bus(int maxStudent, int currStudent) {
        this.maxStudent = maxStudent;
        this.currStudent = currStudent;
    }

}

class AptPoint implements Comparable<AptPoint> {
    int studentNum;
    int dist;
    int point;

    public AptPoint(int studentNum, int dist, int point) {
        this.studentNum = studentNum;
        this.dist = dist;
        this.point = point;
    }

    @Override
    public int compareTo(AptPoint o) {
        return o.dist - this.dist;
    }
}

public class Main {
    static int N, K, S;
    static PriorityQueue<AptPoint> left;
    static PriorityQueue<AptPoint> right;
    static Bus bus;

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("resources/study/week10/BJ2513/input2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 지점수
        K = Integer.parseInt(st.nextToken()); // 인원수
        S = Integer.parseInt(st.nextToken()); // 학교위치
        left = new PriorityQueue<>();
        right = new PriorityQueue<>();
        bus = new Bus(K, 0);
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            int point = Integer.parseInt(st.nextToken());
            int student = Integer.parseInt(st.nextToken());
            int dist = Math.abs(S - point);
            if (point < S) {
                // 왼쪽에 있다
                left.offer(new AptPoint(student, dist, point));
            } else {
                // 오른쪽에 있다
                right.offer(new AptPoint(student, dist, point));
            }
        }

        int lDIst = countDist(left);
        int RDIst = countDist(right);
        System.out.println((lDIst + RDIst));


    }

    private static int countDist(PriorityQueue<AptPoint> pq) {
        int totalDist = 0;
        while (!pq.isEmpty()) {
            AptPoint curStop = pq.poll();
            int repeatCount = curStop.studentNum / bus.maxStudent; // 반복해야하는 횟수
            if (curStop.studentNum % bus.maxStudent > 0) repeatCount++; // 나머지가 있다면 한번더 올수 있는거다
            // 태울수 있는 버스인원 = 왕복하면서 태운 학생수 - 현재 정류장인원
            // 만약 10 인데 4인버스라면 3번간다 (12) - 현재 정류장인웡 = 버스내의 승객은 2명더태울수 있다.
            int remainStudent = bus.maxStudent * repeatCount - curStop.studentNum;
            while (!pq.isEmpty()) {
                // 다음 정류장을 돌면서 더태울수 있는지 탐색한다.
                AptPoint next = pq.poll();
                // 태울수 있는 인원수가 더많으면
                if (next.studentNum <= remainStudent) {
                    //버스에 태운다
                    remainStudent -= next.studentNum;
                } else {
                    // 태울수 있는 인원이 다음 역보다 적으면
                    // 남은 승객수만큼만 태우고 다시온다
                    next.studentNum -= remainStudent;
                    pq.add(next);
                    break;
                }
            }
            totalDist += (2 * repeatCount * curStop.dist);
        }
        return totalDist;
    }


}
