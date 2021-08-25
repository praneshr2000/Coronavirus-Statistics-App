import React from 'react';
import './GlobalCountryData.css';

const GlobalCountryData = ({country,     
     flagURL,
     totalCountryConfirmedCases, 
     totalCountryConfirmedDeaths,
     totalCountryNewConfirmedCases,
     totalCountryNewConfirmedDeaths}) => {

    return (
        <div className="countryContainer">
            <div className="countryRow">
                <div className="country">
                    <h1 className="countryName">{country}</h1>
                    <img src={flagURL} alt="" />
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
            </div>
        </div>
    )
}

export default GlobalCountryData
