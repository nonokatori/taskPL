import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите путь к файлу:");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();

        Task2 task2 = new Task2();
        String string = null;
        try (BufferedReader objReader = new BufferedReader(new FileReader(path))){
            string = objReader.readLine();
        } catch (IOException e) {
            System.out.println("тут ошибка, там ошибка, сям ошибка");
            return;
        }
        task2.createTriangles(string);

    }
}

class Task2 {

    private StringBuilder dataParseBuilder;
    private Triangle triangle1 = new Triangle();
    private Triangle triangle2 = new Triangle();

    public void createTriangles(String data) {
        dataParseBuilder = new StringBuilder(data);

        triangle1.createTriangle(parseTriangles());
        triangle2.createTriangle(parseTriangles());

        System.out.println(areSimilarTriangle() ? "Треугольники подобны" : "Треугольники не подобны" );
    }

    private String parseTriangles() {
        String data;
        int end =  dataParseBuilder.indexOf("]}")+1;
        int start = dataParseBuilder.indexOf("{A")+1;
        data = dataParseBuilder.substring(start,end);
        dataParseBuilder.delete(0,end+1);
        return data;
    }

    private boolean areSimilarTriangle() {
        if (triangle1.lengthAB/triangle2.lengthAB == triangle1.lengthAC/triangle2.lengthAC
                && triangle1.lengthAB/triangle2.lengthAB == triangle1.lengthBC/triangle2.lengthBC)
            return true;
        return false;
    }


    class Triangle {
        private double lengthAB;
        private double lengthBC;
        private double lengthAC;

        private int [] arrA = new int[3];
        private int [] arrB = new int[3];
        private int [] arrC = new int[3];

        private StringBuilder coord;

        void createTriangle(String coord) {
            this.coord = new StringBuilder(coord);

            arrA = parseCoord();
            arrB = parseCoord();
            arrC = parseCoord();
            lengthAB = calcLength(arrA, arrB);
            lengthAC = calcLength(arrA, arrC);
            lengthBC = calcLength(arrB, arrC);
        }

        private int[] parseCoord() {
            int [] arr = new int[3];
            int end =  coord.indexOf("]");
            int start = coord.indexOf("[")+1;
            String strCoord = coord.substring(start,end);
            int ind = 0;
            for (int i = 0; i < arr.length; i++) {
                arr[i] = Character.getNumericValue(strCoord.charAt(ind));
                ind+=2;
            }
            System.out.println(Arrays.toString(arr));
            coord.delete(0,end+1);
            return arr;
        }

        private double calcLength(int[] arr1, int[] arr2) {
            return Math.sqrt(Math.pow(arr2[0]-arr1[0],2)
                    + Math.pow(arr2[1]-arr1[1],2)
                    + Math.pow(arr2[2]-arr1[2],2));
        }
    }
}
