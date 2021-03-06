import React from 'react'
import { useParams } from 'react-router-dom'
import { useState, useEffect } from 'react'
import axios from 'axios'
import {Table, Row, Col, Spinner} from 'react-bootstrap';
import './ProvinceCountryData.css'

const ProvinceCountryData = () => {
    const [countryData, setCountryData] = useState([{
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
                                        }]);
    const [countryConfirmedCases, setCountryConfirmedCases] = useState(0);
    const [countryConfirmedDeaths, setCountryConfirmedDeaths] = useState(0);
    const [countryNewConfirmedCases, setCountryNewConfirmedCases] = useState(0);
    const [countryNewConfirmedDeaths, setCountryNewConfirmedDeaths] = useState(0);
    const [completedLoading, setCompletedLoading] = useState(false);
    const {country} = useParams()

    // Fetch data from backend
    useEffect(() => {

        // Fetch data for country with province data
        axios
        .get(`http://covidapp-env.eba-htewes5z.us-east-2.elasticbeanstalk.com/api/v1/country/province_state/${country.replace(/%20/g, " ")}`)
        .then(result => {
            setCountryData(result.data)
        })
        .catch(error => console.log(error));

        // Fetch all countries page data
        axios
        .get("http://covidapp-env.eba-htewes5z.us-east-2.elasticbeanstalk.com/api/v1/home")
        .then(result => {
            // Map through the entire list and find the desired country
            // and then set the stats
            result.data.countryDataList.map((curr) => {
                
                if (curr.country === country) {
                    setCountryConfirmedCases(curr.totalCountryConfirmedCases)
                    setCountryConfirmedDeaths(curr.totalCountryConfirmedDeaths)
                    setCountryNewConfirmedCases(curr.totalCountryNewConfirmedCases)
                    setCountryNewConfirmedDeaths(curr.totalCountryNewConfirmedDeaths)
                }
            })

            setCompletedLoading(true);
        })
        .catch(error => console.log(error));
        
     }, []);

    return (
        <div className="mainDiv">

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
            : <div className="mainDiv"/>}
            
            <h1 className="countryName" >{country}</h1>

            <Row className="imageRow">
                <img  src={countryData[0].flagURL} alt="" />
            </Row>
            
            <Row className="confirmed">
                <Col>
                    <h3>Confirmed Cases: {countryConfirmedCases.toLocaleString()}</h3>                
                </Col>

                <Col>
                    <h3>Confirmed Deaths: {countryConfirmedDeaths.toLocaleString()}</h3>
                </Col>
            </Row>

            <Row className="new">
                <Col>
                    <h3>New Cases: {countryNewConfirmedCases.toLocaleString()}</h3>
                </Col>

                <Col>
                    <h3> New Deaths: {countryNewConfirmedDeaths.toLocaleString()}</h3>
                </Col>
            </Row>

            <Table className="table" striped bordered hover variant="dark" responsive>
                <thead>
                    <tr>
                    <th>Province/State</th>
                    <th>Confirmed Cases</th>
                    <th>Confirmed Deaths</th>
                    <th>New Cases</th>
                    <th>New Deaths</th>
                    <th>Incident Rate</th>
                    <th>Case Fatality Ratio</th>
                    <th>Latitude</th>
                    <th>Longitude</th>
                    </tr>
                </thead>
                <tbody>
                    {countryData.map((curr, index) => {
                        return (
                            <tr key={index}>
                                <td>{curr.provinceState}</td>
                                <td>{curr.confirmed.toLocaleString()}</td>
                                <td>{curr.deaths.toLocaleString()}</td>
                                <td>{curr.newCases.toLocaleString()}</td> 
                                <td>{curr.newDeaths.toLocaleString()}</td>
                                <td>{curr.incidentRate.toLocaleString()}</td>
                                <td>{curr.caseFatalityRatio.toLocaleString()}</td>
                                <td>{curr.latitude}</td>
                                <td>{curr.longitude}</td>
                            </tr>
                        )
                    })}
                </tbody>                
            </Table>
        </div>
    )
}

export default ProvinceCountryData
