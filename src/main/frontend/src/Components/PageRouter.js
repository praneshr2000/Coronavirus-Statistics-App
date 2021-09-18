import React from 'react'
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import AllCountriesPage from '../AllCountriesPage';
import App from '../App';
import MailingListRegistrationPage from '../MailingListRegistrationPage'
import MailingListUnsubscribePage from '../MailingListUnsubscribePage'
import NoProvinceCountryData from './NoProvinceCountryData';
import ProvinceCountryData from './ProvinceCountryData'
import USData from './USData';
import USCountyData from './USCountyData';
import { useState, useEffect } from 'react';
import axios from 'axios'
import { Spinner } from 'react-bootstrap';

const PageRouter = () => {

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

    const [completedLoading, setCompletedLoading] = useState(false);

    useEffect(() => {
        axios
        .get('http://covidapp-env.eba-htewes5z.us-east-2.elasticbeanstalk.com/api/v1/country/US')
        .then(result => {
            setCountryData(result.data);
            setCompletedLoading(true);
        })
        .catch(error => console.log(error));

     }, []);

    return (
        <Router className="routerClass">
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
            : <div className="routerClass"/>}

            <Switch>
                <Route path='/' component={App} exact/>
                <Route path='/mailing_list/register' component={MailingListRegistrationPage}></Route>
                <Route path='/mailing_list/unsubscribe' component={MailingListUnsubscribePage}></Route>
                <Route path='/countries/all' component={AllCountriesPage}/>
                {// While calling the USCountyData component, pass in the the entrie US country data
                 // so that it can be parsed later on
                }
                <Route path='/countries/US/:state' component={() => <USCountyData counties={countryData}/>} />
                <Route path='/countries/US' component={USData}></Route>
                <Route path='/countries/province_state/:country' component={ProvinceCountryData}></Route>
                <Route path='/countries/:country' component={NoProvinceCountryData}></Route>
            </Switch>
        </Router>
        
    )
}

export default PageRouter
