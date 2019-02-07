import React, { Component } from 'react'
import UserLoggedTabs from '../../layout/tabs/UserLoggedTabs';
import { connect } from 'react-redux'
import { editTip } from '../../../store/actions/TipActions';
import axios from "axios"

class IzmenaTipaAdmin extends Component {
    state = {
        kapacitetIzmeni:"",
        roomTypeIzmeni:"",
        idIzmeni:"",
        hotel:""
    }

    componentDidMount () {
        const id = this.props.match.params.tipId;
        //console.log("ID: "+id);
        axios.get('http://localhost:8092/api/hotel/tipSobe/'+id)
            .then(res => {
                console.log(res);
                this.setState({
                    idIzmeni: res.data.id,
                    kapacitetIzmeni: res.data.kapacitet,
                    roomTypeIzmeni: res.data.roomType,
                    hotel: res.data.hotel_tipSobe
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
        this.props.editTip(this.state)
    }

    render() {
        return (
            <div>
                <UserLoggedTabs></UserLoggedTabs>
                <div className="container">
                    <form className="white" onSubmit={this.handleSubmit} >
                        <h2 className="red-text lighten-1 center">Izmena tipa sobe</h2>
                        <div className="container">
                            <div>
                                <label htmlFor="roomTypeIzmeni">Naziv:</label>
                                <input type="text" id='roomTypeIzmeni' onChange = {this.handleChange} value={this.state.roomTypeIzmeni}/>
                            </div>
                            <div>
                                <label htmlFor="kapacitetIzmeni">Kapacitet:</label>
                                <input type="number" id='kapacitetIzmeni' onChange = {this.handleChange} value={this.state.kapacitetIzmeni}/>
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
        editTip: (tip) => dispatch(editTip(tip))
    }
}

export default connect(null, mapDispatchToProps)(IzmenaTipaAdmin);