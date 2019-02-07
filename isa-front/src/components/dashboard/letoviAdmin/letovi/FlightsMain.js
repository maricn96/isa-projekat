import React from 'react'
import {NavLink} from 'react-router-dom'
import Flight from './Flight'

const FlightsMain = () =>
{
    return(
        <div className="container">
            <div className="center">
                <NavLink to="/" className="indigo-text lighten-1">Dodaj novi let</NavLink>
                <Flight/>
                <Flight/>
                <Flight/>
            </div>
        </div>
    );
}

export default FlightsMain