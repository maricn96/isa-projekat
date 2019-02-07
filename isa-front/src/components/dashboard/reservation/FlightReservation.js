import React from "react"
import StarRating from './StarRating'
import CancelReservation from './CancelReservation'
import DeleteFromShoppingCart from './DeleteFromShoppingCart'
import { BrowserRouter, Switch, Route } from "react-router-dom";


const FlightReservation = () => {

    return (

        <div className="card">

            <div className="card-content">

                <span className="card-title red-text lighten-1"><strong>Let</strong></span>

                <div className="container">
                    <div className="row ">
                        <div className="col s4">
                            <p>Srbobran-Tokio</p>
                        </div>
                        <BrowserRouter>
                            <div className="col s4">
                                <Route path="/userReservations" component={StarRating}></Route>
                            </div>
                        </BrowserRouter>
                        <BrowserRouter>
                            <div className="col s4">
                                <Switch>
                                    <Route exact path="/korpa" component={DeleteFromShoppingCart}></Route>
                                    <Route exact path="/userRegistrations" component={CancelReservation}></Route>
                                </Switch>
                            </div>
                        </BrowserRouter>
                    </div>
                    <div className="row">
                        <div className="col s4">
                            <p>Sediste N50</p>
                        </div>
                        <BrowserRouter>
                            <div className="col s4">
                                <Route path="/userReservations" component={StarRating}></Route>
                            </div>
                        </BrowserRouter>
                    </div>
                    <p className="red-text lighten-3 left"> <strong>Cena: 4000 din</strong></p>
                </div>

            </div>

        </div>


    );

};

export default FlightReservation;