const initState = {
    hoteli: []
}

const hoteliReducer = (state = initState, action) => {
    switch(action.type){
        case 'CREATE_HOTEL':
            console.log('kreiran hotel', action.hotel);
            break;
        case 'DELETE_HOTEL':
            console.log('izbrisan hotel', action.hotelId);
            break;
        case 'EDIT_HOTEL':
            console.log('izmenjen hotel', action.hotelId);
            window.location = '/listaHotelaAdmin';
            break;
        case 'CREATE_USLUGA':
            console.log('kreirana usluga', action.usluga);
            break;
        case 'DELETE_USLUGA':
            console.log('obrisana usluga', action.uslugaId);
            break;
        case 'EDIT_USLUGA':
            console.log('izmenjena usluga', action.uslugaId);
            window.location = '/listaDodatnihUslugaAdmin';
            break;
        case 'CREATE_TIP':
            console.log('kreiran tip', action.tip);
            break;
        case 'DELETE_TIP':
            console.log('izbrisan tip', action.tipId);
            break;
        case 'EDIT_TIP':
            console.log('izmenjen tip', action.tipId);
            window.location = '/listaTipovaSobaAdmin';
            break;
        case 'CREATE_SOBA':
            console.log('kreirana soba', action.soba);
            break;
        case 'DELETE_SOBA':
            console.log('izbrisana soba', action.sobaId);
            break;
        case 'EDIT_SOBA':
            console.log('izmenjena soba', action.sobaId);
            window.location = '/listaHotelskihSobaAdmin';
            break;
        case 'CREATE_CENOVNIK':
            console.log('kreirana usluga cenovnika', action.cenovnik);
            break;
        case 'DELETE_CENOVNIK':
            console.log('izbrisana usluga', action.cenovnikId);
            break;
        case 'EDIT_CENOVNIK':
            console.log('izmeni cenovnik', action.cenovnikId);
            window.location = '/listaCenovnikaAdmin';
            break;
        case 'CREATE_CENA':
            console.log('kreirana cena', action.cena);
            break;
        case 'DELETE_CENA':
            console.log('izbrisana cena', action.cenaId);
            break;
            
    }
    return state
}

export default hoteliReducer;

