import React, { Component } from "react"
import StarRating from "./StarRating";
import CancelReservation from "./CancelReservation"
import DeleteFromShoppingCart from "./DeleteFromShoppingCart"
import { BrowserRouter, Switch, Route } from "react-router-dom";
import { connect } from "react-redux"
import { cancelCarReservation } from "../../../store/actions/PurchasesActions"

//Ovde imamo situaciju kada je objekat slozen iz vise objekata
//pri prvom renderovanju ne ucitaju se svi objekti unutar njega
//i ukoliko ostavimo samo izraz npr carReservation.service.name
//pucace nam error cannot get name of undefined, jer service jos nije ucitan
//zbog toga na svaki ispis je postavljen i uslov ukoliko je objekat jos undefined da se nista ne ispisuje
//i tek kada dodje da mu dozvoli ispis

class CarReservation extends Component {

    state = {

        carRating: '',
        rentACarRating: ''

    }

    componentDidMount() {

        if (this.props.carRatings && this.props.rentACarRatings && this.props.userId && this.props.carReservation) {

            const carRating = (this.props.carRatings.find(item => { if (item.userId == this.props.userId && item.carId == this.props.carReservation.reservedCar.id) { return true; } }));

            const rentServiceRating = (this.props.rentACarRatings.find(item => { if (item.userId == this.props.userId && item.rentACarId == this.props.carReservation.service.id) { return true; } }));

            this.setState({
                carRating: carRating,
                rentACarRating: rentServiceRating
            })

        }


    }

    render() {
        return (
            <div className="card">

                <div className="card-content">

                    <span className="card-title red-text lighten-1"><strong>Vozilo</strong></span>
                    <div className="container">
                        <div className="row ">
                            <div className="col s4">
                                {this.props.carReservation.service
                                    && <p>{this.props.carReservation.service.name}</p>
                                }

                            </div>
                            <BrowserRouter>
                                <div className="col s4">
                                    <Route path="/userReservations" render={(props) => <StarRating date={this.props.carReservation ? this.props.carReservation.dateTo : undefined} rating={this.state.rentACarRating} rentService={this.props.carReservation ? this.props.carReservation.service : undefined}></StarRating>}></Route>
                                </div>
                            </BrowserRouter>
                            <BrowserRouter>
                                <div className="col s4">
                                    <Switch>
                                        <Route exact path="/korpa" render={(props) => <DeleteFromShoppingCart removeRentACarReservationFromShoppingCart={this.props.removeRentACarReservationFromShoppingCart} userShoppingCart={this.props.userShoppingCart} />} ></Route>
                                        {this.props.reservationId ?
                                            <Route exact path="/userReservations" render={(props) => <CancelReservation cancelCarReservation={this.props.cancelCarReservation} reservationId={this.props.reservationId}></CancelReservation>}></Route>
                                            :
                                            ''
                                        }

                                    </Switch>
                                </div>
                            </BrowserRouter>
                        </div>
                        <div className="row">
                            <div className="col s4">
                                {this.props.carReservation.reservedCar && this.props.carReservation.reservedCar.carType
                                    && <p>{this.props.carReservation.reservedCar.carType.brand} {this.props.carReservation.reservedCar.carType.model} {this.props.carReservation.reservedCar.carType.modelYear}. {this.props.carReservation.reservedCar.carType.carType} broj sedista: {this.props.carReservation.reservedCar.carType.numberOfSeats} </p>
                                }
                            </div>
                            <BrowserRouter>
                                <div className="col s4">
                                    <Route path="/userReservations" render={(props) => <StarRating date={this.props.carReservation ? this.props.carReservation.dateTo : undefined} rating={this.state.carRating} reservedCar={this.props.carReservation ? this.props.carReservation.reservedCar : undefined}></StarRating>}></Route>
                                </div>
                            </BrowserRouter>
                        </div>
                        <div>
                            <p className="indigo-text left"><strong>Datum od:</strong> {this.props.carReservation.dateFrom}</p>
                            <br />
                            <p className="indigo-text left"><strong>Datum do:</strong> {this.props.carReservation.dateTo}</p>
                            <br />
                            <br />
                        </div>
                        <p className="red-text lighten-3 left"> <strong>Cena po danu: {this.props.carReservation.reservedCar && this.props.carReservation.reservedCar.rentPrice} din</strong></p>
                    </div>



                </div>

            </div>
        );
    }

};

const mapDispatchToProps = (dispatch) => {
    return {
        cancelCarReservation: (id) => dispatch(cancelCarReservation(id)),
    }
}



export default connect(null, mapDispatchToProps)(CarReservation);
