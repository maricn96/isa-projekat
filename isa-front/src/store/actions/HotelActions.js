import axios from 'axios'

export const createHotel = (hotel) =>{
    return (dispatch, getState) =>{
        //async call to db dodavanje u bazu
        axios.post('http://localhost:8092/api/hotel/hotel/', { adress: hotel.adresaDodaj, name: hotel.imeDodaj, promotionalDescription: hotel.opisDodaj })
            .then(res => {
            console.log(res);
            //console.log(res.data);
      })
        dispatch({type: 'CREATE_HOTEL', hotel: hotel});
    }

}

export const deleteHotel = (hotelId) =>{
    return (dispatch, getState) =>{
        //async call to db dodavanje u bazu
        axios.delete('http://localhost:8092/api/hotel/hotel/'+hotelId)
            .then(res => {
            console.log(res);
      })
        dispatch({type: 'DELETE_HOTEL', hotelId});
    }
}

export const editHotel = (hotel) =>{
    return (dispatch, getState) =>{
        //async call to db dodavanje u bazu
        axios.put('http://localhost:8092/api/hotel/hotel/'+hotel.idIzmeni, { adress: hotel.adresaIzmeni, name: hotel.imeIzmeni, promotionalDescription: hotel.opisIzmeni })
            .then(res => {
            console.log(res);
            console.log(res.data);
      })
        dispatch({type: 'EDIT_HOTEL', hotel: hotel});
    }

}


export const filterHotel = (filter) =>{
    return (dispatch, getState) =>{
        const imeAdresa = filter.ime_adresa
        const datumOd = filter.datum_dolaska
        const datumDo = filter.datum_odlaska
        const brojSoba = filter.sobe
        const brojGostiju = filter.gosti
        window.location = '/listaHotela/'+ imeAdresa +'/'+ datumOd +'/'+ datumDo +'/'+ brojSoba +'/'+ brojGostiju
    }
}

export const createRezervacija = (rezervacija) =>{
    return (dispatch, getState) =>{
        axios.put("http://localhost:8095/api/purchases/shoppingCart/addRoomReservation/1", { id: null, totalPrice: 100, dateFrom: rezervacija.rezervisi_od, dateUntil: rezervacija.rezervisi_do, sobaId: rezervacija.soba, hotel_rezervacijeSobe: rezervacija.hotel })
            .then(
                res => {
                    console.log(res);
                    console.log(res.data);
                    window.location = '/cenovniciLogged/'+ rezervacija.hotel_id
                }  
            )
    }
}