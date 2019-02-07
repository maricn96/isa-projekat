import React, { Component } from 'react'
import UserLoggedTabs from '../../layout/tabs/UserLoggedTabs';

class CreateRezervacijaSobe extends Component {
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
                        <h2 className="red-text lighten-1 center">Dodavanje novog tipa sobe</h2>
                        <div className="container">
                            <div className="input-field">
                                <label htmlFor="cenaRezervacijeDodaj">Cena rezervacije:</label>
                                <input type="number" id='cenaRezervacijeDodaj' />
                            </div>
                            <div>
                                <label htmlFor="rezervisanoOdDodaj" className="active">Rezervisano od:</label>
                                <input type="date" id='rezervisanoOdDodaj' className= "datepicker"/>
                            </div>
                            <div>
                                <label htmlFor="rezervisanoDoDodaj" className="active">Rezervisano do:</label>
                                <input type="date" id='rezervisanoDoDodaj'/>
                            </div>
                            <div class="input-field col s12">
                                <select className="browser-default">
                                    <option value="" disabled selected>Hotelska soba:</option>
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

export default CreateRezervacijaSobe;