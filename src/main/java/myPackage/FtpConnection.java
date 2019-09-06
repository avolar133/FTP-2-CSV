package myPackage;


import com.opencsv.CSVWriter;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.BufferedInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class FtpConnection {

    private String host;
    private String path;
    private FTPClient client;
    private CSVWriter csvWriter;

    //private InputStream is;

    // Create connection to ftp server. Pass host and path
    public FtpConnection(String host, String path){
        this.path = path;
        this.host = host;


        client = new FTPClient();
        //converter = new Converter();

        try {
            int reply;
            client.connect(this.host);

            reply = client.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)){
                client.disconnect();
                System.err.println("FTP Server refused connection.");
                System.exit(1);
            }
            Boolean login = client.login("anonymous", "");
            // what should I do if login returns false?
            System.out.println("Login: " + login);
        }catch (IOException e){
            e.printStackTrace();
        }

    }


    public void readAllFiles(){
        try {

            //initialize new file.
            csvWriter = new CSVWriter(new FileWriter("output.csv"));

            //Save all files that located in the path
            FTPFile[] files = client.listFiles(path);
            //Iterate through all files
            for (FTPFile file : files){
                //Check that, the files are of directory type
                if ( file.isDirectory()) {
                    //save year of directory
                    int yearOfFile = Integer.parseInt(file.getName());

                    // accessing each directory between 1900 and 2100
                    if (yearOfFile >1900 && yearOfFile < 2100 ) {
                        System.out.println("Year of directory: " + yearOfFile);
                        accessEachDir(yearOfFile);
                        System.out.println("After accessEachDir. Back into the loop");
                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public void accessEachDir(int yearOfFile){
        try {


            //list all files from the given year
            FTPFile[] files = client.listFiles(path + yearOfFile + "/");

            //Change directory
            Boolean booleanChangeDir = client.changeWorkingDirectory(path+yearOfFile+"/");

            System.out.println(booleanChangeDir);

            for (FTPFile file : files){
                System.out.println(file.getName());
                //check if it txt file!!!!!!

                InputStream is = new BufferedInputStream(client.retrieveFileStream(file.getName()));
                Scanner scanner = new Scanner(is,"UTF-8");

                while(scanner.hasNext()){

                    String line = scanner.nextLine();
                    LineFormat lineFormat = new LineFormat(line);
                    String[] arrayFromLine = lineFormat.getArrayFromLine();

                    csvWriter.writeNext(arrayFromLine);

                    //System.out.println(line);


                }

                scanner.close();
                is.close();

                Boolean boolPending = client.completePendingCommand();
                System.out.println("BoolPending: " + boolPending);

            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }






}
