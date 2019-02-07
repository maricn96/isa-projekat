import React, { Component } from 'react'
import "./izlistavanje.css"
import axios from 'axios'
import { connect } from 'react-redux'
import { deleteCenovnik } from '../../../store/actions/CenovnikActions';

class CenovnikIzlistavanjeAdmin extends Component {

    state = {
        cenovnici: []
    }

    componentDidMount() {
        axios.get('http://localhost:8092/api/hotel/cenovnik/all')
            .then(res => {
                console.log(res);
                this.setState({
                    cenovnici: res.data
                })
            })
    }

    handleDeleteClick = (cenovnikId) => {
        this.props.deleteCenovnik(cenovnikId)
    }

    handleIzmeniClick = (cenovnikId) => {
        this.props.history.push('/izmenaCenovnikaAdmin/'+cenovnikId)
    }

    render() {
        const { cenovnici } = this.state;
        const cenovniciList = cenovnici.length ? (cenovnici.map(cenovnik => {
            return (
                <div className="post card grey lighten-2">
                    <div className="card-content container">
                        <span className="card-title center"><strong>{cenovnik.imeUsluge}</strong></span>
                        <div className="left-align">
                            <p>Cena: {cenovnik.cenaUsluge}</p>
                            <p>Hotel: {cenovnik.hotel_cenovnikUsluga.name} {cenovnik.hotel_cenovnikUsluga.adress}</p>
                            <button className="dugmici1 btn-floating btn-large waves-effect waves-light red right"  onClick = {() => this.handleDeleteClick(cenovnik.id)}><i>x</i></button>
                            <button className="dugmici2 btn-floating btn-large waves-effect waves-light green right" onClick = {() => this.handleIzmeniClick(cenovnik.id)}>Izmeni</button>
                        </div>
                    </div>
                </div>
            )
        })) : (<div className="center">Nije pronadjen cenovnik.</div>)

        return (
            <div>
                <div className="container center">
                    <h2 className="red-text lighten-1 center">Cenovnik usluga</h2>
                    {cenovniciList}
                </div>
            </div>
        )
    }
}

const mapDispatchToProps = (dispatch) =>{
    return{
        deleteCenovnik: (cenovnik) => dispatch(deleteCenovnik(cenovnik))
    }
}

export default connect(null, mapDispatchToProps)(CenovnikIzlistavanjeAdmin);