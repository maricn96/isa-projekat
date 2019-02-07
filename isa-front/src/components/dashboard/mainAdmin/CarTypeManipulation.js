import React from "react"
import { NavLink } from "react-router-dom"
import ChangeCarType from "./ChangeCarType";


const CarTypeManipulation = ({ carTypes, otvoriProzor, prikaziCardZaDodavanje, iskljuciCardZaDodavanje }) => {


    return (
        <div className="container">

            <h4 className="center red-text lighten-1">Tipovi vozila</h4>

            <div className="center">
                {
                    otvoriProzor == false ?
                        <NavLink to="#" className="indigo-text lighten-1" onClick={(e) => { prikaziCardZaDodavanje(e) }}>Dodaj novi tip vozila</NavLink>
                        :
                        <div>
                            <NavLink to="#" className="indigo-text lighten-1" onClick={(e) => { iskljuciCardZaDodavanje(e) }}>Sakri prozor dodavanja</NavLink>
                            <ChangeCarType izmena={false} carType={undefined} />
                        </div>

                }

                {carTypes.map(carType => {
                    return (
                        <ChangeCarType brisanje={true} izmena={true} carType={carType} key={carType.id} />
                    )
                })}

            </div>


        </div>
    );

};

export default CarTypeManipulation;