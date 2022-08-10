package helloWorld2557;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n  = Integer.parseInt(bf.readLine());
        for(int i =0 ; i < n; i++){
            Stack <String> commandStack = new Stack<String>();
            StringBuffer sb = new StringBuffer(bf.readLine());
            String reverseInput = sb.reverse().toString();
            Arrays.stream(reverseInput.split("")).forEach(commandStack::push);

            actionEdit(commandStack);

        }


    }
    public static void actionEdit(Stack<String> stack){
        ArrayList<String> output = new ArrayList<>();
        int curr = 0;
        System.out.println(output.size());
        while(!stack.empty()){
           //길이를 체

        }
    }
     // 넘어가는지 검사하는 함수
}
