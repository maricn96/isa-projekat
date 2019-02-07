import React from "react";
import { Link } from "react-router-dom";
import SingedUserLinks from "./navbarLinks/singedUserLinks";
import SignedOutOutLinks from './navbarLinks/SignedOutUserLinks';
import SignedInLinks from "./navbarLinks/singedUserLinks";
import SingedAdminLinks from "./navbarLinks/SingedAdminLinks";

const Navbar = ({ user }) => {
  return (
    <nav className="nav-wrapper indigo darken-2">
      <div className="container">
        <Link to="/" className="brand-logo left">
          ISA
        </Link>
        {
          user ?
            ''
            :
            <SignedOutOutLinks></SignedOutOutLinks>
        }

        {
          user && user.role.role == 'USER' ?

            <SignedInLinks />
            :
            ''

        }

        {
          user && user.role.role != "USER" ?
            <SingedAdminLinks />
            :
            ''
        }
      </div>
    </nav>
  );
};



export default Navbar;
/*
<SignedOutOutLinks />

<SingedAdminLinks />
*/