package com.example.coronavirus_stats_app.DataModels;

import java.util.Objects;
import java.util.Set;

public class HomepageData {

    private int globalConfirmedCases;
    private int globalDeaths;
    private int globalNewCases;
    private int globalNewDeaths;
    private Set<CountryData> countryDataList;

    public HomepageData(int globalConfirmedCases,
                        int globalDeaths,
                        int globalNewCases,
                        int globalNewDeaths,
                        Set<CountryData> countryDataList) {
        this.globalConfirmedCases = globalConfirmedCases;
        this.globalDeaths = globalDeaths;
        this.globalNewCases = globalNewCases;
        this.globalNewDeaths = globalNewDeaths;
        this.countryDataList = countryDataList;
    }

    public int getGlobalConfirmedCases() {
        return globalConfirmedCases;
    }

    public void setGlobalConfirmedCases(int globalConfirmedCases) {
        this.globalConfirmedCases = globalConfirmedCases;
    }

    public int getGlobalNewCases() {
        return globalNewCases;
    }

    public void setGlobalNewCases(int globalNewCases) {
        this.globalNewCases = globalNewCases;
    }

    public Set<CountryData> getCountryDataList() {
        return countryDataList;
    }

    public void setCountryDataList(Set<CountryData> countryDataList) {
        this.countryDataList = countryDataList;
    }

    public int getGlobalDeaths() {
        return globalDeaths;
    }

    public void setGlobalDeaths(int globalDeaths) {
        this.globalDeaths = globalDeaths;
    }

    public int getGlobalNewDeaths() {
        return globalNewDeaths;
    }

    public void setGlobalNewDeaths(int globalNewDeaths) {
        this.globalNewDeaths = globalNewDeaths;
    }

    // Inner class
    public static class CountryData implements Comparable<CountryData> {
        private String country;
        private String iso2;
        private String flagURL;
        private int totalCountryConfirmedCases;
        private int totalCountryConfirmedDeaths;
        private int totalCountryNewConfirmedCases;
        private int totalCountryNewConfirmedDeaths;
        private boolean hasProvinceStateData;

        public CountryData(String country,
                            String iso2,
                            String flagURL,
                            int totalCountryConfirmedCases,
                            int totalCountryConfirmedDeaths,
                            int totalCountryNewConfirmedCases,
                            int totalCountryNewConfirmedDeaths,
                            boolean hasProvinceStateData) {

            this.country = country;
            this.iso2 = iso2;
            this.flagURL = flagURL;
            this.totalCountryConfirmedCases = totalCountryConfirmedCases;
            this.totalCountryConfirmedDeaths = totalCountryConfirmedDeaths;
            this.totalCountryNewConfirmedCases = totalCountryNewConfirmedCases;
            this.totalCountryNewConfirmedDeaths = totalCountryNewConfirmedDeaths;
            this.hasProvinceStateData = hasProvinceStateData;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public int getTotalCountryConfirmedCases() {
            return totalCountryConfirmedCases;
        }

        public void setTotalCountryConfirmedCases(int totalCountryConfirmedCases) {
            this.totalCountryConfirmedCases = totalCountryConfirmedCases;
        }

        public int getTotalCountryConfirmedDeaths() {
            return totalCountryConfirmedDeaths;
        }

        public void setTotalCountryConfirmedDeaths(int totalCountryConfirmedDeaths) {
            this.totalCountryConfirmedDeaths = totalCountryConfirmedDeaths;
        }

        public int getTotalCountryNewConfirmedCases() {
            return totalCountryNewConfirmedCases;
        }

        public void setTotalCountryNewConfirmedCases(int totalCountryNewConfirmedCases) {
            this.totalCountryNewConfirmedCases = totalCountryNewConfirmedCases;
        }

        public int getTotalCountryNewConfirmedDeaths() {
            return totalCountryNewConfirmedDeaths;
        }

        public void setTotalCountryNewConfirmedDeaths(int totalCountryNewConfirmedDeaths) {
            this.totalCountryNewConfirmedDeaths = totalCountryNewConfirmedDeaths;
        }

        public String getIso2() {
            return iso2;
        }

        public void setIso2(String iso2) {
            this.iso2 = iso2;
        }

        public String getFlagURL() {
            return flagURL;
        }

        public void setFlagURL(String flagURL) {
            this.flagURL = flagURL;
        }

        public boolean isHasProvinceStateData() {
            return hasProvinceStateData;
        }

        public void setHasProvinceStateData(boolean hasProvinceStateData) {
            this.hasProvinceStateData = hasProvinceStateData;
        }

        @Override
        public int compareTo(CountryData o) {
            return this.country.compareToIgnoreCase(o.country);
        }
    }
}
