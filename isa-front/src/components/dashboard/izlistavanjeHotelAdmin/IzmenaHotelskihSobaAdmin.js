import React, { Component } from 'react'
import UserLoggedTabs from '../../layout/tabs/UserLoggedTabs';
import { connect } from 'react-redux'
import { editSoba } from '../../../store/actions/SobeActions';
import axios from "axios"

class IzmenaHotelskihSobaAdmin extends Component {
    state = {
        spratIzmeni:"",
        originalnaCenaSobeIzmeni:"",
        tipHSIzmeni:"",
        tipoviIzmeni:"",
        hotelSIzmeni:""
    }

    componentDidMount () {
        const id = this.props.match.params.sobaId;
        //console.log("ID: "+id);
        axios.get('http://localhost:8092/api/hotel/hotelskaSoba/'+id)
            .then(res => {
                this.setState({
                    idIzmeni: res.data.id,
                    spratIzmeni: res.data.floor,
                    originalnaCenaSobeIzmeni: res.data.originalnaCena,
                    reservedIzmeni: res.data.reserved,
                    hotelSIzmeni: res.data.hotel_hotelskeSobe,
                    tipHSIzmeni: res.data.tipSobe_hotelskeSobe.id
                })
                axios.get('http://localhost:8092/api/hotel/tipSobe/all')
                    .then(res => {
                        console.log(res);
                        this.setState({
                            tipoviIzmeni: res.data
                        })
                    })
            })
    }

    handleChange = (e) => {
        this.setState({
            [e.target.id]: e.target.value
        })
    }

    handleSubmit = (e) => {
        e.preventDefault();
        console.log("EOOO", this.state);
        this.props.editSoba(this.state)
    }

    render() {
        const tipovi = this.state.tipoviIzmeni;
        const tipoviList = tipovi.length ? (tipovi.map(tip => {
            if(tip.id == this.state.tipHSIzmeni){
                return (<option value={tip.id} selected>{tip.roomType}</option>)
            }else{
                return (<option value={tip.id}>{tip.roomType}</option>)
            }
        })) : (<div className="center">Nije pronadjen nijedan tip sobe.</div>)

        return (
            <div>
                <UserLoggedTabs></UserLoggedTabs>
                <div className="container">
                    <form className="white" onSubmit={this.handleSubmit} >
                        <h2 className="red-text lighten-1 center">Izmena hotelske sobe</h2>
                        <div className="container">
                            <div>
                                <label htmlFor="spratIzmeni">Sprat:</label>
                                <input type="number" id='spratIzmeni' onChange = {this.handleChange} value={this.state.spratIzmeni}/>
                            </div>
                            <div>
                                <label htmlFor="originalnaCenaSobeIzmeni">Originalna cena:</label>
                                <input type="number" id='originalnaCenaSobeIzmeni' onChange = {this.handleChange}  value={this.state.originalnaCenaSobeIzmeni}/>
                            </div>
                            <div class="input-field col s12">
                                <select className="browser-default" id="tipHSIzmeni" onChange = {this.handleChange}>
                                    <option value="" disabled>Tip sobe:</option>
                                    {tipoviList}
                                </select>
                            </div>
                            <div className="input-field">
                                <button className="btn blue lighten-1 z-depth-0">Izmeni</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        )
    }
};

const mapDispatchToProps = (dispatch) =>{
    return{
        editSoba: (tip) => dispatch(editSoba(tip))
    }
}

export default connect(null, mapDispatchToProps)(IzmenaHotelskihSobaAdmin);