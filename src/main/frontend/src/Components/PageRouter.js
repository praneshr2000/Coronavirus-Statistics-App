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

    useEffect(() => {
        axios
        .get('http://localhost:8080/api/v1/country/US')
        .then(result => {
            setCountryData(result.data);
        })
        .catch(error => console.log(error));

     }, []);

    return (
        <Router>
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
