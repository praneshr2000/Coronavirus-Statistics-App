import React from 'react'
import { useParams } from 'react-router-dom'
import { useState, useEffect } from 'react'
import axios from 'axios'
import ProvinceDetails from './ProvinceDetails'

const ProvinceCountryData = () => {
    const [countryData, setCountryData] = useState([]);
    const {country} = useParams()

    // Fetch data from backend
    useEffect(() => {
        axios
        .get(`http://localhost:8080/api/v1/country/province_state/${country.replace(/%20/g, " ")}`)
        .then(result => {
            setCountryData(result.data)
            console.log(result.data)
        })
        .catch(error => console.log(error));
     }, []);

    return (
        <div>
            {countryData.map((curr) => {
                return (
                    <ProvinceDetails
                    key={curr.provinceState}
                    provinceState={curr.provinceState}
                    hasProvinceState={curr.hasProvinceState}
                    lastUpdate={curr.lastUpdate}
                    latitude={curr.latitude}
                    longitude={curr.longitude}
                    confirmed={curr.confirmed}
                    deaths={curr.deaths}
                    recovered={curr.recovered}
                    active={curr.active}
                    combinedKey={curr.combinedKey}
                    incidentRate={curr.incidentRate}
                    caseFatalityRatio={curr.caseFatalityRatio}
                    newCases={curr.newCases}
                    newDeaths={curr.newDeaths}
                    newRecovered={curr.newRecovered}
                    newActive={curr.newActive}                    
                    />
                )
            })}
        </div>
    )
}

export default ProvinceCountryData
