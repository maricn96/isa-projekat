import React from "react"
import { NavLink } from "react-router-dom"


const MainAdminLoggedTabs = () => {

    return (

        <div className="row container">

            <ul className="tabs userLoggedTabsDiv">
                <li className="tab col s3">
                    <NavLink to="/mainAdminAvio" className="white-text text-darken-4">Avio kompanije</NavLink>
                </li>
                <li className="tab col s3">
                    <NavLink to="/mainAdminHotel" className="white-text text-darken-4">Hoteli</NavLink>
                </li>
                <li className="tab col s3">
                    <NavLink to="/mainAdminRentCar" className="white-text text-darken-4">Rent servisi</NavLink>
                </li>
                <li className="tab col s3">
                    <NavLink to="/mainAdminBonusPoints" className="white-text text-darken-4">Definisanje popusta</NavLink>
                </li>
                <li className="tab col s3">
                    <NavLink to="/mainAdminAdministrators" className="white-text text-darken-4">Administratori</NavLink>
                </li>

            </ul>

        </div>

    );

};

export default MainAdminLoggedTabs;