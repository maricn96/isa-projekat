import React, { Component } from 'react'
import UserLoggedTabs from '../layout/tabs/UserLoggedTabs';
import { BrowserRouter, Route } from "react-router-dom"
import ReactDOM from 'react-dom';
import axios from 'axios';
import { NavLink } from 'react-router-dom'
import LetoviIzlistavanje from './LetoviIzlistavanje'



class LetoviPretraga extends Component {
    constructor(props) {
        super(props);
        this.state = {

            destinations: [],
            flights: [],

            ///////

            mestoPolaska: 'novi sad',
            mestoDolaska: 'novi sad',
            flightsByDest: [],

            ///////

            polazak: '',
            dolazak: '',

            datumPoletanja: '',
            vremePoletanja: '',
            datumSletanja: '',
            vremeSletanja: '',

            flightsByTime: [],

            ///////

            tipLeta: '',

            flightsByType: [],

            ///////

            isClicked: 'false'


        };

        this.changeMestoPolaska = this.changeMestoPolaska.bind(this);
        this.changeMestoDolaska = this.changeMestoDolaska.bind(this);

        this.changeDatumPoletanja = this.changeDatumPoletanja.bind(this);
        this.changeDatumSletanja = this.changeDatumSletanja.bind(this);

        this.changeTipLeta = this.changeTipLeta.bind(this);

        this.handleSubmit = this.handleSubmit.bind(this);

        this.handleSearchButton = this.handleSearchButton.bind(this);
    }

    componentDidMount() {
        axios('http://localhost:8091/api/aviocompany/destination/all').then(
            res => {
                this.setState({
                    destinations: res.data
                })
            }
        )

        axios('http://localhost:8091/api/aviocompany/flight/all').then(
            res => {
                this.setState({
                    flights: res.data
                })
            }
        )

    }

    changeMestoPolaska(e) {
        this.setState({ mestoPolaska: e.target.value })
        console.log(e.target.value);
    }

    changeMestoDolaska(e) {
        this.setState({ mestoDolaska: e.target.value })
        console.log(e.target.value);
    }

    changeDatumPoletanja(e) {
        var datee = e.target.value;
        var res = datee.split("-");

        if (res[0] > 1948 && res[0] < 2100) {
            this.state.datumPoletanja = e.target.value;
        }
        else {
            this.state.vremePoletanja = e.target.value;
        }

        this.setState({
            polazak: this.state.datumPoletanja + 'T' + this.state.vremePoletanja + ':00'
        })

        console.log(this.state.polazak);
    }


    changeDatumSletanja(e) {
        var dateee = e.target.value;
        var ress = dateee.split("-");

        if (ress[0] > 1948 && ress[0] < 2100) {
            this.state.datumSletanja = e.target.value;
        }
        else {
            this.state.vremeSletanja = e.target.value;
        }

        this.setState({
            dolazak: this.state.datumSletanja + 'T' + this.state.vremeSletanja + ':00'
        })

        console.log(this.state.dolazak);
    }

    changeTipLeta(e) {
        this.setState({
            tipLeta: e.target.value
        })
        console.log(e.target.value);
    }

    handleSubmit(e) {
        e.preventDefault();
        axios.get('http://localhost:8091/api/aviocompany/flight/getbydest/' + this.state.mestoPolaska + '/' + this.state.mestoDolaska).then(
            res => {
                console.log(res.data);
                this.setState({
                    flightsByDest: res.data
                })
            }
        )

        axios.get('http://localhost:8091/api/aviocompany/flight/getbytime/' + this.state.polazak + '/' + this.state.dolazak).then(
            res => {
                console.log(res.data);
                this.setState({
                    flightsByTime: res.data
                })
            }
        )

        axios.get('http://localhost:8091/api/aviocompany/flight/getbytype/' + this.state.tipLeta).then(
            res => {
                console.log(res.data);
                this.setState({
                    flightsByType: res.data
                })
            }
        )

    }

    handleSearchButton(e) {
        this.setState(function (prevState) {
            //dovrsi
        })
    }

    render() {
        return (
            <div>
                {/* <button onClick={this.handleSearchButton} className="btn blue center lighten-1 z-depth-0">Pretraga</button> */}
                <div className="container">
                    <form className="white" onSubmit={this.handleSubmit}>
                        <h2 className="red-text lighten-1 center">Pretraga letova</h2>
                        <div className="container">
                            <label htmlFor="takeoffdest">Mesto polaska</label>
                            <div className="input-field">
                                <select id="takeoffdest" className="browser-default" name="destinationTakeOff" onChange={this.changeMestoPolaska}>
                                    {this.state.destinations.map(dest =>
                                        <option>{dest.name}</option>
                                    )}
                                </select>
                            </div>
                            <label htmlFor="landingdest">Mesto dolaska</label>
                            <div className="input-field">
                                <select id="landingdest" className="browser-default" name="destinationLanding" onChange={this.changeMestoDolaska}>
                                    {this.state.destinations.map(dest =>
                                        <option>{dest.name}</option>
                                    )}
                                </select>
                            </div>
                            <label htmlFor="takeoff">Datum i vreme poletanja</label>
                            <div className="input-field">
                                <input type="date" className="datepicker" id="takeoff" onChange={this.changeDatumPoletanja} />
                                <input type="time" className="timepicker" id="takeofftime" onChange={this.changeDatumPoletanja} />
                            </div>
                            <label htmlFor="landing">Datum i vreme sletanja</label>
                            <div className="input-field">
                                <input type="date" className="datepicker" id="landing" onChange={this.changeDatumSletanja} />
                                <input type="time" className="timepicker" id="landingtime" onChange={this.changeDatumSletanja} />
                            </div>
                            <label htmlFor="fltype">Tip leta</label>
                            <div className="input-field">
                                <select id="fltype" className="browser-default" name="travelType" onChange={this.changeTipLeta}>
                                    {this.state.flights.map(flight =>
                                        <option>{flight.travelType}</option>
                                    )}
                                </select>
                            </div>

                            <div className="input-field">
                                <input type="submit" value="Pretrazi" className="btn blue lighten-1 z-depth-0" />
                            </div>
                        </div>
                    </form>
                </div>
                <LetoviIzlistavanje></LetoviIzlistavanje>
            </div>
        )
    }
};

export default LetoviPretraga;