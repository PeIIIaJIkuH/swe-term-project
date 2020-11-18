import React, { Component } from 'react';

export default class Table extends Component {
    render() {
        return (
            <div className='Table'>
                <div className='Row'>
                    <div className='Cell'><span className='Element'>id</span></div>
                    <div className='Cell'><span className='Element'>Start Date</span></div>
                    <div className='Cell'><span className='Element'>End Date</span></div>
                    <div className='Cell'><span className='Element'>Room Type</span></div>
                    <div className='Cell'><span className='Element'>Status</span></div>
                </div>
                {
                    this.props.data.map((order) => {
                        return( 
                            <div className='Row'>
                                <div className='Cell'><span className='Element'>{order.id}</span></div>
                                <div className='Cell'><span className='Element'>{order.startDate}</span></div>
                                <div className='Cell'><span className='Element'>{order.endDate}</span></div>
                                <div className='Cell'><span className='Element'>{order.roomType}</span></div>
                                <div className='Cell'><span className='Element'>{order.status}</span></div>
                            </div>
                        )
                    })
                }
            </div>
        );
    }
}