import React, { Component } from 'react'
import UserLoggedTabs from '../layout/tabs/UserLoggedTabs';
import ReactDOM from 'react-dom';
import axios from 'axios';
import { NavLink } from "react-router-dom";
import "../layout/navbarLinks/navBarLinks.css";
import { connect } from "react-redux"
import { logInUser } from "../../store/actions/UserActions"
import { Redirect } from 'react-router-dom'

class PrijavaKorisnika extends Component {

    state = {
        username: undefined,
        password: undefined
    }

    constructor(props) {
        super(props)
    }

    handleChange = (e) => {
        console.log(this.state.username)
        this.setState({
            [e.target.id]: e.target.value
        })
    }

    handleSubmit = (e) => {
        e.preventDefault();
        if (this.state.username && this.state.password) {
            this.props.logInUser(this.state.username, this.state.password)
        }

    }

    render() {
        if (this.props.user) {
            return (<Redirect to='/blabla' />)

        }
        else {


            return (
                <div className="container">
                    <h4 className="center red-text lighten-1">Prijavite se</h4>
                    <div className="container">
                        <div className="card">
                            <div className="card-content">
                                <form className="white" onSubmit={this.handleSubmit}>
                                    <div className="input-field">
                                        <label htmlFor="username">Korisnicko ime:</label>
                                        <input type="text" onChange={this.handleChange} id='username' />
                                    </div>
                                    <div className="input-field">
                                        <label htmlFor="password">Lozinka:</label>
                                        <input type="password" onChange={this.handleChange} id='password' />
                                    </div>
                                    <div className="center">
                                        <input type="submit" className="btn purple darken-2 z-depth-0" value="Prijava" />
                                    </div>

                                </form>
                            </div>
                        </div>
                    </div>


                </div>
            );
        }
    }
}

const mapStateToProps = (state) => {

    return {
        user: state.user.user
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        logInUser: (email, password) => dispatch(logInUser(email, password))
    }
}



export default connect(mapStateToProps, mapDispatchToProps)(PrijavaKorisnika);
