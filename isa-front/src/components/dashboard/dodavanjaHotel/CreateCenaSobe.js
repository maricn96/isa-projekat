import React, { Component } from 'react'
import UserLoggedTabs from '../../layout/tabs/UserLoggedTabs';
import axios from "axios"
import { createCena } from '../../../store/actions/CenaActions'
import { connect } from 'react-redux'

class CreateCenaSobe extends Component {
    state = {
        cenaSobeDodaj:"",
        vaziOdDodaj:"",
        vaziDoDodaj:"",
        sobaA:"",
        sobaID:""
    }

    componentDidMount() {
        this.setState({
            sobaID: this.props.match.params.sobaId
        })
        const id = this.props.match.params.sobaId
        axios.get('http://localhost:8092/api/hotel/hoteskaSoba/' + id)
            .then(res => {
                console.log("AYYY", res.data);
                this.setState({
                    sobaA: res.data
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
        console.log("EOOO", this.state);
        this.props.createCena(this.state)
    }

    render() {
        return (
            <div>
                <UserLoggedTabs></UserLoggedTabs>
                <div className="container">
                    <form className="white" onSubmit={this.handleSubmit} >
                        <h2 className="red-text lighten-1 center">Dodavanje nove cene sobe</h2>
                        <div className="container">
                            <div className="input-field">
                                <label htmlFor="cenaSobeDodaj">Cena:</label>
                                <input type="number" id='cenaSobeDodaj' onChange={this.handleChange}/>
                            </div>
                            <div>
                                <label htmlFor="vaziOdDodaj" className="active">Važi od:</label>
                                <input type="date" id='vaziOdDodaj' className= "datepicker" onChange={this.handleChange}/>
                            </div>
                            <div>
                                <label htmlFor="vaziDoDodaj" className="active">Važi do:</label>
                                <input type="date" id='vaziDoDodaj' onChange={this.handleChange}/>
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
        createCena: (cena) => dispatch(createCena(cena))
    }
}

export default connect(null, mapDispatchToProps)(CreateCenaSobe);