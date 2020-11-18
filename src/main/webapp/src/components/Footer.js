import React, {Component} from 'react';
import footerImage from '../assets/images/footer.jpg'
import yb from '../assets/images/social-networks/yb.png'
import fb from '../assets/images/social-networks/fb.png'
import insta from '../assets/images/social-networks/insta.png'

export default class Footer extends Component {
    render() {
        return (
            <div className="Footer">
                <img src={footerImage} alt='FooterImage' className='FooterImage'></img>
                <div>
                    <span>
                        <span className='main'>Contact us</span><br/>
                        Adress: Kabanbay Batyr ave. 53, Astana,<br/> Kazakhstan
                        Tel: +7(7172)777777<br/>
                        Mobile: +7(777)7777777
                    </span>
                </div>
                <div>
                    <span>
                        <span className='main'>Follow us</span>
                        <img src={fb} alt='facebook'></img>
                        <img src={yb} alt='youtube'></img>
                        <img src={insta} alt='instagram'></img>
                    </span>
                </div>
            </div>
        );
    }
}