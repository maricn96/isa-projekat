import React, { Component } from "react"
import { connect } from "react-redux"
import { createCarType, editCarType, deleteCarType } from "../../../store/actions/RentACarActions"



class ChangeCarType extends Component {

    state = {

        id: -1,
        numberOfSeats: '',
        modelYear: '',
        model: '',
        brand: '',
        carType: ''

    }

    componentDidMount() {

        if (this.props.carType) {
            this.setState({
                id: this.props.carType.id,
                numberOfSeats: this.props.carType.numberOfSeats,
                modelYear: this.props.carType.modelYear,
                model: this.props.carType.model,
                brand: this.props.carType.brand,
                carType: this.props.carType.carType
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

            this.props.editCarType(this.props.carType.id, this.state);
        }
        else {
            this.props.createCarType(this.state);
            this.setState({
                id: -1,
                numberOfSeats: '',
                modelYear: '',
                model: '',
                brand: '',
                carType: ''
            })
        }
    }

    deleteCarType = (id) => {

        this.props.deleteCarType(id);

    }


    render() {
        return (
            <div className="card indigo lighten-1">
                <div className="card-content">
                    <div className="card">
                        <div className="card-content">
                            <span className="card-title indigo-text lighten-1 left"><strong>{this.props.carType ? this.props.carType.id : ''}</strong></span>
                            {this.props.brisanje ?
                                <span className="card-title right" onClick={() => this.deleteCarType(this.props.carType.id)}><a class="btn-floating btn-small waves-effect waves-light red"><i>x</i></a></span>
                                :
                                ''
                            }


                            <div className="container">
                                <form className="white" onSubmit={this.handleSubmit}>
                                    <div className="input-field">
                                        <label className="active" htmlFor="brand">Brend</label>
                                        <input type="text" onChange={this.handleChange} id='brand' defaultValue={this.props.carType ? this.props.carType.brand : ''} />
                                    </div>
                                    <div className="input-field">
                                        <label className="active" htmlFor="model">Model</label>
                                        <input type="text" onChange={this.handleChange} id='model' defaultValue={this.props.carType ? this.props.carType.model : ''} />
                                    </div>
                                    <div className="input-field">
                                        <label className="active" htmlFor="modelYear">Godina proizvodnje</label>
                                        <input type="number" onChange={this.handleChange} id='modelYear' defaultValue={this.props.carType ? this.props.carType.modelYear : ''} />
                                    </div>
                                    <div className="input-field">
                                        <label className="active" htmlFor="carType">Vrsta auta</label>
                                        <input type="text" onChange={this.handleChange} id='carType' defaultValue={this.props.carType ? this.props.carType.carType : ''} />
                                    </div>
                                    <div className="input-field">
                                        <label className="active" htmlFor="numberOfSeats">Broj sedista</label>
                                        <input type="number" onChange={this.handleChange} id='numberOfSeats' defaultValue={this.props.carType ? this.props.carType.numberOfSeats : ''} />
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
        createCarType: (carType) => dispatch(createCarType(carType)),
        editCarType: (id, carType) => dispatch(editCarType(id, carType)),
        deleteCarType: (id) => dispatch(deleteCarType(id))
    }
}

export default connect(null, mapDispatchToProps)(ChangeCarType)


