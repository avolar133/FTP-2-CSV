package myPackage;

public enum Variables {
    WBANNO(null),
    UTC_DATE(null),
    UTC_TIME(null),
    LST_DATE(null),
    LST_TIME(null),
    CRX_VN(null),
    LONGITUDE(null),
    LATITUDE(null),
    AIR_TEMPERATURE("-9999.0"),
    PRECIPITATION("-9999.0"),
    SOLAR_RADIATION("-99999"),
    SR_FLAG(null),
    SURFACE_TEMPERATURE("-9999.0"),
    ST_TYPE(null),
    ST_FLAG(null),
    RELATIVE_HUMIDITY("-9999"),
    RH_FLAG(null),
    SOIL_MOISTURE_5("-99.000"),
    SOIL_TEMPERATURE_5("-9999.0"),
    WETNESS("-9999"),
    WET_FLAG(null),
    WIND_1_5("-99.00"),
    WIND_FLAG(null);


    private final String noData;

    Variables(String noData) {
        this.noData = noData;
    }

    public String filterNoData(String examinationValue){
        if (!(noData==null) && this.noData.equals(examinationValue)){
            return "";
        }else if(this.toString().equals("UTC_TIME") || this.toString().equals("LST_TIME")){
            return this.modifyTime(examinationValue);
        }


        return examinationValue;

    }

    private String modifyTime(String notModifiedTime){
        String[] hoursAndMinuts = new String[2];
        hoursAndMinuts[0] =  notModifiedTime.substring(0,2);
        hoursAndMinuts[1] = notModifiedTime.substring(2);


        return hoursAndMinuts[0] + ":" + hoursAndMinuts[1];
    }


}