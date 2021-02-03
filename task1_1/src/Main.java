import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Введите путь к файлу:");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        long[] longsArr;
        Task11 task11 = null;
        try (BufferedReader objReader = new BufferedReader(new FileReader(path));
             LineNumberReader reader = new LineNumberReader(new FileReader(path))){
            String strCurrentLine;
            int i = 0;
            while ((reader.readLine()) != null);
            longsArr = new long[reader.getLineNumber()];
            while ((strCurrentLine = objReader.readLine()) != null) {
                longsArr[i] = Long.parseLong(strCurrentLine);
                i++;
            }
            task11 = new Task11(longsArr);
        } catch (FileNotFoundException e) {
            System.out.println("Неверный путь");
            return;
        } catch (IOException e) {
            System.out.println("тут ошибка, там ошибка, сям ошибка");
            return;
        }

        System.out.println("Результирующий массив: " +Arrays.toString(task11.elementArray()));
    }
}

class Task11 {
    private long[] longs;

    public Task11(long[] longsArr) {
        longs = longsArr;
    }

    private long[] sortArray(long[] arr) {
        return Arrays.stream(arr).sorted().toArray();
    }
    private long average(long[] array) {
        return (long) Arrays.stream(array).average().getAsDouble();
    }
    public long[] elementArray() {

        long[] array = sortArray(longs);
        final long aver =  average(array);
        final long perc90 = (long) (array[array.length - 1]*0.9);

        return Arrays.stream(array).filter((x) -> (x>aver) && (x<perc90)).toArray();
    }

}