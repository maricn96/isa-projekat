import React, {Component} from 'react'
import UserLoggedTabs from '../../layout/tabs/UserLoggedTabs';
import { connect } from 'react-redux'
import { editHotel } from '../../../store/actions/HotelActions'
import axios from "axios"

class IzmenaHotelaAdmin extends Component {

    state = {
        imeIzmeni: '',
        adresaIzmeni: '',
        opisIzmeni: ''
    }

    componentDidMount () {
        const id = this.props.match.params.hotelId;
        //console.log("ID: "+id);
        axios.get('http://localhost:8092/api/hotel/hotel/'+id)
            .then(res => {
                console.log(res);
                this.setState({
                    idIzmeni: res.data.id,
                    imeIzmeni: res.data.name,
                    adresaIzmeni: res.data.adress,
                    opisIzmeni: res.data.promotionalDescription
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
        this.props.editHotel(this.state)
    }

    render() {
        return (
            <div>
                <UserLoggedTabs></UserLoggedTabs>
                <div className="container">
                    <form className="white" onSubmit={this.handleSubmit} >
                        <h2 className="red-text lighten-1 center">Izmena hotela</h2>
                        <div className="container">
                            <div className="input-field">
                                <label>Ime:</label>
                                <input type="text" id='imeIzmeni' onChange = {this.handleChange} value={this.state.imeIzmeni}/>
                            </div>     
                            <div>
                                <label htmlFor="adresaIzmeni">Adresa:</label>
                                <input type="text" id='adresaIzmeni'  onChange = {this.handleChange}  value={this.state.adresaIzmeni}/>
                            </div> 
                            <div>
                                <label htmlFor="opisIzmeni">Promotivni opis:</label>
                                <input type="text" id='opisIzmeni'  onChange = {this.handleChange}  value={this.state.opisIzmeni}/>
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
        editHotel: (hotel) => dispatch(editHotel(hotel))
    }
}

export default connect(null, mapDispatchToProps)(IzmenaHotelaAdmin)