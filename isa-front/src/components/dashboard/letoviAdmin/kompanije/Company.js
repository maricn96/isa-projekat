import React, {Component} from 'react'
import axios from 'axios'

class Company extends Component
{
    state = {
        companies: [],
        destinations: [],
        flights: []
    }

    componentDidMount()
    {
        axios.get('http://localhost:8091/api/aviocompany/company/all').then(
            res => {
                console.log(res);
                this.setState({
                    companies: res.data
                })
            }
        );

        axios.get('http://localhost:8091/api/aviocompany/destination/all').then(
            res => {
                console.log(res);
                this.setState({
                    destinations: res.data
                })
            }
        );

        axios.get('http://localhost:8091/api/aviocompany/flight/all').then(
            res => {
                console.log(res);
                this.setState({
                    flights: res.data
                })
            }
        );
    }


    handleChange()
    {

    }
    //<!--SPISAK KARATA SA POPUSTIMA ZA BRZU REZ (??!) -->
    //konfiguracija mesta u avionima ide za svaki let posebno, kao i prtljag i cenovnik
    render()
    { 
        return(
            <div className="card indigo lighten-1">
                <div className="card-content">
                    <div className="card">
                        <div className="card-content">
                            <span className="card-title right"><a className="btn-floating btn-small waves-effect waves-light red"><i>x</i></a></span>
                            {this.state.companies.map(company => 
                            <div className="container">
                                <form className="white">
                                    <div className="input-field">
                                        <label htmlFor="name"></label>
                                        <input type="text" id="name" value={company.name}/>
                                    </div>
                                    <div className="input-field">
                                        <label htmlFor="address"></label>
                                        <input type="text" id="address" value={company.address}/>
                                    </div>
                                    <div className="input-field">
                                        <label htmlFor="desc"></label>
                                        <textarea id="desc" value={company.description}/>
                                    </div>
                                    
                                    <div className="input-field col s12">
                                        <select id="dest" onChange={this.handleChange} className="browser-default">
                                            {this.state.destinations.map(destination =>
                                                <option value="vr1">{destination.name}</option>
                                            )}
                                        </select> 
                                    </div>
                                    <div className="input-field col s12">
                                        <select id="flight" onChange={this.handleChange} className="browser-default" multiple>
                                            {this.state.flights.map(flight =>
                                                <option value="v41">{'Redni broj: ' + flight.id + ' Datum polaska: ' + flight.takeOffTime}</option>
                                            )}
                                        </select>
                                    </div>
                                    <button className="btn green lighten-1 z-depth-0">Izmeni</button>
                                </form>
                                <br /><br /><br /><hr></hr><br /><br /><br />
                            </div>
                            )}
                        </div>
                    </div>
                </div>
            </div>
        );
    }

}

export default Company