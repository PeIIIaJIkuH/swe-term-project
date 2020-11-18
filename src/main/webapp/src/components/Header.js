import React, { Component } from 'react';
import {Link} from 'react-router-dom'

export default class Header extends Component {
    render() {
        return (
          <div className="Header">
            {this.props.isRoomsPage === 1 ?  <>
            <select className='CitySelect'>
              <option value={0}>Nur-Sultan</option>
              <option value={1}>Astana</option>
              <option value={2}>Shymkent</option>
            </select>           
            <button><Link to='/'><span>Home</span></Link></button>
            <button><Link to='/rooms'><span className="HighLight">Rooms</span></Link></button>
            <button><Link to={`/account`}><span>My account</span></Link></button>
            <button><Link to={`/auth/${1}`}><span>Log in</span></Link></button>
            <button><Link to={`/auth/${0}`}><span>Sign up</span></Link></button>
            </> : 
            this.props.isAccountPage ?
            <>
            <select className='CitySelect'>
              <option value={0}>Nur-Sultan</option>
              <option value={1}>Astana</option>
              <option value={2}>Shymkent</option>
            </select>   
            <button><Link to='/'><span>Home</span></Link></button>
            <button><Link to='/rooms'><span>Rooms</span></Link></button>
            <button><Link to={`/account`}><span className="HighLight">My account</span></Link></button>
            <button><Link to={`/auth/${1}`}><span>Log in</span></Link></button>
            <button><Link to={`/auth/${0}`}><span>Sign up</span></Link></button>
            </>
            :
            <>
            <select className='CitySelect'>
              <option value={0}>Nur-Sultan</option>
              <option value={1}>Astana</option>
              <option value={2}>Shymkent</option>
            </select>   
            <button><Link to='/'><span className="HighLight">Home</span></Link></button>
            <button><Link to='/rooms'><span>Rooms</span></Link></button>
            <button><Link to={`/account`}><span>My account</span></Link></button>
            <button><Link to={`/auth/${1}`}><span>Log in</span></Link></button>
            <button><Link to={`/auth/${0}`}><span>Sign up</span></Link></button>
            </>}
          </div>  
        );
    }
}