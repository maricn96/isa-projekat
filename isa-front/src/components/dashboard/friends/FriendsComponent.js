import React, { Component } from "react";
import { NavLink } from "react-router-dom"
import FriendRequest from "./FriendRequest";
import Friend from "./Friend";
import "./friends.css"
import SendFriendRequest from "./SendFriendRequest";
import FindFriend from "./FindFriend";
import { connect } from "react-redux";
import { getUserFriendRequests, getUserFriends, loadUserAfterRefresh } from "../../../store/actions/UserActions"


class FriendsComponent extends Component {

    state = {

    }

    componentDidMount() {
        var user = JSON.parse(localStorage.getItem('user'))
        if (user) {
            this.props.loadUserAfterRefresh(user);
            this.props.getUserFriendRequests(user.id);
            this.props.getUserFriends(user.id);
        }


    }

    sortirajNizovePo = (e) => {

    }

    render() {
        return (

            <div>
                <h2 className="center red-text lighten-1">Veljko's friends</h2>

                <div className="center ">
                    <NavLink to="/dodajPrijatelja">Dodaj novog prijatelja</NavLink>
                    <br />
                    <NavLink to="/#">Pretrazi prijatelje</NavLink>
                </div>
                <FindFriend />
                <SendFriendRequest />
                <br />
                <br />

                {this.props.friendRequests.map(friendRequest => {
                    return (
                        <FriendRequest friendRequest={friendRequest} />
                    );
                })}

                <div>
                    <div className="card">
                        <div className="card-content">
                            <div className="container">
                                <form className="white">
                                    <div className="input-field">
                                        <label className="active" htmlFor="naziv">Sortiraj po</label>
                                        <select onChange={this.sortirajNizovePo} className="browser-default">
                                            <option value='naziv'>Imenu</option>
                                            <option value='grad'>Prezimenu</option>
                                        </select>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

                {this.props.friends.map(friend => {
                    return (
                        <Friend friend={friend} />
                    );
                })}
            </div>

        );
    }
};



const mapStateToProps = (state) => {
    return {
        friendRequests: state.user.friendRequests,
        friends: state.user.friends
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        getUserFriendRequests: (id) => dispatch(getUserFriendRequests(id)),
        getUserFriends: (id) => dispatch(getUserFriends(id)),
        loadUserAfterRefresh: (user) => dispatch(loadUserAfterRefresh(user))
    }
}



export default connect(mapStateToProps, mapDispatchToProps)(FriendsComponent);