import axios from 'axios'

export const getAllAvioCompanies = () =>
{
    return(dispatch, getState) => 
    {
        axios.get('http://localhost:8091/api/aviocompany/company/all').then(res =>
        {
            console.log(res.data)
            dispatch({ type: 'GET_ALL_AVIO_COMPANIES', avioCompanies: res.data})
        })
    }
}



export const getAllDestinations = () => 
{
    return(dispatch, getState) =>
    {
        axios.get('http://localhost:8091/api/aviocompany/destination/all').then(res =>
        {
            dispatch({type: 'GET_ALL_DESTINATIONS', destinations: res.data})
        })
    }
}