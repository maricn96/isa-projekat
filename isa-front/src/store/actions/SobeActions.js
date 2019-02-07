import axios from 'axios'

export const createSoba = (soba) =>{

    return (dispatch, getState) =>{
        //async call to db dodavanje u bazu

        axios.get('http://localhost:8092/api/hotel/hotel/'+soba.hotelS)
            .then(res=>{
                console.log(res);       
                soba.hotelS = res.data;
        axios.get('http://localhost:8092/api/hotel/tipSobe/'+soba.tipHSDodaj)
            .then(res=>{
            console.log(res);
            soba.tipHSDodaj = res.data;
                axios.post('http://localhost:8092/api/hotel/hotelskaSoba/', { floor: soba.spratDodaj, originalnaCena: soba.originalnaCenaSobeDodaj, reserved: false, hotel_hotelskeSobe: soba.hotelS, tipSobe_hotelskeSobe: soba.tipHSDodaj })
                    .then(res => {
                    console.log(res);
                })
                dispatch({type: 'CREATE_SOBA', soba: soba});
        })
    })
          
    }

}

export const deleteSoba = (sobaId) =>{

    return (dispatch, getState) =>{
        //async call to db dodavanje u bazu
        axios.delete('http://localhost:8092/api/hotel/hotelskaSoba/'+sobaId)
            .then(res => {
            console.log(res);
      })
        dispatch({type: 'DELETE_SOBA', sobaId});
    }

}


export const editSoba = (soba) =>{
    return (dispatch, getState) =>{
        //async call to db dodavanje u bazu

        axios.get('http://localhost:8092/api/hotel/tipSobe/'+soba.tipHSIzmeni)
            .then(res=>{
            console.log(res);
            soba.tipHSIzmeni = res.data;
        axios.put('http://localhost:8092/api/hotel/hotelskaSoba/'+soba.idIzmeni, {floor: soba.spratIzmeni, originalnaCena: soba.originalnaCenaSobeIzmeni, reserved: soba.reservedIzmeni, hotel_hotelskeSobe: soba.hotelSIzmeni, tipSobe_hotelskeSobe: soba.tipHSIzmeni})
            .then(res => {
            console.log(res);
            console.log(res.data);
      })
    })
        dispatch({type: 'EDIT_SOBA', soba: soba});
    }

}