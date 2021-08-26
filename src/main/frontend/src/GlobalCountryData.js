import React from 'react';
import './GlobalCountryData.css';

const GlobalCountryData = ({country,     
     flagURL,
     totalCountryConfirmedCases, 
     totalCountryConfirmedDeaths,
     totalCountryNewConfirmedCases,
     totalCountryNewConfirmedDeaths,
     hasProvinceStateData}) => {
    
    var countryPageURL = "";
    const URLCountry = country.replace(/ /g, "%20")
    if (hasProvinceStateData) {
        if (country === "US") {
            countryPageURL = "http://localhost:8080/api/v1/country/US";
        } else {
            countryPageURL = `http://localhost:8080/api/v1/country/province_state/${URLCountry}`;
        }
    } else {
        countryPageURL = `http://localhost:8080/api/v1/country/${URLCountry}`;
    }
    return (
        <div className="countryContainer">
            <a className="countryRow" href={countryPageURL}>
                <div className="country">
                    <img src={flagURL} alt="" />
                    <h1 className="countryName">{country}</h1>
                </div>
                <div className="countryData">
                    <p className="totalCountryConfirmedCases">
                        {totalCountryConfirmedCases.toLocaleString()}
                    </p>
                    <p className="totalCountryConfirmedDeaths">
                        {totalCountryConfirmedDeaths.toLocaleString()}
                    </p>
                    <p className="totalCountryNewConfirmedCases">
                        {totalCountryNewConfirmedCases.toLocaleString()}
                    </p>
                    <p className="totalCountryNewConfirmedDeaths">
                        {totalCountryNewConfirmedDeaths.toLocaleString()}
                    </p>
                </div>
            </a>
        </div>
    )
}

export default GlobalCountryData
