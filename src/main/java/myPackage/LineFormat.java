package myPackage;

import java.util.Map;
import java.util.TreeMap;

public class LineFormat {

    private Map<Variables,String> valuesMap;
    private String[] arrayFromLine;

    public LineFormat(String line){

        valuesMap = new TreeMap();

        arrayFromLine = new String[23];

        //Delete duplicate spaces
        line = line.trim().replaceAll(" +"," ");
        //cut the string and store into array
        String[] arrayFromLine = line.split(" ");
        int sizeOfVariables = Variables.values().length;

        if (sizeOfVariables != arrayFromLine.length){
            throw new IllegalArgumentException("The line size is not correct");
        }

        int i = 0;
        for (Variables variable : Variables.values()) {

            valuesMap.put(variable, arrayFromLine[i]);
            i++;
        }

        validation();

    }

    private void validation(){
        int indexForArray = 0;
        for (Map.Entry<Variables, String> entry : valuesMap.entrySet()){
            //System.out.println( entry.getKey() + " : " + entry.getValue());
            Variables key = entry.getKey();
            String value = entry.getValue();

            if (value.equals("-99999")||
                    value.equals("-9999.0") ||
                    value.equals("-9999") ||
                    value.equals("-99.000") ||
                    value.equals("-99.00")
            ){
                valuesMap.replace(key,"");
            }

            //System.out.println( entry.getKey() + " : " + entry.getValue());

            switch (key){
                case UTC_TIME:
                    valuesMap.replace(key,splitTime(value));
                    break;
                case LST_TIME:
                    valuesMap.replace(key,splitTime(value));
                case SR_FLAG:
                    if(!handleFlags(value)){
                        valuesMap.replace(Variables.SOLAR_RADIATION, "");
                    }
                    break;
                case ST_FLAG:
                    if(!handleFlags(value)){
                        valuesMap.replace(Variables.SURFACE_TEMPERATURE, "");
                    }
                    break;
                case RH_FLAG:
                    if(!handleFlags(value)){
                        valuesMap.replace(Variables.RELATIVE_HUMIDITY, "");
                    }
                    break;
                case WET_FLAG:
                    if(!handleFlags(value)){
                        valuesMap.replace(Variables.WETNESS, "");
                    }
                    break;
                case WIND_FLAG:
                    if(!handleFlags(value)){
                        valuesMap.replace(Variables.WIND_1_5, "");
                    }
                    break;
            }

            arrayFromLine[indexForArray] = valuesMap.get(key);
            indexForArray++;

        }

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


    public String[] getArrayFromLine(){
        return arrayFromLine;
    }

    public void printMap(){
        for (Map.Entry<Variables, String> entry : valuesMap.entrySet()) {
            System.out.println( entry.getKey() + " : " + entry.getValue());
        }
    }

}
