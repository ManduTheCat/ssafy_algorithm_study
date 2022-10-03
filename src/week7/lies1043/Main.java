
import java.util.ArrayList;

import java.util.Scanner;


public class Baek1043 {
    static int[] parent;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        parent = new int[N + 1];
        //그룹 여부를 확인하는 parent배열 생성
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        int M = sc.nextInt();
        ArrayList<Integer> know = new ArrayList<>();
        ArrayList<Integer>[] parties = new ArrayList[M];
        int truth = sc.nextInt();
        for (int i = 0; i < truth; i++) {
            know.add(sc.nextInt());
        }
        int ans = M;
        for (int i = 0; i < M; i++) {
            int loop = sc.nextInt();
            parties[i] = new ArrayList<>();
            for (int j = 0; j < loop; j++) {
                parties[i].add(sc.nextInt());
            }
        }
        if (truth == 0) {
            System.out.println(M);
            return;
        }
        //진실 그룹을 만들어줌
        for (int i = 1; i < know.size(); i++) {
            union(know.get(i), know.get(i - 1));
        }
        //파티 참여자 중 진실 그룹에 속하는 사람이 있는지 확인
        for (int i = 0; i < parties.length; i++) {
            for (int j = 1; j < parties[i].size(); j++) {
                union(parties[i].get(j), parties[i].get(j - 1));
            }
        }
        for (int i = 0; i < parties.length; i++) {
            for (int j = 0; j < parties[i].size(); j++) {
                if (isSameParent(parties[i].get(j), know.get(0))) {
                    ans--;
                    break;
                }
            }
        }



        System.out.println(ans);

    }

    static int findParent(int x) {
        if (parent[x] == x)
            return x;
        else
            return parent[x] = findParent(parent[x]);
    }
    
    static void union(int x, int y) {
        x = findParent(x);
        y = findParent(y);

        if (x != y) {
            parent[y] = x;
        }
    }
    
    static boolean isSameParent(int x, int y) {
        x = findParent(x);
        y = findParent(y);
        if (x != y)
            return false;
        
        return true;
    }

    }