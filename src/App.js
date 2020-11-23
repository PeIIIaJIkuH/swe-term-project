import React, {useState} from 'react';
import './App.css';
import Header from './components/Header/Header';
import {Route} from 'react-router-dom';
import Login from './components/Auth/Login';
import Register from './components/Auth/Register';
import Footer from './components/Footer/Footer';
import Main from './components/Main/Main';
import Hotel from './components/Hotel/Hotel';
import Employee from './components/Employee/Employee';
import HeaderEmployee from './components/Employee/HeaderEmployee/HeaderEmployee';
import EmployeeRegister from './components/Auth/EmployeeRegister';

function App() {
	const user = JSON.parse(localStorage.getItem('user'));
	const [isAuth, setIsAuth] = useState(user && user.token);

	return (
		<div className='App'>
			<Route exact path='/login'>
				<Header isAuth={isAuth} setIsAuth={setIsAuth}/>
				<Login isAuth={isAuth} setIsAuth={setIsAuth}/>
			</Route>
			<Route exact path='/register'>
				<Header isAuth={isAuth} setIsAuth={setIsAuth}/>
				<Register/>
			</Route>
			<Route exact path='/'>
				<Header isAuth={isAuth} setIsAuth={setIsAuth}/>
				<Main/>
			</Route>
			<Route exact path='/hotels/:id'>
				<Header isAuth={isAuth} setIsAuth={setIsAuth}/>
				<Hotel/>
			</Route>
			<Route exact path='/employee'>
				<HeaderEmployee isAuth={isAuth} setIsAuth={setIsAuth}/>
				<Employee/>
			</Route>
			<Route exact path='/employee/register'>
				<EmployeeRegister/>
			</Route>
			<Footer/>;
		</div>
	);
}

export default App;
