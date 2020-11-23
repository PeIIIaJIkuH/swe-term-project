import React, {useEffect, useState} from 'react';
import footerImage from '../../assets/img/footer.jpg';
import defaultAxios from '../../api/axios';


const HotelFooter = (props) => {
	const [country, setCountry] = useState('');
	const [city, setCity] = useState('');
	const [street, setStreet] = useState('');
	const [numbers, setNumbers] = useState([]);

	useEffect(() => {
		defaultAxios.get(`hotels/${props.id}`)
			.then((r) => {
				setCountry(r.data.country);
				setCity(r.data.city);
				setStreet(r.data.street);
				r.data.numbers.forEach(number => {
					setNumbers(prev => {
						prev.push(number);
						return prev;
					});
				});
			});
	}, [props.id]);

	return (
		<div className='Footer'>
			<img src={footerImage} alt='FooterImage' className='FooterImage'/>
			<div>
                    <span>
                        <span className='main'>Contact us</span><br/>
                        Address: {street}, {city}, {country} <br/>
	                    {numbers.map(number => (
		                    <div key={number}>
			                    Tel: {number}
		                    </div>
	                    ))}
                    </span>
			</div>
		</div>
	);
};

export default HotelFooter;