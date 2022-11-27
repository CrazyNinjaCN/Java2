import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Введите год");
            printValidValue(sc, "[0-9]+");
        }
    }

    static void printValidValue(Scanner sc, String pattern) {
        while (sc.hasNextLine()) {
            try {
                int g = Integer.parseInt(sc.next(pattern));
                    if (g % 4 == 0 && (g % 100 != 0) || (g % 400 == 0)) {
                        System.out.println("Високосный год");
                    }else{
                        System.out.println("Не високосный год");
                    }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Неправильное значение");
                sc.nextLine();
            }
        }
    }
}