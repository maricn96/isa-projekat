import React, { Component } from "react"



class SendFriendRequest extends Component {

    state = {

    }

    render() {
        return (
            <div className="card ">
                <div className="card-content">
                    <span className="card-title indigo-text lighten-1 center"><strong>Posaljite zahtev za prijateljstvo</strong></span>
                    <div className="container">
                        <form className="white" >
                            <div className="input-field">
                                <label htmlFor="emailKorisnika">Email korisnika</label>
                                <input type="email" id='emailKorisnika' />
                            </div>
                            <div className="input-field">
                                <button className="btn blue lighten-1 z-depth-0">Posalji</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        )
    }
};

export default SendFriendRequest;

