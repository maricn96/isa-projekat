import React, { Component } from 'react'
import { BrowserRouter, Switch, Link, Route } from "react-router-dom";
import axios from 'axios'
import { connect } from 'react-redux'
//import { deleteHotel } from '../../../store/actions/HotelActions';
import "./izlistavanje.css"


class RezervacijaSobeIzlistavanjeAdmin extends Component {

    state = {
        rezervacije: [],
    }

    componentDidMount() {
        axios.get('http://localhost:8092/api/hotel/rezervacije/all')
            .then(res => {
                console.log(res);
                this.setState({
                    rezervacije: res.data
                })
            })
    }

    /*componentDidUpdate() {
         axios.get('http://localhost:8092/api/hotel/hotel/all')
            .then(res => {
                console.log(res);
                this.setState({
                    hoteli: res.data
                })
        })
    }*/

    /*handleDeleteClick = (hotelId) => {
        this.props.deleteHotel(hotelId)
    }

    handleIzmeniClick = (hotelId) => {
    }*/

    render() {
        const { rezervacije } = this.state;
        var dateOd = "";
        var dateDo = "";
        const rezervacijeList = rezervacije.length ? (rezervacije.map(rezervacija => {
            dateOd = rezervacija.dateFrom.substring(0, 10);
            dateDo = rezervacija.dateUntil.substring(0, 10);
            return (
                <div className="post card grey lighten-2">
                    <div className="card-content container">
                        <span className="card-title center"><strong>{rezervacija.id}</strong></span>
                        <div className="right-align">
                        </div>
                        <div className="left-align">
                            <p>Ukupna cena: {rezervacija.totalPrice}</p>
                            <p>Rezervisano od: {dateOd}</p>
                            <p>Rezervisano do: {dateDo}</p>
                            <p>Soba: {rezervacija.sobaId.id}</p>
                            <button className="dugmici1 btn-floating btn-large waves-effect waves-light red right" /*onClick = {() => this.handleDeleteClick(hotel.id)}*/><i>x</i></button>
                            <button className="dugmici2 btn-floating btn-large waves-effect waves-light green right" /*onClick = {() => this.handleIzmeniClick(hotel.id)}*/>Izmeni</button>
                        </div>
                    </div>
                </div>
            )
        })) : (<div className="center">Nije pronadjena nijedana rezervacija.</div>)

        return (
            <div>
                <div className="container center">
                    <h2 className="red-text lighten-1 center">Lista rezervacija soba</h2>
                    {rezervacijeList}
                </div>
            </div>
        )
    }
}

/*const mapDispatchToProps = (dispatch) =>{
    return{
        deleteHotel: (hotel) => dispatch(deleteHotel(hotel))
    }
}*/

export default RezervacijaSobeIzlistavanjeAdmin;