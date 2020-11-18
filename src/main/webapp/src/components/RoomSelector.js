import React, {Component} from 'react';
import { connect } from 'react-redux';

class RoomSelector extends Component {
    constructor(props) {
        super(props);
        this.state = {
            currentRoom: this.props.currentRoom
        }
    }
    handleRoomChange(roomType) {
        this.setState(() => {
            return {currentRoom: roomType}
        })
        this.props.changeRoom(roomType);
    }
    render() {
        return (
            <div className='RoomSelector'>
                <div className='Selector'>
                    <button onClick={() => this.handleRoomChange(0)}>
                        {this.state.currentRoom === 0
                            ? <span className='Selected'><b>Basic</b></span>
                            : <span><b>Basic</b></span>
                            }</button>
                    <button onClick={() => this.handleRoomChange(1)}>
                        {this.state.currentRoom === 1
                            ? <span className='Selected'><b>Lux</b></span>
                            : <span><b>Lux</b></span>
                            }
                    </button>
                    <button onClick={() => this.handleRoomChange(2)}>
                        {this.state.currentRoom === 2
                            ? <span className='Selected'><b>Royal</b></span>
                            : <span><b>Royal</b></span>
                            }
                    </button>
                    <button onClick={() => this.handleRoomChange(3)}>
                        {this.state.currentRoom === 3
                            ? <span className='Selected'><b>Royal Lux</b></span>
                            : <span><b>Royal Lux</b></span>
                            }
                    </button>
                </div>
                <div className='Rooms'>
                    {this.state.currentRoom === 0
                        ? <div className='basicRoom'>
                            <div className='RoomBlock'>
                                <span className = 'RoomType'>BASIC ROOM</span>
                                <span className = 'RoomSize'>30 Sqm.</span>
                                <span className = 'RoomDetail'>Room Detail:<br/>Ad fugiat aute aliquip mollit cupidatat sit eiusmod ipsum adipisicing nisi sint.</span>
                                {this.props.isRoomsPage !== 1 ? <button><span className = 'BookNow'>Book now!</span></button> : <></>}
                            </div>
                        </div>
                        : <></>}
                    {this.state.currentRoom === 1
                        ? <div className='luxRoom'>
                            <div className='RoomBlock'>
                                <span className = 'RoomType'>LUX ROOM</span>
                                <span className = 'RoomSize'>35 Sqm.</span>
                                <span className = 'RoomDetail'>Room Detail:<br/>Ad fugiat aute aliquip mollit cupidatat sit eiusmod ipsum adipisicing nisi sint.</span>
                                {this.props.isRoomsPage !== 1 ? <button><span className = 'BookNow'>Book now!</span></button> : <></>}
                            </div>
                        </div>
                        : <></>}
                    {this.state.currentRoom === 2
                        ? <div className='royalRoom'>
                            <div className='RoomBlock'>
                                <span className = 'RoomType'>ROYAL ROOM</span>
                                <span className = 'RoomSize'>40 Sqm.</span>
                                <span className = 'RoomDetail'>Room Detail:<br/>Ad fugiat aute aliquip mollit cupidatat sit eiusmod ipsum adipisicing nisi sint.</span>
                                {this.props.isRoomsPage !== 1 ? <button><span className = 'BookNow'>Book now!</span></button> : <></>}
                            </div>
                        </div>
                        : <></>}
                    {this.state.currentRoom === 3
                        ? <div className='royalLuxRoom'>
                            <div className='RoomBlock'>
                                <span className = 'RoomType'>LUX ROYAL ROOM</span>
                                <span className = 'RoomSize'>50 Sqm.</span>
                                <span className = 'RoomDetail'>Room Detail:<br/>Ad fugiat aute aliquip mollit cupidatat sit eiusmod ipsum adipisicing nisi sint.</span>
                                {this.props.isRoomsPage !== 1 ? <button><span className = 'BookNow'>Book now!</span></button> : <></>}
                            </div>
                        </div>
                        : <></>}
                </div>
                
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        currentRoom: state.currentRoom,
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        changeRoom: (currentRoom) => {
            dispatch({type: 'CHANGE_ROOM', currentRoom})
        },
    }
}

export default connect(mapStateToProps,mapDispatchToProps)(RoomSelector)