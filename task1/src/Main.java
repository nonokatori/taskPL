import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные: ");

        String [] scanStr = scanner.nextLine().split(" ");
        Task1 task1 = new Task1();
        if (!task1.isValidData(scanStr)) {
            System.out.println("Неверные входные данные");
            return;
        }
        if (scanStr.length == 2) {
            System.out.println("Результат перевода из 10 в "
                    + scanStr[1] + ": "+ task1.itoBase(Integer.parseInt(scanStr[0]), scanStr[1]));
        } else {
            System.out.println("Результат конвертации: " + task1.itoBase(scanStr[0], scanStr[1], scanStr[2]));
        }
    }
}

class Task1 {

    public boolean isValidData(String [] strings) {
        int a = 0;
        if (strings.length > 3 || strings.length < 2) return false;
        if (strings.length == 2) {
            try {
                Integer.parseInt(strings[0]);
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            for (int i = 0; i < strings[0].length(); i++) {
                a = strings[1].indexOf(strings[0].charAt(i));
                if (a == -1) {
                    return false;
                }
            }

        }

        return true;
    }
    public String itoBase(int nb, String base) {
        StringBuilder result = new StringBuilder();
        int l = base.length();
        while ( nb != 0) {
            result.append(base.charAt(nb%l));
            nb = nb/l;
        }

        return result.reverse().toString();
    }

    public String itoBase(String nb, String baseSrc, String baseDst) {

        baseDst = baseDst.toLowerCase();
        baseSrc = baseSrc.toLowerCase();
        nb = nb.toLowerCase();
        int a = inDec(nb, baseSrc);
        System.out.println(a);
        return itoBase(a, baseDst);
    }

    public int inDec (String nb, String baseSrc) {
        int a = 0;
        char [] chars = nb.toCharArray();

        int l = baseSrc.length();
        for (int i = 0; i < chars.length; i++) {

            a = a + (baseSrc.indexOf(chars[i]))*(int) Math.pow(l, chars.length - 1 -i);
        }

        return a;
    }
}