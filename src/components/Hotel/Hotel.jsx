import React, {useEffect, useState} from 'react';
import defaultAxios from '../../api/axios';
import './Hotel.css';
import Booking from './Booking';
import hotelBg from '../../assets/img/main-bg.jpg';
import Description from './Description';
import HotelFooter from './HotelFooter';
import RoomSelector from './RoomSelector';
import Services from './Services';
import {withRouter} from 'react-router-dom';


const Hotel = (props) => {
	const id = props.match.params.id;
	const [hotel, setHotel] = useState({});

	useEffect(() => {
		defaultAxios.get(`hotels/${id}`)
			.then((r) => {
				setHotel(r.data);
			});
	}, [props.id]);

	return (
		<div className='hotel'>
			<img className='hotelBg' src={hotelBg} alt='bg'/>
			<Booking id={id}/>
			<Description/>
			<RoomSelector id={id}/>
			<Services id={id}/>
			<HotelFooter id={id}/>
		</div>
	);
};

export default withRouter(Hotel);