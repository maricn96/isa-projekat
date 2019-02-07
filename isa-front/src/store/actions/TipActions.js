import axios from 'axios'

export const createTip = (tip) =>{
    return (dispatch, getState) =>{
        //async call to db dodavanje u bazu
        axios.get('http://localhost:8092/api/hotel/hotel/'+tip.hotelTipaDodaj)
            .then(res=>{
                console.log(res);
            axios.post('http://localhost:8092/api/hotel/tipSobe/', { kapacitet: tip.kapacitetDodaj, roomType: tip.nazivTipaDodaj, hotel_tipSobe: res.data})
                .then(res => {
                console.log(res);
        })
            dispatch({type: 'CREATE_TIP', tip: tip});
        })
    }

}

export const deleteTip = (tipId) =>{
    return (dispatch, getState) =>{
        //async call to db dodavanje u bazu
        axios.delete('http://localhost:8092/api/hotel/tipSobe/'+tipId)
            .then(res => {
            console.log(res);
      })
        dispatch({type: 'DELETE_TIP', tipId});
    }
}

export const editTip = (tip) =>{
    return (dispatch, getState) =>{
        //async call to db dodavanje u bazu
        axios.put('http://localhost:8092/api/hotel/tipSobe/'+tip.idIzmeni, { kapacitet: tip.kapacitetIzmeni, roomType: tip.roomTypeIzmeni, hotel_tipSobe: tip.hotel })
            .then(res => {
            console.log(res);
            console.log(res.data);
      })
        dispatch({type: 'EDIT_TIP', tip: tip});
    }

}