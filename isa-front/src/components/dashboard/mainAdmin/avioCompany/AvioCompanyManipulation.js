import React from 'react'
import {NavLink} from 'react-router-dom'

import UpdateAvioCompany from './UpdateAvioCompany'

const AvioCompanyManipulation = ({avioCompanies}) =>
{
    return(
        <div className="container">
            <h4 className="center red-text lighten-1">Avio kompanije</h4>

            <div className="center">
                <NavLink to="#" className="indigo-text lighten-1">Dodaj novu avio kompaniju</NavLink>

                {avioCompanies.map(company =>
                    {
                        return(
                            <UpdateAvioCompany company={company} key={company.id}></UpdateAvioCompany>
                        )
                    })}

            </div>

        </div>
    );
}

export default AvioCompanyManipulation;