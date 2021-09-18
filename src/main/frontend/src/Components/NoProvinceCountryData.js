import React from 'react'
import { useParams } from 'react-router-dom'
import { useState, useEffect } from 'react'
import axios from 'axios'
import { Container, Row, Col, Spinner } from 'react-bootstrap'
import './NoProvinceCountryData.css'

const NoProvinceCountryData = () => {
    const [countryData, setCountryData] = useState({
                                            countryName: "",
                                            lastUpdate: "",
                                            latitude: 0.0,
                                            longitude: 0.0,
                                            confirmed: 0,
                                            deaths: 0,
                                            recovered: 0,
                                            active: 0,
                                            incidentRate: 0.0,
                                            caseFatalityRatio: 0.0,
                                            newCases: 0,
                                            newDeaths: 0,
                                            flagURL: "",
                                        });
    const {country} = useParams()
    const [completedLoading, setCompletedLoading] = useState(false);

    // Fetch data from backend
    useEffect(() => {
        // Get the current country's data from the backend
        // hosted on aws elastic beanstalk
        axios
        .get(`http://covidapp-env.eba-htewes5z.us-east-2.elasticbeanstalk.com/api/v1/country/${country.replace(/%20/g, " ")}`)
        .then(result => {
            setCountryData(result.data)
            setCompletedLoading(true);
        })
        .catch(error => console.log(error));
     }, []);

    return (
        <Container fluild="sm" className="container">

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
            : <Container className="container"/>}
            
            <h1 className="header">{countryData.countryName}</h1>

            <img className="image" src={countryData.flagURL} alt="" />

            <Row className="row1">
                <Col>
                    <h3 className="confirmed">
                        Confirmed Cases: {countryData.confirmed.toLocaleString()}
                    </h3>
                </Col>

                <Col>
                    <h3 className="deaths">
                        Confirmed Deaths: {countryData.deaths.toLocaleString()}
                    </h3>
                </Col>
            </Row>


             <Row className="row">
                <Col>
                    <h3 className="newConfirmed">
                        New Cases: {countryData.newCases.toLocaleString()}
                    </h3>
                </Col>

                <Col>
                    <h3 className="newDeaths">
                        New Deaths: {countryData.newDeaths.toLocaleString()}
                    </h3>
                </Col>
            </Row>

             <Row className="row">
                <Col>
                    <h3 className="incidentRate">
                        Incident Rate: {countryData.incidentRate.toLocaleString()}
                    </h3>
                </Col>

                <Col>
                    <h3 className="caseFatalityRatio">
                        Case Fatality Ratio: {countryData.caseFatalityRatio.toLocaleString()}
                    </h3>
                </Col>
            </Row>

            <Row className="row">
                <Col>
                    <h3 className="latitude">
                        Latitude: {countryData.latitude.toLocaleString()}
                    </h3>
                </Col>

                <Col>
                    <h3 className="longitude">
                        Longitude: {countryData.longitude.toLocaleString()}
                    </h3>
                </Col>
            </Row>
            

            

        </Container>
    )
}

export default NoProvinceCountryData
