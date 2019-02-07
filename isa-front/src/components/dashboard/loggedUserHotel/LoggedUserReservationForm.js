import React, { Component } from 'react'
import { connect } from 'react-redux'
import { createRezervacija } from '../../../store/actions/HotelActions'
import axios from 'axios';

class LoggedUserReservationForm extends Component{

    state ={
        rezervisi_od:"",
        rezervisi_do:"",
        hotel_id:"",
        hotel:"",
        soba:"",
        soba_id:""
    }

    componentDidMount () {
        this.setState({
            hotel_id: this.props.match.params.hotelId,
            soba_id: this.props.match.params.sobeId
        })
        axios.get('http://localhost:8092/api/hotel/hotel/' + this.props.match.params.hotelId)
            .then(res => {
                console.log(res);
                this.setState({
                    hotel: res.data
                })
            })

            axios.get('http://localhost:8092/api/hotel/hotelskaSoba/' + this.props.match.params.sobaId)
            .then(res => {
                console.log(res);
                this.setState({
                    soba: res.data
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
        this.props.createRezervacija(this.state);
        //this.props.history.push('/cenovniciLogged/'+this.props.match.params.hotelId)
    }

    render() {
        return (
            <div>
            <div className="container">
                    <form className="white" onSubmit={this.handleSubmit} >
                        <h2 className="red-text lighten-1 center">Rezervacija sobe</h2>
                        <div className="container">
                            <div>
                                <label htmlFor="rezervisi_od" className="active">Rezerviši od</label>
                                <input type="date" id='rezervisi_od' className="datepicker" onChange = {this.handleChange}/>
                            </div>
                            <div>
                                <label htmlFor="rezervisi_do" className="active">Rezerviši do</label>
                                <input type="date" id='rezervisi_do' className="datepicker" onChange = {this.handleChange}/>
                            </div>
                            <div className="input-field">
                                <button className="btn blue lighten-1 z-depth-0">Rezerviši</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        )
    }

};


const mapDispatchToProps = (dispatch) => {
    return {
        createRezervacija: (rezervacija) => dispatch(createRezervacija(rezervacija)),
    }
}

export default connect(null, mapDispatchToProps)(LoggedUserReservationForm)