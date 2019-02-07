import React from "react"
import { NavLink } from "react-router-dom"
import ChangeRentService from "../rentACarAdmin/ChangeRentService"


const RentCarServiceManipulation = ({ rentACarServices, otvoriProzorRentACarServisa, prikaziCardZaDodavanjeRentACarServisa, iskljuciCardZaDodavanjeRentACarServica }) => {

    return (
        <div className="container">

            <h4 className="center red-text lighten-1">Rent servisi</h4>

            <div className="center">
                {
                    otvoriProzorRentACarServisa == false ?
                        <NavLink to="#" className="indigo-text lighten-1" onClick={(e) => { prikaziCardZaDodavanjeRentACarServisa(e) }} >Dodaj novi rent servis</NavLink>
                        :
                        <div>
                            <NavLink to="#" className="indigo-text lighten-1" onClick={(e) => { iskljuciCardZaDodavanjeRentACarServica(e) }}>Sakri prozor dodavanja</NavLink>
                            <ChangeRentService rentACarService={undefined} izmena={false} />
                        </div>
                }


                {rentACarServices.map(rentACarService => {
                    return (
                        <ChangeRentService brisanje={true} rentACarService={rentACarService} izmena={true} key={rentACarService.id} />
                    )
                })}

            </div>


        </div>
    );



}

export default RentCarServiceManipulation;