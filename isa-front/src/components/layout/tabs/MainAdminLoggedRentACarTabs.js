import React from "react"
import { NavLink } from "react-router-dom";


const MainAdminLoggedRentACarTabs = () => {

    return (
        <div className="container">
            <div className="row container center">

                <ul className="tabs userLoggedTabsDiv">
                    <li className="tab col s6">
                        <NavLink to="/mainAdminRentCar/mainAdminRentServisi" className="white-text text-darken-4">Rent servisi</NavLink>
                    </li>
                    <li className="tab col s6">
                        <NavLink to="/mainAdminRentCar/mainAdminTipoviVozila" className="white-text text-darken-4">Tipovi vozila</NavLink>
                    </li>
                </ul>

            </div>
        </div>

    );

};

export default MainAdminLoggedRentACarTabs;