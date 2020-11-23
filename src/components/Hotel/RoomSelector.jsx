import React, {useEffect, useState} from 'react';
import defaultAxios from '../../api/axios';
import roomBg from '../../assets/img/basic-room.jpg';

const RoomSelector = (props) => {
	const [roomTypes, setRoomTypes] = useState([]);
	const [currentRoomType, setCurrentRoomType] = useState(0);

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

	return (
		<div className='RoomSelector'>
			<div className='Selector'>
				{roomTypes.map((roomType, i) => (
					<button key={i} onClick={() => {
						setCurrentRoomType(i);
					}}>
						<span className={currentRoomType === i ? 'Selected' : ''}>
								<b>{roomType.name}</b>
						</span>
					</button>
				))}
			</div>
			<div className='Rooms'>
				{roomTypes.map((roomType, i) => {
					if (currentRoomType === i) {
						return (
							<div key={i} className='Room' style={{background: `url(${roomBg})`}}>
								<div className='RoomBlock'>
									<span className='RoomType'>{roomType.name}</span>
									<span className='RoomSize'>{roomType.size} Sqm.</span>
									<span className='RoomDetail'>Room Detail: <br/>Ad fugiat aute aliquip mollit cupidatat sit eiusmod ipsum adipisicing nisi sint.</span>
									<button onClick={() => {
										window.scrollTo({
											top: 0,
											behavior: 'smooth'
										});
									}}>
										<span className='BookNow'>
											Book now!
										</span>
									</button>
								</div>
							</div>
						);
					} else {
						return <></>;
					}
				})}
			</div>
		</div>
	);
};

export default RoomSelector;