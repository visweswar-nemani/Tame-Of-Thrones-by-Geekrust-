package Problem2;


import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class test {
    public  static ArrayList <String>  fileTest(String fileName){
        ArrayList <String> lines= new ArrayList<>();
        try{
            Scanner sc= new Scanner(new File(fileName)).useDelimiter("\",");
            while(sc.hasNext()) {
                lines.add(sc.next().replaceAll("\"",""));
            }
              return lines;
        } catch (Exception e){
            System.err.println(e);
        }
        return  lines;

    }
}
