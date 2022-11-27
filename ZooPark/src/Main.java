import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Hourse hourse = new Hourse(180, 200, "Brown", false);
        Raccoon raccoon = new Raccoon(50, 15, "Grey", false);
        Fox fox = new Fox(50, 15,"Orange",true);

        int Rnd = (int)(Math.random()*2);
        Scanner in = new Scanner(System.in);
        String enter;
        if(Rnd == 0){
            Hourse animal = new Hourse(180, 200, "Brown", false);
            animal.print();System.out.println("Guess the animal");
            enter = in.nextLine();
            if(enter.equals("Hourse")){
                System.out.println("Win");
            }else{
                animal.printParam();
                System.out.println("Guess the animal");
                enter = in.nextLine();
                if(enter.equals("Hourse")){
                    System.out.println("Win");
                }else{
                    System.out.println("Loose");
                }
            }
        }
        if(Rnd == 1){
            Raccoon animal = new Raccoon(50, 15, "Grey", false);
            animal.print();
            System.out.println("Guess the animal");
            enter = in.nextLine();
            if(enter.equals("Raccoon")){
                System.out.println("Win");
            }else{animal.printParam();
                System.out.println("Guess the animal");
                enter = in.nextLine();
                if(enter.equals("Raccoon")){
                    System.out.println("Win");
                }else{
                    System.out.println("Loose");
                }
            }
        }
        if(Rnd == 2){
            Fox animal = new Fox(50, 15,"Orange",true);
            animal.print();
            System.out.println("Guess the animal");
            enter = in.nextLine();
            if(enter.equals("Fox")){
                System.out.println("Win");
            }else{
                animal.printParam();
                System.out.println("Guess the animal");
                enter = in.nextLine();
                if(enter.equals("Fox")){
                    System.out.println("Win");
                }else{
                    System.out.println("Loose");
                }
            }
        }



        System.out.println();
    }
}