import React, { Component } from 'react'
import axios from 'axios'
import Axios from 'axios';


class ProsecneOceneAdmin extends Component {

    state = {
        sobe: [],
        ocene:[],
        hotel:"",
        oh:""
    }

    componentDidMount() {
        const id = this.props.match.params.hotelId;

        axios.get('http://localhost:8092/api/hotel/hotelskaSoba/all/'+id)
            .then(res => {
                console.log(res);
                this.setState({
                    sobe: res.data
                })
            })

            axios.get('http://localhost:8092/api/hotel/hotel/'+id)
            .then(res => {
                console.log(res);
                this.setState({
                    hotel: res.data
                })
                axios.get('http://localhost:8095/api/hotel/hotelRating/average/'+id)
                    .then(res => {
                    console.log(res);
                    this.setState({
                        oh: res.data
                    })
                })
            })

        axios.get('http://localhost:8095/api/hotel/hotelskaSobaRating/all')
                .then(res => {
                    this.setState({
                        ocene: res.data
                    })
                })
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
                    <div className="card-content container">
                        <span className="card-title center">{soba.id}</span>
                        <div>
                            <p>Procecna ocena: {prosecnaOcena}/5</p>
                        </div>
                    </div>
            )
        })) : (<div className="center">Nije pronadjena nijedana soba.</div>)

        return (
            <div>
                <div className="container center">
                    <h2 className="red-text lighten-1 center">Prosecne ocene</h2>
                    <div className="post card grey lighten-2">
                        <div className="card-content container">
                            <span className="card-title center">{this.state.hotel.name}</span>
                            <div>
                                <p>Procecna ocena: {this.state.oh}/5</p>
                            </div>
                        </div>
                        {sobeList}
                    </div>
                </div>
            </div>
        )
    }
}

export default ProsecneOceneAdmin;