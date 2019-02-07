import React, { Component } from 'react'
import UserLoggedTabs from '../layout/tabs/UserLoggedTabs';
import { Link } from "react-router-dom"
import MapContainer from "./GoogleMapa/MapContainer";
import "./neregistrovan.css";
import axios from 'axios'


class HotelIzlistavanje extends Component {

    state = {
        hoteli: [],
        ocene: []
    }

    componentDidMount() {
        const imeAdresa = this.props.match.params.imeAdresa;
        const datumOd = this.props.match.params.datumOd;
        const datumDo = this.props.match.params.datumDo;
        const brojSoba = this.props.match.params.brojSoba;
        const brojGostiju = this.props.match.params.brojGostiju;
        axios.get('http://localhost:8092/api/hotel/hotel/filter/' + imeAdresa + '/' + datumOd + '/' + datumDo + '/' + brojSoba + '/' + brojGostiju)
            .then(res => {
                console.log(res);
                this.setState({
                    hoteli: res.data
                })

                axios.get('http://localhost:8095/api/hotel/hotelRating/all')
                    .then(res => {
                        this.setState({
                            ocene: res.data
                        })
                    })
            })
    }

    sortirajNizovePo = (e) => {

        const sortirajPo = e.target.value
        var noviNiz = []

        if (sortirajPo === "grad" && this.state.hoteli) {
            noviNiz = this.state.hoteli.sort(function (a, b) {
                var cityA = a.adress.toLowerCase(), cityB = b.adress.toLowerCase()
                if (cityA < cityB)
                    return -1
                if (cityA > cityB)
                    return 1
                return 0
            })
            this.setState({
                hoteli: noviNiz
            })
        }
        else if (sortirajPo === "naziv" && this.state.hoteli) {
            noviNiz = this.state.hoteli.sort(function (a, b) {
                var nameA = a.name.toLowerCase(), nameB = b.name.toLowerCase()
                if (nameA < nameB)
                    return -1
                if (nameA > nameB)
                    return 1
                return 0
            })
            this.setState({
                hoteli: noviNiz
            })
        }



    }

    handleSobeClick = (hotelId) => {
        this.props.history.push('/sobe/' + hotelId + '/' + this.props.match.params.datumOd + '/' + this.props.match.params.datumDo)
    }

    handleCenovnikClick = (hotelId) => {
        this.props.history.push('/cenovnici/' + hotelId)
    }

    handleUslugeClick = (hotelId) => {
        this.props.history.push('/usluge/' + hotelId)
    }

    render() {
        const { hoteli } = this.state;
        var suma = 0;
        var count = 0;
        const hoteliList = hoteli.length ? (hoteli.map(hotel => {
            suma = 0;
            count = 0;
            this.state.ocene.map(ocena => {
                if (ocena.hotelId == hotel.id) {
                    suma += ocena.rating;
                    count++;
                }
            })

            var prosecnaOcena = suma / count;

            return (
                <div className="post card grey lighten-2">
                    <div className="card-content container">
                        <span className="card-title center">{hotel.name}</span>
                        <div className="left-align">
                            <p>Adresa: {hotel.adress}</p>
                            <p>Opis: {hotel.promotionalDescription}</p>
                            <p>Procecna ocena: {prosecnaOcena}/5</p>
                            <button className="buttons btn-small waves-effect waves-light indigo right" onClick={() => this.handleUslugeClick(hotel.id)}>Dodatne usluge</button>
                            <button className="buttons btn-small waves-effect waves-light indigo right" onClick={() => this.handleCenovnikClick(hotel.id)}>Cenovnik usluga</button>
                            <button className="buttons btn-small waves-effect waves-light indigo right" onClick={() => this.handleSobeClick(hotel.id)}>Sobe</button>
                        </div>
                    </div>
                </div>
            )

        })) : (<div className="center">Nije pronadjen nijedan hotel.</div>)


        return (
            <div>
                <UserLoggedTabs></UserLoggedTabs>
                <div className="container center">
                    <h2 className="red-text lighten-1 center">Lista hotela</h2>
                    <div>
                        <div className="card">
                            <div className="card-content">
                                <div className="container">
                                    <form className="white">
                                        <div className="input-field">
                                            <label className="active" htmlFor="naziv">Sortiraj po</label>
                                            <select onChange={this.sortirajNizovePo} className="browser-default">
                                                <option value='naziv'>Nazivu</option>
                                                <option value='grad'>Adresi</option>
                                            </select>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    {hoteliList}
                </div>
            </div>
        )
    }
}


export default HotelIzlistavanje;


