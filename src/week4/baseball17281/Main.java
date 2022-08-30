package week4.baseball17281;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int n, hitterIdx, ans;
    public static int[][] inning;
    public static int[] hitterOrder;
    public static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        inning = new int[n+1][10];

        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= 9; j++) {
                inning[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        hitterOrder = new int[10];
        visited = new boolean[10];
        hitterOrder[4] = 1;
        visited[1] = true;

        makeHitterOrder(1);

        System.out.println(ans);
    }

    public static void makeHitterOrder(int idx) {
        if(idx == 10) {
            hitterIdx = 1;
            int score = 0;
            for(int i = 1; i <= n; i++) {
                score += play(i);
            }

            ans = Math.max(ans, score);
            return;
        }

        for(int i = 1; i <= 9; i++) {
            if(!visited[i]) {
                visited[i] = true;
                hitterOrder[idx] = i;

                if(idx == 3) makeHitterOrder(idx+2);
                else makeHitterOrder(idx+1);

                visited[i] = false;
            }
        }
    }

    public static int play(int inningIdx) {
        int out = 0;
        int score = 0;
        boolean[] field = new boolean[4];

        while(out < 3) {
            int result = inning[inningIdx][hitterOrder[hitterIdx++]];
            if(hitterIdx == 10) hitterIdx = 1;

            switch(result){
                case 0:
                    out++;
                    break;
                case 1:
                    if(field[3]) score++;

                    field[3] = field[2];
                    field[2] = field[1];
                    field[1] = true;
                    break;
                case 2:
                    if(field[3]) score++;
                    if(field[2]) score++;

                    field[3] = field[1];
                    field[2] = true;
                    field[1] = false;
                    break;
                case 3:
                    if(field[3]) score++;
                    if(field[2]) score++;
                    if(field[1]) score++;

                    field[3] = true;
                    field[2] = false;
                    field[1] = false;
                    break;
                case 4:
                    if(field[3]) score++;
                    if(field[2]) score++;
                    if(field[1]) score++;
                    score++;

                    for(int i = 1; i <= 3; i++) field[i] = false;
                    break;
            }
        }
        return score;
    }

}

