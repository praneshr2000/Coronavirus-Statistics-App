import React from 'react'
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import AllCountriesPage from '../AllCountriesPage';
import App from '../App';
import MailingListPage from '../MailingListPage'
import NoProvinceCountryData from './NoProvinceCountryData';
import ProvinceCountryData from './ProvinceCountryData'
import USData from './USData';

const PageRouter = () => {
    return (
        <Router>
            <Switch>
                <Route path='/' component={App} exact/>
                <Route path='/mailing_list' component={MailingListPage}></Route>
                <Route path='/countries/all' component={AllCountriesPage}/>
                <Route path='/countries/US' component={USData}></Route>
                <Route path='/countries/province_state/:country' component={ProvinceCountryData}></Route>
                <Route path='/countries/:country' component={NoProvinceCountryData}></Route>
            </Switch>
        </Router>
        
    )
}

export default PageRouter
