import React, {Component} from 'react'

class Statistic extends Component
{
    state = {

    }
    
    //1 let = 1 izvestaj
    //datum i vreme sletanja = datum i vreme prihoda

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
                                        <label htmlFor="repoflightid">ID leta</label>
                                        <input type="text" id="repoflightid" disabled/>
                                    </div>
                                    <div className="input-field">
                                        <label htmlFor="incomedate">Datum i vreme prihoda</label>
                                        <input type="text" id="incomedate"/>
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
                                        <select id="grade" multiple>
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
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }

}

export default Statistic