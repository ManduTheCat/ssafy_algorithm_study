
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

    static int max = Integer.MIN_VALUE;

    public static void oper(int index, int sum, ArrayList<Integer> number, ArrayList<Character> expression) {

        if(index == expression.size()) {
            max = Math.max(max, sum);
            return;
        }

        oper(index + 1, calc(sum, number.get(index + 1), expression.get(index)), number, expression);

        if(index <= expression.size() -2) {
            oper(index + 2, calc(sum, calc(number.get(index + 1), number.get(index + 2), expression.get(index + 1)), expression.get(index)), number, expression);

        }
    }

    public static int calc(int x1, int x2, char express) {
        if(express == '+') {
            return x1 + x2;
        } else if(express == '-') {
            return x1 - x2;
        } else  {
            return x1 * x2;
        }
    }

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> number = new ArrayList<>();
        ArrayList<Character> expression = new ArrayList<>();
        String st = br.readLine();
        for(int s = 0; s < st.length(); ++s) {
            if(s % 2 == 0) {
                number.add(st.charAt(s) -'0');
            }
            else expression.add(st.charAt(s));
        }

        oper(0, number.get(0), number, expression);
        System.out.println(max);

    }
}
