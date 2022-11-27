import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] mas = {3, 5, 7, 8, 9, 1, 4, 5, 3, 4, 8, 6};
        for (int out = mas.length - 1; out >= 1; out--) {
            for (int in = 0; in < out; in++) {
                if (mas[in] > mas[in + 1]) {
                    int tmp = mas[in];
                    mas[in] = mas[in+1] ;
                    mas[in+1] = tmp;
                }
            }
        }
        for(int i = 0;i<mas.length;i++){
            System.out.println(mas[i]);
        }
    }
}