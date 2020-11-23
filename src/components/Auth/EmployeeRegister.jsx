import React, {useState} from 'react';
import s from './Auth.module.css';
import {toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import defaultAxios from '../../api/axios';
import history from '../../common/history';

toast.configure();

const EmployeeRegister = () => {
	const [username, setUsername] = useState('');
	const [email, setEmail] = useState('');
	const [password, setPassword] = useState('');
	const [firstName, setFirstName] = useState('');
	const [lastName, setLastName] = useState('');
	const [homePhoneNumber, setHomePhoneNumber] = useState('');
	const [mobilePhoneNumber, setMobilePhoneNumber] = useState('');
	const [country, setCountry] = useState('');
	const [city, setCity] = useState('');
	const [street, setStreet] = useState('');
	const [idType, setIdType] = useState('');
	const [idNumber, setIdNumber] = useState('');

	const onSubmit = (e) => {
		e.preventDefault();
		defaultAxios.post('guest/register', {
			username, email, password, firstName, lastName, homePhoneNumber, mobilePhoneNumber, country, city,
			street, idType, idNumber
		})
			.then((r) => {
				toast.success('Registered successfully!', options);
				history.push('/login');
			})
			.catch((e) => {
				if (e.response) {
					toast.warning('Please fill required data correctly!', options);
				} else {
					toast.error('Something bad happened!', options);
				}
			});
	};

	const options = [
		{label: 'Manager', value: 'manager'},
		{label: 'Receptionist', value: 'receptionist'},
		{label: 'Cleaning worker', value: 'cleaning'}
	];

	return (
		<div className='container'>
			<div className={s.baseContainer}>
				<div className={s.inner}>
					<div className={s.header}>Register</div>
					<form className={s.form} onSubmit={onSubmit}>
						<div className={s.formGroup}>
							<label htmlFor='username'>Username <span>*</span></label>
							<input type='text' name='username' minLength='3' maxLength='20'
							       required value={username}
							       onChange={(e) => {
								       setUsername(e.target.value);
							       }}/>
						</div>
						<div className={s.formGroup}>
							<label htmlFor='email'>Email <span>*</span></label>
							<input type='email' name='email' maxLength='50' required value={email}
							       onChange={(e) => {
								       setEmail(e.target.value);
							       }}/>
						</div>
						<div className={s.formGroup}>
							<label htmlFor='password'>Password <span>*</span></label>
							<input type='password' name='password' minLength='6' maxLength='40'
							       required value={password}
							       onChange={(e) => {
								       setPassword(e.target.value);
							       }}/>
						</div>
						<div className={s.formGroup}>
							<label htmlFor='firstname'>First name <span>*</span></label>
							<input type='text' name='firstname' required value={firstName}
							       onChange={(e) => {
								       setFirstName(e.target.value);
							       }}/>
						</div>
						<div className={s.formGroup}>
							<label htmlFor='lastName'>Last name <span>*</span></label>
							<input type='text' name='lastName' required value={lastName}
							       onChange={(e) => {
								       setLastName(e.target.value);
							       }}/>
						</div>
						<div className={s.formGroup}>
							<label htmlFor='homePhoneNumber'>Home phone number</label>
							<input type='text' name='homePhoneNumber'
							       value={homePhoneNumber}
							       onChange={(e) => {
								       setHomePhoneNumber(e.target.value);
							       }}/>
						</div>
						<div className={s.formGroup}>
							<label htmlFor='mobilePhoneNumber'>Mobile phone number</label>
							<input type='text' name='mobilePhoneNumber'
							       value={mobilePhoneNumber}
							       onChange={(e) => {
								       setMobilePhoneNumber(e.target.value);
							       }}/>
						</div>
						<div className={s.formGroup}>
							<label htmlFor='country'>Country</label>
							<input type='text' name='country' value={country}
							       onChange={(e) => {
								       setCountry(e.target.value);
							       }}/>
						</div>
						<div className={s.formGroup}>
							<label htmlFor='city'>City</label>
							<input type='text' name='city' value={city}
							       onChange={(e) => {
								       setCity(e.target.value);
							       }}/>
						</div>
						<div className={s.formGroup}>
							<label htmlFor='street'>Street</label>
							<input type='text' name='street' value={street}
							       onChange={(e) => {
								       setStreet(e.target.value);
							       }}/>
						</div>
						<div className={s.formGroup}>
							<label htmlFor='idType'>Identification type <span>*</span></label>
							<input type='text' name='idType' required value={idType}
							       onChange={(e) => {
								       setIdType(e.target.value);
							       }}/>
						</div>
						<div className={s.formGroup}>
							<label htmlFor='idNumber'>Identification number <span>*</span></label>
							<input type='text' name='idNumber' required
							       value={idNumber}
							       onChange={(e) => {
								       setIdNumber(e.target.value);
							       }}/>
						</div>

						<button type='submit' className={s.btn}>Register</button>
					</form>
				</div>
			</div>
		</div>
	);
};

export default EmployeeRegister;