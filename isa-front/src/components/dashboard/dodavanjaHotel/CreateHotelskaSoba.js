import React, { Component } from 'react'
import UserLoggedTabs from '../../layout/tabs/UserLoggedTabs';
import axios from "axios"
import { connect } from 'react-redux'
import { createSoba } from '../../../store/actions/SobeActions';


class CreateHotelskaSoba extends Component {
    state = {
        spratDodaj:"",
        originalnaCenaSobeDodaj:"",
        tipHSDodaj:"",
        tipovi:"",
        hotelS:""

    }

    componentDidMount() {
        axios.get('http://localhost:8092/api/hotel/tipSobe/all')
            .then(res => {
                console.log(res);
                this.setState({
                    tipovi: res.data
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
        this.props.createSoba(this.state)
    }

    render() {
        const tipovi = this.state.tipovi;
        const tipoviList = tipovi.length ? (tipovi.map(tip => {
            return (<option value={tip.id}>{tip.roomType}</option>)
        })) : (<div className="center">Nije pronadjen nijedan tip sobe.</div>)

        return (
            <div>
                <UserLoggedTabs></UserLoggedTabs>
                <div className="container">
                    <form className="white" onSubmit={this.handleSubmit} >
                        <h2 className="red-text lighten-1 center">Dodavanje nove hotelske sobe</h2>
                        <div className="container">
                            <div className="input-field">
                                <label htmlFor="spratDodaj">Sprat:</label>
                                <input type="number" id='spratDodaj' onChange = {this.handleChange}/>
                            </div>
                            <div className="input-field">
                                <label htmlFor="originalnaCenaSobeDodaj">Originalna cena:</label>
                                <input type="number" id='originalnaCenaSobeDodaj' onChange = {this.handleChange}/>
                            </div>
                            <div class="input-field col s12">
                                <select className="browser-default" id="tipHSDodaj" onChange = {this.handleChange}>
                                    <option value="" disabled selected>Tip sobe:</option>
                                    {tipoviList}
                                </select>
                            </div>
                            <div className="input-field">
                                <label htmlFor="hotelS">Hotel:</label>
                                <input type="number" id='hotelS' onChange = {this.handleChange}/>
                            </div>
                            <div className="input-field">
                                <button className="btn blue lighten-1 z-depth-0">Dodaj</button>
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
        createSoba: (soba) => dispatch(createSoba(soba))
    }
}

export default connect(null, mapDispatchToProps)(CreateHotelskaSoba);