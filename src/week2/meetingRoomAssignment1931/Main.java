package week2.meetingRoomAssignment1931;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_14888_연사자끼워넣기 {

    static int minValue = Integer.MAX_VALUE;
    static int maxValue = Integer.MIN_VALUE;

    private static void operPermu(int cnt, int flag, int[] operators, int[] num, int[] numbers) {

        if (cnt == operators.length) {
            int[] temp = Arrays.copyOf(numbers, numbers.length);

            for(int o = 0; o < operators.length; ++o) {
                if (num[o] == 1) {
                    temp[o + 1] += temp[o]; 
                } else if (num[o] == 2) {
                    temp[o+1] = temp[o] - temp[o+1];
                } else if (num[o] == 3) {
                    temp[o+1] *= temp[o];
                } else if (num[o] == 4) {
                    temp[o+1] = temp[o] / temp[o+1];
                }
            }
            maxValue = Math.max(maxValue, temp[temp.length - 1]);
            minValue = Math.min(minValue, temp[temp.length - 1]);
            return;
        }

        for(int i = 0; i < operators.length; ++i) {
            if( (flag & 1 << i) != 0) continue;
            num[cnt] = operators[i];
            operPermu(cnt + 1, flag | 1 << i, operators, num, numbers);
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] numbers = new int[N];
        int[] operators = new int[N-1];

        st = new StringTokenizer(br.readLine());
        for(int n = 0; n < N; ++n) {
            numbers[n] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int oCount = 1, index = 0;;
        for(int o = 0; o < 4; ++o) {
            int temp = Integer.parseInt(st.nextToken());
            for(int t = 0; t < temp; ++t) {
                operators[index++] = oCount;
            }
            oCount++;
        }

        int[] num = new int[N-1];
        operPermu(0, 0, operators, num, numbers);

        System.out.println(maxValue);
        System.out.println(minValue);
    }
}

