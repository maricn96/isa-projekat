import React, {Component} from 'react'

class UpdateAvioCompany extends Component
{
    state = {

    }

    renter()
    {
        return(
            <div className="card indigo lighten-1">
                <div className="card-content">
                    <div className="card">
                        <div className="card-content">
                        <span className="card-title right"><a class="btn-floating btn-small waves-effect waves-light red"><i>x</i></a></span>
                        <span className="card-title indigo-text lighten-1 left"><strong>{this.props.company.id}</strong></span>
                        
                        <div className="container">
                            <form className="white">
                                <div className="input-field">
                                    <label htmlFor="name">Naziv</label>
                                    <input type="text" id="name" defaultValue={this.props.company.name}/>
                                </div>
                                <div className="input-field">
                                    <label htmlFor="address">Adresa</label>
                                    <input type="text" id="address" defaultValue={this.props.company.address}/>
                                </div>
                                <div className="input-field">
                                    <label htmlFor="description">Opis</label>
                                    <input type="text" id="description" defaultValue={this.props.company.description}/>
                                </div>
                                <div className="input-field">
                                        <button className="btn blue lighten-1 z-depth-0">Azuriraj</button>
                                    </div>
                            </form>
                        </div>
                        
                        </div>
                    </div>
                </div>
            </div>
        );
    }

}

export default UpdateAvioCompany;