import React, { Component } from "react"
import "./StarRating.css"
import { connect } from "react-redux"
import { rateCar, rateRentACarService } from "../../../store/actions/PurchasesActions"

class StarRating extends Component {


    state = {

        rentService: undefined,
        reservedCar: undefined,
        rating: undefined

    }


    componentDidMount() {


    }

    HandleRating = (e) => {

        if (this.props.rentService && this.props.date) {
            //ovde ide user
            this.props.rateRentACarService(this.props.date, 2, this.props.rentService.id, e.target.value);

        }
        else if (this.props.reservedCar && this.props.date) {

            this.props.rateCar(this.props.date, 2, this.props.reservedCar.id, e.target.value);
        }

    }


    render() {

        var idZvezdice = "";

        if (this.props.rentService) {
            idZvezdice = this.props.rentService.id + "servis";
        }
        else if (this.props.reservedCar) {
            idZvezdice = this.props.reservedCar.id + "reservedCar";
        }


        return (
            <div>
                <fieldset class="rating">
                    <input onClick={this.HandleRating} type="radio" id={idZvezdice + '5'} name={idZvezdice + 'rating'} value="5" /><label className="full" for={idZvezdice + '5'} title="Odlican! - 5 zvezdica"></label>
                    <input onClick={this.HandleRating} type="radio" id={idZvezdice + '4'} name={idZvezdice + 'rating'} value="4" /><label className="full" for={idZvezdice + '4'} title="Vrlo dobar - 4 zvezdica"></label>
                    <input onClick={this.HandleRating} type="radio" id={idZvezdice + '3'} name={idZvezdice + 'rating'} value="3" /><label className="full" for={idZvezdice + '3'} title="Ajde da kazem dobar - 3 zvezdice"></label>
                    <input onClick={this.HandleRating} type="radio" id={idZvezdice + '2'} name={idZvezdice + 'rating'} value="2" /><label className="full" for={idZvezdice + '2'} title="Onakooooo - 2 zvezdice"></label>
                    <input onClick={this.HandleRating} type="radio" id={idZvezdice + '1'} name={idZvezdice + 'rating'} value="1" /><label className="full" for={idZvezdice + '1'} title="Jako lose - 1 star"></label>

                </fieldset>
                <br />
                <br />
                <p className="orange-text lighten-1">Vasa trenutna ocena : {this.props.rating ? this.props.rating.rating : ''}</p>
            </div>

        );
    }

};

const mapStateToProps = (state) => {
    return {
        rentACarRatings: state.purchases.rentACarRatings,
        carRatings: state.purchases.carRatings,
        user: state.user.user
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        rateRentACarService: (date, userId, idService, rating) => dispatch(rateRentACarService(date, userId, idService, rating)),
        rateCar: (date, userId, idCar, rating) => dispatch(rateCar(date, userId, idCar, rating))
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(StarRating);