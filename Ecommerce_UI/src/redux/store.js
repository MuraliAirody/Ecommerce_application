import { applyMiddleware, combineReducers, createStore } from 'redux';
import {thunk} from 'redux-thunk';
import authReducer from './Auth/reducer';
import customerProductReducer from './customer/product/reducer';
import cartReducer from './customer/cart/reducer';
import { orderReducer } from './customer/order/reducer';
import ReviewReducer from './customer/Review/Reducer';
import adminOrderReducer from './Admin/Orders/Reducer';

const rootReducers = combineReducers({
    auth: authReducer,
    customersProduct:customerProductReducer,
    cart:cartReducer,
    order:orderReducer,
    review:ReviewReducer,
    adminOrder:adminOrderReducer


});

export const store = createStore(rootReducers, applyMiddleware(thunk));
