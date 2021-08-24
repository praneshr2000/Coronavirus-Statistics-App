import './App.css';
import axios from 'axios'
import {useState} from 'react'
import {useEffect} from 'react'
import GlobalCountryData from './GlobalCountryData';

function App() {

  const [globalData, setGlobalData] = useState({globalConfirmedCases: 0, 
    globalDeaths: 0, 
    globalNewCases: 0, 
    globalNewDeaths: 0, 
    countryDataList: [
      {
        country: '',
        totalCountryConfirmedCases: 0,
        totalCountryConfirmedDeaths: 0,
        totalCountryNewConfirmedCases: 0,
        totalCountryNewConfirmedDeaths: 0
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
    .catch(error => console.log('lololol'));
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
    <div className="App">

      <div className='globalDataPage'>
        <h1 className='globalDataPageHeader'>
          COVID-19 GLOBAL DATA
        </h1>

        <h2 className="totalCases">
          Total Cases: {globalData.globalConfirmedCases.toLocaleString('en-US')}
        </h2>

        <h2 className="totalDeaths">
          Total Deaths: {globalData.globalDeaths.toLocaleString('en-US')}
        </h2>

        <h2 className="newCases">
          New Cases: {globalData.globalNewCases.toLocaleString('en-US')}
        </h2>

        <h2 className="newDeaths">
          New Deaths: {globalData.globalNewDeaths.toLocaleString('en-US')}
        </h2>

        <h3 className='searchFormHeader'>
          Search for a country
        </h3>

        <form>
          <input type="text" placeholder='Search' className='searchForm' onChange={handleChange} />
        </form>

        {
          // Filter out the map
          filteredSearchArray.map(c => {
            return(
              <GlobalCountryData
               key={c.country}
               country={c.country}
               totalCountryConfirmedCases={c.totalCountryConfirmedCases}
               totalCountryConfirmedDeaths={c.totalCountryConfirmedDeaths} 
               totalCountryNewConfirmedCases={c.totalCountryNewConfirmedCases} 
               totalCountryNewConfirmedDeaths={c.totalCountryNewConfirmedDeaths}
              />
            )
          })
        }
      </div>

    </div>
  );
}

export default App;
