package com.example.android.uvdemo;

/**
 * Created by user on 15/9/7.
 */
public class UvItem {
    private String SiteName, UVI, PublishAgency, County, TWD97Lon, TWD97Lat, PublishTime;

    public UvItem() {
    }

    public UvItem(String siteName, String uvi, String publishAgency, String county, String lon,
                  String lat, String publishTime) {
        this.SiteName = siteName;
        this.UVI = uvi;
        this.PublishAgency = publishAgency;
        this.County = county;
        this.TWD97Lon = lon;
        this.TWD97Lat = lat;
        this.PublishTime = publishTime;
    }

    public String getSiteName() {
        return SiteName;
    }

    public void setSiteName(String siteName) {
        SiteName = siteName;
    }

    public String getUVI() {
        return UVI;
    }

    public void setUVI(String UVI) {
        this.UVI = UVI;
    }

    public String getPublishAgency() {
        return PublishAgency;
    }

    public void setPublishAgency(String publishAgency) {
        PublishAgency = publishAgency;
    }

    public String getCounty() {
        return County;
    }

    public void setCounty(String county) {
        County = county;
    }

    public String getTWD97Lon() {
        return TWD97Lon;
    }

    public void setTWD97Lon(String TWD97Lon) {
        this.TWD97Lon = TWD97Lon;
    }

    public String getTWD97Lat() {
        return TWD97Lat;
    }

    public void setTWD97Lat(String TWD97Lat) {
        this.TWD97Lat = TWD97Lat;
    }

    public String getPublishTime() {
        return PublishTime;
    }

    public void setPublishTime(String publishTime) {
        PublishTime = publishTime;
    }
}
