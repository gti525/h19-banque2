import * as React from "react";
// import Dashboard from './Views/Dashboard';
// import Groups from './Views/Groups';
// import History from './Views/History';
import Login from './Login';
import LoginAdmin from './LoginAdmin';
{/*import Home from './Views/Home';*/} 
import {BrowserRouter, Route} from 'react-router-dom';

import './App.css';
import logo from './Images/Logo_banque2.png';

class App extends React.Component {
  public render() {
    return (
      <div className="App">
        <header className="App-header">
        <a href="./"><img src={logo} className="App-logo" alt="logo" /></a>
        </header>

        <BrowserRouter>
          <div>
            <Route exact={true} path="/" component={Login} />
            <br />
            <Route path="/LoginAdmin" component={LoginAdmin} />
            {/* <Route path="/Home" component={Home} /> */} 
          </div>
        </BrowserRouter>
      </div>
    );
  }
}

export default App;
