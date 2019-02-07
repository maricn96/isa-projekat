import React, { Component } from 'react'
import UserLoggedTabs from '../../layout/tabs/UserLoggedTabs';
import { connect } from 'react-redux'
import { createUsluga } from '../../../store/actions/DodatneUslugeActions';

class CreateDodatnaUsluga extends Component {
    state = {
        nazivDodatneUslugeDodaj:"",
        cenaDodatneUslugeDodaj:"",
        popustDodaj:"",
        hotelDodaj:""
    }

    handleChange = (e) => {
        this.setState({
            [e.target.id]: e.target.value
        })
    }

    handleSubmit = (e) => {
        e.preventDefault();
        this.props.createUsluga(this.state)
    }

    render() {
        return (
            <div>
                <UserLoggedTabs></UserLoggedTabs>
                <div className="container">
                    <form className="white" onSubmit={this.handleSubmit} >
                        <h2 className="red-text lighten-1 center">Dodavanje nove dodatne usluge</h2>
                        <div className="container">
                            <div className="input-field">
                                <label htmlFor="nazivDodatneUslugeDodaj">Naziv:</label>
                                <input type="text" id='nazivDodatneUslugeDodaj' onChange = {this.handleChange}/>
                            </div>
                            <div className="input-field">
                                <label htmlFor="cenaDodatneUslugeDodaj">Cena:</label>
                                <input type="number" id='cenaDodatneUslugeDodaj' onChange = {this.handleChange}/>
                            </div>
                            <div className="input-field">
                                <label htmlFor="popustDodaj">Popust:</label>
                                <input type="number" id='popustDodaj' onChange = {this.handleChange}/>
                            </div>
                            <div className="input-field">
                                <label htmlFor="hotelDodaj">Hotel:</label>
                                <input type="number" id='hotelDodaj' onChange = {this.handleChange}/>
                            </div>                           
                            <div className="input-field">
                                <button className="btn blue lighten-1 z-depth-0">Dodaj</button>
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
        createUsluga: (usluga) => dispatch(createUsluga(usluga))
    }
}

export default connect(null, mapDispatchToProps)(CreateDodatnaUsluga);