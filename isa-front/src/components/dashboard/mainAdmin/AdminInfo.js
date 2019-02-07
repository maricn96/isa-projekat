import React from "react"
import DeleteFromShoppingCart from "../reservation/DeleteFromShoppingCart"


const AdminInfo = () => {

    return (
        <div className="card">

            <div className="card-content">

                <span className="card-title red-text lighten-1"><strong>Email admina</strong></span>
                <div className="container">
                    <div className="row ">
                        <div className="col s4">
                            <p><strong>Tip administratora</strong></p>
                            <p>RentACarAdmin</p>
                        </div>
                        <div className="col s4">
                            <p><strong>Id objekta</strong></p>
                            <p>202</p>
                        </div>
                        <div className="col s4">
                            <DeleteFromShoppingCart></DeleteFromShoppingCart>
                        </div>

                    </div>
                </div>



            </div>

        </div>
    );

};

export default AdminInfo;