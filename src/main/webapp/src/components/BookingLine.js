import React, {Component} from 'react';
import { connect } from 'react-redux';
import BookNow from './BookNow'

class BookingLine extends Component {
    constructor(props) {
        super(props);
        
        this.state = {
            startDate: this.props.startDate,
            endDate: this.props.endDate,
        }
    }
    handleStartChange = (e) => {
        this.setState({startDate: e.target.value})
        this.props.changeStartDate(e.target.value)
    }
    handleEndChange = (e) => {
        this.setState({endDate: e.target.value})
        this.props.changeEndDate(e.target.value)
    }
    render() {
        return (
            <div className="BookingLine">
                <div className="TextBlock">
                    <span>Book a Room</span>
                </div>
                <div>
                    <span>Check-in</span>
                    <input type='date' value={this.state.startDate} onChange={e=>this.handleStartChange(e)}/>
                </div>
                <div>
                    <span>Check-out</span>
                    <input type='date' value={this.state.endDate} onChange={e=>this.handleEndChange(e)}/>
                </div>
                <BookNow isRoomsPage={this.props.isRoomsPage}/>
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        startDate: state.startDate,
        endDate: state.endDate
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        changeStartDate: (startDate) => {
            dispatch({type: 'CHANGE_START_DATE', startDate})
        },
        changeEndDate: (endDate) => {
            dispatch({type: 'CHANGE_END_DATE', endDate})
        },
    }
}

export default connect(mapStateToProps,mapDispatchToProps)(BookingLine)