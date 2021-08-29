import React from 'react'
import { useParams } from 'react-router-dom'
import { useState, useEffect } from 'react'
import axios from 'axios'

const USData = () => {
    const [countryData, setCountryData] = useState({});

    useEffect(() => {
        axios
        .get('http://localhost:8080/api/v1/country/US')
        .then(result => {
            console.log(result.data)
            setCountryData(result.data)
        })
        .catch(error => console.log(error));
     }, []);

    return (
        <div>
            <h1>lol</h1>
        </div>
    )
}

export default USData
