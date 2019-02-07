import React, { Component } from 'react'
import UserLoggedTabs from '../layout/tabs/UserLoggedTabs';
import ReactDOM from 'react-dom';
import axios from 'axios';
import { NavLink } from "react-router-dom";
import "../layout/navbarLinks/navBarLinks.css";

class RegistracijaKorisnika extends Component {

   state = {
      id: -1,
      name: '',
      surname: '',
      city: '',
      email: '',
      telephoneNumber: '',
      passport: '',
      pass: undefined,
      pass2: undefined
   }

   handleChange = (e) => {
      this.setState({
         [e.target.id]: e.target.value
      })
   }

   handleSubmit = (e) => {
      e.preventDefault();

      if (/.+@.+\.[A-Za-z]+$/.test(this.state.email)) {

         if (this.state.pass && this.state.pass2 && this.state.pass == this.state.pass2) {
            axios.post('http://localhost:8096/api/user/user/register', {
               id: this.state.id,
               name: this.state.name,
               surname: this.state.surname,
               city: this.state.city,
               email: this.state.email,
               telephoneNumber: this.state.telephoneNumber,
               passport: this.state.passport,
               password: this.state.pass
            })
               .then(res => {
                  if (res.data.email === "Email exits") {
                     console.log("Korisnik sa datim mejlom vec postoji!")
                  }
                  else {
                     console.log('Uspesno rezervisan!')
                  }

               })
         }
      }
      else {
         console.log("Email adresa ne valja !!")
      }



   }

   render() {
      return (
         <div className="container">
            <h2 className="red-text lighten-1 center">Registrujte se</h2>
            <div className="container center">
               <form className="white" onSubmit={this.handleSubmit}>
                  <div className="input-field">
                     <label htmlFor="email">E-mail adresa</label>
                     <input type="text" onChange={this.handleChange} id='email' />
                  </div>
                  <div className="input-field">
                     <label htmlFor="pass">Lozinka</label>
                     <input type="password" onChange={this.handleChange} id='pass' />
                  </div>
                  <div className="input-field">
                     <label htmlFor="pass2">Ponovite lozinku</label>
                     <input type="password" onChange={this.handleChange} id='pass2' />
                  </div>
                  <div className="input-field">
                     <label htmlFor="name">Ime</label>
                     <input type="text" onChange={this.handleChange} id='name' />
                  </div>
                  <div className="input-field">
                     <label htmlFor="surname">Prezime</label>
                     <input type="text" onChange={this.handleChange} id='surname' />
                  </div>
                  <div className="input-field">
                     <label htmlFor="passport">Broj pasosa</label>
                     <input type="text" onChange={this.handleChange} id='passport' />
                  </div>
                  <div className="input-field">
                     <label htmlFor="telephoneNumber" onChange={this.handleChange}>Broj telefona</label>
                     <input type="text" id='telephoneNumber' />
                  </div>
                  <input type="submit" className="btn red darken-2 z-depth-0" value="Registracija" />
               </form>
            </div>
         </div>
      );
   }
}

export default RegistracijaKorisnika