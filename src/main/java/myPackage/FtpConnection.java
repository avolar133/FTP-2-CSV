package myPackage;


import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.IOException;

public class FtpConnection {

    private String host;
    private String path;
    private FTPClient client;



    // Create connection to ftp server. Pass host and path
    public FtpConnection(String host, String path){
        this.path = path;
        this.host = host;

        client = new FTPClient();

        try {
            client.connect(this.host);
            Boolean login = client.login("anonymous", "");
            // what should I do if login returns false?
            System.out.println("Login: " + login);
        }catch (IOException e){
            e.printStackTrace();
        }

    }




    public void printFiles() {
        try {
            FTPFile[] files = client.listFiles(path);
            for (FTPFile file : files){
                if ( file.isDirectory()) {
                    int yearOfFile = Integer.parseInt(file.getName());
                    // accessing each directory between 1900 and 2100
                    if (yearOfFile >1900 && yearOfFile < 2100 ) {
                        accessEachDir(file);
                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }


    }

    public void accessEachDir(FTPFile file){
      try {
          FTPFile[] files = client.listFiles(path + file.getName() + "/");
          for (FTPFile eachFile : files){
              String fileName = eachFile.getName();
              System.out.println(fileName);
              //converter.inputToOutput(path+fileName+"/");


          }
      }catch (IOException e){
          e.printStackTrace();
      }
    }



}
