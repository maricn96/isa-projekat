import React, { Component } from "react"
import FastReservationCarInfo from "./FastReservationCarInfo";
import { connect } from "react-redux"
import { getCarsOnDiscount } from "../../../store/actions/RentACarActions"



class FastCarReservation extends Component {

    state = {
        dateFrom: '2017-01-01T00:00:01',
        dateTo: '2018-01-01T00:00:01'
    }

    componentDidMount() {

        this.props.getCarsOnDiscount(this.state.dateFrom, this.state.dateTo);
    }

    render() {

        return (
            <div>
                <div className="container">
                    <h3 className="red-text lighten-1 center">Brza rezervacija vozila</h3>

                    {this.props.carsOnDiscount ?
                        this.props.carsOnDiscount.map(carOnDiscount => {
                            return (
                                <FastReservationCarInfo carOnDiscount={carOnDiscount} />
                            );
                        })
                        :
                        ''
                    }



                </div>
            </div>
        );
    }

}


const mapStateToProps = (state) => {
    return {
        carsOnDiscount: state.rentACar.carsOnDiscount

    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        getCarsOnDiscount: (dateFrom, dateTo) => dispatch(getCarsOnDiscount(dateFrom, dateTo)),
    }
}



export default connect(mapStateToProps, mapDispatchToProps)(FastCarReservation);
