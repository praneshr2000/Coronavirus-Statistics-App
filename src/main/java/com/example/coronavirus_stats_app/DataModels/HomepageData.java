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
        private int totalCountryConfirmedCases;
        private int totalCountryConfirmedDeaths;
        private int totalCountryNewConfirmedCases;
        private int totalCountryNewConfirmedDeaths;

        public CountryData(String country,
                            int totalCountryConfirmedCases,
                            int totalCountryConfirmedDeaths,
                            int totalCountryNewConfirmedCases,
                            int totalCountryNewConfirmedDeaths) {

            this.country = country;
            this.totalCountryConfirmedCases = totalCountryConfirmedCases;
            this.totalCountryConfirmedDeaths = totalCountryConfirmedDeaths;
            this.totalCountryNewConfirmedCases = totalCountryNewConfirmedCases;
            this.totalCountryNewConfirmedDeaths = totalCountryNewConfirmedDeaths;
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

        @Override
        public int compareTo(CountryData o) {
            return this.country.compareToIgnoreCase(o.country);
        }
    }
}
