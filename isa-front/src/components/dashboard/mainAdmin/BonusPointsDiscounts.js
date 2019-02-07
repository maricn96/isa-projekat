import React from "react"
import { NavLink } from "react-router-dom"
import ChangeBonusPointsDiscount from "./ChangeBonusPointsDiscount";


const BonusPointsDiscounts = ({ bonusPointsDiscounts, otvoriProzor, prikaziCardZaDodavanje, iskljuciCardZaDodavanje }) => {

    return (
        <div className="container">

            <h4 className="center red-text lighten-1">Definisanje popusta na osnovu bonus poena</h4>

            <div className="center">

                {
                    otvoriProzor == false ?
                        <NavLink to="#" className="indigo-text lighten-1" onClick={(e) => { prikaziCardZaDodavanje(e) }}>Dodaj novi popust</NavLink>
                        :
                        <div>
                            <NavLink to="#" className="indigo-text lighten-1" onClick={(e) => { iskljuciCardZaDodavanje(e) }} >Dodaj novi popust</NavLink>
                            <ChangeBonusPointsDiscount izmena={false} discount={undefined} />
                        </div>
                }

                {bonusPointsDiscounts.map(discount => {
                    return (
                        <ChangeBonusPointsDiscount brisanje={true} izmena={true} discount={discount} key={discount.id} />
                    );
                })}


            </div>


        </div>
    );

};

export default BonusPointsDiscounts;