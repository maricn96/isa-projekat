const initState = {
    avioCompanies: [],
    destinations: []
}

const letoviReducer = (state = initState, action) => 
{
    switch(action.type)
    {
        case 'GET_ALL_AVIO_COMPANIES':
            return{
                ...state,
                avioCompanies: action.avioCompanies
            }
    }
    return state
}

export default letoviReducer;