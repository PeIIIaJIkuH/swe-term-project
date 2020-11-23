import React, {useEffect, useState} from 'react';
import Select from 'react-select';
import defaultAxios from '../../api/axios';
import {toast} from 'react-toastify';
import options from '../../common/toast';

const dateToString = (date) => {
	let dd = date.getDate(),
		mm = date.getMonth() + 1,
		yyyy = date.getFullYear();
	if (dd < 10)
		dd = '0' + dd;
	if (mm < 10)
		mm = '0' + mm;
	return `${yyyy}-${mm}-${dd}`;
};

toast.configure();

const Booking = (props) => {
	const [roomTypes, setRoomTypes] = useState([]);
	const [currentRoomType, setCurrentRoomType] = useState({});
	const [startDate, setStartDate] = useState('');
	const [endDate, setEndDate] = useState('');

	let today = new Date();
	today.setDate(today.getDate() + 7);
	const minStartDate = useState(dateToString(today))[0];
	today.setDate(today.getDate() + 1);
	const [minEndDate, setMinEndDate] = useState(dateToString(today));

	useEffect(() => {
		defaultAxios.get(`hotels/${props.id}`)
			.then((r) => {
				r.data.roomTypes.forEach(type => {
					setRoomTypes(prev => {
						prev.push(type);
						return prev;
					});
				});
			});
	}, [props.id]);

	const findRoomTypeId = (roomTypes, obj) => {
		for (let i = 0; i < roomTypes.length; i++) {
			if (roomTypes[i].name === obj.label)
				return roomTypes[i].id;
		}
		return -1;
	};

	const toCorrectDate = (str) => {
		const arr = str.split('.');
		return `${arr[2]}.${arr[1]}.${arr[0]}`;
	};

	const onBook = () => {
		const user = JSON.parse(localStorage.getItem('user'));
		if (user && user.token) {
			if (!startDate || !endDate || !currentRoomType.label) {
				toast.warning('Please fill the date correctly!', options);
			} else {
				const obj = {
					start: toCorrectDate(startDate.replaceAll('-', '.')),
					end: toCorrectDate(endDate.replaceAll('-', '.')),
					hotelId: props.id,
					guestId: user.id,
					roomTypeId: findRoomTypeId(roomTypes, currentRoomType)
				};
				defaultAxios.post('reservations', obj)
					.then((r) => {
						toast.success('Reservation created successfully!', options);
					})
					.catch((e) => {
						if (e.response) {
							toast.warning('There are no free room types for this period!');
						} else {
							toast.warning('Something bad happened!', options);
						}
					});
			}
		} else {
			toast.warning('Please login or signup to book a room!', options);
		}
	};

	return (
		<div className='BookingLine'>
			<div className='block'>
				<span>Book a Room</span>
			</div>
			<div className='block'>
				<span>Check-in</span>
				<input type='date' value={startDate} min={minStartDate} onChange={(e) => {
					setStartDate(e.target.value);
					const date = new Date(e.target.value);
					date.setDate(date.getDate() + 1);
					setMinEndDate(dateToString(date));
				}}/>
			</div>
			<div className='block'>
				<span>Check-out</span>
				<input type='date' value={endDate} min={minEndDate} onChange={(e) => {
					setEndDate(e.target.value);
				}}/>
			</div>
			<Select className='select' options={roomTypes.map(el => ({label: el.name, value: el.name}))}
			        value={currentRoomType} onChange={(e) => {
				setCurrentRoomType(e);
			}}/>
			<button className='BookNow' onClick={onBook}>
                <span>
                    <b>Book Now!</b>
                </span>
			</button>
		</div>
	);
};

export default Booking;