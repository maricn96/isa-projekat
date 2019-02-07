import React from "react";
import { NavLink } from "react-router-dom";
import "./navBarLinks.css";

const SignedOutOutLinks = () => {
  return (
    <div>
      <ul className="right">
        <li className="isa_links">
          <NavLink to="/prijavaKorisnika">Prijavi se</NavLink>
        </li>
        <li className="isa_links">
          <NavLink to="/registracijaKorisnika">Registruj se</NavLink>
        </li>
      </ul>
    </div>
  );
};

export default SignedOutOutLinks;
