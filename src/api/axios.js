import axios from 'axios';

const authHeader = () => {
	const user = JSON.parse(localStorage.getItem('user'));
	if (user && user.token) {
		return {Authorization: `Bearer ${user.token}`};
	} else {
		return {};
	}
};

const defaultAxios = axios.create({
	baseURL: 'http://localhost:8080/api/',
	headers: authHeader()
});

export default defaultAxios;