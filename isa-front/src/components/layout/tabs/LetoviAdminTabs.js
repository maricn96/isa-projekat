import React from 'react'
import {NavLink} from 'react-router-dom'

const LetoviAdminTabs = () =>
{
    return(
        <div className="row container">
            <ul className="tabs userLoggedTabsDiv">
                <li className="tab col s2">
                    <NavLink to="/letoviAdminCompanies" className="white-text text-darken-4">Avio kompanije</NavLink>
                </li>
                <li className="tab col s2">
                    <NavLink to="/letoviAdminFlights" className="white-text text-darken-4">Letovi</NavLink>
                </li>
                <li className="tab col s2">
                    <NavLink to="/letoviReports" className="white-text text-darken-4">Izvestaji</NavLink>
                </li>
                <li className="tab col s2">
                    <NavLink to="/letoviAdminStatistics" className="white-text text-darken-4">Statistika</NavLink>
                </li>
            </ul>
        </div>
    );
}

export default LetoviAdminTabs