import React, { Component } from 'react'




class RentACarPretraga extends Component {

    state = {

    }

    handleSubmit = (e) => {
        e.preventDefault();
        this.props.history.push('/')
    }

    render() {
        return (
            <div>

                <div className="container">

                    <form className="white" onSubmit={this.handleSubmit}>
                        <h2 className="red-text lighten-1 center">Pretraga vozila</h2>
                        <div className="container">

                            <div className="input-field">
                                <label htmlFor="lokacija_preuzimanja">Lokacija preuzimanja</label>
                                <input type="text" id='lokacija_preuzimanja' />
                            </div>

                            <div className="input-field">
                                <label htmlFor="lokacija_ostavljanja">Lokacija ostavljanja</label>
                                <input type="text" id='lokacija_ostavljanja' />
                            </div>

                            <div>
                                <label htmlFor="datum_preuzimanja" className="active">Datum preuzimanja</label>
                                <input type="date" id='datum_preuzimanja' className="datepicker" />
                            </div>

                            <div>
                                <label htmlFor="vreme_preuzimanja" className="active">Vreme preuzimanja</label>
                                <input type="time" id='vreme_preuzimanja' />
                            </div>


                            <div>
                                <label htmlFor="datum_ostavljanja" className="active">Datum ostavljanja</label>
                                <input type="date" id='datum_ostavljanja' className="datepicker" />
                            </div>

                            <div>
                                <label htmlFor="vreme_ostavljanja" className="active">Vreme ostavljanja</label>
                                <input type="time" id='vreme_ostavljanja' />
                            </div>

                            <div className="input-field">
                                <label htmlFor="broj_putnika">Broj putnika</label>
                                <input type="number" id='broj_putnika' />
                            </div>

                            <div className="input-field col s12">
                                <select className="browser-default">
                                    <option value="" disabled selected>Tip vozila</option>
                                    <option value="1">Option 1</option>
                                    <option value="2">Option 2</option>
                                    <option value="3">Option 3</option>
                                </select>
                            </div>

                            <div className="input-field">
                                <button className="btn blue lighten-1 z-depth-0">Pretraži</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        )
    }
};

export default RentACarPretraga;