import React, {Component} from 'react'

class AddAvioCompany extends Component
{
    state = {

    }

    renter()
    {
        return(
            <div className="card indigo lighten-1">
                <div className="card-content">
                    <div className="card">
                        <div className="card-content">
                        <div className="container">
                            <form className="white">
                                <div className="input-field">
                                    <label htmlFor="name">Naziv</label>
                                    <input type="text" id="name"/>
                                </div>
                                <div className="input-field">
                                    <label htmlFor="address">Adresa</label>
                                    <input type="text" id="address"/>
                                </div>
                                <div className="input-field">
                                    <label htmlFor="description">Opis</label>
                                    <input type="text" id="description"/>
                                </div>
                                <div className="input-field">
                                        <button className="btn blue lighten-1 z-depth-0">Kreiraj</button>
                                    </div>
                            </form>
                        </div>
                        
                        </div>
                    </div>
                </div>
            </div>
        );
    }

}