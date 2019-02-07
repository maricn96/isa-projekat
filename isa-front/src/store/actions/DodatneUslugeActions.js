import axios from 'axios'

export const createUsluga = (usluga) =>{
    return (dispatch, getState) =>{
        //async call to db dodavanje u bazu
        axios.get('http://localhost:8092/api/hotel/hotel/'+usluga.hotelDodaj)
            .then(res=>{
            axios.post('http://localhost:8092/api/hotel/dodatneUsluge/', { additionalServiceName: usluga.nazivDodatneUslugeDodaj, additionalServicePrice: usluga.cenaDodatneUslugeDodaj, popust: usluga.popustDodaj, hotel_dodatneUsluge: res.data })
                .then(res => {
                console.log(res);
                //console.log(res.data);
        })
            dispatch({type: 'CREATE_USLUGA', usluga: usluga});
        })
    }
}

export const deleteUsluga = (uslugaId) =>{
    return (dispatch, getState) =>{
        //async call to db dodavanje u bazu
        axios.delete('http://localhost:8092/api/hotel/dodatneUsluge/'+uslugaId)
            .then(res => {
            console.log(res);
      })
        dispatch({type: 'DELETE_USLUGA', uslugaId});
    }
}

export const editUsluga = (usluga) =>{
    return (dispatch, getState) =>{
        //async call to db dodavanje u bazu
        axios.put('http://localhost:8092/api/hotel/dodatneUsluge/'+usluga.idIzmeni, { additionalServiceName: usluga.nazivDodatneUslugeIzmeni, additionalServicePrice: usluga.cenaDodatneUslugeIzmeni, popust: usluga.popustIzmeni, hotel_dodatneUsluge: usluga.hotel })
            .then(res => {
            console.log(res);
            console.log(res.data);
      })
        dispatch({type: 'EDIT_USLUGA', usluga: usluga});
    }

}

