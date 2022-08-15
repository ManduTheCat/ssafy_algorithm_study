package week2.meetingRoomAssignment1931;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 끝나는 시간 기준 정렬 -> find() 함수로 가능한 다음회의를 찾음 -> count 출력
 */
public class Main {
    static int n;
    static int result;
    static int[][] roomList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        roomList = new int[n][2];
        for (int i = 0; i < n; i++) {
            int[] time = new int[2];
            StringTokenizer st = new StringTokenizer(br.readLine());
            time[0] = Integer.parseInt(st.nextToken());
            time[1] = Integer.parseInt(st.nextToken());
            roomList[i] = time; // time[0] 은 시작 시간  time [1] 은 끝나는시간
        }
        // 정렬 회의 끝나는시간 기준으로 오름 차순 정렬하되 시작과 끝시간이 같은 회의 경우 시작 시간을 기준으로 오름차순
        Arrays.sort(roomList, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return Integer.compare(o1[0], o2[0]);
            }
            return Integer.compare(o1[1], o2[1]);
        });
        find(0, 0, 0);
        System.out.println(result);
    }

    /**
     * 끝나는 시간 과 시작시간 을 비교하면 기준이 되는 회의 를 갈아치우며 count 하는 재귀 함수
     * @param curTime 기준이 되는 시작시간
     * @param count 출력할 회의 count
     * @param index 루프를 돌때 전부를 도는게 아니라 기준 회의의 다음 회의부터 돌게 함
     */
    static void find(int curTime, int count, int index) {
        result = count;
        for (int i = index; i < n; i++) {
            if (curTime <= roomList[i][0]) {
                find(roomList[i][1], ++count, i + 1);
                break;
            }
        }
    }
}
