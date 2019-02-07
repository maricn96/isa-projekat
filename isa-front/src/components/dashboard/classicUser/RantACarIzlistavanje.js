import React, { Component } from 'react'
import "../neregistrovan.css";
import axios from 'axios';
import { NavLink } from "react-router-dom"


class RentACarIzlistavanje extends Component {

    state = {
        servisi: [],
        sakriPretragu: true,
        ponistiPretragu: false,
        naziv: 'nema',
        gradOd: 'nema',
        gradDo: 'nema'
    }

    componentDidMount() {
        this.getAllRentServices();

    }

    getAllRentServices = () => {
        axios.get('http://localhost:8090/api/rentacar/rentACarService/all')
            .then(res => {
                console.log(res);
                this.setState({
                    servisi: res.data,
                    ponistiPretragu: false
                })
            })
    }

    handleVozilaClick = (serviceId) => {
        this.props.history.push('/vozila/' + serviceId)
    }

    handleFilijalaClick = (serviceId) => {
        this.props.history.push('/filijale/' + serviceId)
    }

    toggleForm = () => {
        this.setState({
            sakriPretragu: false
        })
    }

    hideForm = () => {
        this.setState({
            sakriPretragu: true
        })
    }

    handleChange = (e) => {
        const vrednost = e.target.value == '' ? "nema" : e.target.value
        this.setState({
            [e.target.id]: vrednost
        })
    }

    sortirajNizovePo = (e) => {

        const sortirajPo = e.target.value
        var noviNiz = []

        if (sortirajPo === "grad" && this.state.servisi) {
            noviNiz = this.state.servisi.sort(function (a, b) {
                var cityA = a.adress.toLowerCase(), cityB = b.adress.toLowerCase()
                if (cityA < cityB)
                    return -1
                if (cityA > cityB)
                    return 1
                return 0
            })
            this.setState({
                servisi: noviNiz
            })
        }
        else if (sortirajPo === "naziv" && this.state.servisi) {
            noviNiz = this.state.servisi.sort(function (a, b) {
                var nameA = a.name.toLowerCase(), nameB = b.name.toLowerCase()
                if (nameA < nameB)
                    return -1
                if (nameA > nameB)
                    return 1
                return 0
            })
            this.setState({
                servisi: noviNiz
            })
        }



    }

    handleSubmit = (e) => {
        e.preventDefault();
        axios.get('http://localhost:8090/api/rentacar/rentACarService/getAllServices/' + this.state.naziv + '/' + this.state.gradOd + '/' + this.state.gradDo)
            .then(res => {
                this.setState({
                    servisi: res.data,
                    ponistiPretragu: true

                })
            })
    }

    render() {
        const { servisi } = this.state;
        const servisiList = servisi.length ? (servisi.map(servis => {
            return (
                <div className="post card grey lighten-2">
                    <div className="card-content container">
                        <span className="card-title center">{servis.name}</span>
                        <div className="left-align">
                            <p>Adresa: {servis.adress}</p>
                            <p>Opis: {servis.description}</p>
                            <button className="buttons btn-small waves-effect waves-light indigo right" onClick={() => this.handleVozilaClick(servis.id)}>Vozila</button>
                            <button className="buttons btn-small waves-effect waves-light indigo right" onClick={() => this.handleFilijalaClick(servis.id)}>Filijale</button>
                        </div>
                    </div>
                </div>
            )
        })) : (<div className="center">Nije pronadjen nijedan servis.</div>)

        return (
            <div>
                <div className="container center">
                    <h2 className="red-text lighten-1 center">Lista servisa</h2>
                    {
                        this.state.sakriPretragu ?
                            <NavLink to="#" onClick={this.toggleForm}>Pretrazite servise</NavLink>
                            :
                            <div>
                                <NavLink to="#" onClick={this.hideForm}>Sakrij formu</NavLink>
                                <div className="card">
                                    <div className="card-content">
                                        <div className="container">
                                            <form className="white" onSubmit={this.handleSubmit}>
                                                <div className="input-field">
                                                    <label htmlFor="naziv">Ime</label>
                                                    <input type="text" onChange={this.handleChange} id='naziv' />
                                                </div>
                                                <div className="input-field">
                                                    <label htmlFor="gradOd">Grad od</label>
                                                    <input type="text" onChange={this.handleChange} id='gradOd' />
                                                </div>
                                                <div className="input-field">
                                                    <label htmlFor="gradDo">Grad do</label>
                                                    <input type="text" onChange={this.handleChange} id='gradDo' />
                                                </div>
                                                <div className="input-field">
                                                    <button className="btn purple darken-3 z-depth-0">Pretrazi</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>


                            </div>
                    }

                    {
                        this.state.ponistiPretragu ?
                            <NavLink to="#" onClick={this.getAllRentServices}>Ponistite pretragu</NavLink>
                            :
                            ''
                    }

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
                    {servisiList}
                </div>
            </div>
        )
    }
}

export default RentACarIzlistavanje;