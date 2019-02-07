import React, { Component } from 'react'
import UserLoggedTabs from '../../layout/tabs/UserLoggedTabs';
import { connect } from 'react-redux'
import { createCenovnik } from '../../../store/actions/CenovnikActions'

class CreateCenovnikUsluga extends Component {
    state = {
        nazivCenovnikaDodaj:"",
        cenaCenovnikaDodaj:"",
        hotelCenovnikaDodaj:""
    }

    handleSubmit = (e) => {
        e.preventDefault();
        
    }

    handleChange = (e) => {
        this.setState({
            [e.target.id]: e.target.value
        })
    }

    handleSubmit = (e) => {
        e.preventDefault();
        console.log("EOOO", this.state);
        this.props.createCenovnik(this.state)
    }

    render() {
        return (
            <div>
                <UserLoggedTabs></UserLoggedTabs>
                <div className="container">
                    <form className="white" onSubmit={this.handleSubmit} >
                        <h2 className="red-text lighten-1 center">Dodavanje nove usluge u cenovnik</h2>
                        <div className="container">
                        <div className="input-field">
                                <label htmlFor="nazivCenovnikaDodaj">Naziv:</label>
                                <input type="text" id='nazivCenovnikaDodaj' onChange={this.handleChange}/>
                            </div> 
                            <div className="input-field">
                                <label htmlFor="cenaCenovnikaDodaj">Cena:</label>
                                <input type="number" id='cenaCenovnikaDodaj' onChange={this.handleChange}/>
                            </div>     
                            <div className="input-field">
                                <label htmlFor="hotelCenovnikaDodaj">Hotel:</label>
                                <input type="number" id='hotelCenovnikaDodaj' onChange={this.handleChange}/>
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
        createCenovnik: (cenovnik) => dispatch(createCenovnik(cenovnik))
    }
}

export default connect(null, mapDispatchToProps)(CreateCenovnikUsluga);