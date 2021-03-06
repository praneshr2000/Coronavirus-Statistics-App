import React from 'react'
import { useState, useEffect } from 'react'
import axios from 'axios'
import {Col, Row, Table, Spinner} from 'react-bootstrap'
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
    const [completedLoading, setCompletedLoading] = useState(false);

    useEffect(() => {
        // Fetch the US data
        axios
        .get('http://covidapp-env.eba-htewes5z.us-east-2.elasticbeanstalk.com/api/v1/country/US')
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
            
            setCompletedLoading(true);
            
        })
        .catch(error => console.log(error));
     }, []);

    return (
        
        <div className="mainContainer">

            {!completedLoading?
                /*
                Code for centering the loading component. Taken from
                https://stackoverflow.com/questions/396145/how-can-i-vertically-center-a-div-element-for-all-browsers-using-css
                */
                <div className="outer">
                    <div className="middle">
                        <div className="inner">
                            <div className="fullScreen">
                                <Spinner className="spinner" animation="border" size='lg' role="status">
                                <span className="sr-only">Loading...</span>
                                </Spinner>
                            </div>
                        </div>
                    </div>
                </div>
            : <div className="mainContainer"/>}

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
                            ).map(function(state, index) {
                                var countyListURL = `/countries/US/${state}`;
                                return (
                                    <tr key={index}>
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
