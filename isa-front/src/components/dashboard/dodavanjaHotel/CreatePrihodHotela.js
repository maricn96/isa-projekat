import React, { Component } from 'react'
import UserLoggedTabs from '../../layout/tabs/UserLoggedTabs';

class CreatePrihodHotela extends Component {
    state = {

    }

    handleSubmit = (e) => {
        e.preventDefault();
    }

    render() {
        return (
            <div>
                <UserLoggedTabs></UserLoggedTabs>
                <div className="container">
                    <form className="white" onSubmit={this.handleSubmit} >
                        <h2 className="red-text lighten-1 center">Dodavanje novog prihoda</h2>
                        <div className="container">
                            <div className="input-field">
                                <label htmlFor="prihodDodaj">Prihod:</label>
                                <input type="number" id='prihodDodaj' />
                            </div>
                            <div>
                                <label htmlFor="datumPrihodaDodaj">Datum prihoda:</label>
                                <input type="date" id='datumPrihodaDodaj' />
                            </div>
                            <div class="input-field col s12">
                                <select className="browser-default">
                                    <option value="" disabled selected>Hotel:</option>
                                </select>
                            </div>
                            <div className="input-field">
                                <button className="btn blue lighten-1 z-depth-0">Dodaj</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        )
    }
};

export default CreatePrihodHotela;