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
            countryPageURL = "../countries/US";
        } else {
            countryPageURL = `../countries/province_state/${URLCountry}`;
        }
    } else {
        countryPageURL = `../countries/${URLCountry}`;
    }

    return (
        <div className="countryContainer">
            <a href={countryPageURL} className="countryRow">
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
