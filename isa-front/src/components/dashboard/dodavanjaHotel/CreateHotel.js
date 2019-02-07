import React, { Component } from 'react'
import UserLoggedTabs from '../../layout/tabs/UserLoggedTabs';
import { connect } from 'react-redux'
import { createHotel } from '../../../store/actions/HotelActions'

class CreateHotel extends Component {

    state = {
        imeDodaj: '',
        adresaDodaj: '',
        opisDodaj: ''
    }

    handleChange = (e) => {
        this.setState({
            [e.target.id]: e.target.value
        })
    }

    handleSubmit = (e) => {
        e.preventDefault();
        console.log("EOOO", this.state);
        this.props.createHotel(this.state)
        
    }

    render() {
        return (
            <div>
                <UserLoggedTabs></UserLoggedTabs>
                <div className="container">
                    <form className="white" onSubmit={this.handleSubmit} >
                        <h2 className="red-text lighten-1 center">Dodavanje novog hotela</h2>
                        <div className="container">
                            <div className="input-field">
                                <label htmlFor="imeDodaj">Ime:</label>
                                <input type="text" id='imeDodaj' onChange={this.handleChange} />
                            </div>
                            <div className="input-field">
                                <label htmlFor="adresaDodaj">Adresa:</label>
                                <input type="text" id='adresaDodaj' onChange={this.handleChange} />
                            </div>
                            <div className="input-field">
                                <label htmlFor="opisDodaj">Promotivni opis:</label>
                                <input type="text" id='opisDodaj' onChange={this.handleChange} />
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

const mapDispatchToProps = (dispatch) => {
    return {
        createHotel: (hotel) => dispatch(createHotel(hotel))
    }
}

export default connect(null, mapDispatchToProps)(CreateHotel)