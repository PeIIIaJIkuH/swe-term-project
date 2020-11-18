import React, {Component} from 'react';


import hotelWide from '../assets/images/wide-hotel.jpg'

class Home extends Component {
    render() {
        return (
            <div className="Home">
                <img className='HotelWide' src={hotelWide} alt='hotel-wide'></img>
            </div>
        );
    }
}

export default Home