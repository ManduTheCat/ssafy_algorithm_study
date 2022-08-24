package week3.countingPaper1789;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int minusCount = 0, zeroCount = 0, oneCount = 0;

    private static void CuttingWithSize(int[][] paper, int size, int Y, int X) {

        int temp = paper[Y][X];
        boolean flag = false;
        for(int y = Y; y < Y + size; ++y) {
            for(int x = X; x < X + size; ++x) {
                if(paper[y][x] != temp) {
                    flag = true;
                    break;
                }
            }
            if(flag)
                break;
        }

        if(!flag) {
            if(temp == 1) {
                oneCount++;
            } else if(temp == 0) {
                zeroCount++;
            } else if(temp == -1) {
                minusCount++;
            }
            return;
        }

        CuttingWithSize(paper, size / 3, Y, X);
        CuttingWithSize(paper, size / 3, Y, X + size / 3);
        CuttingWithSize(paper, size / 3, Y, X + size * 2 / 3);
        CuttingWithSize(paper, size / 3, Y + size / 3, X);
        CuttingWithSize(paper, size / 3, Y + size / 3, X + size / 3);
        CuttingWithSize(paper, size / 3, Y + size / 3, X + size * 2 / 3);
        CuttingWithSize(paper, size / 3, Y + size * 2 / 3, X);
        CuttingWithSize(paper, size / 3, Y + size * 2 / 3, X + size / 3);
        CuttingWithSize(paper, size / 3, Y + size * 2 / 3, X + size * 2 / 3);
    }
    private static void Cutting(int[][] paper){

        int temp = paper[0][0];
        boolean flag = false;
        for(int y = 0; y < paper.length; ++y) {
            for(int x = 0; x < paper[y].length; ++x) {
                if(paper[y][x] != temp) {
                    flag = true;
                    break;
                }
            }
            if (flag)
                break;
        }

        if(!flag) {
            if(temp == 1) {
                oneCount++;
            } else if(temp == 0) {
                zeroCount++;
            } else if(temp == -1) {
                minusCount++;
            }
            return;
        }


        int[][] p1 = new int[paper.length / 3][paper[0].length / 3];
        int[][] p2 = new int[paper.length / 3][paper[0].length / 3];
        int[][] p3 = new int[paper.length / 3][paper[0].length / 3];
        int[][] p4 = new int[paper.length / 3][paper[0].length / 3];
        int[][] p5 = new int[paper.length / 3][paper[0].length / 3];
        int[][] p6 = new int[paper.length / 3][paper[0].length / 3];
        int[][] p7 = new int[paper.length / 3][paper[0].length / 3];
        int[][] p8 = new int[paper.length / 3][paper[0].length / 3];
        int[][] p9 = new int[paper.length / 3][paper[0].length / 3];

        for(int y = 0; y < paper.length; ++y) {
            for(int x = 0; x < paper[y].length; ++x) {
                if(x < paper[0].length / 3 && y < paper.length / 3){
                    p1[y][x] = paper[y][x];
                } else if(x >= paper[0].length / 3 && x < paper[0].length * 2 / 3 && y < paper.length / 3) {
                    p2[y][x -  paper[0].length / 3] = paper[y][x];
                } else if (x >= paper[0].length * 2 / 3 && x < paper[0].length && y < paper.length / 3) {
                    p3[y][x - paper[0].length * 2 / 3] = paper[y][x];
                } else if (x < paper[0].length / 3 && y >= paper.length / 3 && y < paper.length * 2 / 3) {
                    p4[y - paper.length / 3][x] = paper[y][x];
                } else if(x >= paper[0].length / 3 && x < paper[0].length * 2 / 3 && y >= paper.length / 3 && y < paper.length * 2 / 3){
                    p5[y - paper.length / 3][x -  paper[0].length / 3] = paper[y][x];
                } else if(x >= paper[0].length * 2 / 3 && x < paper[0].length && y >= paper.length / 3 && y < paper.length * 2 / 3) {
                    p6[y - paper.length / 3][x - paper[0].length * 2 / 3] = paper[y][x];
                } else if (x < paper[0].length / 3 && y >= paper.length * 2 / 3) {
                    p7[y - paper.length * 2 / 3][x] = paper[y][x];
                } else if (x >= paper[0].length / 3 && x < paper[0].length * 2 / 3 && y >= paper.length * 2 / 3) {
                    p8[y - paper.length * 2 / 3][x - paper[0].length / 3] = paper[y][x];
                } else {
                    p9[y - paper.length * 2 / 3][x - paper.length * 2 / 3] = paper[y][x];
                }
            }
        }

        Cutting(p1);
        Cutting(p2);
        Cutting(p3);
        Cutting(p4);
        Cutting(p5);
        Cutting(p6);
        Cutting(p7);
        Cutting(p8);
        Cutting(p9);

    }




    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int[][] paper = new int[N][N];
        for(int n1 = 0; n1 < N; ++n1){
            st = new StringTokenizer(br.readLine());
            for(int n2 = 0; n2 < N; ++n2) {
                paper[n1][n2] = Integer.parseInt(st.nextToken());
            }
        }

        //Cutting(paper);
        CuttingWithSize(paper, N, 0, 0);
        System.out.println(minusCount);
        System.out.println(zeroCount);
        System.out.println(oneCount);

    }
}

