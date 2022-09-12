package week4.movePipe17070;

import java.io.*;
import java.util.*;

public class Main {

    public static int n, ans;
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

        pipeline(0, 1, 2);
        System.out.println(ans);
    }

    public static void pipeline(int shape, int x, int y){
        if(x == n && y == n){
            ans++;
            return;
        }

        if(shape == 0){
            if(checkRight(x, y)) pipeline(0, x, y+1);
            if(checkCross(x, y)) pipeline(2, x+1, y+1);
        }
        else if(shape == 1){
            if(checkDown(x, y)) pipeline(1, x+1, y);
            if(checkCross(x, y)) pipeline(2, x+1, y+1);
        }
        else{
            if(checkRight(x, y)) pipeline(0, x, y+1);
            if(checkDown(x, y)) pipeline(1, x+1, y);
            if(checkCross(x, y)) pipeline(2, x+1, y+1);
        }
    }

    public static boolean checkRight(int x, int y){
        if(!isRange(x, y+1)) return false;
        return map[x][y+1] == 0;
    }

    public static boolean checkDown(int x, int y){
        if(!isRange(x+1, y)) return false;
        return map[x+1][y] == 0;
    }

    public static boolean checkCross(int x, int y){
        if(!isRange(x+1, y+1) || !checkRight(x, y) || !checkDown(x, y)) return false;
        return map[x+1][y+1] == 0;
    }

    public static boolean isRange(int x, int y){
        return x > 0 && x <= n && y > 0 && y <= n;
    }

}
