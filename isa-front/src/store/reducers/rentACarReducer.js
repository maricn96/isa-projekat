const initState = {

    carTypes: [],
    cars: [],
    incomes: [],
    branchOffices: [],
    rentServices: [],
    rentService: {},
    carDiscounts: [],
    sumOfIncome: undefined,
    takenCars: undefined,
    freeCars: undefined,
    carOnDiscounts: undefined,
    rentACarServiceId: undefined

}

const rentACarReducer = (state = initState, action) => {
    switch (action.type) {
        case 'GET_ALL_CAR_TYPES':
            return {
                ...state,
                carTypes: action.carTypes
            }
        case 'GET_ONE_RENT_SERVICE':
            return {
                ...state,
                rentService: action.rentService
            }
        case 'GET_ALL_BRANCHES_BY_RENT_SERVICE':
            return {
                ...state,
                branchOffices: action.branchOffices
            }
        case 'GET_ALL_CARS_BY_RENT_SERVICE':
            return {
                ...state,
                cars: action.cars
            }

        case 'GET_ALL_CAR_DISCOUNTS_BY_RENT_SERVICE':
            return {
                ...state,
                carDiscounts: action.carDiscounts
            }
        case 'GET_ALL_RENT_A_CAR_SERVICES':
            return {
                ...state,
                rentServices: action.rentServices
            }
        case 'CREATE_RENT_A_CAR_SERVICE':
            return {
                ...state,
                rentServices: state.rentServices.concat(action.rentService)
            }
        case 'EDIT_RENT_A_CAR_SERVICE':
            return {
                ...state,
                rentServices: (state.rentServices.filter(item => item.id !== action.editedService.id)).concat(action.editedService)
            }

        case 'DELETE_RENT_A_CAR_SERVICE':
            return {
                ...state,
                rentServices: (state.rentServices.filter(item => item.id !== action.deletedService.id))

            }

        case 'CREATE_CAR_TYPE':
            return {
                ...state,
                carTypes: state.carTypes.concat(action.createdCarType)
            }

        case 'EDIT_CAR_TYPE':
            return {
                ...state,
                carTypes: (state.carTypes.filter(item => item.id !== action.editedCarType.id)).concat(action.editedCarType)
            }

        case 'DELETE_RENT_A_CAR_SERVICE':
            return {
                ...state,
                carTypes: (state.carTypes.filter(item => item.id !== action.deletedCarType.id))
            }

        case 'CREATE_BRANCH_OFFICE':
            return {
                ...state,
                branchOffices: state.branchOffices.concat(action.createtBranchOffice)
            }
        case 'UPDATE_BRANCH_OFFICE':
            return {
                ...state,
                branchOffices: (state.branchOffices.filter(item => item.id !== action.editedBranchOffice.id)).concat(action.editedBranchOffice)
            }
        case 'DELETE_BRANCH_OFFICE':
            return {
                ...state,
                branchOffices: state.branchOffices.filter(item => item.id !== action.deleteBranchOffice.id)
            }
        case 'CREATE_CAR':
            return {
                ...state,
                cars: state.cars.concat(action.createdCar)
            }
        case 'UPDATE_CAR':
            return {
                ...state,
                cars: (state.cars.filter(item => item.id !== action.editedCar.id)).concat(action.editedCar)
            }
        case 'DELETE_CAR':
            return {
                ...state,
                cars: state.cars.filter(item => item.id !== action.deletedCar.id)
            }

        case 'CREATE_CAR_DISCOUNT':
            return {
                ...state,
                carDiscounts: state.carDiscounts.concat(action.createdCarDiscount)
            }
        case 'UPDATE_CAR_DISCOUNT':
            return {
                ...state,
                carDiscounts: (state.carDiscounts.filter(item => item.id !== action.editedCarDiscount.id)).concat(action.editedCarDiscount)
            }
        case 'DELETE_CAR_DISCOUNT':
            return {
                ...state,
                carDiscounts: state.carDiscounts.filter(item => item.id !== action.deletedCarDiscount.id)
            }

        case 'GET_SUM_OF_INCOMES':
            return {
                ...state,
                sumOfIncome: action.sumOfIncome
            }

        case 'GET_NUMBER_OF_TAKEN_CARS_ON_PERIOD':
            return {
                ...state,
                takenCars: action.takenCars
            }
        case 'GET_NUMBER_OF_FREE_CARS_ON_PERIOD':
            return {
                ...state,
                freeCars: action.freeCars
            }

        case 'GET_ALL_CARS_ON_DISCOUNT':
            return {
                ...state,
                carsOnDiscount: action.carsOnDiscount

            }

        case 'GET_RENT_SERVICE_ID_BY_RENT_ADMIN_ID':
            return {
                ...state,
                rentACarServiceId: action.rentACarServiceId
            }
    }
    return state
}

export default rentACarReducer;