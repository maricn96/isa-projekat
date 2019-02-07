import React, { Component } from "react"
import UserLoggedTabs from "../../layout/tabs/UserLoggedTabs";
import { BrowserRouter, Route, NavLink } from "react-router-dom"
import RentACarPretraga from "../classicUser/RentACarPretraga";
import RentACarIzlistavanje from "../classicUser/RantACarIzlistavanje";
import Vozila from "../classicUser/Vozila.js"
import Filijale from "../classicUser/Filijale.js"
import FastCarReservation from "./FastCarResevation"
import HotelPretraga from "../HotelPretraga";
import LetoviPretraga from "../LetoviPretraga";
import HotelIzlistavanje from "../HoteliIzlistavanje";
import LetoviIzlistavanje from "../LetoviIzlistavanje";
import LoggedUserHotelList from "../loggedUserHotel/LoggedUserHotelList";
import UserReservations from "../reservation/UserReservations";
import InvitationPage from "../invitation/InvitationPage";
import Friend from "../friends/Friend";
import FriendsComponent from "../friends/FriendsComponent";
import ShoppingCart from "../reservation/ShoppingCart";
import UserProfile from "../UserProfile";
import Navbar from "../../layout/Navbar";


class DashboardUserLogged extends Component {

    state = {

    }

    render() {

        return (
            <div>
                <BrowserRouter>
                    <div>
                        <NavLink to="/fastCarReservation"><p className="blue-text darken-3">Brza rezervacija vozila</p></NavLink>

                        <div>
                            <Route path="/" component={UserLoggedTabs}></Route>
                        </div>
                        <div>


                            <Route exact path="/vozila" component={RentACarIzlistavanje}></Route>
                            <Route path="/vozila/:serviceId" render={(props) => <Vozila userId={2} ulogovanUser={true} {...props} />}></Route>
                            <Route path="/filijale/:serviceId" component={Filijale}></Route>
                            <Route path="/fastCarReservation" render={(props) => <FastCarReservation />}></Route>

                            <Route path="/smestaj" component={HotelPretraga}></Route>
                            <Route path="/letovi" component={LetoviPretraga}></Route>
                            <Route path="/listaHotela/:imeAdresa/:datumOd/:datumDo/:brojSoba/:brojGostiju" component={HotelIzlistavanje}></Route>
                            <Route path="/listaLetova" component={LetoviIzlistavanje}></Route>
                            <Route path="/listaHotelaLogged" component={LoggedUserHotelList}></Route>

                        </div>
                    </div>

                </BrowserRouter>
            </div>
        );
    }

}

export default DashboardUserLogged