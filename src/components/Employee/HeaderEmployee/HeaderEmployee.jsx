import React from 'react';
import s from './HeaderEmployee.module.css';
import logo from '../../../assets/img/logoBlack.png';
import {Link} from 'react-router-dom';

const HeaderEmployee = (props) => {
	const user = JSON.parse(localStorage.getItem('user'));

	const logout = () => {
		localStorage.removeItem('user');
		props.setIsAuth(false);
	};

	return (
		<div className={s.header}>
			<div className='container'>
				<div className={s.inner}>
					<Link className={s.logoLink} to='/'>
						<img src={logo} alt='logo' className={s.logo}/>
					</Link>
					<div className={s.right}>
						{props.isAuth ?
							<>
								<Link className={s.authLink} to='/profile'>{user.username}</Link>
								<Link className={s.authLink} to='/' onClick={logout}>Logout</Link>
							</> :
							<>
								<Link className={s.authLink} to='/login'>Login</Link>
								<Link className={s.authLink} to='/employee/register'>Signup</Link>
							</>
						}
					</div>
				</div>
			</div>
		</div>
	);
};

export default HeaderEmployee;