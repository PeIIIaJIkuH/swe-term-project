import {Switch, Route} from 'react-router-dom'
import HomePage from './pages/HomePage'
import Auth from './pages/AuthPage'
import RoomPage from './pages/RoomsPage'
import MyAccount from './pages/MyAccount'
const Main = () => (
      <main>
          <Switch>
              <Route exact path='/' component={HomePage}/>
              <Route path='/auth/:isLogin' component={Auth}/>
              <Route path='/account' component={MyAccount}/>
              <Route path='/rooms' component={RoomPage}/>
          </Switch>
      </main>
)

export default Main
