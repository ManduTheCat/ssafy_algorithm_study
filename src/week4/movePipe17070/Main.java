package week4.movePipe17070;

import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n+1][n+1];

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][][] dp = new int[n+1][n+1][3];
        dp[1][2][0] = 1;

        for(int i = 1; i <= n; i++){
            for(int j = 3; j <= n; j++){
                if(map[i][j] == 0) {
                    if (checkLeft(i, j)) dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][2];
                    if (checkUp(i, j)) dp[i][j][1] = dp[i - 1][j][1] + dp[i - 1][j][2];
                    if (checkCross(i, j)) dp[i][j][2] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
                }
            }
        }

        int ans = dp[n][n][0] + dp[n][n][1] + dp[n][n][2];
        System.out.println(ans);
    }

    public static boolean checkLeft(int x, int y){
        return y-1 > 0;
    }

    public static boolean checkUp(int x, int y){
        return x-1 > 0;
    }

    public static boolean checkCross(int x, int y){
        return checkLeft(x,y) && checkUp(x,y) && map[x][y-1] == 0 && map[x-1][y] == 0;
    }

}