import React from "react"
import { NavLink } from "react-router-dom"
import ChangeBranchOffice from "./ChangeBranchOffice";


const BranchOfficeManipulation = ({ rentACarService, branchOffices, otvoriProzor, prikaziCardZaDodavanje, iskljuciCardZaDodavanje }) => {

    return (
        <div className="container">

            <h4 className="center red-text lighten-1">Filijale</h4>

            <div className="center">
                {
                    otvoriProzor == false ?
                        <NavLink to="#" onClick={(e) => { prikaziCardZaDodavanje(e) }} className="indigo-text lighten-1">Dodaj novu filijalu</NavLink>
                        :
                        <div>
                            <NavLink to="#" className="indigo-text lighten-1" onClick={(e) => { iskljuciCardZaDodavanje(e) }}>Sakrij prozor za dodavanje</NavLink>
                            <ChangeBranchOffice rentACarService={rentACarService} izmena={false} branchOffice={undefined} ></ChangeBranchOffice>
                        </div>
                }

                {branchOffices.map(branchOffice => {
                    return (
                        <ChangeBranchOffice rentACarService={rentACarService} brisanje={true} izmena={true} branchOffice={branchOffice} key={branchOffice.id}></ChangeBranchOffice>
                    );
                })}

            </div>


        </div>
    );

};

export default BranchOfficeManipulation;