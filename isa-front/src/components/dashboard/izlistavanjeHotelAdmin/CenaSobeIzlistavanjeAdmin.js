import React, { Component } from 'react'
import { BrowserRouter, Switch, Link, Route } from "react-router-dom";
import axios from 'axios'
import { connect } from 'react-redux'
import { deleteCena } from '../../../store/actions/CenaActions';
import "./izlistavanje.css"


class CenaSobeIzlistavanjeAdmin extends Component {

    state = {
        cene: [],
        sobaId:""
    }

    componentDidMount() {
        this.setState({
            sobaId: this.props.match.params.sobaId
        })
        const id = this.props.match.params.sobaId;
        axios.get('http://localhost:8092/api/hotel/cene/soba/'+id)
            .then(res => {
                console.log(res);
                this.setState({
                    cene: res.data
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

    handleDeleteClick = (cenaId) => {
        this.props.deleteCena(cenaId)
    }

    /*handleIzmeniClick = (hotelId) => {
    }*/

    handleDodajClick = (sobaId) => {
        this.props.history.push('/createRoomPrice/'+sobaId)
    }

    render() {
        const { cene } = this.state;
        var dateOd = "";
        var dateDo = "";
        const ceneList = cene.length ? (cene.map(cena => {
            dateOd = cena.datumOd.substring(0, 10);
            dateDo = cena.datumDo.substring(0, 10);
            return (
                <div className="post card grey lighten-2">
                    <div className="card-content container">
                        <span className="card-title center"><strong>{cena.id}</strong></span>
                        <div className="right-align">
                        </div>
                        <div className="left-align">
                            <p>Cena: {cena.cena}</p>
                            <p>Važi od: {dateOd}</p>
                            <p>Važi do: {dateDo}</p>
                            <p>Soba: {cena.hotelskaSoba_cena.id}</p>
                            <button className="dugmici1 btn-floating btn-large waves-effect waves-light red right" onClick = {() => this.handleDeleteClick(cena.id)}><i>x</i></button>
                            <button className="dugmici2 btn-floating btn-large waves-effect waves-light green right" /*onClick = {() => this.handleIzmeniClick(hotel.id)}*/>Izmeni</button>
                        </div>
                    </div>
                </div>
            )
        })) : (<div className="center">Nije pronadjena nijedana cena.</div>)

        return (
            <div>
                <div className="container center">
                    <h2 className="red-text lighten-1 center">Lista cena soba</h2>
                    <button className="dugmici3 btn-large waves-effect waves-light indigo right" onClick = {() => this.handleDodajClick(this.state.sobaId)}>Dodaj novu cenu</button>
                    {ceneList}
                </div>
            </div>
        )
    }
}

const mapDispatchToProps = (dispatch) =>{
    return{
        deleteCena: (cena) => dispatch(deleteCena(cena))
    }
}

export default connect(null, mapDispatchToProps)(CenaSobeIzlistavanjeAdmin);