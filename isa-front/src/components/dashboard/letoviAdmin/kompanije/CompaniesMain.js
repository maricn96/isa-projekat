import React from 'react'
import { NavLink } from 'react-router-dom'

import Company from './Company'


const CompaniesMain = () => {
    return (
        <div className="container">
            <div className="center">
                <NavLink to="/createCompany" className="indigo-text lighten-1">Dodaj novu kompaniju</NavLink><br />
                <br /><h1>Postojece aviokompanije</h1><br />
                <Company />
            </div>
        </div>
    );
}

export default CompaniesMain