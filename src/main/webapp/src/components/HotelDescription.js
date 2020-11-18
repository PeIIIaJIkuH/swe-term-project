import React, { Component } from 'react';

import logo from '../assets/images/logo.png'

export default class HotelDescription extends Component {
    render() {
        return (
            <div className='HotelDescription'>
                <img className='Logo' src = {logo} alt='Logo' ></img>
                <span className = 'DescriptionText' >Laborum enim commodo commodo exercitation commodo. Tempor qui esse dolore ullamco<br/> magna qui. <span>Reprehenderit</span> consequat cupidatat proident<br/> officia cillum commodo ea duis deserunt. Dolore dolore exercitation elit nulla nostrud proident.<br/> Laborum est reprehenderit <span>aliqua</span> dolore velit laboris in est do sit aute do.</span>
            </div>
        );
    }
}