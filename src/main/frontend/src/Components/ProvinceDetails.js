import React from 'react'

const ProvinceDetails = ({provinceState,
                    hasProvinceState,
                    lastUpdate,
                    latitude,
                    longitude,
                    confirmed,
                    deaths,
                    recovered,
                    active,
                    combinedKey,
                    incidentRate,
                    caseFatalityRatio,
                    newCases,
                    newDeaths,
                    newRecovered,
                    newActive}) => {
    return (
        <div>
            {"" + provinceState + " " + confirmed}
        </div>
    )
}

export default ProvinceDetails
