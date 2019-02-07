import React, { Component } from 'react'
import UserLoggedTabs from '../../layout/tabs/UserLoggedTabs';
import { connect } from 'react-redux'
import { editUsluga } from '../../../store/actions/DodatneUslugeActions';
import axios from "axios"

class IzmenaDodatnihUslugaAdmin extends Component {
    state = {
        nazivDodatneUslugeIzmeni:"",
        cenaDodatneUslugeIzmeni:"",
        popustIzmeni:"",
        idIzmeni:"",
        hotel:""
    }

    componentDidMount () {
        const id = this.props.match.params.uslugaId;
        //console.log("ID: "+id);
        axios.get('http://localhost:8092/api/hotel/dodatneUsluge/'+id)
            .then(res => {
                console.log(res);
                this.setState({
                    idIzmeni: res.data.id,
                    nazivDodatneUslugeIzmeni: res.data.additionalServiceName,
                    cenaDodatneUslugeIzmeni: res.data.additionalServicePrice,
                    popustIzmeni: res.data.popust,
                    hotel: res.data.hotel_dodatneUsluge
                })
            })
    }

    handleChange = (e) => {
        this.setState({
            [e.target.id]: e.target.value
        })
    }

    handleSubmit = (e) => {
        e.preventDefault();
        this.props.editUsluga(this.state)
    }

    render() {
        return (
            <div>
                <UserLoggedTabs></UserLoggedTabs>
                <div className="container">
                    <form className="white" onSubmit={this.handleSubmit} >
                        <h2 className="red-text lighten-1 center">Izmena dodatne usluge</h2>
                        <div className="container">
                            <div>
                                <label htmlFor="nazivDodatneUslugeIzmeni">Naziv:</label>
                                <input type="text" id='nazivDodatneUslugeIzmeni' onChange = {this.handleChange} value={this.state.nazivDodatneUslugeIzmeni}/>
                            </div>
                            <div>
                                <label htmlFor="cenaDodatneUslugeIzmeni">Cena:</label>
                                <input type="number" id='cenaDodatneUslugeIzmeni' onChange = {this.handleChange} value={this.state.cenaDodatneUslugeIzmeni}/>
                            </div>
                            <div>
                                <label htmlFor="popustIzmeni">Popust:</label>
                                <input type="number" id='popustIzmeni' onChange = {this.handleChange} value={this.state.popustIzmeni}/>
                            </div>                         
                            <div className="input-field">
                                <button className="btn blue lighten-1 z-depth-0">Izmeni</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        )
    }
};

const mapDispatchToProps = (dispatch) =>{
    return{
        editUsluga: (usluga) => dispatch(editUsluga(usluga))
    }
}

export default connect(null, mapDispatchToProps)(IzmenaDodatnihUslugaAdmin);