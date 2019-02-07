import React, {Component} from "react"
import {
    VictoryBar,
    VictoryChart,
    VictoryLine,
    VictoryPie,
  } from "victory";
import Axios from "axios";

class PrihodiGrafAdmin extends Component{

    state = {
        datumOd:"",
        mesec:"",
        nedelja:"",
        godina:""
    }

    handleChange = (e) => {
        this.setState({
            [e.target.id]: e.target.value
        })
        console.log(this.state);
    }

    handleSubmit = (e) => {
        e.preventDefault();
        Axios.get('http://localhost:8092/api/hotel/prihodi/1/'+ this.state.datumOd +'/mesec').then(res => {
            console.log(res);
            this.setState({
                mesec: res.data
            })
        })
        Axios.get('http://localhost:8092/api/hotel/prihodi/1/' + this.state.datumOd + '/nedelja').then(res => {
            console.log(res);
            this.setState({
                nedelja: res.data
            })
        })
        Axios.get('http://localhost:8092/api/hotel/prihodi/1/' + this.state.datumOd +'/godina').then(res => {
            console.log(res);
            this.setState({
                godina: res.data
            })
        })
    }

    render() {
        return(
            <div>
                <div className = "container">
                    <form onSubmit={this.handleSubmit}>
                        <div>
                            <label htmlFor="datumOd">Datum od:</label>
                            <input type="date" id='datumOd' onChange = {this.handleChange} /*value={this.state.nazivCenovnikaIzmeni}*//>
                        </div>
                        <button>Pretrazi</button>
                    </form>
                    <VictoryChart>
                        <VictoryBar
                            data={[
                            { vreme: 'nedeljna', prihod: this.state.nedelja },
                            { vreme: 'mesecna', prihod: this.state.mesec },
                            { vreme: 'godisnja', prihod: this.state.godina },
                            ]}
                            x="vreme"
                            y="prihod"
                        />
                    </VictoryChart>
                </div>
            </div>
        )
    }
}

export default PrihodiGrafAdmin;