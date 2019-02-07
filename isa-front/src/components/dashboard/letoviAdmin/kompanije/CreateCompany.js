import React, {Component} from 'react'
import axios from 'axios'

class CreateCompany extends Component
{
    state = {
        destinations: [],
        flights: []
    }

    componentDidMount()
    {
        // axios.post('http://localhost:8091/api/aviocompany/company/').then(
        //     res => {
        //         console.log(res);
        //         this.setState({
        //             companies: res.data
        //         })
        //     }
        // );

        //za combobox sa spiskom destinacija
        axios.get('http://localhost:8091/api/aviocompany/destination/all').then(
            res => {
                console.log(res);
                this.setState({
                    destinations: res.data
                })
            }
        );

        //za dodavanje letova
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

    render()
    { 
        return(
            <div className="card indigo lighten-1">
                <div className="card-content">
                    <div className="card">
                        <div className="card-content">
                            <span className="card-title right"><a className="btn-floating btn-small waves-effect waves-light red"><i>x</i></a></span>
                            <div className="container">
                                <form className="white">
                                    <div className="input-field">
                                        <label htmlFor="name">Unesi naziv</label>
                                        <input type="text" id="name"/>
                                    </div>
                                    <div className="input-field">
                                        <label htmlFor="address">Unesi adresu</label>
                                        <input type="text" id="address"/>
                                    </div>
                                    <div className="input-field">
                                        <label htmlFor="desc">Unesi Opis</label>
                                        <textarea id="desc"/>
                                    </div>
                                    
                                    <div className="input-field col s12">
                                        <select id="dest" className="browser-default">
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
                                    <button className="btn green lighten-1 z-depth-0">Kreiraj</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }

}

export default CreateCompany