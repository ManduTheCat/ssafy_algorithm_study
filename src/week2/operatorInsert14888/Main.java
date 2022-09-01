package week2.operatorInsert14888;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    // 연산자를 기록한 배열
    public static int[] operatorInputList = new int[4];
    // 연산자들의 경우의 수를 담아놓는 배열
    public static int[] propbsIndexList;
    // 입력의 경우의수를 돌리기 위해 연산자 수와 종류로 기록한 배열 입력: 2 1 1 1=> propbsIndexList:[0 0 1 2 3]
    public static int[] ableOperatorList;
    public static int n;
    public static int[] numList;
    // 연산 결과 담아놓는 arraylist
    public static ArrayList<Integer> resList = new ArrayList<>();

    /**
     * 풀이 순서
     * 1. candidateOperator() 주어진 입력을 순열 을 적용 할수 있게 변형
     *     ex) 입력 2 1 1 1=> [0 0 1 2 3]   0="+" 1="-" 2="*" 3="/"
     * 2. perm() 순열 구하면서 하나 구해질때마다
     * 3. calculate() 계산
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("resources/study/week2/bk14888/input1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        numList = new int[n];
        StringTokenizer numTk = new StringTokenizer(br.readLine());
        for (int numIdx = 0; numIdx < n; numIdx++) {
            numList[numIdx] = Integer.parseInt(numTk.nextToken());
        }

        StringTokenizer oTk = new StringTokenizer(br.readLine());
        for (int oIdx = 0; oIdx < 4; oIdx++) {
            operatorInputList[oIdx] = Integer.parseInt(oTk.nextToken());
        }

        candidateOperator();
        propbsIndexList = new int[n - 1];
        //경우의 수 구함
        perm(0, 0);
        System.out.printf("%d\n", Collections.max(resList));// 최대 출력
        System.out.printf("%d", Collections.min(resList));// 최소 출력

    }

    public static void calculate() {

        int res = numList[0];
        int index = 1;
        for (int operator : propbsIndexList) {
            switch (operator) {
                case 0:
                    res += numList[index++];
                    break;
                case 1:
                    res -= numList[index++];
                    break;
                case 2:
                    res *= numList[index++];
                    break;
                case 3:
                    res /= numList[index++];
                    break;
            }
        }
        resList.add(res);
    }

    /**
     * 입력을 갯수와 연산자 인덱스를 반영한 ableOperatorList 만드는 함수
     */
    private static void candidateOperator() {
        int countOper = 0;
        for (int op : operatorInputList) {
            countOper += op;
        }
        ableOperatorList = new int[countOper];
        int idx = 0;
        for (int i = 0; i < operatorInputList.length; i++) {
            int value = operatorInputList[i];
            for (int re = 0; re < value; re++) {
                ableOperatorList[idx++] = i;
            }
        }
    }

    /**
     * 인덱스 순열의 경우의수를 구한다
     */
    public static void perm(int depth, int flag) {
        if (depth == n - 1) {
            calculate();
            return;
        }
        for (int i = 0; i < n - 1; i++) {
            // 비트 마스킹 0이 아니면 이건쓰고 있다
            // 비트 마스킹 0이면 사용가능하다
            if ((flag & 1 << i) == 0) {
                propbsIndexList[depth] = ableOperatorList[i];
                perm(depth + 1, flag | 1 << i);
            }
        }
    }
}
