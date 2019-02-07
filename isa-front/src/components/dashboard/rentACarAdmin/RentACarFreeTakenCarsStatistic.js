import React, { Component } from "react"
import { NavLink } from "react-router-dom"
import { VictoryBar, VictoryChart } from "victory";
import { connect } from "react-redux"
import { getNumberOfTakenCars, getNumberOfFreeCars } from "../../../store/actions/RentACarActions"


class RentACarFreeTakenCarsStatistic extends Component {

    state = {
        sakriPretragu: true,
        datumPocetka: undefined,
        datumKraja: undefined,
        vremePocetka: undefined,
        vremeKraja: undefined,
        dateFrom: '',
        dateTo: '',


    }

    toggleForm = () => {
        this.setState({
            sakriPretragu: false
        })
    }

    hideForm = () => {
        this.setState({
            sakriPretragu: true
        })
    }

    handleDateTimeChange = (e) => {

        if (e.target.id == "datumPocetka" || e.target.id == "vremePocetka") {

            var datumPocetka = undefined;
            var vremePocetka = undefined;
            if (e.target.id == "datumPocetka") {
                datumPocetka = e.target.value;
                if (this.state.vremePocetka)
                    vremePocetka = this.state.vremePocetka;
            }
            else {
                vremePocetka = e.target.value;
                if (this.state.datumPocetka)
                    datumPocetka = this.state.datumPocetka;

            }

            var formatDatumIVremeZaApi = datumPocetka + 'T' + vremePocetka + ':00';

            this.setState({

                datumPocetka: datumPocetka,
                vremePocetka: vremePocetka,
                dateFrom: formatDatumIVremeZaApi

            })

        }
        else if (e.target.id == "datumKraja" || e.target.id == "vremeKraja") {

            var datumKraja = undefined;
            var vremeKraja = undefined;
            if (e.target.id == "datumKraja") {
                datumKraja = e.target.value;
                if (this.state.vremeKraja)
                    vremeKraja = this.state.vremeKraja;
            }
            else {
                vremeKraja = e.target.value;
                if (this.state.datumKraja)
                    datumKraja = this.state.datumKraja;

            }

            var formatDatumIVremeZaApi = datumKraja + 'T' + vremeKraja + ":00";

            this.setState({

                datumKraja: datumKraja,
                vremeKraja: vremeKraja,
                dateTo: formatDatumIVremeZaApi

            })

        }


    }

    handleSubmit = (e) => {
        e.preventDefault();

        if (this.state.vremePocetka && this.state.vremeKraja && this.state.datumPocetka && this.state.datumKraja && this.props.rentServiceId) {

            this.props.getNumberOfFreeCars(this.props.rentServiceId, this.state.dateFrom, this.state.dateTo)
            this.props.getNumberOfTakenCars(this.props.rentServiceId, this.state.dateFrom, this.state.dateTo)

        }



    }



    render() {

        return (
            <div className="container">
                {
                    this.state.sakriPretragu ?
                        <div className="center">
                            <NavLink to="#" onClick={this.toggleForm} >Pogledaj broj slobodnih i zauzetih vozila za odredjeni period</NavLink>
                        </div>
                        :
                        <div>
                            <div className="center">
                                <NavLink to="#" onClick={this.hideForm} >Sakrij formu</NavLink>
                            </div>
                            <div className="card">
                                <div className="card-content">
                                    <h3 className="card-title red-text lighten-1 center"><strong>Prikaz broja slobodnih i zauzetih vozila za period</strong></h3>

                                    <div className="container">
                                        <form className="white" onSubmit={this.handleSubmit} >

                                            <div>
                                                <label htmlFor="datumPocetka" className="active">Datum od</label>
                                                <input type="date" id='datumPocetka' onChange={this.handleDateTimeChange} value={this.state.datumPocetka} />
                                            </div>

                                            <div>
                                                <label htmlFor="vremePocetka" className="active">Vreme od</label>
                                                <input type="time" id='vremePocetka' onChange={this.handleDateTimeChange} value={this.state.vremePocetka} />
                                            </div>


                                            <div>
                                                <label htmlFor="datumKraja" className="active">Datum do</label>
                                                <input type="date" id='datumKraja' onChange={this.handleDateTimeChange} value={this.state.datumKraja} />
                                            </div>

                                            <div>
                                                <label htmlFor="vremeKraja" className="active">Vreme do</label>
                                                <input type="time" id='vremeKraja' onChange={this.handleDateTimeChange} value={this.state.vremeKraja} />
                                            </div>

                                            <div className="input-field">
                                                <button className="btn purple darken-3 z-depth-0">Prikazi</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>



                            <VictoryChart>
                                <VictoryBar
                                    data={[
                                        { vozila: 'slobodna vozila', broj: this.props.freeCars },
                                        { vozila: 'zauzeta vozila', broj: this.props.takenCars },
                                    ]}
                                    x="vozila"
                                    y="broj"
                                />
                            </VictoryChart>

                            }


                        </div>

                }



            </div>
        );

    }

}

const mapStateToProps = (state) => {
    return {
        takenCars: state.rentACar.takenCars,
        freeCars: state.rentACar.freeCars
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        getNumberOfFreeCars: (rentId, dateFrom, dateTo) => dispatch(getNumberOfFreeCars(rentId, dateFrom, dateTo)),
        getNumberOfTakenCars: (rentId, dateFrom, dateTo) => dispatch(getNumberOfTakenCars(rentId, dateFrom, dateTo))
    }
}



export default connect(mapStateToProps, mapDispatchToProps)(RentACarFreeTakenCarsStatistic);
