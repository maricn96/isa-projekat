import React, { Component } from "react";
import { NavLink } from "react-router-dom";
import "./navBarLinks.css";
import { connect } from "react-redux"
import { logOutUser } from "../../../store/actions/UserActions"

class SignedInLinks extends Component {

  logOutUser = () => {

    this.props.logOutUser();
  }

  render() {
    return (
      <div>
        <ul className="right">
          <li className="isa_links">
            <NavLink to="/korpa">Korpa</NavLink>
          </li>
          <li className="isa_links">
            <NavLink to="/invitations">Pozivnice</NavLink>
          </li>
          <li className="isa_links">
            <NavLink to="/userReservations">Rezervacije</NavLink>
          </li>
          <li className="isa_links">
            <NavLink to="/friends"> Prijatelji</NavLink>
          </li>
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



export default connect(null, mapDispatchToProps)(SignedInLinks);

