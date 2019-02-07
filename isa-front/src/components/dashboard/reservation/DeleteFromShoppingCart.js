import React from "react"
import { NavLink } from "react-router-dom"

const DeleteFromShoppingCart = ({ removeRentACarReservationFromShoppingCart, userShoppingCart }) => {

    return (
        <div>
            <NavLink to="/#" onClick={() => { removeRentACarReservationFromShoppingCart(userShoppingCart.id) }}><strong className="red-text">Izbaci</strong></NavLink>
        </div>
    );

};

export default DeleteFromShoppingCart;