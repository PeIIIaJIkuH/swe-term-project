import React, {useEffect, useRef, useState} from 'react';
import s from './Main.module.css';
import bg from '../../assets/img/main-bg.jpg';
import Select from 'react-select';
import defaultAxios from '../../api/axios';
import history from '../../common/history';

const Main = (props) => {
	const options = useRef([]);
	const [hotels, setHotels] = useState([]);
	const [currentHotel, setCurrentHotel] = useState({});

	useEffect(() => {
		defaultAxios.get('hotels/main')
			.then((r) => {
				r.data.forEach((e) => {
					options.current.push({label: e.name, value: e.name});
					setHotels(prev => {
						prev.push(e);
						return prev;
					});
				});
			});
	}, []);

	window.a = currentHotel;

	const onSelectChange = (e) => {
		setCurrentHotel(e);
	};

	const findHotel = (hotels, option) => {
		for (let i = 0; i < hotels.length; i++) {
			if (hotels[i].name === option.label)
				return hotels[i];
		}
		return {id: -1, name: '', country: '', city: '', avgPrice: ''};
	};

	return (
		<div className={s.main} style={{background: `url(${bg}) no-repeat center`, backgroundSize: 'cover'}}>
			<div className={`container ${s.mainContainer}`}>
				<div className={s.inner}>
					<div className={s.content}>
						<h1 className={s.title}>Hotel Chain</h1>
						<Select className={s.select} options={options.current} onChange={onSelectChange}
						        placeholder='Select hotel' defaultValue={options.current[0]}/>
						<h3 className={s.info}>
							<span>Name:</span> {currentHotel.label}
						</h3>
						<h3 className={s.info}>
							<span>Location:</span> {findHotel(hotels, currentHotel).country} {findHotel(hotels, currentHotel).city}
						</h3>
						<h3 className={s.info}>
							<span>Average price:</span> {findHotel(hotels, currentHotel).avgPrice}
						</h3>
						<div className={s.btn}>
							<button onClick={() => {
								const id = findHotel(hotels, currentHotel).id;
								if (id !== -1) {
									history.push(`/hotels/${id}`);
								}
							}}>
								Learn more
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	);
};

export default Main;