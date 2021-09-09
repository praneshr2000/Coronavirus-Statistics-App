import React from 'react'
import { useState, useEffect } from 'react'
import axios from 'axios'
import {Col, Container, Row, Table} from 'react-bootstrap'
import './USData.css'

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
        // Fetch the US data
        axios
        .get('http://localhost:8080/api/v1/country/US')
        .then(result => {
            setCountryData(result.data);
            var tc = 0;
            var td = 0;
            var nc = 0;
            var nd = 0;

            // Map though each state and each county
            // and add up all the stats
            // Together, they will make up the entire US's
            // numbers
            Object.keys(result.data).map(function(state){
                result.data[state].map((county) => {
                    tc += county.confirmed;
                    td += county.deaths;
                    nc += county.newCases;
                    nd += county.newDeaths;
                })
            })
            
            // Set entire US data
            setConfirmedCases(tc);
            setConfirmedDeaths(td);
            setNewCases(nc);
            setNewDeaths(nd);

            // Set the flag URL
            setFlagURL("https://flagcdn.com/h120/us.png");

            
        })
        .catch(error => console.log(error));
     }, []);

    return (
        <div className="mainContainer">
            <h1 className="countryName">US</h1>
            <Row className="imageRow">
                <img className="image" src={flagURL} alt="" />
            </Row>
            
            <Row className="topRow">
                <Col>
                    <h3>Confirmed Cases: {confirmedCases.toLocaleString()}</h3>
                </Col>
                <Col>
                    <h3>Confirmed Deaths: {confirmedDeaths.toLocaleString()}</h3>
                </Col>
            </Row>
            
            <Row className="bRow">
                <Col>
                    <h3>New Cases: {newCases.toLocaleString()}</h3>
                </Col>
                <Col>
                    <h3>New Deaths: {newDeaths.toLocaleString()}</h3>
                </Col>
            </Row>
            
             <div className="tableClass" >
                <Table className="tableState" striped bordered hover variant="dark" responsive>
                    <thead>
                        <tr>
                        <th className="tableHeader">State</th>
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
                                            <a className="aClass" href={countyListURL}>
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
