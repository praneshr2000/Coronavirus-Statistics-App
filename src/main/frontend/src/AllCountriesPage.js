import React from 'react'
import axios from 'axios'
import { useState } from 'react'
import { useEffect } from 'react'
import GlobalCountryData from './Components/GlobalCountryData'

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

  const [searchText, setSearchText] = useState('');

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
  }, [searchText])


  return (
    <div className="AllCountriesDataPage">

      <div className='globalDataPage'>
        <h1 className='globalDataPageHeader'>
          COVID-19 GLOBAL DATA
        </h1>

        <div className="totalStats">
          <h2 className="totalCases">
            Total Cases: {globalData.globalConfirmedCases.toLocaleString('en-US')}
          </h2>

          <h2 className="totalDeaths">
            Total Deaths: {globalData.globalDeaths.toLocaleString('en-US')}
          </h2>
        </div>

        <div className="newStats">
          <h2 className="newCases">
            New Cases: {globalData.globalNewCases.toLocaleString('en-US')}
          </h2>

          <h2 className="newDeaths">
            New Deaths: {globalData.globalNewDeaths.toLocaleString('en-US')}
          </h2>
        </div>

        <div className="countrySearch">
          <h3 className='countrySearchText'>
            Search for a country
          </h3>

          <form>
            <input type="text" placeholder='Search' className='countrySearchInput' onChange={handleChange} />
          </form>
        </div>
        
        <div className="tableHeaderRow">
          <p className="countryNameTable">
            Country
          </p>

          <p className="totalCasesTable">
            Total Cases
          </p>

          <p className="totalDeathsTable">
            Total Deaths
          </p>

          <p className="newCasesTable">
            New Cases
          </p>

          <p className="newDeathsTable">New Deaths</p>
        </div>

        {
          // Filter out the map
          filteredSearchArray.map(c => {
            return(
              <GlobalCountryData
               key={c.country}
               flagURL={c.flagURL}
               country={c.country}
               totalCountryConfirmedCases={c.totalCountryConfirmedCases}
               totalCountryConfirmedDeaths={c.totalCountryConfirmedDeaths} 
               totalCountryNewConfirmedCases={c.totalCountryNewConfirmedCases} 
               totalCountryNewConfirmedDeaths={c.totalCountryNewConfirmedDeaths}
               hasProvinceStateData={c.hasProvinceStateData}
              />
            )
          })
        }
      </div>

    </div>
  );
}

export default AllCountriesPage
