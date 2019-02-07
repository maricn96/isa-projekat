import React, { Component } from 'react'
import { BrowserRouter, Route } from "react-router-dom"
import UserLoggedTabs from '../layout/tabs/UserLoggedTabs';
import { Link } from "react-router-dom"
import axios from 'axios';
import { getAllAvioCompanies, getAllDestinations, getAllFlights, searchFlightsByTime, searchFlightsByDestination } from "../../store/actions/LetoviActions"

import {connect} from 'react-redux'

class LetoviIzlistavanje extends Component {
    state = {
        flights: []
    }

    componentDidMount() {
        axios.get('http://localhost:8091/api/aviocompany/flight/all').then(res => {
            this.setState({
                flights: res.data
            })
        })
    }

    render() {

        return (
            <div className="post card white lighten-2">
                {this.state.flights.map(flight =>
                    <div className="card-content container">
                        <span className="card-title center"><b>{flight.avioCompany.name}</b></span>
                        <div className="center">
                            <p>Datum poletanja: <b>{flight.takeOffTime}</b></p>
                            <p>Datum sletanja: <b>{flight.landingTime}</b></p>
                            <p>Destinacija sa koje polece: <b>{flight.destinationTakeOff.name}</b></p>
                            <p>Destinacija na koju slece: <b>{flight.destinationLanding.name}</b></p>
                            <p>Tip leta: <b>{flight.travelType}</b></p>
                            <p>Duzina putovanja (u km): <b>{flight.flightLength}</b></p>
                            <p>Preostalo karata: <b>{flight.allTickets - flight.ticketsSold}</b></p>
                            <p>Broj presedanja: <b>{flight.numberOfTransfers}</b></p><br />
                            <button className="btn green lighten-1 z-depth-0" value="Reservisi kartu" id={flight.id}>Rezervisi kartu</button>
                        </div>
                        
                    </div>
                    
                )}
                
            </div>
        )

    }
};

export default LetoviIzlistavanje;
