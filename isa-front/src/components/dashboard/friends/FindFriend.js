import React, { Component } from "react";


class FindFriend extends Component {

    state = {

    }

    render() {

        return (

            <div className="card">
                <div className="card-content">
                    <span className="card-title indigo-text lighten-1 center"><strong>Pronadji prijatelja</strong></span>

                    <div className="container">
                        <form className="white" >

                            <div className="input-field">
                                <label htmlFor="imePrijatelja">Ime</label>
                                <input type="text" id='imePrijatelja' />
                            </div>
                            <div className="input-field">
                                <label htmlFor="prezimePrijatelja">Prezime</label>
                                <input type="text" id='prezimePrijatelja' />
                            </div>

                            <div className="input-field">
                                <button className="btn blue lighten-1 z-depth-0 center">Pretrazi</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        );


    }

}

export default FindFriend;