import React, { Component } from 'react'
import { BrowserRouter, Switch, Link, Route } from "react-router-dom";
import axios from 'axios'
import { connect } from 'react-redux'
import { deleteSoba } from '../../../store/actions/SobeActions';
import "./izlistavanje.css"


class HotelskaSobaIzlistavanjeAdmin extends Component {

    state = {
        sobe: [],
        ocene:[]
    }

    componentDidMount() {
        axios.get('http://localhost:8092/api/hotel/hotelskaSoba/all')
            .then(res => {
                console.log(res);
                this.setState({
                    sobe: res.data
                })
            })

            axios.get('http://localhost:8095/api/hotel/hotelskaSobaRating/all')
                .then(res => {
                    this.setState({
                        ocene: res.data
                    })
                })
    }

    handleDeleteClick = (sobaId) => {
        this.props.deleteSoba(sobaId)
    }

    handleIzmeniClick = (sobaId) => {
        this.props.history.push('/izmenaHotelskihSobaAdmin/'+sobaId)
    }

    handleCeneClick = (sobaId) => {
        this.props.history.push('/izlistavanjeCenaSobeAdmin/'+sobaId)
    }

    render() {
        const { sobe } = this.state;
        var rezervisano = "";
        var suma = 0;
        var count = 0;
        const sobeList = sobe.length ? (sobe.map(soba => {
            if(soba.reserved){
                rezervisano = "DA";
            }else{
                rezervisano = "NE";
            }

            suma = 0;
            count = 0;
            this.state.ocene.map(ocena =>{
                if(ocena.hotelskaSobaId == soba.id){
                    suma += ocena.rating;
                    count++;
                }
            })

            var prosecnaOcena = suma / count;

            return (
                <div className="post card grey lighten-2">
                    <div className="card-content container">
                        <span className="card-title center"><strong>{soba.id}</strong></span>
                        <div className="right-align">
                        </div>
                        <div className="left-align">
                            <p>Sprat: {soba.floor}</p>
                            <p>Originalna cena: {soba.originalnaCena}</p>
                            <p>Rezervisana: {rezervisano}</p>
                            <p>Tip sobe: {soba.tipSobe_hotelskeSobe.roomType}</p>
                            <p>Hotel: {soba.hotel_hotelskeSobe.name} {soba.hotel_hotelskeSobe.adress}</p>
                            <p>Procecna ocena: {prosecnaOcena}/5</p>
                            <button className="dugmici1 btn-floating btn-large waves-effect waves-light red right" onClick = {() => this.handleDeleteClick(soba.id)}><i>x</i></button>
                            <button className="dugmici2 btn-floating btn-large waves-effect waves-light green right" onClick = {() => this.handleIzmeniClick(soba.id)}>Izmeni</button>
                            <button className="dugmici2 btn-floating btn-large waves-effect waves-light green right" onClick = {() => this.handleCeneClick(soba.id)}>Cene</button>
                        </div>
                    </div>
                </div>
            )
        })) : (<div className="center">Nije pronadjena nijedana soba.</div>)

        return (
            <div>
                <div className="container center">
                    <h2 className="red-text lighten-1 center">Lista hotelskih soba</h2>
                    {sobeList}
                </div>
            </div>
        )
    }
}

const mapDispatchToProps = (dispatch) =>{
    return{
        deleteSoba: (soba) => dispatch(deleteSoba(soba))
    }
}

export default connect(null, mapDispatchToProps)(HotelskaSobaIzlistavanjeAdmin);