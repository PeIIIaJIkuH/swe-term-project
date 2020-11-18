import React, {useCallback} from 'react';
import {useHistory} from 'react-router-dom';
import {useDispatch} from 'react-redux'

function BookNow(props) {
    const dispatch = useDispatch()
    const history = useHistory();
    const handleOnClick = useCallback(() => history.push('/rooms'), [history]);
    if (!props.isRoomsPage) 
        return ( 
            <button className="BookNow" onClick={handleOnClick}>
                <span>
                    <b>Book Now!</b>
                </span>
            </button>
        )
    else 
        return (
            <button className="BookNow" onClick={() => dispatch({ type: 'MODAL_SHOW' })}>
                <span>
                    <b>Book Now!</b> 
                </span> 
            </button>
        )
}


export default BookNow