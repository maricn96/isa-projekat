import React, { Component } from "react"
import { connect } from "react-redux"
import { createBonusPointsDiscount, editBonusPointsDiscount, deleteBonusPointsDiscount } from "../../../store/actions/PurchasesActions"
class ChangeBonusPointsDiscount extends Component {

    state = {
        id: -1,
        points: '',
        discount: ''
    }

    componentDidMount() {

        if (this.props.discount) {

            this.setState({

                id: this.props.discount.id,
                points: this.props.discount.points,
                discount: this.props.discount.discount

            })

        }

    }

    handleChange = (e) => {
        this.setState({
            [e.target.id]: e.target.value
        })
    }


    handleSubmit = (e) => {
        e.preventDefault();
        if (this.props.izmena) {

            this.props.editBonusPointsDiscount(this.props.discount.id, this.state);
        }
        else {
            this.props.createBonusPointsDiscount(this.state);
            this.setState({
                id: -1,
                points: '',
                discount: ''
            })
        }
    }

    deleteDiscount = (id) => {

        this.props.deleteBonusPointsDiscount(id);

    }

    render() {
        return (
            <div className="card indigo lighten-1">
                <div className="card-content">
                    <div className="card">
                        <div className="card-content">
                            <span className="card-title indigo-text lighten-1 left"><strong>{this.props.discount ? this.props.discount.id : ''}</strong></span>

                            {this.props.brisanje ?
                                <span className="card-title right" onClick={() => this.deleteDiscount(this.props.discount.id)}><a class="btn-floating btn-small waves-effect waves-light red"><i>x</i></a></span>
                                :
                                ''
                            }

                            <div className="container">
                                <form className="white" onSubmit={this.handleSubmit} >
                                    <div className="input-field">
                                        <label className="active" htmlFor="points">Broj bonus poena</label>
                                        <input type="number" onChange={this.handleChange} id='points' defaultValue={this.props.discount ? this.props.discount.points : ''} />
                                    </div>
                                    <div className="input-field">
                                        <label className="active" htmlFor="discount">Popust</label>
                                        <input type="number" onChange={this.handleChange} id='discount' defaultValue={this.props.discount ? this.props.discount.discount : ''} />
                                    </div>
                                    {this.props.izmena ?
                                        <div className="input-field">
                                            <button className="btn blue lighten-1 z-depth-0">Azuriraj</button>
                                        </div>
                                        :
                                        <div className="input-field">
                                            <button className="btn green darken-3 z-depth-0">Dodaj</button>
                                        </div>
                                    }
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
};


const mapDispatchToProps = (dispatch) => {
    return {
        createBonusPointsDiscount: (bonusPointsDiscount) => dispatch(createBonusPointsDiscount(bonusPointsDiscount)),
        editBonusPointsDiscount: (id, bonusPointsDiscount) => dispatch(editBonusPointsDiscount(id, bonusPointsDiscount)),
        deleteBonusPointsDiscount: (id) => dispatch(deleteBonusPointsDiscount(id))
    }
}

export default connect(null, mapDispatchToProps)(ChangeBonusPointsDiscount)
