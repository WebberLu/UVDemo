package com.example.android.uvdemo;

/**
 * Created by user on 2016/3/16.
 */
public class RealtimeRainfallItem {
    private String SiteId, SiteName, County, Township, TWD67Lon, TWD67Lat, Rainfall10min,
            Rainfall1hr, Rainfall3hr, Rainfall6hr, Rainfall12hr, Rainfall24hr, Now, Unit, PublishTime;

    public RealtimeRainfallItem() {
    }

    public RealtimeRainfallItem(String SiteId, String SiteName, String County, String Township, String TWD67Lon,
                                String TWD67Lat, String Rainfall10min, String Rainfall1hr, String Rainfall3hr, String Rainfall6hr, String Rainfall12hr, String Rainfall24hr, String Now, String Unit, String PublishTime) {
        this.SiteId = SiteId;
        this.SiteName = SiteName;
        this.County = County;
        this.Township = Township;
        this.TWD67Lon = TWD67Lon;
        this.TWD67Lat = TWD67Lat;
        this.Rainfall10min = Rainfall10min;
        this.Rainfall1hr = Rainfall1hr;
        this.Rainfall3hr = Rainfall3hr;
        this.Rainfall6hr = Rainfall6hr;
        this.Rainfall12hr = Rainfall12hr;
        this.Rainfall24hr = Rainfall24hr;
        this.Now = Now;
        this.Unit = Unit;
        this.PublishTime = PublishTime;
    }

    public String getSiteId() {
        return SiteId;
    }

    public void setSiteId(String siteId) {
        SiteId = siteId;
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

    public String getTownship() {
        return Township;
    }

    public void setTownship(String township) {
        Township = township;
    }

    public String getTWD67Lon() {
        return TWD67Lon;
    }

    public void setTWD67Lon(String TWD67Lon) {
        this.TWD67Lon = TWD67Lon;
    }

    public String getTWD67Lat() {
        return TWD67Lat;
    }

    public void setTWD67Lat(String TWD67Lat) {
        this.TWD67Lat = TWD67Lat;
    }

    public String getRainfall10min() {
        return Rainfall10min;
    }

    public void setRainfall10min(String rainfall10min) {
        Rainfall10min = rainfall10min;
    }

    public String getRainfall1hr() {
        return Rainfall1hr;
    }

    public void setRainfall1hr(String rainfall1hr) {
        Rainfall1hr = rainfall1hr;
    }

    public String getRainfall3hr() {
        return Rainfall3hr;
    }

    public void setRainfall3hr(String rainfall3hr) {
        Rainfall3hr = rainfall3hr;
    }

    public String getRainfall6hr() {
        return Rainfall6hr;
    }

    public void setRainfall6hr(String rainfall6hr) {
        Rainfall6hr = rainfall6hr;
    }

    public String getRainfall12hr() {
        return Rainfall12hr;
    }

    public void setRainfall12hr(String rainfall12hr) {
        Rainfall12hr = rainfall12hr;
    }

    public String getRainfall24hr() {
        return Rainfall24hr;
    }

    public void setRainfall24hr(String rainfall24hr) {
        Rainfall24hr = rainfall24hr;
    }

    public String getNow() {
        return Now;
    }

    public void setNow(String now) {
        Now = now;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public String getPublishTime() {
        return PublishTime;
    }

    public void setPublishTime(String publishTime) {
        PublishTime = publishTime;
    }
}
