package com.example.android.uvdemo;

/**
 * Created by user on 15/9/8.
 */
public class AQXItem {
    private String SiteName, County, PSI, MajorPollutant, Status, SO2, CO, O3, PM10, PM25, NO2, WindSpeed,
            WindDirec, FPMI, NOx, NO, PublishTime;

    public AQXItem(String siteName, String county, String psi, String majorPollutant, String status,
                   String so2, String co, String o3, String pm10, String pm25, String no2, String
                           windSpeed, String windDirec, String fpmi, String nox, String no, String
                           publishTime) {
        this.SiteName = siteName;
        this.County = county;
        this.PSI = psi;
        this.MajorPollutant = majorPollutant;
        this.Status = status;
        this.SO2 = so2;
        this.CO = co;
        this.O3 = o3;
        this.PM10 = pm10;
        this.PM25 = pm25;
        this.NO2 = no2;
        this.WindSpeed = windSpeed;
        this.WindDirec = windDirec;
        this.FPMI = fpmi;
        this.NOx = nox;
        this.NO = no;
        this.PublishTime = publishTime;
    }

    public String getSiteName() {
        return SiteName;
    }

    public void setSiteName(String siteName) {
        SiteName = siteName;
    }

    public String getCounty() {
        return County;
    }

    public void setCounty(String county) {
        County = county;
    }

    public String getPSI() {
        return PSI;
    }

    public void setPSI(String PSI) {
        this.PSI = PSI;
    }

    public String getMajorPollutant() {
        return MajorPollutant;
    }

    public void setMajorPollutant(String majorPollutant) {
        MajorPollutant = majorPollutant;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getSO2() {
        return SO2;
    }

    public void setSO2(String SO2) {
        this.SO2 = SO2;
    }

    public String getCO() {
        return CO;
    }

    public void setCO(String CO) {
        this.CO = CO;
    }

    public String getO3() {
        return O3;
    }

    public void setO3(String o3) {
        O3 = o3;
    }

    public String getPM10() {
        return PM10;
    }

    public void setPM10(String PM10) {
        this.PM10 = PM10;
    }

    public String getPM25() {
        return PM25;
    }

    public void setPM25(String PM25) {
        this.PM25 = PM25;
    }

    public String getNO2() {
        return NO2;
    }

    public void setNO2(String NO2) {
        this.NO2 = NO2;
    }

    public String getWindSpeed() {
        return WindSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        WindSpeed = windSpeed;
    }

    public String getWindDirec() {
        return WindDirec;
    }

    public void setWindDirec(String windDirec) {
        WindDirec = windDirec;
    }

    public String getFPMI() {
        return FPMI;
    }

    public void setFPMI(String FPMI) {
        this.FPMI = FPMI;
    }

    public String getNOx() {
        return NOx;
    }

    public void setNOx(String NOx) {
        this.NOx = NOx;
    }

    public String getNO() {
        return NO;
    }

    public void setNO(String NO) {
        this.NO = NO;
    }

    public String getPublishTime() {
        return PublishTime;
    }

    public void setPublishTime(String publishTime) {
        PublishTime = publishTime;
    }
}
