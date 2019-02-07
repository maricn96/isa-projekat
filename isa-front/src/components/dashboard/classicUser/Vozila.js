import React, { Component } from 'react'
import axios from 'axios'
import { NavLink } from 'react-router-dom'
import { connect } from "react-redux"
import { createUserShoppingCart, addRentACarReservationToShoppingCart } from "../../../store/actions/PurchasesActions"


class Cenovnici extends Component {

    state = {
        vozila: [],
        tipoviVozila: [],
        izabraniTipVozila: -1,
        sakriPretragu: true,
        ponistiPretragu: false,
        carType: '',
        datumPocetka: undefined,
        datumKraja: undefined,
        vremePocetka: undefined,
        vremeKraja: undefined,
        dateFrom: '',
        dateTo: '',
        aktivirajDugmeRezervacije: false
    }

    rezervisiVozilo = (e) => {
        var id_vozila = e.target.id;

        //ispitam da li korpa postoji...


        if (!this.props.userShoppingCart) {

            //ukoliko ne postoji pozivam dispatch da je kreira i prosledjujem user_id....
            this.props.createUserShoppingCart(this.props.userId)

        }

        var vozilo = this.state.vozila.find(vozilo => vozilo.id == id_vozila)

        this.props.addRentACarReservationToShoppingCart(this.props.userId, this.state.dateFrom, this.state.dateTo, vozilo)



    }

    componentDidMount() {
        this.getAllCars();
        this.getAllCarTypes();
    }

    getAllCarTypes = () => {
        axios.get('http://localhost:8090/api/rentacar/carType/all')
            .then(res => {
                console.log(res.data);
                this.setState({
                    tipoviVozila: res.data
                })
            })
    }

    getAllCars = () => {
        const id = this.props.match.params.serviceId;//id servisa
        axios.get('http://localhost:8090/api/rentacar/car/getByRentService/' + id)//tvoj url za pronalazak vozila po id servisa
            .then(res => {
                console.log(res);
                this.setState({
                    vozila: res.data
                })
            })
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

    handleSubmit = (e) => {
        e.preventDefault();
        if (this.state.dateFrom != '' && this.state.dateTo != '') {
            axios.get('http://localhost:8090/api/rentacar/car/getFreeCars/' + this.state.dateFrom + '/' + this.state.dateTo)//tvoj url za pronalazak vozila po id servisa
                .then(res => {
                    console.log(res);
                    if (this.state.izabraniTipVozila == -1) {
                        this.setState({
                            vozila: res.data.filter(item => item.rentService.id == this.props.match.params.serviceId),
                            aktivirajDugmeRezervacije: true
                        })
                    }
                    else {
                        this.setState({
                            vozila: res.data.filter(item => item.carType.id == this.state.izabraniTipVozila && item.rentService.id == this.props.match.params.serviceId),
                            aktivirajDugmeRezervacije: true
                        })
                    }

                })
        }

        if (this.state.dateFrom == '' && this.state.dateTo == '' && this.state.izabraniTipVozila != -1) {

            this.setState({
                vozila: this.state.vozila.filter(item => item.carType.id == this.state.izabraniTipVozila)
            })
        }
        else if (this.state.izabraniTipVozila == -1) {
            this.getAllCars();
        }


    }

    carTypeChange = (e) => {
        this.setState({
            izabraniTipVozila: e.target.value
        })
    }

    handleDateTimeChange = (e) => {

        if (e.target.id == "datumPocetka" || e.target.id == "vremePocetka") {

            var datumPocetka = undefined;
            var vremePocetka = undefined;
            if (e.target.id == "datumPocetka") {
                datumPocetka = e.target.value;
                if (this.state.vremePocetka)
                    vremePocetka = this.state.vremePocetka;
            }
            else {
                vremePocetka = e.target.value;
                if (this.state.datumPocetka)
                    datumPocetka = this.state.datumPocetka;

            }

            var formatDatumIVremeZaApi = datumPocetka + 'T' + vremePocetka + ':00';

            this.setState({

                datumPocetka: datumPocetka,
                vremePocetka: vremePocetka,
                dateFrom: formatDatumIVremeZaApi

            })

        }
        else if (e.target.id == "datumKraja" || e.target.id == "vremeKraja") {

            var datumKraja = undefined;
            var vremeKraja = undefined;
            if (e.target.id == "datumKraja") {
                datumKraja = e.target.value;
                if (this.state.vremeKraja)
                    vremeKraja = this.state.vremeKraja;
            }
            else {
                vremeKraja = e.target.value;
                if (this.state.datumKraja)
                    datumKraja = this.state.datumKraja;

            }

            var formatDatumIVremeZaApi = datumKraja + 'T' + vremeKraja + ":00";

            this.setState({

                datumKraja: datumKraja,
                vremeKraja: vremeKraja,
                dateTo: formatDatumIVremeZaApi

            })

        }


    }




    render() {
        const { vozila } = this.state;
        var imeServisa = "";
        const vozilaList = vozila.length ? (vozila.map(vozilo => {
            imeServisa = vozilo.rentService.name;
            return (
                <div className="post card grey lighten-2">
                    <div className="card-content container">
                        <span className="card-title center"><strong>{vozilo.carType.model} {vozilo.carType.brand}</strong></span>
                        <div className="left-align">
                            <p>Cena: {vozilo.rentPrice}</p>
                            <p>Filijala: {vozilo.branchOffice.name} {vozilo.branchOffice.city} {vozilo.branchOffice.adress}</p>
                        </div>
                        {
                            this.state.aktivirajDugmeRezervacije ?
                                <div>
                                    <br />
                                    <button onClick={this.rezervisiVozilo} id={vozilo.id} className="btn orange darken-3 z-depth-0">Rezervisi</button>

                                </div>
                                :
                                ''
                        }
                    </div>
                </div>
            )
        })) : (<div className="center">Nisu pronadjena vozila.</div>)

        return (
            <div>

                <div className="container center">
                    <h2 className="red-text lighten-1 center">Vozila servisa {imeServisa}</h2>
                    {this.props.ulogovanUser && this.state.sakriPretragu ?
                        <NavLink to="#" onClick={this.toggleForm}>Prikazi formu za rezervaciju</NavLink>
                        :
                        ''
                    }
                    {this.props.ulogovanUser && this.state.sakriPretragu == false ?
                        <div>
                            <NavLink to="#" onClick={this.hideForm}>Sakrij formu</NavLink>
                            <div className="card">
                                <div className="card-content">

                                    <div className="container">
                                        <form className="white" onSubmit={this.handleSubmit} >

                                            <div>
                                                <label htmlFor="datumPocetka" className="active">Datum pocetka rezervacije</label>
                                                <input type="date" id='datumPocetka' onChange={this.handleDateTimeChange} value={this.state.datumPocetka} />
                                            </div>

                                            <div>
                                                <label htmlFor="vremePocetka" className="active">Vreme pocetka rezervacije</label>
                                                <input type="time" id='vremePocetka' onChange={this.handleDateTimeChange} value={this.state.vremePocetka} />
                                            </div>


                                            <div>
                                                <label htmlFor="datumKraja" className="active">Datum kraja rezervacije</label>
                                                <input type="date" id='datumKraja' onChange={this.handleDateTimeChange} value={this.state.datumKraja} />
                                            </div>

                                            <div>
                                                <label htmlFor="vremeKraja" className="active">Vreme kraja rezervacije</label>
                                                <input type="time" id='vremeKraja' onChange={this.handleDateTimeChange} value={this.state.vremeKraja} />
                                            </div>

                                            <div>
                                                <label htmlFor="tipVozilaSelect" className="active">Tip vozila</label>

                                                <select id="tipVozilaSelect" onChange={this.carTypeChange} className="browser-default">
                                                    <option value={-1}>Bilo koji tip</option>
                                                    {this.state.tipoviVozila.map(carType => {
                                                        return (
                                                            <option value={carType.id}>{carType.brand} {carType.model} {carType.modelYear}, {carType.carType}, broj sedista: {carType.numberOfSeats}</option>
                                                        );
                                                    })}
                                                </select>
                                            </div>

                                            <div className="input-field">
                                                <button className="btn purple darken-3 z-depth-0">Pretraga</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                        :
                        ''
                    }
                    {
                        this.state.ponistiPretragu ?

                            <NavLink to="#" onClick={this.getAllCars}>Ponistite pretragu</NavLink>


                            :
                            ''
                    }


                    {vozilaList}
                </div>
            </div>
        )
    }
}

const mapStateToProps = (state) => {
    return {
        userShoppingCart: state.purchases.userShoppingCart
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        createUserShoppingCart: (id) => dispatch(createUserShoppingCart(id)),
        addRentACarReservationToShoppingCart: (idKorpe, dateFrom, dateTo, car) => dispatch(addRentACarReservationToShoppingCart(idKorpe, dateFrom, dateTo, car))
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Cenovnici)
