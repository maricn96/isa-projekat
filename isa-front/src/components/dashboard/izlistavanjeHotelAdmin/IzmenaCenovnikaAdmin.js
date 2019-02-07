import React, { Component } from 'react'
import UserLoggedTabs from '../../layout/tabs/UserLoggedTabs';
import { connect } from 'react-redux'
import { editCenovnik } from '../../../store/actions/CenovnikActions';
import axios from "axios"

class IzmenaCenovnikaAdmin extends Component {
    state = {
        nazivCenovnikaIzmeni:"",
        cenaCenovnikaIzmeni:"",
        hotel:""
    }

    componentDidMount () {
        const id = this.props.match.params.cenovnikId;
        //console.log("ID: "+id);
        axios.get('http://localhost:8092/api/hotel/cenovnik/'+id)
            .then(res => {
                console.log(res);
                this.setState({
                    idIzmeni: res.data.id,
                    nazivCenovnikaIzmeni: res.data.imeUsluge,
                    cenaCenovnikaIzmeni: res.data.cenaUsluge,
                    hotel: res.data.hotel_cenovnikUsluga
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
        this.props.editCenovnik(this.state)
    }

    render() {
        return (
            <div>
                <UserLoggedTabs></UserLoggedTabs>
                <div className="container">
                    <form className="white" onSubmit={this.handleSubmit} >
                        <h2 className="red-text lighten-1 center">Izmena usluge cenovnika</h2>
                        <div className="container">
                            <div>
                                <label htmlFor="nazivCenovnikaIzmeni">Naziv:</label>
                                <input type="text" id='nazivCenovnikaIzmeni' onChange = {this.handleChange} value={this.state.nazivCenovnikaIzmeni}/>
                            </div>
                            <div>
                                <label htmlFor="cenaCenovnikaIzmeni">Cena:</label>
                                <input type="number" id='cenaCenovnikaIzmeni' onChange = {this.handleChange} value={this.state.cenaCenovnikaIzmeni}/>
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
        editCenovnik: (cenovnik) => dispatch(editCenovnik(cenovnik))
    }
}

export default connect(null, mapDispatchToProps)(IzmenaCenovnikaAdmin);