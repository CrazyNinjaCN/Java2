import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Float.parseFloat;

public class Main {

    public static void main(String[] args) {
        System.out.println("Введите выражение");
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        int n = 0;
        ArrayList<String> arr = new ArrayList<>();
        String tmp = "";
        for(int i = 0; i < str.length(); i++)
        {
            if(str.charAt(i) == '+' || str.charAt(i) == '-' || str.charAt(i) == '*' || str.charAt(i) == '/'){
                   if(tmp != "") {
                       arr.add(tmp);
                       tmp = "";
                   }
                   arr.add(String.valueOf(str.charAt(i)));
            }else{
                if(str.charAt(i) != ' ') {
                   // if(str.charAt(i) == '.'){
                    //    tmp += ',';
                   // }else {
                        tmp += str.charAt(i);
                   // }
                }
            }
            if(i == str.length()-1){
                arr.add(tmp);
            }
        }


        for(int i = 0; i < arr.size(); i++){
            if(i+1 < arr.size()) {
                if (arr.get(i).equals("+")) {
                    if (arr.get(i+1).equals("+")) {
                        arr.remove(i+1);
                        i -= 1;
                    }
                }
                if (arr.get(i).equals("-")) {
                    if (arr.get(i+1).equals("-")) {
                        arr.remove(i+1);
                        i -= 1;
                    }
                }
                if (arr.get(i).equals("/")) {
                    if (arr.get(i+1).equals("/")) {
                        arr.remove(i+1);
                        i -= 1;
                    }
                }
                if (arr.get(i).equals("*")) {
                    if (arr.get(i+1).equals("*")) {
                        arr.remove(i+1);
                        i -= 1;
                    }
                }
            }
        }

        float res = parseFloat(arr.get(0));



        for(int i = 0; i < arr.size(); i++){
            if(i+1 < arr.size()){
                if(arr.get(i+1).equals("+")){

                    if(i+2 < arr.size()){
                        res += parseFloat(arr.get(i+2));

                    }
                }
                if(arr.get(i+1).equals("-")){
                    if(arr.get(i+2)!=null){
                        res -= parseFloat(arr.get(i+2));
                    }
                }
                if(arr.get(i+1).equals("*")){
                    if(arr.get(i+2)!=null){
                        res *= parseFloat(arr.get(i+2));
                    }
                }
                if(arr.get(i+1).equals("/")){
                    if(arr.get(i+2)!=null){
                        res /= parseFloat(arr.get(i+2));
                    }
                }
            }
        }

        System.out.println(res);
    }
}