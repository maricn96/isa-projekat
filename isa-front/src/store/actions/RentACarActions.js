import axios from 'axios'

export const getAllCarTypes = () => {

    return (dispatch, getState) => {
        axios.get('http://localhost:8090/api/rentacar/carType/all')
            .then(res => {
                console.log(res.data);
                dispatch({ type: 'GET_ALL_CAR_TYPES', carTypes: res.data })
            })
    }

}


export const getOneRentService = (id) => {

    return (dispatch, getState) => {
        axios.get('http://localhost:8090/api/rentacar/rentACarService/' + id)
            .then(res => {
                console.log(res.data);
                dispatch({ type: 'GET_ONE_RENT_SERVICE', rentService: res.data })
            })
    }

}

export const getAllBranchOfficesByRentId = (id) => {

    return (dispatch, getState) => {

        axios.get('http://localhost:8090/api/rentacar/branchOffice/getAllByRentService/' + id)
            .then(res => {
                console.log(res.data);
                dispatch({ type: 'GET_ALL_BRANCHES_BY_RENT_SERVICE', branchOffices: res.data })
            })

    }
}

export const getAllCarsByRentId = (id) => {

    return (dispatch, getState) => {

        axios.get('http://localhost:8090/api/rentacar/car/getByRentService/' + id)
            .then(res => {
                console.log(res.data);
                dispatch({ type: 'GET_ALL_CARS_BY_RENT_SERVICE', cars: res.data })
            })
    }

}

export const getAllCarDiscountsByRentId = (id) => {

    return (dispatch, getState) => {

        axios.get('http://localhost:8090/api/rentacar/carDiscounts/rentService/' + id)
            .then(res => {
                console.log(res.data);
                dispatch({ type: 'GET_ALL_CAR_DISCOUNTS_BY_RENT_SERVICE', carDiscounts: res.data })
            })

    }

}

export const getAllRentACarServices = () => {

    return (dispatch, getState) => {

        axios.get('http://localhost:8090/api/rentacar/rentACarService/all')
            .then(res => {
                console.log(res.data);
                dispatch({ type: 'GET_ALL_RENT_A_CAR_SERVICES', rentServices: res.data })
            })

    }


}

export const createRentACarService = (rentService) => {

    return (dispatch, getState) => {

        axios.post('http://localhost:8090/api/rentacar/rentACarService/', { id: rentService.id, name: rentService.name, adress: rentService.adress, description: rentService.description })
            .then(res => {
                console.log(res);
                dispatch({ type: 'CREATE_RENT_A_CAR_SERVICE', rentService: res.data });
            })
    }

}

export const editRentACarService = (id, editedService) => {

    return (dispatch, getState) => {

        axios.put('http://localhost:8090/api/rentacar/rentACarService/' + id, { id: editedService.id, name: editedService.name, adress: editedService.adress, description: editedService.description })
            .then(res => {
                console.log(res);
                dispatch({ type: 'EDIT_RENT_A_CAR_SERVICE', editedService: res.data });
            })


    }

}

export const deleteRentACarService = (id) => {

    return (dispatch, getState) => {
        console.log("OVO JE ID" + id)
        axios.delete('http://localhost:8090/api/rentacar/rentACarService/' + id)
            .then(res => {
                console.log(res);
                dispatch({ type: 'DELETE_RENT_A_CAR_SERVICE', deletedService: res.data });
            })

    }

}


export const createCarType = (carType) => {

    return (dispatch, getState) => {

        axios.post('http://localhost:8090/api/rentacar/carType/', { id: carType.id, numberOfSeats: carType.numberOfSeats, modelYear: carType.modelYear, model: carType.model, brand: carType.brand, carType: carType.carType })
            .then(res => {
                console.log(res);
                dispatch({ type: 'CREATE_CAR_TYPE', createdCarType: res.data });
            })
    }
}


export const editCarType = (id, carType) => {

    return (dispatch, getState) => {

        axios.put('http://localhost:8090/api/rentacar/carType/' + id, { id: carType.id, numberOfSeats: carType.numberOfSeats, modelYear: carType.modelYear, model: carType.model, brand: carType.brand, carType: carType.carType })
            .then(res => {
                console.log(res);
                dispatch({ type: 'EDIT_CAR_TYPE', editedCarType: res.data });
            })
    }
}


export const deleteCarType = (id) => {

    return (dispatch, getState) => {

        axios.delete('http://localhost:8090/api/rentacar/carType/' + id)
            .then(res => {
                console.log(res);
                dispatch({ type: 'DELETE_CAR_TYPE', deletedCarType: res.data });
            })

    }
}


export const createBranchOffice = (branchOffice) => {

    return (dispatch, getState) => {

        axios.post('http://localhost:8090/api/rentacar/branchOffice/', { id: branchOffice.id, name: branchOffice.name, adress: branchOffice.adress, city: branchOffice.city, rentServiceDTO: { id: branchOffice.rentServiceDTO.id, name: branchOffice.rentServiceDTO.name, adress: branchOffice.rentServiceDTO.adress, city: branchOffice.rentServiceDTO.city } })
            .then(res => {
                console.log(res);
                dispatch({ type: 'CREATE_BRANCH_OFFICE', createtBranchOffice: res.data });
            })
    }
}

export const editBranchOffice = (id, branchOffice) => {

    return (dispatch, getState) => {

        axios.put('http://localhost:8090/api/rentacar/branchOffice/' + id, { id: branchOffice.id, name: branchOffice.name, adress: branchOffice.adress, city: branchOffice.city, rentServiceDTO: { id: branchOffice.rentServiceDTO.id, name: branchOffice.rentServiceDTO.name, adress: branchOffice.rentServiceDTO.adress, city: branchOffice.rentServiceDTO.city } })
            .then(res => {
                console.log(res);
                dispatch({ type: 'UPDATE_BRANCH_OFFICE', editedBranchOffice: res.data });
            })
    }
}


export const deleteBranchOffice = (id) => {

    return (dispatch, getState) => {

        axios.delete('http://localhost:8090/api/rentacar/branchOffice/' + id)
            .then(res => {
                console.log(res);
                dispatch({ type: 'DELETE_BRANCH_OFFICE', deleteBranchOffice: res.data });
            })

    }
}


export const createCar = (car) => {

    return (dispatch, getState) => {

        axios.post('http://localhost:8090/api/rentacar/car/', { id: car.id, rentPrice: car.rentPrice, carType: { id: car.carType.id, numberOfSeats: '', modelYear: '', model: '', brand: '', carType: '' }, rentService: { id: car.rentService.id, name: '', adress: '', description: '' }, branchOffice: { id: car.branchOffice.id, name: '', adress: '', city: '', rentServiceDTO: { id: car.rentService.id, adress: '', name: '', description: '' } } })
            .then(res => {
                console.log(res);
                dispatch({ type: 'CREATE_CAR', createdCar: res.data });
            })
    }
}

export const editCar = (id, car) => {

    return (dispatch, getState) => {

        axios.put('http://localhost:8090/api/rentacar/car/' + id, { id: car.id, rentPrice: car.rentPrice, carType: { id: car.carType.id, numberOfSeats: '', modelYear: '', model: '', brand: '', carType: '' }, rentService: { id: car.rentService.id, name: '', adress: '', description: '' }, branchOffice: { id: car.branchOffice.id, name: '', adress: '', city: '', rentServiceDTO: { id: car.rentService.id, adress: '', name: '', description: '' } } })
            .then(res => {
                console.log(res);
                dispatch({ type: 'UPDATE_CAR', editedCar: res.data });
            })
    }
}


export const deleteCar = (id) => {

    return (dispatch, getState) => {

        axios.delete('http://localhost:8090/api/rentacar/car/' + id)
            .then(res => {
                console.log(res);
                dispatch({ type: 'DELETE_CAR', deletedCar: res.data });
            })

    }
}

export const createCarDiscount = (carDiscount) => {

    return (dispatch, getState) => {

        axios.post('http://localhost:8090/api/rentacar/carDiscounts/', { id: carDiscount.id, dateFrom: carDiscount.dateFrom, dateTo: carDiscount.dateTo, carDiscountPrecentage: carDiscount.carDiscountPrecentage, carId: { id: carDiscount.carId.id, rentPrice: '', carType: { id: carDiscount.carId.carType.id, numberOfSeats: '', modelYear: '', model: '', brand: '', carType: '' }, rentService: { id: carDiscount.carId.rentService.id, name: '', adress: '', description: '' }, branchOffice: { id: carDiscount.carId.branchOffice.id, name: '', adress: '', city: '', rentServiceDTO: { id: carDiscount.carId.rentService.id, adress: '', name: '', description: '' } } } })
            .then(res => {
                console.log(res);
                dispatch({ type: 'CREATE_CAR_DISCOUNT', createdCarDiscount: res.data });
            })
    }
}

export const editCarDiscount = (id, carDiscount) => {

    return (dispatch, getState) => {

        axios.put('http://localhost:8090/api/rentacar/carDiscounts/' + id, { id: carDiscount.id, dateFrom: carDiscount.dateFrom, dateTo: carDiscount.dateTo, carDiscountPrecentage: carDiscount.carDiscountPrecentage, carId: { id: carDiscount.carId.id, rentPrice: '', carType: { id: carDiscount.carId.carType.id, numberOfSeats: '', modelYear: '', model: '', brand: '', carType: '' }, rentService: { id: carDiscount.carId.rentService.id, name: '', adress: '', description: '' }, branchOffice: { id: carDiscount.carId.branchOffice.id, name: '', adress: '', city: '', rentServiceDTO: { id: carDiscount.carId.rentService.id, adress: '', name: '', description: '' } } } })
            .then(res => {
                console.log(res);
                dispatch({ type: 'UPDATE_CAR_DISCOUNT', editedCarDiscount: res.data });
            })
    }
}


export const deleteCarDiscount = (id) => {

    return (dispatch, getState) => {

        axios.delete('http://localhost:8090/api/rentacar/carDiscounts/' + id)
            .then(res => {
                console.log(res);
                dispatch({ type: 'DELETE_CAR_DISCOUNT', deletedCarDiscount: res.data });
            })

    }
}

export const getSumOfIncomes = (rentId, dateFrom, dateTo) => {

    return (dispatch, getState) => {

        axios.get('http://localhost:8090/api/rentacar/rentACarService/getSumOfIncomes/' + rentId + '/' + dateFrom + '/' + dateTo)
            .then(res => {
                console.log(res)
                dispatch({ type: 'GET_SUM_OF_INCOMES', sumOfIncome: res.data })
            })

    }

}

export const getNumberOfFreeCars = (rentId, dateFrom, dateTo) => {

    return (dispatch, getState) => {

        axios.get('http://localhost:8090/api/rentacar/car/getFreeCars/' + dateFrom + '/' + dateTo)
            .then(res => {

                var freeCarsByRentService = res.data.filter(item => item.rentService.id == rentId)
                var freeCars = 0
                if (freeCarsByRentService) {
                    freeCars = freeCarsByRentService.length
                }
                dispatch({ type: 'GET_NUMBER_OF_FREE_CARS_ON_PERIOD', freeCars: freeCars })
            })

    }

}

export const getNumberOfTakenCars = (rentId, dateFrom, dateTo) => {

    return (dispatch, getState) => {

        axios.get('http://localhost:8090/api/rentacar/car/getReservedCars/' + dateFrom + '/' + dateTo)
            .then(res => {
                var takenCarsByRentService = res.data.filter(item => item.rentService.id == rentId)
                var takenCars = 0
                if (takenCarsByRentService) {
                    takenCars = takenCarsByRentService.length
                }
                dispatch({ type: 'GET_NUMBER_OF_TAKEN_CARS_ON_PERIOD', takenCars: takenCars })
            })

    }

}



export const getCarsOnDiscount = (dateFrom, dateTo) => {

    return (dispatch, getState) => {

        axios.get('http://localhost:8090/api/rentacar/car/currentlyDiscount/' + dateFrom + '/' + dateTo)
            .then(res => {
                dispatch({ type: 'GET_ALL_CARS_ON_DISCOUNT', carsOnDiscount: res.data })
            })
    }

}


export const getRentACarServiceId = (userId) => {

    return (dispatch, getState) => {

        axios.get('http://localhost:8095/api/purchases/adminLink/user/' + userId)
            .then(res => {
                dispatch(getOneRentService(res.data.serviceId));
                dispatch(getAllBranchOfficesByRentId(res.data.serviceId));
                dispatch(getAllCarDiscountsByRentId(res.data.serviceId));
                dispatch(getAllCarsByRentId(res.data.serviceId));

                dispatch({ type: 'GET_RENT_SERVICE_ID_BY_RENT_ADMIN_ID', rentACarServiceId: res.data.serviceId })
            })

    }
}
