import React from 'react';
import s from './Footer.module.css';

const Footer = () => {
	return (
		<div className={s.footer}>
			<div className={s.info}>
				By Team 22: Yerkhan Baigal, Nurlan Salavat, Zhanarys Khorshat, Adilet Orken, Zhangeldi Amangeldi
			</div>
		</div>
	);
};

export default Footer;