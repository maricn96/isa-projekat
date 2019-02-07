import React, { Component } from 'react'
import axios from 'axios'


class LoggedUserUslugeList extends Component {

    state = {
        usluge: []
    }

    componentDidMount() {
        const id = this.props.match.params.hotelId;
        axios.get('http://localhost:8092/api/hotel/dodatneUsluge/uslugeHotela/'+id)
            .then(res => {
                console.log(res);
                this.setState({
                    usluge: res.data
                })
            })
    }

    render() {
        const { usluge } = this.state;
        var imeHotela = "";
        const uslugeList = usluge.length ? (usluge.map(usluga => {
            imeHotela = usluga.hotel_dodatneUsluge.name;
            return (
                <div className="post card grey lighten-2">
                    <div className="card-content container">
                        <span className="card-title center">{usluga.additionalServiceName}</span>
                        <div className="left-align">
                            <p>Cena: {usluga.additionalServicePrice}</p>
                            <p>Popust: {usluga.popust}</p>
                            <button>Dodaj</button>
                        </div>
                    </div>
                </div>
            )
        })) : (<div className="center">Nema dodatnih usluga.</div>)

        return (
            <div>
                <div className="container center">
                    <h2 className="red-text lighten-1 center">Dodatne usluge hotela {imeHotela}</h2>
                    {uslugeList}
                    <button onClick = {this.onClick}>Rezervisi</button>
                </div>
            </div>
        )
    }
}

export default LoggedUserUslugeList;