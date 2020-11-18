import React, { Component } from 'react';
import billiards from '../assets/images/services/billiards.jpg'
import restraunt from '../assets/images/services/restraunt.jpg'
import spa from '../assets/images/services/spa.jpg'

export default class Services extends Component {
    render() {
        return (
            <div className='Services'>
                <div>
                    <span>Lounge</span>
                    <img src={billiards} alt='billiards'></img>
                </div>
                <div>
                    <span>Spa</span>
                    <img src={spa} alt='spa'></img>
                </div>
                <div>
                    <span>Restraunt</span>
                    <img src={restraunt} alt='restraunt'></img>
                </div>
            </div>
        );
    }
}