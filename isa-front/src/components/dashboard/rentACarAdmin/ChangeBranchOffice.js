import React, { Component } from "react"
import { connect } from "react-redux"
import { createBranchOffice, editBranchOffice, deleteBranchOffice } from "../../../store/actions/RentACarActions"

class ChangeBranchOffice extends Component {

    state = {
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

    componentDidMount() {

        if (this.props.branchOffice) {
            this.setState({
                id: this.props.branchOffice.id,
                name: this.props.branchOffice.name,
                adress: this.props.branchOffice.adress,
                city: this.props.branchOffice.city,
                rentServiceDTO: {
                    id: this.props.branchOffice.rentServiceDTO.id,
                    adress: this.props.branchOffice.rentServiceDTO.adress,
                    name: this.props.branchOffice.rentServiceDTO.name,
                    description: this.props.branchOffice.rentServiceDTO.description
                }
            })
        }

        if (this.props.rentACarService) {
            this.setState({
                rentServiceDTO: {
                    id: this.props.rentACarService.id,
                    adress: this.props.rentACarService.adress,
                    name: this.props.rentACarService.name,
                    description: this.props.rentACarService.description
                }
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

            this.props.editBranchOffice(this.props.branchOffice.id, this.state);
        }
        else {

            this.props.createBranchOffice(this.state);
            this.setState({
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
            })
        }
    }

    deleteBranchOffice = (id) => {

        this.props.deleteBranchOffice(id);

    }

    render() {
        return (
            <div className="card indigo lighten-1">
                <div className="card-content">
                    <div className="card">
                        <div className="card-content">
                            <span className="card-title indigo-text lighten-1 left"><strong>{this.props.branchOffice ? this.props.branchOffice.id : ''}</strong></span>

                            {this.props.brisanje ?
                                <span className="card-title right" onClick={() => this.deleteBranchOffice(this.props.branchOffice.id)}><a class="btn-floating btn-small waves-effect waves-light red"><i>x</i></a></span>
                                :
                                ''
                            }

                            <div className="container">
                                <form className="white" onSubmit={this.handleSubmit} >
                                    <div className="input-field">
                                        <label className="active" htmlFor="name">Ime</label>
                                        <input type="text" onChange={this.handleChange} id='name' defaultValue={this.props.branchOffice ? this.props.branchOffice.name : ''} />
                                    </div>
                                    <div className="input-field">
                                        <label className="active" htmlFor="adress">Adresa</label>
                                        <input type="text" onChange={this.handleChange} id='adress' defaultValue={this.props.branchOffice ? this.props.branchOffice.adress : ''} />
                                    </div>
                                    <div className="input-field">
                                        <label className="active" htmlFor="city">Grad</label>
                                        <input type="text" id='city' onChange={this.handleChange} defaultValue={this.props.branchOffice ? this.props.branchOffice.city : ''} />
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
        createBranchOffice: (branchOffice) => dispatch(createBranchOffice(branchOffice)),
        editBranchOffice: (id, branchOffice) => dispatch(editBranchOffice(id, branchOffice)),
        deleteBranchOffice: (id) => dispatch(deleteBranchOffice(id))
    }
}

export default connect(null, mapDispatchToProps)(ChangeBranchOffice);