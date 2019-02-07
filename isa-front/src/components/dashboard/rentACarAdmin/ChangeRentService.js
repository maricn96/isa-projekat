import React, { Component } from "react"
import StarRating from "../reservation/StarRating";
import { connect } from "react-redux"
import { createRentACarService, editRentACarService, deleteRentACarService } from "../../../store/actions/RentACarActions"
import axios from 'axios'

class ChangeRentService extends Component {

    state = {
        id: -1,
        name: '',
        adress: '',
        description: '',
        prosecnaOcena: -1
    }

    componentDidMount() {
        //ukoliko je izmena ovo ce postojati i onda nam i treba da nam ubaci podatke
        if (this.props.rentACarService) {
            this.setState({
                id: this.props.rentACarService.id,
                name: this.props.rentACarService.name,
                adress: this.props.rentACarService.adress,
                description: this.props.rentACarService.description
            })
        }

        //uzimamo njegovu prosecnu ocenu...
        if (this.props.izmena) {

            axios.get('http://localhost:8095/api/purchases/rentACarRating/getAverageRating/' + this.props.rentACarService.id)
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

            this.props.editRentACarService(this.props.rentACarService.id, this.state);
        }
        else {
            this.props.createRentACarService(this.state);
            this.setState({
                id: -1,
                name: '',
                adress: '',
                description: ''
            })
        }
    }

    deleteRentACarService = (id) => {

        this.props.deleteRentACarService(id);

    }

    render() {
        return (
            <div className="card indigo lighten-1">
                <div className="card-content">
                    <span className="card-title white-text lighten-1 center"><strong>Id: {this.props.rentACarService ? this.props.rentACarService.id : ''}</strong></span>
                    <div className="card">
                        <div className="card-content">
                            {this.props.izmena ?

                                <div>
                                    {
                                        this.state.prosecnaOcena == -1 ?
                                            <p>Nema jos ocena</p>
                                            :
                                            <h5 className="yellow-text darken-3 center">Prosecna ocena: {this.state.prosecnaOcena}</h5>
                                    }
                                </div>

                                :
                                ''
                            }

                            {this.props.brisanje ?

                                <span className="card-title right" onClick={() => this.deleteRentACarService(this.props.rentACarService.id)}><a class="btn-floating btn-small waves-effect waves-light red"><i>x</i></a></span>
                                :
                                ''
                            }
                            <div className="container">
                                <form className="white" onSubmit={this.handleSubmit} >
                                    <div className="input-field">
                                        <label className="active" htmlFor="name">Ime</label>
                                        <input type="text" id='name' onChange={this.handleChange} defaultValue={this.props.rentACarService ? this.props.rentACarService.name : ''} />
                                    </div>
                                    <div className="input-field">
                                        <label className="active" htmlFor="adress">Adresa</label>
                                        <input type="text" id='adress' onChange={this.handleChange} defaultValue={this.props.rentACarService ? this.props.rentACarService.adress : ''} />
                                    </div>
                                    <div className="input-field">
                                        <textarea id="description" onChange={this.handleChange} class="materialize-textarea" defaultValue={this.props.rentACarService ? this.props.rentACarService.description : ''}></textarea>
                                        <label className="active" for="description">Textarea</label>
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
        createRentACarService: (rentACarService) => dispatch(createRentACarService(rentACarService)),
        editRentACarService: (id, editedService) => dispatch(editRentACarService(id, editedService)),
        deleteRentACarService: (id) => dispatch(deleteRentACarService(id))
    }
}

export default connect(null, mapDispatchToProps)(ChangeRentService)

