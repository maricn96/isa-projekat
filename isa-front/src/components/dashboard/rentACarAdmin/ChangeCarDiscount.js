import React, { Component } from "react"
import { connect } from "react-redux"
import { createCarDiscount, editCarDiscount, deleteCarDiscount } from "../../../store/actions/RentACarActions"

class ChangeCarDiscount extends Component {

    state = {

        id: -1,
        carId: {
            id: -1,
            rentPrice: -1,
            carType: {
                id: -1,
                numberOfSeats: '',
                modelYear: '',
                model: '',
                brand: '',
                carType: ''
            },
            rentService: {
                id: -1,
                name: '',
                adress: '',
                description: ''
            },
            branchOffice: {
                id: -1,
                name: '',
                adress: '',
                city: '',
                rentServiceDTO: {
                    id: '',
                    adress: '',
                    name: '',
                    description: ''
                }
            }
        },
        dateFrom: '',
        dateTo: '',
        carDiscountPrecentage: '',
        datumPocetka: undefined,
        datumKraja: undefined,
        vremePocetka: undefined,
        vremeKraja: undefined

    }

    componentDidMount() {

        if (this.props.carDiscount) {
            var Pocetak = this.props.carDiscount.dateFrom.split("T");
            var Kraj = this.props.carDiscount.dateTo.split("T")


            this.setState(
                {
                    id: this.props.carDiscount.id,
                    carId: {
                        id: this.props.carDiscount.carId.id,
                        rentPrice: -1,
                        carType: {
                            id: this.props.carDiscount.carId.carType.id,
                            numberOfSeats: '',
                            modelYear: '',
                            model: '',
                            brand: '',
                            carType: ''
                        },
                        rentService: {
                            id: this.props.carDiscount.carId.rentService.id,
                            name: '',
                            adress: '',
                            description: ''
                        },
                        branchOffice: {
                            id: this.props.carDiscount.carId.branchOffice.id,
                            name: '',
                            adress: '',
                            city: '',
                            rentServiceDTO: {
                                id: '',
                                adress: '',
                                name: '',
                                description: ''
                            }
                        }
                    },
                    dateFrom: this.props.carDiscount.dateFrom,
                    dateTo: this.props.carDiscount.dateTo,
                    carDiscountPrecentage: this.props.carDiscount.carDiscountPrecentage,
                    datumPocetka: Pocetak[0],
                    datumKraja: Kraj[0],
                    vremePocetka: Pocetak[1],
                    vremeKraja: Kraj[1]
                }
            );
        }


    }

    handleChange = (e) => {
        this.setState({
            [e.target.id]: e.target.value
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

    handleCarChange = (e) => {

        var idVozila = e.target.value

        const foundCar = this.props.cars.find(item => item.id == idVozila)

        this.setState({

            carId: {
                id: foundCar.id,
                rentPrice: '',
                carType: {
                    id: foundCar.carType.id,
                    numberOfSeats: '',
                    modelYear: '',
                    model: '',
                    brand: '',
                    carType: ''
                },
                rentService: {
                    id: foundCar.rentService.id,
                    name: '',
                    adress: '',
                    description: ''
                },
                branchOffice: {
                    id: foundCar.branchOffice.id,
                    name: '',
                    adress: '',
                    city: '',
                    rentServiceDTO: {
                        id: foundCar.branchOffice.rentServiceDTO.id,
                        adress: '',
                        name: '',
                        description: ''
                    }
                }
            }


        })
    }


    handleSubmit = (e) => {
        e.preventDefault();
        if (this.props.izmena) {

            this.props.editCarDiscount(this.props.car.id, this.state);
        }
        else {
            this.props.createCarDiscount(this.state);
            this.setState({
                id: -1,
                carId: {
                    id: -1,
                    rentPrice: -1,
                    carType: {
                        id: -1,
                        numberOfSeats: '',
                        modelYear: '',
                        model: '',
                        brand: '',
                        carType: ''
                    },
                    rentService: {
                        id: -1,
                        name: '',
                        adress: '',
                        description: ''
                    },
                    branchOffice: {
                        id: -1,
                        name: '',
                        adress: '',
                        city: '',
                        rentServiceDTO: {
                            id: '',
                            adress: '',
                            name: '',
                            description: ''
                        }
                    }
                },
                dateFrom: '',
                dateTo: '',
                carDiscountPrecentage: '',
                datumPocetka: {},
                datumKraja: {},
                vremePocetka: {},
                vremeKraja: {}
            })
        }
    }

    deleteCarDiscount = (id) => {

        this.props.deleteCarDiscount(id);

    }



    render() {
        return (
            <div className="card indigo lighten-1">
                <div className="card-content">
                    <div className="card">
                        <div className="card-content">
                            <span className="card-title indigo-text lighten-1 left"><strong>{this.props.carDiscount ? this.props.carDiscount.id : ''}</strong></span>
                            {
                                this.props.brisanje ?
                                    <span className="card-title right" onClick={() => this.deleteCarDiscount(this.props.carDiscount.id)}><a class="btn-floating btn-small waves-effect waves-light red"><i>x</i></a></span>
                                    :
                                    ''
                            }
                            <div className="container">
                                <form className="white" onSubmit={this.handleSubmit} >
                                    <div class="input-field col s12">
                                        <select className="browser-default" onChange={(e) => { this.handleCarChange(e) }}>

                                            {this.props.cars.map(car => {
                                                return (
                                                    <option selected={this.props.carDiscount ? car.id == this.props.carDiscount.carId.id : 'false'} value={car.id}>Id vozila: {car.id}, Cena: {car.rentPrice}</option>
                                                );
                                            })}
                                        </select>
                                    </div>
                                    <div className="input-field">
                                        <label className="active" htmlFor="carDiscountPrecentage">Popust</label>
                                        <input type="number" id='carDiscountPrecentage' onChange={this.handleChange} defaultValue={this.props.carDiscount ? this.props.carDiscount.carDiscountPrecentage : ''} />
                                    </div>

                                    <div>
                                        <label htmlFor="datumPocetka" className="active">Datum pocetka popusta</label>
                                        <input type="date" id='datumPocetka' onChange={this.handleDateTimeChange} value={this.state.datumPocetka} />
                                    </div>

                                    <div>
                                        <label htmlFor="vremePocetka" className="active">Vreme pocetka popusta</label>
                                        <input type="time" id='vremePocetka' onChange={this.handleDateTimeChange} value={this.state.vremePocetka} />
                                    </div>


                                    <div>
                                        <label htmlFor="datumKraja" className="active">Datum kraja popusta</label>
                                        <input type="date" id='datumKraja' onChange={this.handleDateTimeChange} value={this.state.datumKraja} />
                                    </div>

                                    <div>
                                        <label htmlFor="vremeKraja" className="active">Vreme kraja popusta</label>
                                        <input type="time" id='vremeKraja' onChange={this.handleDateTimeChange} value={this.state.vremeKraja} />
                                    </div>

                                    {this.props.izmena ?
                                        <div className="input-field">
                                            <button className="btn blue lighten-1 z-depth-0">Azuriraj</button>
                                        </div>
                                        :
                                        <div className="input-field">
                                            <button className="btn green darken-3 z-depth-0">Dodaj</button>
                                        </div>
                                    }
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
};

const mapDispatchToProps = (dispatch) => {
    return {
        createCarDiscount: (carDiscount) => dispatch(createCarDiscount(carDiscount)),
        editCarDiscount: (id, carDiscount) => dispatch(editCarDiscount(id, carDiscount)),
        deleteCarDiscount: (id) => dispatch(deleteCarDiscount(id))
    }
}

export default connect(null, mapDispatchToProps)(ChangeCarDiscount)

