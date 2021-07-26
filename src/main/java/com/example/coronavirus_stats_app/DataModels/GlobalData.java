package com.example.coronavirus_stats_app.DataModels;

public class GlobalData {

    private int FIPSCode;
    private String USCountyName;
    private String countryName;
    private boolean isUSA;
    private boolean hasProvinceState;
    private String lastUpdate;
    private float latitude;
    private float longitude;
    private int confirmed;
    private int deaths;
    private int recovered;
    private int active;
    private String combinedKey;
    private float incidentRate;
    private float caseFatalityRatio;


    public GlobalData() {
    }

    public GlobalData(String countryName,
                      boolean isUSA,
                      boolean hasProvinceState,
                      String lastUpdate,
                      float latitude,
                      float longitude,
                      int confirmed,
                      int deaths,
                      int recovered,
                      int active,
                      String combinedKey,
                      float incidentRate,
                      float caseFatalityRatio) {

        this.countryName = countryName;
        this.isUSA = isUSA;
        this.hasProvinceState = hasProvinceState;
        this.lastUpdate = lastUpdate;
        this.latitude = latitude;
        this.longitude = longitude;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
        this.active = active;
        this.combinedKey = combinedKey;
        this.incidentRate = incidentRate;
        this.caseFatalityRatio = caseFatalityRatio;
    }

    public GlobalData(int FIPSCode,
                      String USCountyName,
                      String countryName,
                      boolean isUSA,
                      boolean hasProvinceState,
                      String lastUpdate,
                      float latitude,
                      float longitude,
                      int confirmed,
                      int deaths,
                      int recovered,
                      int active,
                      String combinedKey,
                      float incidentRate,
                      float caseFatalityRatio) {

        this.FIPSCode = FIPSCode;
        this.USCountyName = USCountyName;
        this.countryName = countryName;
        this.isUSA = isUSA;
        this.hasProvinceState = hasProvinceState;
        this.lastUpdate = lastUpdate;
        this.latitude = latitude;
        this.longitude = longitude;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
        this.active = active;
        this.combinedKey = combinedKey;
        this.incidentRate = incidentRate;
        this.caseFatalityRatio = caseFatalityRatio;
    }

    public int getFIPSCode() {
        return FIPSCode;
    }

    public void setFIPSCode(int FIPSCode) {
        this.FIPSCode = FIPSCode;
    }

    public String getUSCountyName() {
        return USCountyName;
    }

    public void setUSCountyName(String USCountyName) {
        this.USCountyName = USCountyName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public boolean isUSA() {
        return isUSA;
    }

    public void setUSA(boolean USA) {
        isUSA = USA;
    }

    public boolean isHasProvinceState() {
        return hasProvinceState;
    }

    public void setHasProvinceState(boolean hasProvinceState) {
        this.hasProvinceState = hasProvinceState;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getCombinedKey() {
        return combinedKey;
    }

    public void setCombinedKey(String combinedKey) {
        this.combinedKey = combinedKey;
    }

    public float getIncidentRate() {
        return incidentRate;
    }

    public void setIncidentRate(float incidentRate) {
        this.incidentRate = incidentRate;
    }

    public float getCaseFatalityRatio() {
        return caseFatalityRatio;
    }

    public void setCaseFatalityRatio(float caseFatalityRatio) {
        this.caseFatalityRatio = caseFatalityRatio;
    }

    @Override
    public String toString() {
        return "GlobalData{" +
                "FIPSCode=" + FIPSCode +
                ", USCountyName='" + USCountyName + '\'' +
                ", countryName='" + countryName + '\'' +
                ", isUSA=" + isUSA +
                ", hasProvinceState=" + hasProvinceState +
                ", lastUpdate='" + lastUpdate + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", confirmed=" + confirmed +
                ", deaths=" + deaths +
                ", recovered=" + recovered +
                ", active=" + active +
                ", combinedKey='" + combinedKey + '\'' +
                ", incidentRate=" + incidentRate +
                ", caseFatalityRatio=" + caseFatalityRatio +
                '}';
    }
}
