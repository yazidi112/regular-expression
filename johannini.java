// Press Shift twice to open the Search Everywhere dialog and type show whitespaces,
// then press Enter. You can now see whitespace characters in your code.

import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.io.File;


public class Main {
    public static void main(String[] args) {
        String section="",regex,param,value,output="",input="",codeClient="FO14T",path;
        path = "input.txt";
        try {
            File file = new File(path);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                input += myReader.nextLine()+"\n";
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //String s = "[a]\na=1\nb=3\n[b]\nx=t\ny=u";
        String[] arr = input.split("\n");

        for (String a : arr){
            regex = "\\[(\\w+)\\]";
            if(Pattern.matches(regex, a)){
                section = a.replaceAll(regex,"$1");
            }
            regex = "(.*)=(.*)";
            if(Pattern.matches(regex, a)){
                param = a.replaceAll(regex,"$1");
                value = a.replaceAll(regex,"$2");
                if(Pattern.matches(".'.",value)){
                    value = value.replaceAll("'","''");
                }
                output+="INSERT INTO GER_JOHANINI VALUES ('"+codeClient+"','"+section.trim()+"', '"+param.trim()+"', '"+value.trim()+"');\n";
                //System.out.println(output);
            }
        }
        ///    Write to external file
        try {
            FileWriter myWriter = new FileWriter("output.txt");
            myWriter.write(output);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
