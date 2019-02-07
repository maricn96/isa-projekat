import React from "react"
import { NavLink } from "react-router-dom";
import ChangeCar from "./ChangeCar";


const CarManipulation = ({ rentACarService, cars, carTypes, branchOffices, otvoriProzor, prikaziCardZaDodavanje, iskljuciCardZaDodavanje }) => {

    return (
        <div className="container">

            <h4 className="center red-text lighten-1">Vozila</h4>

            <div className="center">
                {
                    otvoriProzor == false ?
                        <NavLink to="#" className="indigo-text lighten-1" onClick={(e) => { prikaziCardZaDodavanje(e) }}>Dodaj novo vozilo</NavLink>
                        :
                        <div>
                            <NavLink to="#" className="indigo-text lighten-1" onClick={(e) => { iskljuciCardZaDodavanje(e) }} >Sakri prozor za dodavanje</NavLink>
                            <ChangeCar rentACarService={rentACarService} izmena={false} car={undefined} carTypes={carTypes} branchOffices={branchOffices} />
                        </div>
                }

                {cars.map(car => {
                    return (
                        <ChangeCar rentACarService={rentACarService} brisanje={true} izmena={true} car={car} carTypes={carTypes} branchOffices={branchOffices} key={car.id} />
                    );
                })}
            </div>

        </div>
    );

};

export default CarManipulation;