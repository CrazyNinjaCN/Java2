import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");

        String str = "hello   hello world world world world";
        String pat = "(?!)\\b(\\w+)(?:\\s+\\1\\b)+";
        String res = str.replaceAll(pat, "$1");
        System.out.println(res); // => hello world




        //Assign the original file : file to
        //FileInputStream for reading data
        FileInputStream fis=new FileInputStream("file1.txt");

        //Assign compressed file:file2 to FileOutputStream
        FileOutputStream fos=new FileOutputStream("file2.txt");

        //Assign FileOutputStream to DeflaterOutputStream
        DeflaterOutputStream dos=new DeflaterOutputStream(fos);

        //read data from FileInputStream and write it into DeflaterOutputStream
        int data;
        while ((data=fis.read())!=-1)
        {
            dos.write(data);
        }

        String str1 = readFileAsString("file1.txt");


        ArrayList<String> str2 = new ArrayList<>();



        String tmp = "";
        for(int i = 0; i < str1.length(); i++){


            if(str1.charAt(i) == ' ' || str1.charAt(i) == '\n'){
                if(tmp != " ") {
                    str2.add(tmp);
                    tmp = "";
                }
            }else{
                if(tmp != " ") {
                    tmp += str1.charAt(i);
                }
            }
        }



       int n = 0;

        for(int j=0;j<str2.size();j++) {
            for (int i = 0; i < str2.size(); i++) {
                if (str2.get(i) == str2.get(j)) {
                    str2.remove(i);
                    n++;
                }
            }
        }

        for(int i = 0; i < str2.size(); i++){
            System.out.println(str2.get(i));
        }



        //close the file
        fis.close();
        dos.close();

        BufferedWriter writer = new BufferedWriter(new FileWriter("file1.txt", true));
        writer.append("\nКоличество повторов строки " + n);
        writer.flush();

        //assign Input File : file2 to FileInputStream for reading data
        FileInputStream fis1=new FileInputStream("file2.txt");

        //assign output file: file3 to FileOutputStream for reading the data
        FileOutputStream fos1=new FileOutputStream("file3.txt");

        //assign inflaterInputStream to FileInputStream for uncompressing the data
        InflaterInputStream iis=new InflaterInputStream(fis1);

        //read data from inflaterInputStream and write it into FileOutputStream
        int data1;
        while((data1=iis.read())!=-1)
        {
            fos1.write(data1);
        }

        //close the files
        fos1.close();
        iis.close();
    }


    private static String readFileAsString(String filePath) throws IOException {
        StringBuffer fileData = new StringBuffer();
        BufferedReader reader = new BufferedReader(
                new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead=0;
        while((numRead=reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
        }
        reader.close();
        return fileData.toString();
    }

}