package myPackage;

public enum Variables {
    WBANNO(false),
    UTC_DATE(false),
    UTC_TIME(false),
    LST_DATE(false),
    LST_TIME(false),
    CRX_VN(false),
    LONGITUDE(false),
    LATITUDE(false),
    AIR_TEMPERATURE(true),
    PRECIPITATION(true),
    SOLAR_RADIATION(true),
    SR_FLAG(false),
    SURFACE_TEMPERATURE(true),
    ST_TYPE(false),
    ST_FLAG(false),
    RELATIVE_HUMIDITY(true),
    RH_FLAG(false),
    SOIL_MOISTURE_5(true),
    SOIL_TEMPERATURE_5(true),
    WETNESS(true),
    WET_FLAG(false),
    WIND_1_5(true),
    WIND_FLAG(false);


    private boolean testRequired;

    Variables(boolean testRequired){
        this.testRequired = testRequired;
    }

    public boolean isTestRequired() {
        return testRequired;
    }
}
