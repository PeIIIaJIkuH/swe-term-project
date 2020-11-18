import React, { Component } from 'react';
import Home from '../components/Home';
import BookingLine from '../components/BookingLine';
import HotelDescription from '../components/HotelDescription';
import Footer from '../components/Footer';
import RoomSelector from '../components/RoomSelector';
import Header from '../components/Header';
import Services from '../components/Services'
import Meetings from '../components/Meetings'

class HomePage extends Component {
    constructor(props){
        super(props)
        this.state = {
            modalShow: 0,
        }
    }
    render() {
        return (
            <div className="HomeRoot">
                <Header/>
                <Home/>
                <BookingLine isRoomsPage={0}/>
                <HotelDescription/> 
                <RoomSelector/>
                <span className = 'Title'>SERVICES</span>
                <Services/>
                <span className = 'Title'>MEETINGS <span>& EVENTS</span></span>
                <Meetings/>
                <Footer/>
            </div>
        );
    }
}


export default HomePage