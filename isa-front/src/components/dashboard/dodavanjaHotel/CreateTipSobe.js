import React, { Component } from 'react'
import UserLoggedTabs from '../../layout/tabs/UserLoggedTabs';
import { connect } from 'react-redux'
import { createTip } from '../../../store/actions/TipActions'

class CreateTipSobe extends Component {
    state = {
        kapacitetDodaj:"",
        nazivTipaDodaj:"", 
        hotelTipaDodaj:""
    }

    handleChange = (e) => {
        this.setState({
            [e.target.id]: e.target.value
        })
    }

    handleSubmit = (e) => {
        e.preventDefault();
        this.props.createTip(this.state)
    }

    render() {
        return (
            <div>
                <UserLoggedTabs></UserLoggedTabs>
                <div className="container">
                    <form className="white" onSubmit={this.handleSubmit} >
                        <h2 className="red-text lighten-1 center">Dodavanje novog tipa sobe</h2>
                        <div className="container">
                            <div className="input-field">
                                <label htmlFor="nazivTipaDodaj">Naziv:</label>
                                <input type="text" id='nazivTipaDodaj'  onChange = {this.handleChange}/>
                            </div>
                            <div className="input-field">
                                <label htmlFor="kapacitetDodaj">Kapacitet:</label>
                                <input type="number" id='kapacitetDodaj'  onChange = {this.handleChange}/>
                            </div>
                            <div className="input-field">
                                <label htmlFor="hotelTipaDodaj">Hotel:</label>
                                <input type="number" id='hotelTipaDodaj'  onChange = {this.handleChange}/>
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
        createTip: (tip) => dispatch(createTip(tip))
    }
}

export default connect(null, mapDispatchToProps)(CreateTipSobe);