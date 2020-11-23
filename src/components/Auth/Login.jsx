import React, {useState} from 'react';
import s from './Auth.module.css';
import defaultAxios from '../../api/axios';
import {toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import history from '../../common/history';
import options from '../../common/toast';

toast.configure();

const Login = (props) => {
	const [username, setUsername] = useState('');
	const [password, setPassword] = useState('');

	const onSubmit = (e) => {
		e.preventDefault();
		defaultAxios.post('guest/login', {username, password})
			.then((r) => {
				toast.success('Logged in successfully!', options);
				localStorage.setItem('user', JSON.stringify(r.data));
				props.setIsAuth(true);
				history.push('/');
			})
			.catch((e) => {
				if (e.response) {
					if (e.response.status === 400) {
						toast.warning('Please fill username and password correctly!', options);
					} else if (e.response.status === 401) {
						toast.warning('Incorrect username or password!', options);
					}
				} else {
					toast.error('Something bad happened!', options);
				}
			});
	};

	return (
		<div className='container'>
			<div className={s.baseContainer}>
				<div className={s.inner}>
					<div className={s.header}>Login</div>
					<form className={s.form} onSubmit={onSubmit}>
						<div className={s.formGroup}>
							<label htmlFor='username'>Username</label>
							<input type='text' name='username' placeholder='username' minLength='3' maxLength='20'
							       required
							       value={username}
							       onChange={(e) => {
								       setUsername(e.target.value);
							       }}/>
						</div>
						<div className={s.formGroup}>
							<label htmlFor='password' onClick={() => {
							}}>Password</label>
							<input type='password' name='password' placeholder='password' minLength='6' maxLength='40'
							       required value={password}
							       onChange={(e) => {
								       setPassword(e.target.value);
							       }}/>
						</div>
						<button type='submit' className={s.btn}>Login</button>
					</form>
				</div>
			</div>
		</div>
	);
};

export default Login;