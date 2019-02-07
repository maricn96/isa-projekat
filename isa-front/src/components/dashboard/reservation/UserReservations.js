import React, { Component } from "react";
import ReservationInfo from "./ReservationInfo";
import { connect } from "react-redux"
import { getAllUserReservations } from "../../../store/actions/PurchasesActions"


class UserReservations extends Component {



    componentDidMount() {

        var user = JSON.parse(localStorage.getItem('user'))
        if (user) {
            this.props.getAllUserReservations(user.id);
        }


    }




    render() {
        return (

            <div>

                <h2 className="center red-text lighten-1">Veljko's reservations</h2>

                {

                    this.props.userReservations ?
                        this.props.userReservations.map(reservation => {

                            return (
                                <ReservationInfo reservation={reservation} />
                            );
                        })
                        :
                        ''



                }



            </div>

        );
    }

};

const mapStateToProps = (state) => {
    return {
        userReservations: state.purchases.userReservations,
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        getAllUserReservations: (id) => dispatch(getAllUserReservations(id))
    }
}



export default connect(mapStateToProps, mapDispatchToProps)(UserReservations);
