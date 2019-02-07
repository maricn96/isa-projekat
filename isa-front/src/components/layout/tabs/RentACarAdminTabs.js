import React from "react"
import { NavLink } from "react-router-dom"



const RentACarAdminTabs = () => {

    return (

        <div className="row container">

            <ul className="tabs userLoggedTabsDiv">
                <li className="tab col s3">
                    <NavLink to="/rentACarStatistic" className="white-text text-darken-4">Statistika</NavLink>
                </li>
                <li className="tab col s3">
                    <NavLink to="/rentAdminRentService" className="white-text text-darken-4">Rent servis</NavLink>
                </li>
                <li className="tab col s3">
                    <NavLink to="/rentAdminCars" className="white-text text-darken-4">Vozila</NavLink>
                </li>
                <li className="tab col s3">
                    <NavLink to="/rentAdminDiscounts" className="white-text text-darken-4">Popusti</NavLink>
                </li>

            </ul>

        </div>

    );



};

export default RentACarAdminTabs;