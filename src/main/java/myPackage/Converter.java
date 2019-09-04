package myPackage;

import com.opencsv.CSVWriter;
import org.apache.commons.net.ftp.FTPClient;

import java.io.*;
import java.net.URL;
import java.util.Scanner;


public class Converter {

    private File input = new File("/home/michael/Documents/FTP-2-CSV/CRNS0101-05-2019-AK_Bethel_87_WNW.txt/");
    private CSVWriter writer;
    private FTPClient ftpClient;
    private OutputStream os;
    private String fileName;
    private InputStream is;


    public Converter(String fileName){
        //input = new File(pathToFile);
        ftpClient  = new FTPClient();
        this.fileName = fileName;

        try{
            os = new FileOutputStream(fileName);
            ftpClient.connect("ftp.ncdc.noaa.gov");
            Boolean login = ftpClient.login("anonymous","");

            System.out.println("Login: " + login);
            writer = new CSVWriter(new FileWriter("myfile.csv"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void inputToOutput(){
        try{
            ftpClient.changeWorkingDirectory("/pub/data/uscrn/products/subhourly01/2019/");
            is = new BufferedInputStream(ftpClient.retrieveFileStream(fileName));

            System.out.println(is);

            Scanner scanner = new Scanner(is,"UTF-8");

            while(scanner.hasNext()){
                String line = scanner.nextLine();
                //System.out.println(line);
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
