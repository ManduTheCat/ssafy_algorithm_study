package week3.countingPaper1789;

import java.io.*;
import java.util.*;

public class Main {

    public static int minus, zero, one;
    public static int[][] map;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int size = Integer.parseInt(br.readLine());
        map = new int[size][size];

        for(int i = 0; i < size; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < size; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        partition(0, 0, size);
        System.out.println(minus);
        System.out.println(zero);
        System.out.println(one);
    }

    public static void partition(int x, int y, int size){
        if(isOne(x, y, size)){
            if(map[x][y] == -1) minus++;
            else if(map[x][y] == 0) zero++;
            else one++;
            return;
        }

        int nSize = size/3;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                partition(x + i * nSize, y + j * nSize, nSize);
            }
        }
    }

    public static boolean isOne(int x, int y, int size){
        for(int i = x; i < x + size; i++){
            for(int j = y; j < y + size; j++){
                if(map[x][y] != map[i][j]) return false;
            }
        }
        return true;
    }
}
