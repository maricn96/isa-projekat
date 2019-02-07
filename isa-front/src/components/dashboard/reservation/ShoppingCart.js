import React, { Component } from "react"
import ShoppingCartReservation from "./ShoppingCartReservation";
import { connect } from "react-redux";
import { getUserShoppingCart, makeReservation, removeRentACarReservationFromShoppingCart } from "../../../store/actions/PurchasesActions";


class ShoppingCart extends Component {


    state = {

    }

    componentDidMount() {

        var user = JSON.parse(localStorage.getItem('user'))
        if (user) {
            this.props.getUserShoppingCart(user.id);
        }



    }

    poruciIzKorpe = (event) => {

        this.props.makeReservation(this.props.userShoppingCart.id);

    }


    render() {
        return (
            <div>
                <h2 className="center red-text lighten-1">Korpa</h2>

                {this.props.userShoppingCart != undefined ?
                    <div className="container">
                        <ShoppingCartReservation removeRentACarReservationFromShoppingCart={this.props.removeRentACarReservationFromShoppingCart} userShoppingCart={this.props.userShoppingCart} carReservation={this.props.carReservation} avioCompanyReservation={this.props.avioCompanyReservation} hotelReservation={this.props.hotelReservation}></ShoppingCartReservation>
                        <h5 className="red-text lighten-1" ><strong>Ukupna cena: {this.props.userShoppingCart.price} din</strong></h5>
                        <p>Bonus poeni</p>
                        <a class="btn-floating btn-small waves-effect waves-light red"><i class="material-icons">-</i></a>
                        {this.props.userShoppingCart.bonusPoints} poena
                    <a class="btn-floating btn-small waves-effect waves-light red"><i class="material-icons">+</i></a>

                        <div className="row right">
                            <div className="col s8">
                                <button className="btn  indigo lighten-1">Dodeli je prijatelju</button>
                            </div>
                            <div className="col s2">
                                <button className="btn red lighten-1 z-depth-0" onClick={this.poruciIzKorpe}>Naruci</button>
                            </div>
                        </div>
                    </div>

                    :

                    <div>
                        <h4 className="center indigo-text lighten-1">Vasa korpa je prazna</h4>
                    </div>
                }



            </div>
        );
    }

};

const mapStateToProps = (state) => {
    return {
        userShoppingCart: state.purchases.userShoppingCart,
        carReservation: state.purchases.carReservation,
        avioCompanyReservation: state.purchases.avioCompanyReservation,
        hotelReservation: state.purchases.hotelReservation
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        getUserShoppingCart: (id) => dispatch(getUserShoppingCart(id)),
        makeReservation: (id) => dispatch(makeReservation(id)),
        removeRentACarReservationFromShoppingCart: (id) => dispatch(removeRentACarReservationFromShoppingCart(id))
    }
}



export default connect(mapStateToProps, mapDispatchToProps)(ShoppingCart);
