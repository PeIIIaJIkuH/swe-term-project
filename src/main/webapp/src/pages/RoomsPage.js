import React, {Component} from 'react';
import RoomSelector from '../components/RoomSelector';
import BookingLine from '../components/BookingLine';
import Header from '../components/Header';
import {connect} from 'react-redux';
import Modal from "react-bootstrap/Modal";

class RoomsPage extends Component {
    render() {
        return (
            <div>
                <Header isRoomsPage={1}/>
                <RoomSelector isRoomsPage={1}/>
                <BookingLine isRoomsPage={1}/>

                <Modal show={this.props.modalShow} onHide={this.props.modalClose}>
                    <Modal.Header closeButton>
                        <Modal.Title>Modal heading</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>Woohoo, you're reading this text in a modal!</Modal.Body>
                    <Modal.Footer>
                    </Modal.Footer>
                </Modal>
            </div>
        );
    }
}
const mapStateToProps = (state) => {
    return {modalShow: state.modalShow}
}

const mapDispatchToProps = (dispatch) => {
    return {
        modalClose: () => {
            dispatch({type: 'MODAL_CLOSE'})
        }
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(RoomsPage)