import React, { Component } from "react"
import UserLoggedTabs from "../../layout/tabs/UserLoggedTabs";
import { BrowserRouter, Route } from "react-router-dom"
import RentACarPretraga from "./RentACarPretraga";
import RentACarIzlistavanje from "./RantACarIzlistavanje";
import Vozila from "./Vozila.js"
import Filijale from "./Filijale.js"
import HotelPretraga from "../HotelPretraga";
import LetoviPretraga from "../LetoviPretraga";
import LetoviIzlistavanje from "../LetoviIzlistavanje";
import HotelIzlistavanje from "../HoteliIzlistavanje";

class DashboardClassicUser extends Component {

    state = {

    }


    render() {

        return (
            <div>



                <BrowserRouter>
                    <div>
                        <div>

                            <Route path="/" component={UserLoggedTabs}></Route>

                        </div>
                        <div>
                            <Route exact path="/vozila" component={RentACarIzlistavanje}></Route>
                            <Route path="/listaVozila" component={RentACarIzlistavanje}></Route>
                            <Route path="/vozila/:serviceId" component={Vozila}></Route>
                            <Route path="/filijale/:serviceId" component={Filijale}></Route>


                            <Route path="/smestaj" component={HotelPretraga}></Route>
                            <Route path="/letovi" component={LetoviPretraga}></Route>




                           
                            <Route path="/listaHotela/:imeAdresa/:datumOd/:datumDo/:brojSoba/:brojGostiju" component={HotelIzlistavanje}></Route>
                            <Route path="/listaLetova" component={LetoviIzlistavanje}></Route>
                        </div>
                    </div>

                </BrowserRouter>
            </div>
        );
    }

}

export default DashboardClassicUser;