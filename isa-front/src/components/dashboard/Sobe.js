import React, { Component } from 'react'
import UserLoggedTabs from '../layout/tabs/UserLoggedTabs';
import axios from 'axios'
import Axios from 'axios';


class Sobe extends Component {

    state = {
        sobe: [],
        cenaMin:"-1",
        cenaMax:"-1",
        ocene:[]
    }

    componentDidMount() {
        const id = this.props.match.params.hotelId;
        const datumOd = this.props.match.params.datumOd;
        const datumDo = this.props.match.params.datumDo;
        if(datumOd == "-1" || datumDo == "-1"){
        axios.get('http://localhost:8092/api/hotel/hotelskaSoba/all/'+id)
            .then(res => {
                console.log(res);
                this.setState({
                    sobe: res.data
                })
            })
        }else{
            axios.get('http://localhost:8092/api/hotel/rezervacije/'+id+'/'+ datumOd +'/' + datumDo)
            .then(res => {
                console.log(res);
                this.setState({
                    sobe: res.data
                })
            }) 
        }

        axios.get('http://localhost:8095/api/hotel/hotelskaSobaRating/all')
                .then(res => {
                    this.setState({
                        ocene: res.data
                    })
                })
    }

    handleChange = (e) => {
        if(e.target.value!=""){
            this.setState({
                [e.target.id]: e.target.value
            })
        }else{
            this.setState({
                [e.target.id]: "-1"
            })
        }
    }

    handleSubmit = (e) => {
        e.preventDefault();
        const cenaMin = this.state.cenaMin;
        const cenaMax = this.state.cenaMax;
        const datumOd = this.props.match.params.datumOd;
        const datumDo = this.props.match.params.datumDo;
        const id = this.props.match.params.hotelId;
        console.log(this.state);
        Axios.get('http://localhost:8092/api/hotel/rezervacije/'+ id +'/'+ datumOd +'/'+ datumDo +'/'+ cenaMin +'/'+ cenaMax)
        .then(res => {
            console.log(res);
            this.setState({
                sobe: res.data
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
                <div className="post card grey lighten-2">
                    <div className="card-content container">
                        <span className="card-title center">{soba.id}</span>
                        <div className="left-align">
                            <p>Sprat: {soba.floor}</p>
                            <p>Cena: {soba.originalnaCena}</p>
                            <p>Tip sobe: {soba.tipSobe_hotelskeSobe.roomType}</p>
                            <p>Procecna ocena: {prosecnaOcena}/5</p>
                        </div>
                    </div>
                </div>
            )
        })) : (<div className="center">Nije pronadjena nijedana soba.</div>)

        return (
            <div>
                <UserLoggedTabs></UserLoggedTabs>
                <div className="container center">
                    <h2 className="red-text lighten-1 center">Lista soba hotela {imeHotela}</h2>
                    <form onSubmit = {this.handleSubmit}>
                        <div className="input-field">
                            <label htmlFor="cenaMin">Cena od:</label>
                            <input type="number" id='cenaMin' onChange = {this.handleChange}/>
                        </div>
                        <div className="input-field">
                            <label htmlFor="cenaMax">Cena do:</label>
                            <input type="number" id='cenaMax' onChange = {this.handleChange}/>
                        </div>
                        <button>Pretrazi</button>
                    </form>
                    {sobeList}
                </div>
            </div>
        )
    }
}

export default Sobe;