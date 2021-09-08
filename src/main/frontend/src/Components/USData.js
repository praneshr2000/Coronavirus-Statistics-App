import React from 'react'
import { useParams } from 'react-router-dom'
import { useState, useEffect } from 'react'
import axios from 'axios'
import {Col, Container, Row, Table} from 'react-bootstrap'
import USCountyData from './USCountyData'

const USData = () => {
    const [countryData, setCountryData] = useState({"": [{
                                            provinceState: "",
                                            countryName: "",
                                            hasProvinceState: false,
                                            lastUpdate: "",
                                            latitude: 0.0,
                                            longitude: 0.0,
                                            confirmed: 0,
                                            deaths: 0,
                                            recovered: 0,
                                            active: 0,
                                            combinedKey: "",
                                            incidentRate: 0.0,
                                            caseFatalityRatio: 0.0,
                                            newCases: 0,
                                            newDeaths: 0,
                                            newRecovered: 0,
                                            newActive: 0,
                                            iso2: "",
                                            flagURL: "",
                                            usa: false,
                                            uscountyName: "",
                                            fipscode: -1
    }]});

    const [confirmedCases, setConfirmedCases] = useState(0);
    const [confirmedDeaths, setConfirmedDeaths] = useState(0);
    const [newCases, setNewCases] = useState(0);
    const [newDeaths, setNewDeaths] = useState(0);
    const [flagURL, setFlagURL] = useState("");

    useEffect(() => {
        axios
        .get('http://localhost:8080/api/v1/country/US')
        .then(result => {
            setCountryData(result.data);
            var tc = 0;
            var td = 0;
            var nc = 0;
            var nd = 0;

            Object.keys(result.data).map(function(state){
                result.data[state].map((county) => {
                    tc += county.confirmed;
                    td += county.deaths;
                    nc += county.newCases;
                    nd += county.newDeaths;
                })
            })
            
            setConfirmedCases(tc);
            setConfirmedDeaths(td);
            setNewCases(nc);
            setNewDeaths(nd);

            setFlagURL("https://flagcdn.com/h120/us.png");

            
        })
        .catch(error => console.log(error));
     }, []);

    return (
        <div>
            <h1>US</h1>
            <img src={flagURL} alt="" />
            <h3>Confirmed Cases: {confirmedCases.toLocaleString()}</h3>
            <h3>Confirmed Deaths: {confirmedDeaths.toLocaleString()}</h3>
            <h3>New Cases: {newCases.toLocaleString()}</h3>
            <h3>New Deaths: {newDeaths.toLocaleString()}</h3>
             <div >
                <Table className="table" striped bordered hover variant="dark" responsive>
                    <thead>
                        <tr>
                        <th>State</th>
                        </tr>
                    </thead>

                    <tbody>
                        {
                            Object.keys(countryData).filter(function(s) {
                                if (s === 'Recovered') {
                                    return false;
                                }
                                return true;
                            }
                            ).map(function(state) {
                                var countyListURL = `/countries/US/${state}`;
                                return (
                                    <tr key={state}>
                                        <td>
                                            <a href={countyListURL}>
                                                {state}
                                            </a>
                                        </td>
                                    </tr>
                                )
                            })
                        }
                    </tbody> 
                </Table>
            </div>
        
        </div>
    )
}

export default USData
