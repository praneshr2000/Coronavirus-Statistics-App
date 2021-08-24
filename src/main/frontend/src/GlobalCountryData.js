import React from 'react'

const GlobalCountryData = ({country,
     totalCountryConfirmedCases, 
     totalCountryConfirmedDeaths,
     totalCountryNewConfirmedCases,
     totalCountryNewConfirmedDeaths}) => {

    return (
        <div className="countryContainer">
            <div className="countryRow">
                <div className="country">
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
            </div>
        </div>
    )
}

export default GlobalCountryData
