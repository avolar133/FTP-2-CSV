package myPackage;


public class App {
    public static void main(String[] args) {
        FtpConnection connection = new FtpConnection("ftp.ncdc.noaa.gov","/pub/data/uscrn/products/subhourly01/");
        connection.printFiles();

        //Converter converter = new Converter("CRNS0101-05-2019-AK_Deadhorse_3_S.txt");
        //converter.inputToOutput();
    }

}
