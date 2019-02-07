const initState = {

    bonusPointsDiscounts: [],
    userShoppingCart: undefined,
    carReservation: undefined,
    hotelReservation: undefined,
    avioCompanyReservation: undefined,
    finalReservation: undefined,
    userReservations: undefined,
    rentCarReservations: [],
    rentACarRatings: [],
    carRatings: []

}

const purchasesReducer = (state = initState, action) => {
    switch (action.type) {
        case 'GET_ALL_BONUS_POINTS_DISCOUNTS':
            return {
                ...state,
                bonusPointsDiscounts: action.bonusPointsDiscounts
            }
        case 'GET_USER_SHOPPING_CART':
            return {
                ...state,
                userShoppingCart: action.userShoppingCart
            }

        case 'CREATE_USER_SHOPPING_CART':
            return {
                ...state,
                userShoppingCart: action.userShoppingCart
            }

        case 'ADD_CAR_RESERVATION_TO_SHOPPING_CART':
            return {
                ...state,
                userShoppingCart: action.userShoppingCart
            }

        case 'REMOVE_CAR_RESERVATION_FROM_SHOPPING_CART':
            var vrednost = action.userShoppingCart
            if (state.hotelReservation == null) {
                vrednost = undefined
            }
            return {
                ...state,
                userShoppingCart: vrednost,
                carReservation: vrednost

            }

        case 'GET_CAR_RESERVATION':
            return {
                ...state,
                carReservation: action.carReservation
            }
        case 'MAKE_RESERVATION':
            return {
                ...state,
                userShoppingCart: undefined,
                carReservation: undefined,
                avioCompanyReservation: undefined,
                hotelReservation: undefined,
                finalReservation: action.finalReservation
            }

        case 'GET_USER_RESERVATIONS':
            return {
                ...state,
                userReservations: action.userReservations,
                rentCarReservations: []
            }

        case 'GET_USER_RENT_A_CAR_RESERVATIONS':
            return {
                ...state,
                rentCarReservations: state.rentCarReservations.concat(action.rentCarReservations)
            }

        case 'CREATE_BONUS_POINTS_DISCOUNT':
            return {
                ...state,
                bonusPointsDiscounts: state.bonusPointsDiscounts.concat(action.bonusPointsDiscount)
            }

        case 'UPDATE_BONUS_POINTS_DISCOUNT':
            return {
                ...state,
                bonusPointsDiscounts: (state.bonusPointsDiscounts.filter(item => item.id !== action.editedDiscount.id)).concat(action.editedDiscount)
            }

        case 'DELETE_BONUS_POINTS_DISCOUNT':
            return {
                ...state,
                bonusPointsDiscounts: state.bonusPointsDiscounts.filter(item => item.id !== action.deletedDiscount.id)
            }

        case "RATE_RENT_A_CAR":
            return {
                ...state,
                rentACarRatings: (state.rentACarRatings.filter(rating => rating.userId !== action.rating.userId || rating.rentACarId !== action.rating.rentACarId)).concat(action.rating)
            }

        case "RATE_CAR":
            return {
                ...state,
                carRatings: (state.carRatings.filter(rating => rating.userId !== action.rating.userId || rating.carId !== action.rating.carId)).concat(action.rating)
            }

        case "GET_ALL_RENT_A_CAR_RATINGS":
            return {
                ...state,
                rentACarRatings: action.userRatings
            }

        case "GET_ALL_CAR_RATINGS":
            return {
                ...state,
                carRatings: action.userRatings
            }

    }
    return state
}

export default purchasesReducer;
