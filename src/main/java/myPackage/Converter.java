package myPackage;

import com.opencsv.CSVWriter;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;


public class Converter {

    private File input = new File("/home/michael/Documents/FTP-2-CSV/CRNS0101-05-2019-AK_Bethel_87_WNW.txt/");
    private CSVWriter writer;
    private FTPClient ftpClient;

    public Converter(){
        //input = new File(pathToFile);
        ftpClient  = new FTPClient();

        try{

         ftpClient.connect("ftp.ncdc.noaa.gov");
         ftpClient.login("anonymous","");

         writer = new CSVWriter(new FileWriter("myfile.csv"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void inputToOutput(){
        try{

            Scanner sc = new Scanner(input);


            //String line = sc.nextLine();
            //System.out.println(line);


            while(sc.hasNext()){
                String line = sc.nextLine();
                System.out.println(line);
                String[] arrayFromTxt = txtLineToCsv(line);
                writer.writeNext(arrayFromTxt);
            }
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    public String[] txtLineToCsv(String line){

        //Delete duplicate spaces
        line = line.trim().replaceAll(" +"," ");
        //cut the string and store into array
        String[] arrayFromLine = line.split(" ");

        //remove missing data and replace with empty String
        for (int i = 0; i< arrayFromLine.length ;i++){
            String currentString = arrayFromLine[i];
            if (currentString.equals("-99999")||
                currentString.equals("-9999.0") ||
                currentString.equals("-9999") ||
                currentString.equals("-99.000") ||
                currentString.equals("-99.00")
                 ){
                    arrayFromLine[i] = "";
            }
        }

        return arrayFromLine;
    }
}
