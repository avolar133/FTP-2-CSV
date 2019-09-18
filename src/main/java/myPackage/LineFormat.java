package myPackage;

import java.util.Map;
import java.util.TreeMap;

public class LineFormat {

    private String[] arrayFromLine ;

    public LineFormat(String line){


        arrayFromLine = new String[23];

        //Delete duplicate spaces
        line = line.trim().replaceAll(" +"," ");
        //cut the string and store into array
        arrayFromLine = line.split(" ");
        int sizeOfVariables = Variables.values().length;

        if (sizeOfVariables != arrayFromLine.length){
            throw new IllegalArgumentException("The line size is incorrect");
        }

        this.modifiyArray();
    }


    private void modifiyArray(){

        int i = 0;
        for(Variables variable : Variables.values()){
            //arrayFromLine[i] = this.validation(variable,arrayFromLine[i]);

            arrayFromLine[i] = variable.filterNoData(arrayFromLine[i]);
            i++;
        }

    }


    public String[] getArrayFromLine() {
        return arrayFromLine;
    }
}
