import React, { Component } from 'react';
import event from '../assets/images/events-room.jpg'
import meeting from '../assets/images/meeting-room.jpg'

export default class Meetings extends Component {
    render() {
        return (
            <div className='Meetings'>
                <span className = 'DescriptionText'>
                    Reprehenderit consectetur laborum ipsum ut et deserunt ut. In ex incididunt officia esse nisi<br/> id nisi. Esse <span>mollit</span> magna Lorem sit magna eu et. Mollit laborum <br/>ullamco velit consequat ut enim sunt nisi laboris aliquip incididunt officia nostrud.<br/> Magna do ut enim in laboris consequat occaecat nostrud anim do et veniam mollit.<br/> Culpa ea elit ipsum esse laborum Lorem mollit elit mollit aliquip proident anim.
                </span>
                <div className='Images'>
                    <img src={event} alt='events'></img>
                    <img src={meeting} alt='meetings'></img>
                </div>
            </div>
        );
    }
}