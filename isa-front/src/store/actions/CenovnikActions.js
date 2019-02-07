import axios from 'axios'

export const createCenovnik = (cenovnik) =>{
    return (dispatch, getState) =>{
        //async call to db dodavanje u bazu
        axios.get('http://localhost:8092/api/hotel/hotel/'+cenovnik.hotelCenovnikaDodaj)
            .then(res=>{
                axios.post('http://localhost:8092/api/hotel/cenovnik/', { imeUsluge: cenovnik.nazivCenovnikaDodaj, cenaUsluge: cenovnik.cenaCenovnikaDodaj, hotel_cenovnikUsluga: res.data })
                    .then(res => {
                    console.log(res);
                    //console.log(res.data);
                })
        })
        dispatch({type: 'CREATE_CENOVNIK', cenovnik: cenovnik});
    }

}

export const deleteCenovnik = (cenovnikId) =>{
    return (dispatch, getState) =>{
        //async call to db dodavanje u bazu
        axios.delete('http://localhost:8092/api/hotel/cenovnik/'+cenovnikId)
            .then(res => {
            console.log(res);
      })
        dispatch({type: 'DELETE_CENOVNIK', cenovnikId});
    }
}


export const editCenovnik = (cenovnik) =>{
    return (dispatch, getState) =>{
        //async call to db dodavanje u bazu
        axios.put('http://localhost:8092/api/hotel/cenovnik/'+cenovnik.idIzmeni, { imeUsluge: cenovnik.nazivCenovnikaIzmeni, cenaUsluge: cenovnik.cenaCenovnikaIzmeni, hotel_cenovnikUsluga:cenovnik.hotel })
            .then(res => {
            console.log(res);
            console.log(res.data);
      })
        dispatch({type: 'EDIT_CENOVNIK', cenovnik: cenovnik});
    }

}