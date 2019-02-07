import React, { Component } from 'react'
import axios from 'axios'


class LoggedUserCenovnikList extends Component {

    state = {
        cenovnici: []
    }

    componentDidMount() {
        const id = this.props.match.params.hotelId;
        axios.get('http://localhost:8092/api/hotel/cenovnik/cenovniciHotela/'+id)
            .then(res => {
                console.log(res);
                this.setState({
                    cenovnici: res.data
                })
            })
    }

    handleClick = (e) => {

        this.props.history.push('/uslugeLogged/'+this.props.match.params.hotelId)

    }

    render() {
        const { cenovnici } = this.state;
        var imeHotela = "";
        const cenovniciList = cenovnici.length ? (cenovnici.map(cenovnik => {
            imeHotela = cenovnik.hotel_cenovnikUsluga.name;
            return (
                <div className="post card grey lighten-2">
                    <div className="card-content container">
                        <span className="card-title center">{cenovnik.imeUsluge}</span>
                        <div className="left-align">
                            <p>Cena: {cenovnik.cenaUsluge}</p>
                            <button className = "right">Dodaj</button>
                        </div>
                    </div>
                </div>
            )
        })) : (<div className="center">Nije pronadjen cenovnik.</div>)

        return (
            <div>
                <div className="container center">
                    <h2 className="red-text lighten-1 center">Cenovnik hotela {imeHotela}</h2>
                    {cenovniciList}
                    <button onClick = {this.handleClick}>Rezervisi</button>
                </div>
            </div>
        )
    }
}

export default LoggedUserCenovnikList;