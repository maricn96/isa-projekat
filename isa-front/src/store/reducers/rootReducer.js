import hoteliReducer from './hoteliReducer'
import letoviReducer from './letoviReducer'
import rentACarReducer from './rentACarReducer'
import { combineReducers } from 'redux'
import userReducer from './userReducer';
import purchasesReducer from './purchasesReducer';

const rootReducer = combineReducers({
    hotel: hoteliReducer,
    let: letoviReducer,
    rentACar: rentACarReducer,
    user: userReducer,
    purchases: purchasesReducer
});

export default rootReducer;