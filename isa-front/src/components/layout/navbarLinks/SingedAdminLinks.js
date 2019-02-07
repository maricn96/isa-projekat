import React, { Component } from "react";
import "./navBarLinks.css";
import { NavLink } from "react-router-dom";
import { connect } from "react-redux"
import { logOutUser } from "../../../store/actions/UserActions"

class SingedAdminLinks extends Component {

    logOutUser = () => {

        this.props.logOutUser();
    }

    render() {
        return (
            <div>
                <ul className="right">
                    <li className="isa_links">
                        <NavLink to="/userProfile">Profil</NavLink>
                    </li>
                    <li className="isa_links">
                        <NavLink to="/" onClick={this.logOutUser}>Izloguj se</NavLink>
                    </li>
                </ul>
            </div>
        );
    }

};

const mapDispatchToProps = (dispatch) => {
    return {
        logOutUser: () => dispatch(logOutUser()),

    }
}

export default connect(null, mapDispatchToProps)(SingedAdminLinks);