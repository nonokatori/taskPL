import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные: ");

        String [] scanStr = scanner.nextLine().split(" ");
        if (scanStr.length != 2) {
            System.out.println("Неверное количество входных данных");
            return;
        }
        Task4 task4 = new Task4();
        task4.equalsString(scanStr[0], scanStr[1]);
    }
}

class Task4 {

    public String equalsString (String a, String b) {
        if (a == null || b == null) return "KO";
        if (a.equals(b)) {
            return "OK";
        }

        StringBuilder stringBuilder = new StringBuilder("\\w{0,}");

        if(b.contains("*")) {
            b = b.replaceAll("^ +| +$|(\\*)+", "$1");
            String[] strings = b.split("\\*");
            System.out.println(Arrays.toString(strings));

            for (int i = 0; i < strings.length; i++) {
                if (strings[i].equals("")) continue;
                stringBuilder.append("("+strings[i]+")").append("\\w{0,}");
            }
            System.out.println(stringBuilder.toString());
        }
        Pattern pattern = Pattern.compile(stringBuilder.toString());
        Matcher matcher = pattern.matcher(a);

        return matcher.matches() ? "OK" : "KO";
    }

}
