import React, { Component } from 'react'
import axios from 'axios'


class Filijale extends Component {

    state = {
        filijale: []
    }

    componentDidMount() {
        const id = this.props.match.params.serviceId;//id servisa
        axios.get('http://localhost:8090/api/rentacar/branchOffice/getAllByRentService/' + id)//tvoj url za pronalazak vozila po id servisa
            .then(res => {
                console.log(res);
                this.setState({
                    filijale: res.data
                })
            })
    }

    render() {
        const { filijale } = this.state;
        var imeServisa = "";
        const filijaleList = filijale.length ? (filijale.map(filijala => {
            imeServisa = filijala.rentServiceDTO.name;
            return (
                <div className="post card grey lighten-2">
                    <div className="card-content container">
                        <span className="card-title center"><strong>{filijala.name}</strong></span>
                        <div className="left-align">
                            <p>Grad: {filijala.city}</p>
                            <p>Adresa: {filijala.adress}</p>
                        </div>
                    </div>
                </div>
            )
        })) : (<div className="center">Nisu pronadjena vozila.</div>)

        return (
            <div>

                <div className="container center">
                    <h2 className="red-text lighten-1 center">Filijale servisa {imeServisa}</h2>
                    {filijaleList}
                </div>
            </div>
        )
    }
}

export default Filijale;