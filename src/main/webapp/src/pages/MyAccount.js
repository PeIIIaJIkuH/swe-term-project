import React, {Component} from 'react';
import Header from '../components/Header';
import Table from '../components/Table'

export default class MyAccount extends Component {
    constructor(props) {
        super(props)
        this.state = {
            order: [
                {
                    id: 2,
                    roomType: 3,
                    startDate: '2020-19-20',
                    endDate: '2020-19-21',
                    status: 0
                }
            ]
        }

    }
    render() {
        return (
            <div className='MyAccountPage'>
                <Header isAccountPage={1}/>
                Order History
                <Table data={this.state.order} />
            </div>
        );
    }
}