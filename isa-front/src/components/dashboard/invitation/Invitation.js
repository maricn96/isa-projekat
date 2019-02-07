import React from "react"
import ReservationInfo from "../reservation/ReservationInfo";


//<ReservationInfo></ReservationInfo>
const Invitation = () => {

    return (

        <div className="card">
            <div className="card-content">
                <span className="card-title indigo-text lighten-1"><strong>Veljko je kreirao rezervaciju za Vas:</strong></span>
                <div className="container left">
                    <div className="card">
                        <div className="card-content">
                            <h3>Ovde bi trebala ici rezervacija</h3>
                        </div>
                    </div>
                </div>
                <div className="row">
                    <div className="col s1">
                        <button className="btn  indigo lighten-1">Prihvati</button>
                    </div>
                    <div className="col s1">
                        <button className="btn red lighten-1 z-depth-0">Odbaci</button>
                    </div>
                </div>
            </div>

        </div>

    );

};

export default Invitation;