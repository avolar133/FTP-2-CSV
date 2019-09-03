package myPackage;


public class App {
    public static void main(String[] args) {
        //FtpConnection connection = new FtpConnection("ftp.ncdc.noaa.gov","/pub/data/uscrn/products/subhourly01/");
        //connection.printFiles();

        Converter converter = new Converter();
        converter.inputToOutput();
    }

}
