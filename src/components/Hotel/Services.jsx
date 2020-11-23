import React, {useEffect, useState} from 'react';
import defaultAxios from '../../api/axios';
import spa from '../../assets/img/services/spa.jpg';

const Services = (props) => {
	const [features, setFeatures] = useState([]);

	useEffect(() => {
		defaultAxios.get(`hotels/${props.id}`)
			.then((r) => {
				r.data.features.forEach(feature => {
					setFeatures(prev => {
						prev.push(feature);
						return prev;
					});
				});
				console.log(features);
			});
	}, [props.id]);

	return (
		<>
			<span className='Title'>SERVICES</span>
			<div className='Services'>
				{features.map((feature, i) => (
					<div key={i}>
						<span>{feature}</span>
						<img src={spa} alt='spa'/>
					</div>
				))}
			</div>
		</>
	);
};

export default Services;
