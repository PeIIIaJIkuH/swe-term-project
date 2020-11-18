var today = new Date();
let todayDate = today.getFullYear() + '-' + (today.getMonth() + 1) + '-' + today.getDate();
let next = today.getFullYear() + '-' + (today.getMonth() + 1) + '-' + (today.getDate() + 1);
const initState = {
    startDate: todayDate,
    endDate: next,
    currentRoom: 0,
    modalShow: 0,
}

const rootReducer = (state = initState, action) => {
    switch(action.type){
        case 'CHANGE_START_DATE':
            return{
                ...state,
                startDate: action.startDate,
            };
        case 'CHANGE_END_DATE':
            return{
                ...state,
                endDate: action.endDate,
            };
        case 'CHANGE_ROOM':
            return{
                ...state,
                currentRoom: action.currentRoom,
            };
        case 'MODAL_SHOW':
            return{
                ...state,
                modalShow: 1,
            };
        case 'MODAL_CLOSE':
            return{
                ...state,
                modalShow: 0,
            };
        default:
            return state
    };
}

export default rootReducer