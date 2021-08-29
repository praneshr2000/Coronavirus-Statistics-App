import React from 'react'
import { useParams } from 'react-router-dom'
import { useState, useEffect } from 'react'
import axios from 'axios'

const NoProvinceCountryData = () => {
    const [countryData, setCountryData] = useState({});
    const {country} = useParams()

    // Fetch data from backend
    useEffect(() => {
        axios
        .get(`http://localhost:8080/api/v1/country/${country.replace(/%20/g, " ")}`)
        .then(result => {
            setCountryData(result.data)
        })
        .catch(error => console.log(error));
     }, []);

    return (
        <div>
            <h1>{countryData.latitude}</h1>
        </div>
    )
}

export default NoProvinceCountryData
