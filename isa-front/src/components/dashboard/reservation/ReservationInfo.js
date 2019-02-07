import React, { Component } from 'react'
import FlightReservation from './FlightReservation'
import HotelReservation from './HotelReservation'
import CarReservation from './CarReservation';
import { connect } from 'react-redux'
import { addRentCarReservationToCollection, getAllRentACarRatings, getAllCarRatings } from "../../../store/actions/PurchasesActions"

class ReservationInfo extends Component {


    componentDidMount() {

        this.props.addRentCarReservationToCollection(this.props.reservation.carReservationId);

        var user = JSON.parse(localStorage.getItem('user'))
        if (user) {
            this.props.getAllRentACarRatings(user.id);

            this.props.getAllCarRatings(user.id);
        }


    }



    render() {


        return (

            <div className="container">
                <div className="card indigo darken-2">

                    <div className="card-content">

                        <span className="card-title flow-text white-text  "><strong>Id: {this.props.reservation.id}</strong></span>

                        {this.props.FlightReservation ?
                            <FlightReservation></FlightReservation>
                            :
                            ''
                        }
                        {this.props.HotelReservation ?
                            <HotelReservation></HotelReservation>
                            :
                            ''
                        }




                        {this.props.rentCarReservations && this.props.rentCarReservations.find(el => el.id == this.props.reservation.carReservationId) ?

                            <CarReservation userId={2} carRatings={this.props.carRatings} rentACarRatings={this.props.rentACarRatings} reservationId={this.props.reservation.id} carReservation={this.props.rentCarReservations.find(el => el.id == this.props.reservation.carReservationId)} ></CarReservation>
                            :
                            ''
                        }
                        <h6 className="white-text " ><strong>Ukupna cena: {this.props.reservation.price} din</strong></h6>


                    </div>

                </div>
            </div>

        );
    }

};


const mapStateToProps = (state) => {
    return {
        rentCarReservations: state.purchases.rentCarReservations,
        rentACarRatings: state.purchases.rentACarRatings,
        carRatings: state.purchases.carRatings
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        addRentCarReservationToCollection: (id) => dispatch(addRentCarReservationToCollection(id)),
        getAllRentACarRatings: (id) => dispatch(getAllRentACarRatings(id)),
        getAllCarRatings: (id) => dispatch(getAllCarRatings(id))
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(ReservationInfo);
