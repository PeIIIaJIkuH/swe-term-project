import React, { Component } from 'react';
import { Link } from 'react-router-dom';

export default class AuthPage extends Component {
    constructor(props) {
        super(props)
        let isL=parseInt(props.match.params.isLogin)
        this.state={
            isLogin: isL
        }
    }
    handleChange(i) {
        this.setState(() => {
            return {isLogin: i}
        });
    }
    render() {
        return (
            <div className='AuthPage'>
                {this.state.isLogin ? 
                <div className='Block'>
                    <form>
                        <input name='email' type = 'text' placeholder='Email' required></input>
                        <input name='password' type = 'password' placeholder='password' required></input>
                        <button type='submit'>Log in</button>
                        <button onClick={()=>this.handleChange(0)}>Sign up</button>
                    </form>
                    <Link to='/'><span>Back to Home Page</span></Link>
                </div>: 
                <div className='Block'>
                    <form>
                        <input name='name' type = 'text' placeholder='Name' required></input>
                        <input name='surname' type = 'text' placeholder='Surname' required></input>
                        <input name='tel' type = 'tel' placeholder='Phone number'
                        pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}" required></input>
                        <input name='email' type = 'text' placeholder='Email' required></input>
                        <input name='password' type = 'password' placeholder='password' required></input>
                        <button type='submit'>Sign up</button>
                        <button onClick={()=>this.handleChange(1)}>Log in</button>
                    </form>
                    <Link to='/'><span>Back to Home Page</span></Link>
                </div>}
            </div>
        );
    }
}