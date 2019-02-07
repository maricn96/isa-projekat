import React from 'react'
import {BrowserRouter, Route} from 'react-router-dom'
import CompaniesMain from './kompanije/CompaniesMain'
import FlightsMain from './letovi/FlightsMain'
import StatisticsMain from './statistika/StatisticsMain'

import LetoviAdminTabs from "../../layout/tabs/LetoviAdminTabs"

const LetoviAdminDashboard = () =>
{
    return(
        <div>
            <BrowserRouter>
                <div>
                    <div>
                        <Route path="/" component={LetoviAdminTabs}></Route>
                    </div>
                    <div>
                        <Route path="/letoviAdminCompanies" component={CompaniesMain}></Route>
                        <Route path="/letoviAdminFlights" component={FlightsMain}></Route>
                        <Route path="/letoviAdminStatistics" component={StatisticsMain}></Route>
                    </div>
                </div>
            </BrowserRouter>
        </div>
    );
}

export default LetoviAdminDashboard