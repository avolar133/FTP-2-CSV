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
            arrayFromLine[i] = this.validation(variable,arrayFromLine[i]);
            i++;
        }

    }

    private String validation(Variables variable, String valueOfHeader){

        boolean testRequired = variable.isTestRequired();
        if (testRequired){
            switch (variable){
                case AIR_TEMPERATURE: case PRECIPITATION: case SURFACE_TEMPERATURE:
                case SOIL_MOISTURE_5: case SOIL_TEMPERATURE_5:
                    String[] noDataValuesSeven = {"-999999","-9999.0","-999.00","-99.000"};
                    for (String data: noDataValuesSeven){
                        if (valueOfHeader.equals(data)){
                            return "";
                        }
                    }
                    return valueOfHeader;
                case SOLAR_RADIATION: case WIND_1_5:
                    String[] noDataValuesSix = {"-99999","-999.0","-99.00"};
                    for (String data: noDataValuesSix){
                        if (valueOfHeader.equals(data)){
                            return "";
                        }
                    }
                    return valueOfHeader;
                case RELATIVE_HUMIDITY: case WETNESS:
                    String[] noDataValuesFive = {"-9999","-99.0"};
                    for (String data: noDataValuesFive){
                        if (valueOfHeader.equals(data)){
                            return "";
                        }
                    }
                    return valueOfHeader;
            }
        }

        switch (variable){
            case UTC_TIME: case LST_TIME:
                return splitTime(valueOfHeader);
        }

        return valueOfHeader;
    }

    private String splitTime(String toSplitString){
        String[] hoursAndMinuts = new String[2];
        hoursAndMinuts[0] =  toSplitString.substring(0,2);
        hoursAndMinuts[1] = toSplitString.substring(2);


        return hoursAndMinuts[0] + ":" + hoursAndMinuts[1];
    }

    private boolean handleFlags(String valueFromFlag){


        if (valueFromFlag.equals("0")){
            //no need for change- good data
        }else if (valueFromFlag.equals("1")){
            //field-length overflow
        }else if(valueFromFlag.equals("3")){
            //erroneuos
            return false;
        }

        return true;
    }

    public String[] getArrayFromLine() {
        return arrayFromLine;
    }
}
