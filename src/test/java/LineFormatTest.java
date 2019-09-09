import myPackage.LineFormat;
import org.junit.Assert;
import org.junit.Test;


public class LineFormatTest {

    LineFormat lineFormat;
    String[] arrayFromList;

    public LineFormatTest(){

        String list ="26565 20190505 1720 20190305 0820      3 -148.46   70.16   -15.7     0.0     27 0   -17.6 C 0    75 0 -99.000 -9999.0  1179 0   0.42 0";

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

        //Test length of date format
        Assert.assertEquals(utcDate.length() , 8);
        Assert.assertEquals(ltcDate.length() , 8);

        String yearOfUtcDate = utcDate.substring(0,4);
       // String monthOfUtcDate = utcDate.substring(4,6);



        String yearOfLtcDate = ltcDate.substring(0,4);
       // String monthOfLtcDate = ltcDate.substring(4,6);

        Assert.assertEquals(yearOfUtcDate,yearOfLtcDate);


    }


    @Test
    public void testTime(){
        // Test for right format.(HH:MM)
        int hourMin = 00;
        int hourMax = 23;

        int minutesMin = 00;
        int minutesMax = 59;


        String utcTime = arrayFromList[2];
        String ltcTime = arrayFromList[4];

       String hourUtc = utcTime.substring(0,2);
       String seprateUtc = utcTime.substring(2,3);
       String minUtc = utcTime.substring(3);

       Assert.assertTrue( Integer.parseInt(hourUtc) >= hourMin);
       Assert.assertTrue( Integer.parseInt(hourUtc) <= hourMax);

       Assert.assertTrue( Integer.parseInt(minUtc) >= minutesMin);
        Assert.assertTrue( Integer.parseInt(minUtc) <= minutesMax);





        String hourLtc =  ltcTime.substring(0,2);
       String separteLtc = ltcTime.substring(2,3);
       String minLtc = ltcTime.substring(3);


        Assert.assertTrue( Integer.parseInt(hourLtc) >= hourMin);
        Assert.assertTrue( Integer.parseInt(hourLtc) <= hourMax);

        Assert.assertTrue( Integer.parseInt(minLtc) >= minutesMin);
        Assert.assertTrue( Integer.parseInt(minLtc) <= minutesMax);


    }

    @Test
    public void testForMissingValues(){

    }
}
