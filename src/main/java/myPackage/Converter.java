package myPackage;

import com.opencsv.CSVWriter;
import org.apache.commons.net.ftp.FTPClient;

import java.io.*;
import java.util.Scanner;


public class Converter {

    private CSVWriter writer;
    private FTPClient ftpClient;
    private InputStream is;


    public Converter(){
        ftpClient  = new FTPClient();

        try{
            ftpClient.connect("ftp.ncdc.noaa.gov");
            Boolean login = ftpClient.login("anonymous","");

            System.out.println("Login: " + login);
            writer = new CSVWriter(new FileWriter("myfile.csv"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void inputToOutput(String fileName, int year){
        try{
            // change directory
            ftpClient.changeWorkingDirectory("/pub/data/uscrn/products/subhourly01/"+year+"/");
            // open input stream in the directory( listed above ).  and the fileName
            is = new BufferedInputStream(ftpClient.retrieveFileStream(fileName));


            // Covert from input stream to scanner
            Scanner scanner = new Scanner(is,"UTF-8");

            while(scanner.hasNext()){
                String line = scanner.nextLine();
                String[] arrayFromTxt = txtLineToCsv(line);

                writer.writeNext(arrayFromTxt);
            }
            scanner.close();
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
