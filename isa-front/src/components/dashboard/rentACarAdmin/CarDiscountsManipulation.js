import React from "react"
import ChangeCarDiscount from "./ChangeCarDiscount";
import { NavLink } from "react-router-dom"

const CarDiscountManipulation = ({ carDiscounts, cars, otvoriProzor, prikaziCardZaDodavanje, iskljuciCardZaDodavanje }) => {

    return (
        <div className="container">

            <h4 className="center red-text lighten-1">Popusti na vozila</h4>

            <div className="center">
                {
                    otvoriProzor == false ?
                        <NavLink to="#" onClick={(e) => { prikaziCardZaDodavanje(e) }} className="indigo-text lighten-1">Dodaj novi popust</NavLink>
                        :
                        <div>
                            <NavLink to="#" onClick={(e) => { iskljuciCardZaDodavanje(e) }} className="indigo-text lighten-1">Sakrij prozor za dodavanje</NavLink>
                            <ChangeCarDiscount izmena={false} cars={cars} carDiscount={undefined} />
                        </div>
                }

                {carDiscounts.map(carDiscount => {

                    return (
                        <ChangeCarDiscount brisanje={true} izmena={true} key={carDiscount.id} cars={cars} carDiscount={carDiscount} />
                    );

                })}
            </div>

        </div>
    );

};

export default CarDiscountManipulation;