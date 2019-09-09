import myPackage.LineFormat;
import org.junit.Assert;
import org.junit.Test;

public class LineFormatTest {

    LineFormat lineFormat;
    String[] arrayFromList;

    public LineFormatTest(){
        //26565
        String list ="26565 20190305 1720 20190305 0820      3 -148.46   70.16   -15.7     0.0     27 0   -17.6 C 0    75 0 -99.000 -9999.0  1179 0   0.42 0";

        lineFormat = new LineFormat(list);
        arrayFromList = lineFormat.getArrayFromLine();
    }

    @Test
    public void testSizeOfLine(){


        Assert.assertEquals(arrayFromList.length,23);
    }

    @Test
    public void testStation(){

        Assert.assertEquals(arrayFromList[0].length() , 5);
    }

    @Test
    public void testDate(){
        String utcDate = arrayFromList[1];
        String ltcDate = arrayFromList[3];

        Assert.assertEquals(utcDate.length() , 8);
        Assert.assertEquals(ltcDate.length() , 8);


    }


    @Test
    public void testTime(){
        // Test for right format.(HH:MM)

        String utcTime = arrayFromList[2];
        String ltcTime = arrayFromList[4];

        

    }

    @Test
    public void testForMissingValues(){

    }
}
