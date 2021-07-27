package com.example.coronavirus_stats_app.DataModels;

public class PlaceData {

    private int FIPSCode;
    private String USCountyName;
    private String provinceState;
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
    private int newCases;
    private int newDeaths;
    private int newRecovered;
    private int newActive;

    public PlaceData() {

    }

    public PlaceData(int FIPSCode,
                     String USCountyName,
                     String provinceState,
                     String countryName,
                     boolean isUSA,
                     boolean hasProvinceState,
                     String lastUpdate,
                     float latitude,
                     float longitude,
                     int confirmed,
                     int deaths,
                     String combinedKey,
                     float incidentRate,
                     float caseFatalityRatio,
                     int newCases,
                     int newDeaths) {

        this.FIPSCode = FIPSCode;
        this.USCountyName = USCountyName;
        this.provinceState = provinceState;
        this.countryName = countryName;
        this.isUSA = isUSA;
        this.hasProvinceState = hasProvinceState;
        this.lastUpdate = lastUpdate;
        this.latitude = latitude;
        this.longitude = longitude;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.combinedKey = combinedKey;
        this.incidentRate = incidentRate;
        this.caseFatalityRatio = caseFatalityRatio;
        this.newCases = newCases;
        this.newDeaths = newDeaths;
    }

    public PlaceData(String countryName,
                      String provinceState,
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
                      float caseFatalityRatio,
                      int newCases,
                      int newDeaths,
                      int newRecovered,
                      int newActive) {

        this.countryName = countryName;
        this.provinceState = provinceState;
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
        this.newCases = newCases;
        this.newDeaths = newDeaths;
        this.newRecovered = newRecovered;
        this.newActive = newActive;
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

    public String getProvinceState() {
        return provinceState;
    }

    public void setProvinceState(String provinceState) {
        this.provinceState = provinceState;
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

    public int getNewCases() {
        return newCases;
    }

    public void setNewCases(int newCases) {
        this.newCases = newCases;
    }

    public int getNewDeaths() {
        return newDeaths;
    }

    public void setNewDeaths(int newDeaths) {
        this.newDeaths = newDeaths;
    }

    public int getNewRecovered() {
        return newRecovered;
    }

    public void setNewRecovered(int newRecovered) {
        this.newRecovered = newRecovered;
    }

    public int getNewActive() {
        return newActive;
    }

    public void setNewActive(int newActive) {
        this.newActive = newActive;
    }

    @Override
    public String toString() {
        return "PlaceData{" +
                "FIPSCode=" + FIPSCode +
                ", USCountyName='" + USCountyName + '\'' +
                ", provinceState='" + provinceState + '\'' +
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
                ", newCases=" + newCases +
                ", newDeaths=" + newDeaths +
                ", newRecovered=" + newRecovered +
                ", newActive=" + newActive +
                '}';
    }
}
