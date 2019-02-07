import React from "react"
import { NavLink } from "react-router-dom"
import AdminInfo from "./AdminInfo"

const AdministratorsDashboard = () => {

    return (
        <div className="container">

            <div className="center">
                <NavLink to="#" className="indigo-text lighten-1">Dodaj novi tip administratora</NavLink>
            </div>
            <br />
            <div className="center">
                <NavLink to="#" className="indigo-text lighten-1">Dodaj novog administratora</NavLink>
            </div>

            <AdminInfo></AdminInfo>
            <AdminInfo></AdminInfo>
            <AdminInfo></AdminInfo>


        </div>
    );

};

export default AdministratorsDashboard;