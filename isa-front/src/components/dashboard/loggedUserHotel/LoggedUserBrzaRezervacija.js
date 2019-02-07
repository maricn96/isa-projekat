import React, { Component } from 'react'
import axios from 'axios'
import Axios from 'axios';


class LoggedUserBrzaRezervacija extends Component {

    state = {
        sobe: [],
        ocene:[]
    }

    componentDidMount() {
        const id = this.props.match.params.hotelId;
        axios.get('http://localhost:8092/api/hotel/hotelskaSoba/discount/'+ id + '/2018-01-29/2018-02-06')
            .then(res => {
                console.log(res);
                this.setState({
                    sobe: res.data
                })
            })

            axios.get('http://localhost:8095/api/hotel/hotelskaSobaRating/all')
                .then(res => {
                    this.setState({
                        ocene: res.data
                    })
                })
    }
     

    handleClick = (sobaId) => (e) => {
        this.props.history.push('/rezervacijaLogged/'+this.props.match.params.hotelId + '/' + sobaId)
    }

    render() {
        const sobe = this.state.sobe;
        var imeHotela = "";
        var suma = 0;
        var count = 0;
        const sobeList = sobe.length ? (sobe.map(soba => {
            imeHotela = soba.hotel_hotelskeSobe.name;

            suma = 0;
            count = 0;
            this.state.ocene.map(ocena =>{
                if(ocena.hotelskaSobaId == soba.id){
                    suma += ocena.rating;
                    count++;
                }
            })

            var prosecnaOcena = suma / count;

            return (
                <div className="post card grey lighten-2">
                    <div className="card-content container">
                        <span className="card-title center">{soba.id}</span>
                        <div className="left-align">
                            <p>Sprat: {soba.floor}</p>
                            <p>Cena: {soba.originalnaCena}</p>
                            <p>Tip sobe: {soba.tipSobe_hotelskeSobe.roomType}</p>
                            <p>Procecna ocena: {prosecnaOcena}/5</p>
                            <button className = "right" onClick = {this.handleClick(soba.id)}>Rezervisi</button>
                        </div>
                    </div>
                </div>
            )
        })) : (<div className="center">Nije pronadjena nijedana soba na popustu.</div>)

        return (
            <div>
                <div className="container center">
                    <h2 className="red-text lighten-1 center">Lista soba hotela {imeHotela}</h2>                   
                    {sobeList}
                </div>
            </div>
        )
    }
}

export default LoggedUserBrzaRezervacija;