import React from 'react'
import axios from 'axios'
import { useState } from 'react'
import { useEffect } from 'react'
import './AllCountriesPage.css'
import {Table, Col, Row} from 'react-bootstrap'

const AllCountriesPage = () => {
  const [globalData, setGlobalData] = useState({globalConfirmedCases: 0, 
  globalDeaths: 0, 
  globalNewCases: 0, 
  globalNewDeaths: 0, 
  countryDataList: [
    {
      country: '',
      flagURL: '',
      totalCountryConfirmedCases: 0,
      totalCountryConfirmedDeaths: 0,
      totalCountryNewConfirmedCases: 0,
      totalCountryNewConfirmedDeaths: 0,
      hasProvinceStateData: false
    }
  ]
  });

  const [searchText, setSearchText] = useState("");

  const [filteredSearchArray, setFilteredSearchArray] = useState([]);
                                      

  // Fetch data from backend
  useEffect(() => {
    axios
    .get("http://localhost:8080/api/v1/home")
    .then(result => {
      setGlobalData(result.data)
    })
    .catch(error => console.log(error));
  }, []);

  // When the value in the search box is changed,
  // useState for search text
  const handleChange = (e) => {
    setSearchText(e.target.value);
  }

  // Every time search text is updated, we have to filter out the
  // country list based on the search text.
  useEffect(() => {
    const dataList = globalData.countryDataList
    setFilteredSearchArray(dataList.filter(countryData => 
      countryData.country.toLowerCase().includes(searchText.toLowerCase())));
  }, [searchText, globalData])


  return (
    <div className="globalDataContainer">
      <h1>CORONAVIRUS GLOBAL DATA</h1>
    
      <Row className="topRowStats">
        <Col>
          Total Cases: {globalData.globalConfirmedCases.toLocaleString('en-US')}
        </Col>
        <Col>
          Total Deaths: {globalData.globalDeaths.toLocaleString('en-US')}
        </Col>
      </Row>

      <Row className="bottomRowStats">
        <Col>
          New Cases: {globalData.globalNewCases.toLocaleString('en-US')}
        </Col>
        <Col>
          New Deaths: {globalData.globalNewDeaths.toLocaleString('en-US')}
        </Col>
      </Row>

      <div className="countrySearch">
        <h3 className='countrySearchText'>
          Search for a country
        </h3>

        <div className="searchForm">
          <input type="text" placeholder='Search here...' className='countrySearchInput' onChange={handleChange} />
        </div>
      </div>

      <Table className="allCountriesTable" striped bordered hover variant="dark" responsive>
        <thead className="tableHeader">
          <tr>
            <th></th>
            <th>Country</th>
            <th>Confirmed Cases</th>
            <th>Confirmed Deaths</th>
            <th>New Cases</th>
            <th>New Deaths</th>
          </tr>
        </thead>
            
        <tbody>
        {                        
          filteredSearchArray.map((c) => {
          var countryPageURL = "";
          const URLCountry = c.country.replace(/ /g, "%20")
          if (c.hasProvinceStateData) {
            if (c.country === "US") {
              countryPageURL = "../countries/US";
            } else {
              countryPageURL = `../countries/province_state/${URLCountry}`;
            }
          } else {
            countryPageURL = `../countries/${URLCountry}`;
          }

          return (
            <tr className="tableRow" key={c.country}>
              <td className="flagCol"><a href={countryPageURL}>
                  <img className="rowFlag" src={c.flagURL} alt="" /></a></td>
              <td className="countryCol">
                <a href={countryPageURL}>
                  {c.country}
                </a>
              </td>
              <td><a href={countryPageURL}>{c.totalCountryConfirmedCases.toLocaleString()}</a></td>
              <td><a href={countryPageURL}>{c.totalCountryConfirmedDeaths.toLocaleString()}</a></td>
              <td><a href={countryPageURL}>{c.totalCountryNewConfirmedCases.toLocaleString()}</a></td>
              <td><a href={countryPageURL}>{c.totalCountryNewConfirmedDeaths.toLocaleString()}</a></td>
            </tr>
          ) 
        })}
        </tbody>
      </Table>

    </div>
  );
}

export default AllCountriesPage
