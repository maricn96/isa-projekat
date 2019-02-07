import React from 'react'
import {NavLink} from 'react-router-dom'

const MainAdminLoggedLetoviTabs = () =>
{
    return(
        <div className="container">
            <div className="row container center">
                <ul className="tabs userLoggedTabsDiv">
                    <li className="tab col s6">
                        <NavLink to="/mainAdminAvio/mainAdminAvioCompanies" className="white-text text-darken-4">Avionske kompanije</NavLink>
                    </li>
                    <li className="tab col s6">
                        <NavLink to="/mainAdminAvio/mainAdminDestinations" className="white-text text-darken-4">Destinacije</NavLink>
                    </li>
                </ul>
            </div>
        </div>
    );
}

export default MainAdminLoggedLetoviTabs;

