import React, { Component } from "react";
import { NavLink } from "react-router-dom";
import "../layout.css"

class UserLoggedTabs extends Component {
  render() {
    return (

      <div className="row container">

        <ul className="tabs userLoggedTabsDiv">
          <li className="tab col s4">
            <NavLink to="/letovi" className="white-text text-darken-4">Letovi</NavLink>
          </li>
          <li className="tab col s4">
            <NavLink to="/smestaj" className="white-text text-darken-4">Smestaj</NavLink>
          </li>
          <li className="tab col s4">
            <NavLink to="/vozila" className="white-text text-darken-4">Vozila</NavLink>
          </li>
        </ul>

      </div>

    );
  }
};

export default UserLoggedTabs;
