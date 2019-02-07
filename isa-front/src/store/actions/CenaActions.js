import axios from "axios"

export const createCena = (cena) =>{
    return (dispatch, getState) =>{
        //async call to db dodavanje u bazu
        axios.post('http://localhost:8092/api/hotel/cene/', { cena: cena.cenaSobeDodaj, datumOd: cena.vaziOdDodaj, datumDo: cena.vaziDoDodaj, hotelskaSoba_cena: cena.soba })
            .then(res => {
            console.log(res);
            //console.log(res.data);
        })
        dispatch({type: 'CREATE_CENA', cena: cena});
    }

}

export const deleteCena = (cenaId) =>{
    return (dispatch, getState) =>{
        //async call to db dodavanje u bazu
        axios.delete('http://localhost:8092/api/hotel/cene/'+cenaId)
            .then(res => {
            console.log(res);
      })
        dispatch({type: 'DELETE_CENA', cenaId});
    }
}