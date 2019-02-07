import React, {Component} from 'react'

class Flight extends Component
{
    state = {

    }
    
    //vreme putovanja mi ne treba, to se dobija kao razlika
    //duzina putovanja, da li to treba ili ce se dobijati kao
    //1 let = 1 avion => u letu namestamo pozicije sedenja

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
                                        <label htmlFor="flightid">ID leta</label>
                                        <input type="text" id="flightid" disabled/>
                                    </div>
                                    <button className="btn danger lighten-1 z-depth-0">Azuriraj raspored..</button>
                                    <div className="input-field">
                                        <label htmlFor="takeoff">Datum i vreme poletanja</label>
                                        <input type="text" className="datepicker" placeholder="postojeci datum" id="takeoff"/>
                                        <input type="text" className="timepicker" placeholder="postojece vreme" id="takeofftime"/>
                                    </div>
                                    <div className="input-field">
                                        <label htmlFor="landing">Datum i vreme sletanja</label>
                                        <input type="text" className="datepicker" placeholder="postojeci datum" id="landing"/>
                                        <input type="text" className="timepicker" placeholder="postojece vreme" id="landingtime"/>
                                    </div>
                                    <div className="input-field">
                                        <label htmlFor="transfer">Presedanja</label>
                                        <select id="transfer" multiple>
                                            <option value="">PostojecaDest1</option>
                                            <option value="">PostojecaDest2</option>
                                            <option value="">PostojecaDest3</option>
                                        </select>
                                    </div>
                                    <div className="input-field">
                                        <label htmlFor="grade">Razredi</label>
                                        <select id="grade" multiple className="browser-default">
                                            <option value="">1. klasa</option>
                                            <option value="">2. klasa</option>
                                            <option value="">3. klasa</option>
                                        </select>
                                    </div>
                                    <div className="input-field">
                                        <label htmlFor="luggage">Dozvoljeni prtljag</label>
                                        <select id="luggage">
                                            <option value="">Rucni</option>
                                            <option value="">Rucni + kofer-1</option>
                                            <option value="">Rucni + kofer-2</option>
                                            <option value="">Rucni + kofer-3</option>
                                        </select>
                                    </div>
                                    <button className="btn green lighten-1 z-depth-0">Izmeni</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }

}

export default Flight