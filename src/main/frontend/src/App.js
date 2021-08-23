import './App.css';
import axios from 'axios'
import {useState} from 'react'
import {useEffect} from 'react'

function App() {

  const [globalData, setGlobalData] = useState([]);

  // Fetch data from backend
  useEffect(() => {
    axios
    .get("http://localhost:8080/api/v1/home")
    .then(result => {
      setGlobalData(result.data)
      console.log(globalData)
    })
    .catch(error => console.log(error));
  }, []);


  return (
    <div className="App">

      <div className='globalDataPage'>
        <h1 className='globalDataPageHeader'>
          COVID-19 GLOBAL DATA
        </h1>

        <h2 className="totalCases">
          Total Cases: {globalData.globalConfirmedCases.toLocaleString()}
        </h2>

        <h2 className="totalDeaths">
          Total Deaths: {globalData.globalDeaths.toLocaleString()}
        </h2>

        <h2 className="newCases">
          New Cases: {globalData.globalNewCases.toLocaleString()}
        </h2>

        <h2 className="newDeaths">
          New Deaths: {globalData.globalNewDeaths.toLocaleString()}
        </h2>

        <h3 className='searchFormHeader'>
          Search for a country
        </h3>

        <form>
          <input type="text" placeholder='Search' className='searchForm' />
        </form>
      </div>

    </div>
  );
}

export default App;
