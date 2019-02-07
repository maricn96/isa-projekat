import React from "react";
import { NavLink } from "react-router-dom"
import "./friends.css"


const Friend = ({ friend }) => {

    return (
        <div className="center container">
            <div className="row container center">
                <div className="col s6">
                    <p >{friend.name} {friend.surname}</p>
                </div>
                <div className="col s2">
                    <NavLink to="/#" className=" red-text " >Obrisi prijatelja</NavLink>
                </div>

            </div>
        </div>
    );

};

export default Friend;