import React from "react"
import ChangeRentService from "./ChangeRentService";
import BranchOfficeManipulation from "./BranchOfficeManipulation"


const AdminRentServiceInfo = ({ rentACarService, branchOffices, otvoriProzor, prikaziCardZaDodavanje, iskljuciCardZaDodavanje }) => {

    return (
        <div className="container">
            <ChangeRentService rentACarService={rentACarService} izmena={true} ></ChangeRentService>
            <BranchOfficeManipulation rentACarService={rentACarService} otvoriProzor={otvoriProzor} prikaziCardZaDodavanje={prikaziCardZaDodavanje} iskljuciCardZaDodavanje={iskljuciCardZaDodavanje} branchOffices={branchOffices}></BranchOfficeManipulation>
        </div>
    );

};

export default AdminRentServiceInfo;