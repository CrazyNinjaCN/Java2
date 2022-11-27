import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numbers = in.nextInt();
        int percent = in.nextInt();
        int periods =  in.nextInt();
        System.out.println(compoundInterest(numbers,percent,periods));
        System.out.println(compoundPercent(compoundInterest(numbers,percent,periods),numbers,periods));
    }
    public static double compoundInterest(double start, double percent, int periods) {
        return start * Math.pow(1 + percent/100, periods);
    }

    public static double compoundPercent(double start, int numbers, int periods) {
        return Math.round((Math.pow(start/numbers, 1.0 / periods)-1)*100);
    }
}