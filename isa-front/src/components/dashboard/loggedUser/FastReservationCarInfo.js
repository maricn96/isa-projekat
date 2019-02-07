import React from "react"
import { getOneRentService } from "../../../store/actions/RentACarActions";


const FastReservationCarInfo = ({ carOnDiscount }) => {

    return (
        <div className="container">
            <div className="card">
                <div className="card-content">
                    <h5 className="card-title indigo-text darken-2">{carOnDiscount.carType ? carOnDiscount.carType.brand : ''} {carOnDiscount.carType ? carOnDiscount.carType.model : ''}, {carOnDiscount.carType ? carOnDiscount.carType.modelYear : ''}</h5>

                    <div className="container">
                        <p>{carOnDiscount.branchOffice ? carOnDiscount.branchOffice.adress : ''} , {carOnDiscount.rentService ? carOnDiscount.rentService.name : ''}</p>
                        <p>Cena po danu : {carOnDiscount.rentPrice} , <strong className="red-text"> popust : {carOnDiscount.carDiscountPrecentage} % </strong> </p>
                        <p>Vazi od : {carOnDiscount.dateFrom} , do: {carOnDiscount.dateTo}</p>
                        <button className="btn orange darken-3 center">Rezervisi</button>
                    </div>
                </div>




            </div>
        </div>
    );

}

export default FastReservationCarInfo;