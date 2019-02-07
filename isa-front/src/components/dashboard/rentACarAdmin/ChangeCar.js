import React, { Component } from "react"
import { connect } from "react-redux"
import { createCar, editCar, deleteCar } from "../../../store/actions/RentACarActions"
import axios from "axios"

class ChangeCar extends Component {

    state = {
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
        ,
        prosecnaOcena: -1
    }

    componentDidMount() {

        if (this.props.car) {
            this.setState({
                id: this.props.car.id,
                rentPrice: this.props.car.rentPrice,
                carType: this.props.car.carType,
                rentService: this.props.car.rentService,
                branchOffice: this.props.car.branchOffice
            })
        }

        if (this.props.rentACarService) {
            this.setState({
                rentService: {
                    id: this.props.rentACarService.id,
                    adress: this.props.rentACarService.adress,
                    name: this.props.rentACarService.name,
                    description: this.props.rentACarService.description
                }
            })
        }

        //uzimamo njegovu prosecnu ocenu...
        if (this.props.brisanje) {

            axios.get('http://localhost:8095/api/purchases/carRating/getAverageRating/' + this.props.car.id)
                .then(res => {
                    this.setState({
                        prosecnaOcena: res.data
                    })
                })

        }

    }

    handleChange = (e) => {
        this.setState({
            [e.target.id]: e.target.value
        })
    }


    handleSubmit = (e) => {
        e.preventDefault();
        if (this.props.izmena) {

            this.props.editCar(this.props.car.id, this.state);
        }
        else {
            this.props.createCar(this.state);
            this.setState({
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
            })
        }
    }

    deleteCar = (id) => {

        this.props.deleteCar(id);

    }

    carTypeChange = (e) => {

        this.setState({
            carType: {
                id: e.target.value,
                numberOfSeats: '',
                modelYear: '',
                model: '',
                brand: '',
                carType: ''
            }
        })

    }

    branchOfficeChange = (e) => {
        this.setState({
            branchOffice: {
                id: e.target.value,
                name: '',
                adress: '',
                city: '',
                rentServiceDTO: {
                    id: this.props.rentACarService.id,
                    adress: '',
                    name: '',
                    description: ''
                }
            }
        })
    }

    render() {
        return (
            <div className="card indigo lighten-1">
                <div className="card-content">
                    <div className="card">
                        <div className="card-content">
                            <span className="card-title indigo-text lighten-1 left"><strong>{this.props.car ? this.props.car.id : ''}</strong></span>
                            {this.props.brisanje ?
                                <div>
                                    {
                                        this.state.prosecnaOcena == -1 ?
                                            <p>Nema jos ocena</p>
                                            :
                                            <h6 className="yellow-text darken-3">Prosecna ocena: {this.state.prosecnaOcena}</h6>
                                    }
                                    <span className="card-title right" onClick={() => this.deleteCar(this.props.car.id)}><a class="btn-floating btn-small waves-effect waves-light red"><i>x</i></a></span>

                                </div>
                                :
                                ''
                            }

                            <div className="container">

                                <form className="white" onSubmit={this.handleSubmit} >
                                    <div className="input-field">
                                        <label className="active" htmlFor="rentPrice" >Cena</label>
                                        <input onChange={this.handleChange} type="text" id='rentPrice' defaultValue={this.props.car ? this.props.car.rentPrice : ''} />
                                    </div>
                                    <div class="input-field col s12">
                                        <label htmlFor="chooseCarType" className="active">Tip vozila</label>
                                        <select id="chooseCarType" onChange={this.carTypeChange} className="browser-default">

                                            {this.props.carTypes.map(carType => {
                                                return (
                                                    <option selected={this.props.car ? carType.id == this.props.car.carType.id : 'false'} value={carType.id}>{carType.brand} {carType.model} {carType.modelYear}, {carType.carType}, broj sedista: {carType.numberOfSeats}</option>
                                                );
                                            })}

                                        </select>
                                    </div>
                                    <div class="input-field col s12">
                                        <label htmlFor="chooseBranch" className="active">Filijala</label>
                                        <select id="chooseBranch" onChange={this.branchOfficeChange} className="browser-default">
                                            {this.props.branchOffices.map(branch => {
                                                return (
                                                    <option selected={this.props.car ? branch.id == this.props.car.branchOffice.id : 'false'} value={branch.id}>{branch.name}, {branch.adress}, {branch.city} </option>
                                                );
                                            })}

                                        </select>
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
        createCar: (car) => dispatch(createCar(car)),
        editCar: (id, car) => dispatch(editCar(id, car)),
        deleteCar: (id) => dispatch(deleteCar(id))
    }
}

export default connect(null, mapDispatchToProps)(ChangeCar)
