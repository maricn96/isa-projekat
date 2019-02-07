import React, { Component } from 'react'
import { BrowserRouter, Switch, Link, Route } from "react-router-dom";
import axios from 'axios'
import { connect } from 'react-redux'
//import { deleteHotel } from '../../../store/actions/HotelActions';
import "./izlistavanje.css"


class PrihodHotelaIzlistavanjeAdmin extends Component {

    state = {
        prihodi: [],
    }

    componentDidMount() {
        axios.get('http://localhost:8092/api/hotel/prihodi/all')
            .then(res => {
                console.log(res);
                this.setState({
                    prihodi: res.data
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
        const { prihodi } = this.state;
        var date = "";
        const prihodiList = prihodi.length ? (prihodi.map(prihod => {
            date = prihod.incomeDate.substring(0, 10);
            return (
                <div className="post card grey lighten-2">
                    <div className="card-content container">
                        <span className="card-title center"><strong>{prihod.id}</strong></span>
                        <div className="right-align">
                        </div>
                        <div className="left-align">
                            <p>Ostvaren prihod: {prihod.totalPrice}</p>
                            <p>Datum prihoda: {date}</p>
                            <p>Hotel: {prihod.hotel_prihodiHotela.name} {prihod.hotel_prihodiHotela.adress}</p>
                            <button className="dugmici1 btn-floating btn-large waves-effect waves-light red right" /*onClick = {() => this.handleDeleteClick(hotel.id)}*/><i>x</i></button>
                            <button className="dugmici2 btn-floating btn-large waves-effect waves-light green right" /*onClick = {() => this.handleIzmeniClick(hotel.id)}*/>Izmeni</button>
                        </div>
                    </div>
                </div>
            )
        })) : (<div className="center">Nije pronadjen nijedan prihod.</div>)

        return (
            <div>
                <div className="container center">
                    <h2 className="red-text lighten-1 center">Lista prihoda hotela</h2>
                    {prihodiList}
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

export default PrihodHotelaIzlistavanjeAdmin;