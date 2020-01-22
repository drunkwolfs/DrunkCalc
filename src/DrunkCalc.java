import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

interface calc {
    public int exec(int a1, int a2);
}

public class DrunkCalc {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String data = reader.readLine().replace(" ", "");

        if (data.length() < 3 || data.length() > 9)
            throw new Exception("дурак чо вводишь");

        Map<String, calc> map = new HashMap<String, calc>();
        map.put("\\+", (a, b) -> a + b);
        map.put("-", (a, b) -> a - b);
        map.put("\\*", (a, b) -> a * b);
        map.put("\\\\", (a, b) -> a / b);
        map.put("/", (a, b) -> a / b);

        Map<String, Integer> numMap = new HashMap<String, Integer>();
        numMap.put("I", 1);
        numMap.put("II", 2);
        numMap.put("III", 3);
        numMap.put("IV", 4);
        numMap.put("V", 5);
        numMap.put("VI", 6);
        numMap.put("VII", 7);
        numMap.put("VIII", 8);
        numMap.put("IX", 9);
        numMap.put("X", 10);


        for (Map.Entry<String, calc> el : map.entrySet()) {
            String[] num = data.split(el.getKey());
            if (num.length == 2) {
                if (Character.isDigit(num[0].charAt(0)) & Character.isDigit(num[1].charAt(0))) {
                    int a = Integer.parseInt(num[0]);
                    int b = Integer.parseInt(num[1]);
                    if (a < 1 || a > 10 || b < 1 || b > 10)
                        throw new Exception("дурак чо вводишь");
                    int res = el.getValue().exec(a, b);
                    System.out.println(res);
                    return;
                }
                if (Character.isLetter(num[0].charAt(0)) & Character.isLetter(num[1].charAt(0))) {
                    Integer a = numMap.get(num[0]);
                    Integer b = numMap.get(num[1]);
                    if (a == null || b == null)
                        throw new Exception("дурак чо вводишь");
                    int res = el.getValue().exec(a, b);
                    if(res==0)
                        return;
                    if(res<0) {
                        System.out.println("-" + RomanNumeral.arabicToRoman(Math.abs(res)));
                        return;
                    }
                    System.out.println(RomanNumeral.arabicToRoman(res));
                    return;
                }
            }
        }
        throw new Exception("дурак чо вводишь");
    }


}
