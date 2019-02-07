import React from "react";
import "./friends.css"


const FriendRequest = ({ friendRequest }) => {

    return (
        <div className="center container">
            <div className="row container center">
                <div className="col s6">
                    <p>{friendRequest.name} {friendRequest.surname}</p>
                </div>
                <div className="col s2">
                    <a className="waves-effect  waves-light teal btn friendsButton">Dodaj</a>
                </div>
                <div className="col s2">
                    <a className="waves-effect waves-light red btn friendsButton">Odbi</a>
                </div>

            </div>
        </div>
    );

};

export default FriendRequest;