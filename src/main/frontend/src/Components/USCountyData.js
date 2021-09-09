import React from 'react'
import { useParams } from 'react-router'
import {Table, Col, Row} from 'react-bootstrap'
import { useEffect, useState } from 'react'
import './USCountyData.css'

const USCountyData = (props) => {
    const countiesList = props.counties;
    const stateParams = useParams().state;
    const [confirmedCases, setConfirmedCases] = useState(0);
    const [confirmedDeaths, setConfirmedDeaths] = useState(0);
    const [newCases, setNewCases] = useState(0);
    const [newDeaths, setNewDeaths] = useState(0);

    useEffect(() => {
        var tc = 0;
        var td = 0;
        var nc = 0;
        var nd = 0;

        // First check if countiesList[stateParams] is not undefined
        countiesList[stateParams] && countiesList[stateParams].map((curr) => {
            tc += curr.confirmed;
            td += curr.deaths;
            nc += curr.newCases;
            nd += curr.newDeaths;
        })

        setConfirmedCases(tc);
        setConfirmedDeaths(td);
        setNewCases(nc);
        setNewDeaths(nd);
        
    }, [])

    return (
        <div className="mainContainer">
            <h1 className="countyName">{stateParams}</h1>
            <Row>
                <Col>
                    <h3>Confirmed Cases: {confirmedCases.toLocaleString()}</h3>
                </Col>
                <Col>
                    <h3>Confirmed Deaths: {confirmedDeaths.toLocaleString()}</h3>
                </Col>
            </Row>

            <Row className="bottomRow">
                <Col>
                    <h3>New Cases: {newCases.toLocaleString()}</h3>                
                </Col>
                <Col>
                    <h3>New Deaths: {newDeaths.toLocaleString()}</h3>
                </Col>
            </Row>

            <Table className="table" striped bordered hover variant="dark" responsive>
                <thead>
                    <tr>
                    <th>County</th>
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
                    
                    {countiesList[stateParams] && countiesList[stateParams].map((curr) => {
                        return (
                            <tr key={curr.uscountyName}>
                                <td>{curr.uscountyName}</td>
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

export default USCountyData
