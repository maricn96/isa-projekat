import React from "react"
import { NavLink } from "react-router-dom"

const CancelReservation = ({ reservationId, cancelCarReservation }) => {

    return (
        <div>
            <NavLink to="/#" onClick={() => cancelCarReservation(reservationId)}><strong className="red-text">Otkazi</strong></NavLink>
        </div >
    );

};

export default CancelReservation;